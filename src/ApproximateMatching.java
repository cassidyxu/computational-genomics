import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ApproximateMatching {

	public static void main(String[] args) {
		
		System.out.println(".......");

		
		String p = "AACTTG";
		String t = "CACTTAATTTG";
		HashSet<Integer> result = approx(p, t, 2);
		for(Integer i: result) {
			System.out.print(i + " ");
		}
		//0 5
		System.out.println();
		
		HashSet<Integer> result2 = approx(p, t, 1);
		for(Integer i: result2) {
			System.out.print(i + " ");
		}
		//5
		System.out.println();

		
		String x = "shake spea";
		String y = "Shakespear";
		
		Date begin = new Date();
		System.out.println(recur(x, y));
		Date end = new Date();
		long execTimeInMillies = end.getTime()-begin.getTime();
		System.out.println("Calcuate recur time: " + execTimeInMillies + " millliseconds");

		
		Date begin2 = new Date();
		System.out.println(dynamic(x, y));		
		Date end2 = new Date();
		long execTimeInMillies2 = end2.getTime()-begin2.getTime();
		System.out.println("Calcuate recur time: " + execTimeInMillies2 + " millliseconds");

		

	}
	
	public static HashSet<Integer> approx(String p, String t, int n) {
		
		int segmentLength = p.length()/(n+1);
		HashSet<Integer> allMatches = new HashSet<>();
		for(int i=0; i<n+1; i++) {
			int start = i*segmentLength;
			int end = Math.min((i+1)*segmentLength, p.length());
			String ss = p.substring(start, end);
			HashSet<Integer> matches = NaiveExactMatching.find(p.substring(start, end), t);
			
			for(Integer m: matches) {
				if(m<start || m-start+p.length()>t.length()) {
					continue;
				}
				
				int mismatches = 0;
				
				for(int j=0; j<start; j++) {
					if(p.charAt(j)!=t.charAt(m-start+j)) {
						mismatches++;
						if(mismatches>n)	break;
					}
				}
				for(int j=end; j<p.length(); j++) {
					if(p.charAt(j)!=t.charAt(m-start+j)) {
						mismatches++;
						if(mismatches>n)	break;
					}
				}
				
				if(mismatches<=n)	allMatches.add(m-start);	
				
			}
			
		}
				
		return allMatches;
		
	}
	
	public static int recur(String x, String y) {
		
		
		if(x.length()==0) {
			return y.length();
		}
		if (y.length()==0) {
			return x.length();
		}

		int distHor = recur(x.substring(0, x.length()-1), y) +1;
		int distVer = recur(x, y.substring(0, y.length()-1)) +1;

		int distDiag = Integer.MAX_VALUE;
		if(x.charAt(x.length()-1)==y.charAt(y.length()-1)){
			distDiag = recur(x.substring(0, x.length()-1), y.substring(0, y.length()-1));
		}else {
			distDiag = recur(x.substring(0, x.length()-1), y.substring(0, y.length()-1)) + 1;
		}

		return Math.min(distHor, Math.min(distVer, distDiag));




	}
	
	public static int dynamic(String x, String y) {
		int[][] d = new int[x.length()+1][y.length()+1];
		
		for(int r=0; r<d.length; r++) {
			d[r][0]=r;
		}
		for(int c=0; c<d[0].length; c++) {
			d[0][c]=c;
		}
		
		for(int r=1; r<d.length; r++) {
			for(int c=1; c<d[0].length; c++) {
				int distHor = d[r][c-1]+1;
				int distVer = d[r-1][c]+1;
				int distDiag = Integer.MAX_VALUE;
				if(x.charAt(r-1)==y.charAt(c-1)) {
					distDiag = d[r-1][c-1];
				}else {
					distDiag = d[r-1][c-1]+1;
				}
				
				d[r][c] = Math.min(distHor, Math.min(distVer, distDiag));
			}
					
		}
		
		return d[d.length-1][d[0].length-1];
		
		
	}


}



