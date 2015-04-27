package main;

public class Mod {
	String type;
	int slot;
	public String getType() {
		return type;
	}
	public int getSlot() {
		return slot;
	}
	
	public Mod(String type, int slot){
		this.type = type;
		this.slot = slot;
	}
	public Mod(String type){
		this.type = type;
	}
}
