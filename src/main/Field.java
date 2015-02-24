package main;

public class Field {
	int p1x;
	int p1y;
	int p2x;
	int p2y;
	int p3x;
	int p3y;
	String team;
	public Field(int a,int b,int c,int d,int e,int f,String g){
		p1x = a;
		p1y = b;
		p2x = c;
		p2y = d;
		p3x = e;
		p3y = f;
		team = g;
	}

	public int getP1x() {
		return p1x;
	}

	public int getP1y() {
		return p1y;
	}

	public int getP2x() {
		return p2x;
	}

	public int getP2y() {
		return p2y;
	}

	public int getP3x() {
		return p3x;
	}

	public int getP3y() {
		return p3y;
	}
	public String getTeam(){
		return team;
	}
}
