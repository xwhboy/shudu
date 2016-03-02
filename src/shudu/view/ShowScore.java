package shudu.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import shudu.controller.ButtonController;
import shudu.model.NetChange;
import shudu.model.ScoreCount;
import shudu.model.TimeCount;
public class ShowScore extends JFrame{
	  
    private int score_show=200;
    private String time_show="00:00:00";
    private JButton sure;
    private JButton cancel;
    private Color co=new Color(97,144,232);
    private Color co2=new Color(249,203,104);
    private String kongge;
    private String name;
    private NetChange nc;
    private boolean iflisten=true;
    Image image=new ImageIcon("resource/bgss2.png").getImage();  
    public ShowScore(int score,String time ) {  
        
    	 nc=new NetChange();
        this.score_show=score;
        this.time_show=time;
        init(); 
        
    }  
   
    private void init() {  
        setTitle("通关");  
       // 设置窗体大小
        int windowsWedth = 300;
        int windowsHeight = 420;
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗体在显示器居中显示
        this.setBounds((width - windowsWedth) / 2,
        (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        //setLocation(300, 200);  
        setSize(300, 420); // 设置内容面板  
        setContentPane(createContentPane());
        setUndecorated(true); //去掉边框
        setVisible(true);

    }  
  
    private JPanel createContentPane() {  
        JPanel pane = new BackgroundPanel(image);  
        pane.setBorder(new EmptyBorder(58, 25, 25,10));
        pane.setLayout(new BorderLayout(2,2)); 
        JLabel tit=new JLabel("", JLabel.CENTER);
        tit.setForeground(co);//设置字体颜色
        tit.setPreferredSize(new Dimension(180,40));
        pane.add(BorderLayout.NORTH,tit); 
        if((score_show/10)>=100){
        	kongge="  ";
        }
        else
        {
        	kongge=" ";
        }
        JLabel show_s=new JLabel(kongge+score_show,JLabel.CENTER);
        JLabel show_t=new JLabel("    "+time_show,JLabel.CENTER);
        show_s.setFont(new Font("隶书",Font.BOLD ,35));
        show_t.setFont(new Font("隶书",Font.BOLD,25));
        show_t.setPreferredSize(new Dimension(180,32));
        show_s.setPreferredSize(new Dimension(180,28));
        show_t.setForeground(co);//设置字体颜色
        show_s.setForeground(co2);//设置字体颜色
        show_s.setHorizontalAlignment(SwingConstants.CENTER);
        show_t.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel pane2 = new  JPanel();
        pane2.setSize(260,280);
        pane2.setLayout(new FlowLayout(FlowLayout.CENTER,5,15));
        pane2.add(show_s);
        pane2.add(show_t);
        pane2.setBorder(new EmptyBorder(20,15, 15, 10));
        pane2.setBackground(null);  
        pane2.setOpaque(false);
        pane2.add(BorderLayout.SOUTH, createBtnPane()); 
        pane.add(BorderLayout.CENTER, pane2);
        return pane;  
    }  
  
   
  
    private JPanel createBtnPane() {  
        JPanel pane = new JPanel(new GridLayout(3,1,5,15)); 
        pane.setBorder(new EmptyBorder(9,5,5,5));
       
         sure = new JButton(new ImageIcon("resource/bn01.png"));
         sure.setPressedIcon(new ImageIcon("resource/bn02.png")); 
         sure.setSelectedIcon(new ImageIcon("resource/bn02.png")); 
         
         cancel = new JButton(new ImageIcon("resource/bn03.png"));
         cancel.setPressedIcon(new ImageIcon("resource/bn04.png")); 
         cancel.setSelectedIcon(new ImageIcon("resource/bn04.png")); 
         
         sure.setPreferredSize(new Dimension(180,40));
         cancel.setPreferredSize(new Dimension(180,40));
         JTextField leave_name=new JTextField();
         leave_name.setText(" 英雄请在此留名!");
         leave_name.setFont(new Font("楷体",Font.BOLD,20));
         leave_name.setOpaque(false);
         leave_name.setForeground(new Color(255,255,255));//设置字体颜色
         leave_name.setPreferredSize(new Dimension(180,40));
         sure.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	name=leave_name.getText();
            	if(name.equals(" 英雄请在此留名!")){
            		name="default";
            	}
            	System.out.println(name);
            	try {
					nc.submitScore(name, score_show+"");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	setVisible(false);
            }  
        });  
//        JButton cancel = new JButton("退出");  
        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	setVisible(false);
            }  
        });  
        
        	leave_name.addCaretListener(new CaretListener() { 
        		//异步重绘线程
                public void caretUpdate(CaretEvent e) {  
                    try {  
                        //e.getDot() 获得插入符的位置。
                    	int a=e.getDot();
                    	if(a==0)
                    	iflisten=false;
                    	
                    	if(iflisten){
                    		EventQueue.invokeLater(new Runnable() {
                       	     public void run() {
                       	    	 leave_name.setText("");
                       	     }
                       	    });
                    	}
                    	
                    
                          
                    } catch (Exception ex) {  
                        ex.printStackTrace();  
                    }  
                }  
       });  
     
        pane.add(leave_name);
        pane.add(sure);  
        pane.add(cancel);  
        pane.setBackground(null);  
        pane.setOpaque(false);
        return pane;  
    }  
  
   
//  
//    @SuppressWarnings("deprecation")
//	public static void main(String []arg){
//    	System.out.println("dd");
//    	ShowScore sc=new ShowScore(2000,"00:10:00");
//    	sc.show();
//    }
   
}  