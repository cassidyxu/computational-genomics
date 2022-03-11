import java.util.*;

public class BoyerMoore {
		
	public static int badCharacter(String p, String t) {

		HashMap<Character, Integer> badMatchTable = new HashMap();
		
		int ind = 0;
		while(ind<p.length()) {
			if(ind == p.length()-1) {
				if(!badMatchTable.containsKey(p.charAt(ind))) {
					badMatchTable.put(p.charAt(ind), p.length());
				}
			}else {
				badMatchTable.put(p.charAt(ind), p.length()-ind-1);	//formula
			}
			ind++;
		}
		
		int rightAlignInd = p.length()-1; //where p and t match up from the right index of p
		
		while(rightAlignInd<t.length()) {
			
			int i = p.length()-1;
			//int ti = rightAlignInd;
			while(i>=0) {
				if(p.charAt(i)!=t.charAt(rightAlignInd-(p.length()-1-i))) {
					break;
				}
				i--;
			}
			
			if(i==-1) {
				return rightAlignInd-p.length()+1;
			}else{
				rightAlignInd+=badMatchTable.get(t.charAt(rightAlignInd));
			}
			
		}
		
		return -1;
		
		

	}

	public static int goodSuffix(String p, String t) {

		//index of first different char from the right
		int ind = -1;
		String prefix = "";

		for(int i = p.length()-1; i >= 0; i--) {

			if(p.charAt(i)!=t.charAt(i)) {
				ind = i;
				prefix = p.substring(0, i+1);
				break;
			}

		}
		
		String suffix = p.substring(ind+1, p.length());
		//index which part of p fits suffix
		int ind2 = -1;

		while(p.lastIndexOf(suffix, p.length()-suffix.length()-1)==-1) {
			suffix = suffix.substring(1, suffix.length());
			ind2 = p.lastIndexOf(suffix, p.length()-suffix.length()-1);
		}

		return p.length()-suffix.length()-ind2;

	}

}
