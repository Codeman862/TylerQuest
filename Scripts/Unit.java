package Scripts;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import Sprites.bloodSprite;

public abstract class Unit extends cosmeticSprite{
	//this is a soldier class, all soldiers inherit from this class
	
	//use abstract classes if you want a soldier to give its own definition,
	//almost everything a soldier does should have some degree of randomness
	//example shooting,
	public Unit(){
		
	}
	
	public float baseAP=5;				//base AP
	public float baseMoveSpeed;		//base speed
	
	public int morale=15;		//moral affects a bunch of stuff
	public int health=100;
	public int accuracy=15;
	
	public int side=(int) 2;				//determines the faction, 0 for other, 1 for player, 2 for ai, 3 for possibly a 2nd ai
	
	public int state=-1;			//soldier state
	
	String displayName="IsUnit";//name displayed
	
	/*
	public long aquireTimer[]=new long[4];//0 is timer, 1 is timer end, 2 is current duration, 3 is base duration
	public long baseFireTimer[]=new long[4];//in between shoots delay
	public long reloadTimer[]=new long[4];//reloading timer
	*/

	public long TimerExample[]={(long) 500,(long) 500,(long) 500,-1};//0 is timer,1 is current duration, 2 is base duration, 4 is the lock
	
	public void generateName(){
		String nameArray[]={"s","d"};
		displayName=nameArray[(int)(Math.random()*nameArray.length)];
	}
	public float rotateToPosition(ArrayList list,int enemytargetname){
		float theta=0;
		int size=list.size();
		for(int a=0;a<size;a++){
			Unit sol=(Unit) list.get(a);
			if(sol.targetname==enemytargetname){
				int x2=sol.x;
				int y2=sol.y;
				float xdis=x2-this.x;
				float ydis=y2-this.y;
				float tan=ydis/xdis;
				theta=(float) ((float) Math.atan(tan)+((-90*Math.PI)/180));
				break;
			}
		}
		
		//theta=0;
		System.out.println("The rotation is "+theta);
		return theta;
	}
	
	
	
	
	public void damage(int ammount) throws IOException{
		health=health-ammount;
		bloodSprite blood=new bloodSprite(this.x,this.y,30,30);
		Game_Applet.Instantiate(gameController.cosmeticList, blood);
		if(health<-30){
			//excecute gib command and kill command
		}
	}
	public void setMoveOrders(int x,int y){
	}
	public void doMove(){
	}
	public void checkIfAlive() {
		if(this.health<1){
			//do dead stuff here
		}
	}
	
}

