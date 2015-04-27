
public class Golf {
	public static void main(String[] args){
		g("(()(()(())))");
	}
static void g(String s){
	int a=-1,m=0,z=s.length(),b[]=new int[z],i,c;
	char l=0,p;
	for(i=0;i<z-1;){
		p=s.charAt(i);
		if(p==40){
			a+=l==41?0:1;
			b[i++]=a;
			m=a>m?a:m;
		}else{
			b[i++]=a;
			if(s.charAt(i)==41)
				a--;			
		}
		l=p;
	}		
	for(i=0;i<=m;i++){
		for(c=0;c<z;c++)
			System.out.print(b[c]==i?s.charAt(c):' ');
		System.out.println();
	}
}
}