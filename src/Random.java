
public class Random {
	public static void main(String[] args){
		String s = "((()())()(()(())()))";
		int a=-1;
		int max=0;
		int[] b = new int[s.length()];
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='('){
				if(i!=0&&s.charAt(i-1)==')'){
					a--;
				}
				a++;
				b[i]=a;
				if(a>max){
					max=a;
				}
			}else{
				b[i]=a;
				if(i+1!=s.length()&&s.charAt(i+1)==')'){
					a--;
				}
			}
		}		
		for(int i=0;i<=max;i++){
			for(int c=0;c<b.length;c++){
				if(b[c]==i){
					System.out.print(s.charAt(c));
				}else{
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}
}
