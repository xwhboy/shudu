package shudu.model;


public class ScoreCount  {
	
	private  int allScore;
	private static ScoreCount sc=null;
	private int achange[];
	private boolean jiancha=false;
	private ScoreCount(){};
	 public static  ScoreCount getScoreClass(){
		 if(sc==null){
			 sc=new ScoreCount(); 
		 }
		 
		 return sc;
	 }
    public  void setScore(int tt){
		 System.out.println(tt);
		 if(tt<150){
			 allScore=2000; 
		 }
		 else if(tt>=150&&tt<1850){
			 allScore=1850-tt; 
		 }
		 else{
			 allScore=60; 
		 }
		 
		
	 }
	 public  int getScore(){
		 return allScore;
	 }
	 public boolean getIFGS(){
		 return jiancha;
	 }
	 public void setIFGS(boolean ifgs){
		 jiancha=ifgs;
	 }
}
