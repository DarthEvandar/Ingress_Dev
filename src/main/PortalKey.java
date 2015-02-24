package main;

public class PortalKey extends Player{
	String portalId;
	public PortalKey(String i){
		portalId = i;
	}
	public String getId(){
		return portalId;
	}
}
