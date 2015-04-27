package main;


public class Resonator extends Player{
	int x;
	int y;
	int resLevel;
	int energyLevel;
	boolean resDead = false;
	public Resonator(int level,int pos,int portalX,int portalY){
		resLevel = level;
		switch(pos){
		case 0:			
			x = portalX+8;
			y = portalY-15+8;
			break;
		case 1:
			x = portalX+8+15;
			y = portalY-15+8;
			break;
		case 2:
			x = portalX+8+15;
			y = portalY+8;			
			break;
		case 3:
			x = portalX+8+15;
			y = portalY+15+8;
			break;
		case 4:
			x = portalX+8;
			y = portalY+15+8;
			break;
		case 5:
			x = portalX+8-15;
			y = portalY+15+8;
			break;
		case 6:
			x = portalX+8-15;
			y = portalY+8;
			break;
		case 7:
			x = portalX+8-15;
			y = portalY-15+8;
			break;				
		}
		switch(resLevel){
		case 1:energyLevel = 1000;
		break;
		case 2:energyLevel = 1500;
		break;
		case 3:energyLevel = 2000;
		break;
		case 4:energyLevel = 2500;
		break;
		case 5:energyLevel = 3000;
		break;
		case 6:energyLevel = 4000;
		break;
		case 7:energyLevel = 5000;
		break;
		case 8:energyLevel = 6000;
		break;
		}
		energyLevel = getEnergy();
	}
	public int getLevel(){
		return resLevel;
	}
	public int getEnergy(){
		
		return energyLevel;
	}
	public void damage(int a){
		energyLevel-=a;
		if(energyLevel<=0){
			resDead = true;
		}
	}
	@Override
	public String toString(){
		return "" + getLevel();
	}
	public boolean dead(){
		return resDead;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
