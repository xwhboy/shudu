package shudu.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import shudu.model.Game;
import shudu.model.ScoreCount;
import shudu.model.TimeCount;
import shudu.view.ShowList;
import shudu.view.ShowScore;

/**
 * This class controls all user actions from ButtonPanel.
 *
 */
public class ButtonController implements ActionListener {
    private Game game;
    private ScoreCount Sscore;
    private int currentTime;
    private ShowList sc = null;
    /**
     * Constructor, sets game.
     *
     * @param game  Game to be set.
     */
    public ButtonController(Game game) {
        this.game = game;
        Sscore=ScoreCount.getScoreClass();
        game.printresult();
        
		try {
			sc = new ShowList();
			sc.setVisible(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
    }

    /**
     * Performs action after user pressed button.
     *
     * @param e ActionEvent.
     */
    public void actionPerformed(ActionEvent e) {
    	currentTime=TimeCount.getCurrentTime();
        if (e.getActionCommand().equals("New")){
        	 TimeCount.setTimeStop(false);
        	 TimeCount.setTimeData(true);
        	  game.newGame();
        	  boolean ss_allcheck=false;
              ss_allcheck=game.allCheck();
        	  if(ss_allcheck){
        		  Sscore.setScore(currentTime);
              }
        	 
        }
          
        else if (e.getActionCommand().equals("Check"))
        {
        	
            game.checkGame();
            
            boolean ss_allcheck=false;
            ss_allcheck=game.allCheck();
            
            if(ss_allcheck){
                TimeCount.setTimeStop(true);
                Sscore.setScore(currentTime);
            	Sscore.setIFGS(true);  	
            	ShowScore newdialog=new ShowScore(Sscore.getScore(),TimeCount.getTimeData());
            	newdialog.show();
            }
            
            //TimeCount.setTimeData(true);
            
        }
        else if (e.getActionCommand().equals("Exit")){
        	
    	    sc.show();
        }
          
        else if (e.getActionCommand().equals("ÌבÊ¾"))
            game.setHelp(((JCheckBox)e.getSource()).isSelected());
//        else if(e.getActionCommand().equals("sure")){
//        	System.out.println("queding");
//        }
//        else if(e.getActionCommand().equals("cancel")){
//        	System.out.println("cancel");
//        }
        else
            game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
    }
}