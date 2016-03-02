package shudu.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import shudu.model.NetChange;

public class ShowList extends JFrame {
	Image image=new ImageIcon("resource/bgss7.png").getImage();  
	private JList list1;
	private DefaultListModel model;
	private String [][] mylist;
	private String [][] ourlist;
	private NetChange nc;
	String showData[];
    public ShowList() throws Exception{
    	nc=new NetChange();
    	mylist=nc.getMyListWay();
    	ourlist=nc.getOurListWay();
    	init(); 
    }
    private void init() {  
        setTitle("分数显示");  
       // 设置窗体大小
        int windowsWedth = 360;
        int windowsHeight= 480;
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        //设置窗体在显示器居中显示
        this.setBounds((width - windowsWedth) / 2,
        (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        //setLocation(300, 200);  
        setSize(windowsWedth, windowsHeight); // 设置内容面板  
        setContentPane(createContentPane());
        setUndecorated(true); //去掉边框
        setVisible(true);

    }  
    private JPanel createContentPane() {  
        JPanel pane = new BackgroundPanel(image);  
        pane.setBorder(new EmptyBorder(100, 20,20,20));
        pane.setLayout(new BorderLayout(5,5)); 
        int length=mylist.length;
        model=new DefaultListModel(); 
        setShowData(mylist);
       
        list1 = new JList(model);
        list1.setFont(new Font("consolas",Font.PLAIN,24));
        list1.setOpaque(false);
         //(JLabel)list1.getCellRenderer.setOpaque(false);
        list1.setForeground(new Color(255,255,255));//设置字体颜色
        list1.setBackground(new Color(0, 0, 0, 0));
        JScrollPane jp=new JScrollPane(list1);
        jp.setOpaque(false);
        jp.getViewport().setOpaque(false);
        
        pane.add(BorderLayout.CENTER,jp);
        pane.add(BorderLayout.SOUTH, createBtnPane()); 
        return pane;
    }
    private JPanel createBtnPane() {  
        JPanel pane = new JPanel(new GridLayout(1,3,10,5)); 
        pane.setBorder(new EmptyBorder(9,5,5,5));
        JButton Mybu=new JButton(new ImageIcon("resource/bnmylists.png"));
        Mybu.setPressedIcon(new ImageIcon("resource/bnmylistp.png")); 
        Mybu.setSelectedIcon(new ImageIcon("resource/bnmylistp.png")); 
        JButton Ourbu = new JButton(new ImageIcon("resource/bnourlists.png"));
        Ourbu.setPressedIcon(new ImageIcon("resource/bnourlistp.png")); 
        Ourbu.setSelectedIcon(new ImageIcon("resource/bnourlistp.png")); 
        JButton cancel=new JButton(new ImageIcon("resource/bncans.png"));
        cancel.setPressedIcon(new ImageIcon("resource/bncanp.png")); 
        cancel.setSelectedIcon(new ImageIcon("resource/bncanp.png")); 
        Mybu.setPreferredSize(new Dimension(80,35));
        Ourbu.setPreferredSize(new Dimension(80,35));
        cancel.setPreferredSize(new Dimension(80,35));
         
        Mybu.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	model.removeAllElements();
            	try {
					mylist=nc.getMyListWay();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	setShowData(mylist);
            }  
        });  
        Ourbu.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
            	model.removeAllElements();
            	try {
					ourlist=nc.getOurListWay();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	setShowData(ourlist);
            }  
        });  
        
        cancel.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
            	setVisible(false);
            }  
        });  
         pane.add(Mybu);  
         pane.add(Ourbu);
         pane.add(cancel);  
         pane.setBackground(null);  
         pane.setOpaque(false);
         return pane;  
     }  
    public void setShowData(String [][]datalist){
    	int length=datalist.length;
    	showData=new String [length];
    	String temp1,temp2;
    	for(int i=0;i<length;i++){
//    		尾部不足6位长度用空格补足
       	    temp1=String.format("%-6s", datalist[i][0]);
       	    temp2=String.format("%-2s", (length-i)+"");
        	if(((i+1)/10)<1){
        		showData[i]="  "+temp2+"    "+temp1+"    "+datalist[i][1];
//        		System.out.println(datalist[i][0].length());
        	}
        	else{
        		showData[i]="  "+temp2+"    "+temp1+"    "+datalist[i][1];
        	}
        	  
        }
    	for(int i=0;i<length;i++){
    		model.addElement(showData[length-i-1]);   
        }
    	
    }
	
  
}
