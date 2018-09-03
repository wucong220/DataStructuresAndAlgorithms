package KMS;

public class KMS {
	public static int[] getNext(char[] pattern){
		int[] next = new int[pattern.length];
		next[0]=-1;
		int k=-1;
		int j=0;
		while(j<pattern.length-1){
			if(k==-1||pattern[k]==pattern[j]){
				++k;
				++j;
				next[j]=k;
			}
			else{
				k=next[k];
			}
		}
		
		return next;
	}
	
	public static int kms(char[] text,char[] pattern){
		int i=0,j=0;
		int[] next = getNext(pattern);
		
		while(i<text.length){
			
			if(j==-1||text[i]==pattern[j]){
				i++;
				j++;
			}
			else{
				j=next[j];
			}
			
			if(j==pattern.length){
				break;
			}
		}
		if(j==pattern.length)return i-pattern.length;
		return -1;
	}
	
	public static void main(String[] args) {
		String s = "youaregoodboy";
		String p = "good";
		System.out.println(kms(s.toCharArray(),p.toCharArray()));
		
	}
}
