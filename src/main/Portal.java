package main;

import java.util.ArrayList;
import java.util.Collections;

public class Portal {
	int pX;
	int pY;
	int numRes;
	String alignment;
	String name;
	int links;
	int l2num;
	int l3num;
	int l4num;
	int l5num;
	int l6num;
	int l7num;
	int l8num;
	int curSlot=0;
	ArrayList<String> linked = new ArrayList<String>();
	ArrayList<Resonator> resonators = new ArrayList<Resonator>();
	ArrayList<Mod> mods = new ArrayList<Mod>();
	public Portal(int x, int y,String n){
		pY = y;
		pX = x;
		alignment = "ntrl";
		name = n;
		
	}
	public void addMod(String type, int slot){
		if(curSlot==4){
			return;
		}
		curSlot++;
		mods.add(new Mod(type,slot));
	}
	public void setRes(int a){
		numRes = a;
	}
	public void setAlign(String s){
		alignment = s;
	}
	public int retX(){
		return pX;
	}
	public int retY(){
		return pY;
	}
	public int numReses(){
		return numRes;
	}
	public String namee(){
		return name;
	}
	public int energy(){
		int ener = 0;
		for(Resonator r:resonators){
			ener+=r.getEnergy();
		}
		return ener;
	}
	public void addReso(int a){
		if(a==1){
		resonators.add(new Resonator(a,numRes,pX,pY));
		numRes++;
		}
		if(a==2&&l2num<4){
		resonators.add(new Resonator(a,numRes,pX,pY));
		l2num++;
		numRes++;
		}else if(a==3&&l3num<4){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l3num++;
		numRes++;
		}else if(a==4&&l4num<4){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l4num++;
		numRes++;
		}else if(a==5&&l5num<2){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l5num++;
		numRes++;
		}else if(a==6&&l6num<2){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l6num++;
		numRes++;
		}else if(a==7&&l7num<1){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l7num++;
		numRes++;
		}else if(a==8&&l8num<1){
			resonators.add(new Resonator(a,numRes,pX,pY));
		l8num++;
		numRes++;
		}
		
	}
	public String team(){
		return alignment;
	}
	public void capture(String alg){
		alignment = alg;
	}
	public void addLink(String a){
		links++;
		linked.add(a);
	}
	public boolean isLinkedTo(String x){
		if(linked.contains(x)){
			return true;
		}
		return false;
	}
	public void checkDead(){
		for(Resonator r:resonators){
			if(r.dead()){
				
					
				resonators.remove(r);
				numRes--;
			}
		}
	}
	public int avgLevel(){
		int added = 0;
		for(Resonator r: resonators){
			added+=r.getLevel();
		}
		if(resonators.size()==0||(int)added/8==0){
			return 1;
		}else{		
		return (int)added/8;
		}
	}
}
