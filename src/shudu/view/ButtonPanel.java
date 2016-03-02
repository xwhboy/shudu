package shudu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import shudu.controller.ButtonController;
import shudu.model.UpdateAction;

/**
 * This class draws the button panel and reacts to updates from the model.
 *
 *
 */
public class ButtonPanel extends JPanel implements Observer {
    JButton btnNew, btnCheck, btnExit;   // Used buttons.
    JCheckBox cbHelp;               // Used check box.
    ButtonGroup bgNumbers;          // Group for grouping the toggle buttons.
    JToggleButton[] btnNumbers;     // Used toggle buttons.
    
    Color co=new Color(97,144,232);
    /**
     * Constructs the panel and arranges all components.
     */
    public ButtonPanel() {
        super(new BorderLayout());
        Image image=new ImageIcon("resource/bgss3.png").getImage();  
        JPanel pnlAlign = new BackgroundPanel(image); 
//        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        pnlAlign.setBackground(null);  
        pnlAlign.setOpaque(false);
        add(pnlAlign, BorderLayout.NORTH);
        setVisible(true); 
        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING,15,10));
        //pnlOptions.setBorder(BorderFactory.createTitledBorder(" Options "));
        pnlOptions.setBackground(null);  
        pnlOptions.setOpaque(false);
        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlNumbersHelp.setBackground(null);  
        pnlNumbersHelp.setOpaque(false);
        cbHelp = new JCheckBox("提示", true);
        cbHelp.setFont(new Font("宋体",Font.BOLD,15));
        cbHelp.setForeground(Color.WHITE);
        cbHelp.setFocusable(false);
        cbHelp.setOpaque(false);
        pnlNumbersHelp.add(cbHelp);
        pnlOptions.add(pnlNumbersHelp);
        btnNew = new JButton("New");
        btnNew.setFocusable(false);
        btnNew.setIcon(new ImageIcon("resource/bnnews.png"));
        btnNew.setPressedIcon(new ImageIcon("resource/bnnewp.png")); 
        btnNew.setSelectedIcon(new ImageIcon("resource/bnnewp.png")); 
        btnNew.setFont(new Font("楷体",Font.BOLD,0));
        btnNew.setPreferredSize(new Dimension(104,32));
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Check");
        btnCheck.setFocusable(false);
        btnCheck.setIcon(new ImageIcon("resource/bnches.png"));
        btnCheck.setPressedIcon(new ImageIcon("resource/bnchep.png")); 
        btnCheck.setSelectedIcon(new ImageIcon("resource/bnchep.png")); 
        btnCheck.setFont(new Font("楷体",Font.BOLD,0));
        btnCheck.setPreferredSize(new Dimension(104,32));
        pnlOptions.add(btnCheck);

        btnExit = new JButton("Exit");
        btnExit.setFocusable(false);
        btnExit.setIcon(new ImageIcon("resource/bnexs.png"));
        btnExit.setPressedIcon(new ImageIcon("resource/bnexp.png")); 
        btnExit.setSelectedIcon(new ImageIcon("resource/bnexp.png")); 
        btnExit.setFont(new Font("楷体",Font.BOLD,0));
        btnExit.setPreferredSize(new Dimension(104,32));
        pnlOptions.add(btnExit);
            
        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        //pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numbers "));
        pnlNumbers.setBackground(null);  
        pnlNumbers.setOpaque(false);
        
        pnlAlign.add(pnlNumbers);
        pnlAlign.add(pnlOptions);
        
      

        JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING,10,10));
        pnlNumbersNumbers.setBackground(null);  
        pnlNumbersNumbers.setOpaque(false);
        
        pnlNumbers.add(pnlNumbersNumbers);  
        //pnlNumbers.add(pnlNumbersHelp);
        bgNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
       
        for (int i = 0; i < 9; i++) {
        	String pathname="resource/bnnus";
        	String pathname2=".png";
        	String pathname3="resource/bnnup";
        	ImageIcon icon = new ImageIcon(pathname+(i+1)+pathname2);
            btnNumbers[i] = new JToggleButton("" + (i + 1),icon);
            //btnNumbers[i] = new JToggleButton(icon);
            btnNumbers[i].setPreferredSize(new Dimension(40, 40));
            btnNumbers[i].setFont(new Font("黑体",Font.BOLD ,0));
            btnNumbers[i].setForeground(Color.BLACK);
            btnNumbers[i].setPressedIcon(new ImageIcon(pathname3+(i+1)+pathname2)); 
            btnNumbers[i].setSelectedIcon(new ImageIcon(pathname3+(i+1)+pathname2)); 
//            btnNumbers[i].setContentAreaFilled(false);
            btnNumbers[i].setBackground(co);
            btnNumbers[i].setFocusable(false);
            bgNumbers.add(btnNumbers[i]);
            pnlNumbersNumbers.add(btnNumbers[i]);
        }
    }

    /**
     * Method called when model sends update notification.
     *
     * @param o     The model.
     * @param arg   The UpdateAction.
     */
    public void update(Observable o, Object arg) {
        switch ((UpdateAction)arg) {
            case NEW_GAME:
            case CHECK:
                bgNumbers.clearSelection();
                break;
        }
    }

    /**
     * Adds controller to all components.
     *
     * @param buttonController  Controller which controls all user actions.
     */
    public void setController(ButtonController buttonController) {
        btnNew.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        cbHelp.addActionListener(buttonController);
        for (int i = 0; i < 9; i++)
            btnNumbers[i].addActionListener(buttonController);
    }
}