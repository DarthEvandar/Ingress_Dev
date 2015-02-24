package main;

public class Burster extends Player{
	int level;
	public Burster(int l){
		level = l;
	}
	
	public int burstRange(int level){
		switch(level){
		case 1:
			return 10;
		case 2: 
			return 14;
		case 3:
			return 18;
		case 4:
			return 22;
		case 5:
			return 24;
		case 6:
			return 26;
		case 7:
			return 27;
		case 8:
			return 30;
		}
		return 0;
	}
			
}
