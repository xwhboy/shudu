package shudu.model;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class TimeCount {
	private Timer timer=new Timer();
	 private static boolean is_pause=false;
	 private static boolean renew=false;
	 private String time_data="00:00:00";
	 private static String sendTime_data="00:00:00";
	 private int hh,mm,ss;
	 private String hhb,mmb,ssb;
	 private static TimeCount timecount=null;
	 private static int curTime=0;
	 private  TimeCount(){
		 timetask mytimetask=new timetask();//��ʱ��
		 timer.scheduleAtFixedRate(mytimetask, 0, 1000);
//		 System.out.println("");
	 }
	 public static  TimeCount getTimeClass(){
		 if(timecount==null){
			 timecount=new TimeCount(); 
		 }
		 
		 return timecount;
	 }
	 
		
	 public static  String getTimeData(){
	    	return sendTime_data;
	    }
	 public static   void  setTimeData(boolean iftime){
		    renew=true;
	     }
	 public static   void  setTimeStop(boolean ifok){
		    is_pause=ifok;
	     }
	 public static int getCurrentTime(){
		 return curTime;
	 }
	
	 //��ʱ��  renew=true ����
    class timetask extends TimerTask{
		private int count_time=0;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(!is_pause){
				count_time++;
				hh=count_time/3600;
				mm=(count_time%3600)/60;
				ss=count_time%60;
				//System.out.println(count_time);
				mmb=mm+"";
				hhb=hh+"";
				ssb=ss+"";
					if(mm<=9){
						mmb="0"+mm;
					}
					if(ss<=9){
						ssb="0"+ss;
					}
					if(hh<=9){
						hhb="0"+hh;
					}
					
					curTime=hh*3600+mm*60+ss;
					time_data="ʱ��:"+hhb+":"+mmb+":"+ssb;
					sendTime_data=hhb+":"+mmb+":"+ssb;
				
			}
			if(renew){
				count_time=0;
				//���ÿհ�ʱ��
				time_data="ʱ�䣺00:00:00";
				renew=false;
			}
		}
		
	}

}
