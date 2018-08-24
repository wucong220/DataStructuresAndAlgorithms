package Math;

public class Gcd {
	//最大公约数，辗转相除法
	static int GetGcd(int a,int b){
		if(b==0)return a;
		else return GetGcd(b,a%b);
	}
	
	public static void main(String[] args) {
		System.out.println(GetGcd(24, 18));
	}
}
