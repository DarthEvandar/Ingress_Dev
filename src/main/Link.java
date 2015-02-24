package main;

public class Link {
	int xx1;
	int xx2;
	int yy1;
	int yy2;
	String team;
	public Link(int a,int b, int c, int d,String e){
		xx1 = a;
		xx2 = b;
		yy1 = c;
		yy2 = d;
		team = e;
	}
	
		public int x1() {
			return xx1;
		}

		public int x2() {
			return xx2;
		}

		public int y1() {
			return yy1;
		}

		public int y2() {
			return yy2;
		}
		public String getTeam(){
			return team;
		}
}
