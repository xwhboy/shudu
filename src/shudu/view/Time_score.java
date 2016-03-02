package shudu.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import shudu.model.Game;
import shudu.model.ScoreCount;
import shudu.model.TimeCount;
import shudu.model.UpdateAction;

public class Time_score extends JPanel implements Observer {
	private JPanel time_panel=new JPanel();
	private JLabel time_label=new JLabel("  时间：00:00:00");
	private JLabel score_label=new JLabel("    分数：2000");
    private boolean ifstinme=true;
    private int aScore;
    private Color co=new Color(97,144,232);
    private Color co2=new Color(249,203,104);
    
	public Time_score(){
		Image image=new ImageIcon("resource/bgss4.png").getImage();  
	    JPanel pane = new BackgroundPanel(image); 
		TimeCount tc =TimeCount.getTimeClass();
		ScoreCount sc= ScoreCount.getScoreClass();
		setLayout(new FlowLayout());
		time_label.setFont(new Font("黑体",Font.BOLD ,20));
		score_label.setFont(new Font("黑体",Font.BOLD ,20));
		time_label.setPreferredSize(new Dimension(200,50));
		score_label.setPreferredSize(new Dimension(200,50));  
		time_label.setForeground(co);//设置字体颜色
		score_label.setForeground(co);//设置字体颜色
		pane.add(score_label);
		pane.add(time_label);
		
        new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(ifstinme){
					String timedata=tc.getTimeData();
					time_label.setText("时间："+timedata); 
					if(sc.getIFGS()){
						aScore=sc.getScore();
						score_label.setText("分数："+aScore);
						sc.setIFGS(false);
						
					}
					
					try{Thread.sleep(1000);} //让线程睡眠1秒再启动
		             catch(Exception ex)
					{ex.printStackTrace();}
					
				}
			}
        	
        }).start();
        pane.setVisible(true);
        pane.setPreferredSize(new Dimension(472,50));
        pane.setBorder(new EmptyBorder(0, 0, 0,0));
        add(pane);
        setVisible(true);
	}
	 /**
     * Method called when model sends update notification.
     *
     * @param o     The model.
     * @param arg   The UpdateAction.
     */

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		 switch ((UpdateAction)arg) {
         case CHECK:
        	 System.out.println("---+");
             break;
     }
	}
	

	
}
