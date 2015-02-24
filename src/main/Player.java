package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Player{
	static ArrayList<Object> inv = new ArrayList<Object>();
	int level = 1;
	int ap = 0;
	int levelUp = 2500;
	BufferedReader reader;
	String s;
	String align = "enl";
	int mu;
	public Player(){
		
		try {
			reader = new BufferedReader(new FileReader("inventory\\ap.txt"));
			s = reader.readLine();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ap = Integer.parseInt(s);
		addAp(0);
	}
	public int pLevel(){
		return level;
	}
	public int apLevel(){
		return ap;
	}
	
	/*public void addRes(int level){
		inv.add(new Resonator(level));
	}*/
	
	public void addAp(int a){
		ap = ap + a;
		if(ap>=levelUp && level == 1){
			level++;
			levelUp = 20000;
		}
		if(ap>=levelUp && level == 2){
			level++;
			levelUp = 70000;
		}
		if(ap>=levelUp && level == 3){
			level++;
			levelUp = 150000;
		}
		if(ap>=levelUp && level == 4){
			level++;
			levelUp = 300000;
		}
		if(ap>=levelUp && level == 5){
			level++;
			levelUp = 600000;
		}
		if(ap>=levelUp && level == 6){
			level++;
			levelUp = 1200000;
		}
		if(ap>=levelUp && level == 7){
			level++;
		}
		}
	public void setAlign(String s){
		align = s;
	}
	public void addKey(String id){
		inv.add(new PortalKey(id));
	}
	public String getAlign(){
		return align;
	}
	public void addMu(int a){
		mu+=a;
	}
}
