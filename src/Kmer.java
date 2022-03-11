import java.util.*;

public class Kmer {

	public static void main(String[] args) {
		
		String t = "CGTGCGTGCTT";
		String p = "GCGTGC";
		String p2 = "GCGTGA";
		String p3 = "GCGTGCTT";
		int k = 5;
		
		System.out.println(kmer(p, t, k));
		System.out.println(kmer(p2, t, k));
		System.out.println(kmer2(p3, t, k, 2));
		
		
		
	}
	
	public static int kmer(String p, String t, int k) {
		// value - index hits
		HashMap<String, ArrayList<Integer>> table = new HashMap<>();
		for(int i=0; i<t.length()-k; i++) {
			String s = t.substring(i, i+k);
			if(table.containsKey(s)) {
				table.get(s).add(i);
			}else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				table.put(s, list);
			}
			
		}
		
		//every possible mod n
		//n=1
		boolean found=false;
		for(int i=0; i<1; i++) {
			if(i+k>=p.length())	break;
			String s = p.substring(i, i+k);
			if(table.containsKey(s)) {
				//verification
				for(Integer indexHit: table.get(s)) {
					if(indexHit-i>=0 && indexHit-i+p.length()<t.length()) {
						if(t.substring(indexHit-i, indexHit-i+p.length()).equals(p)) {
							//System.out.println(indexHit-i);
							
							found=true;
							return indexHit-i;
						}
						
					
						
					}
				}			
				
			}
		}
		
		//if(!found)	System.out.println(-1);
		return -1;
		
		
		
	}
	
	// offset every n
	public static int kmer2(String p, String t, int k, int n) {
		// value - index hits
		HashMap<String, ArrayList<Integer>> table = new HashMap<>();
		for(int i=0; i<=t.length()-k; i+=n) {
			String s = t.substring(i, i+k);
			if(table.containsKey(s)) {
				table.get(s).add(i);
			}else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				table.put(s, list);
			}
			
		}
		
		//every possible mod n
		boolean found=false;
		for(int i=0; i<n; i++) {
			if(i+k>=p.length())	break;
			String s = p.substring(i, i+k);
			if(table.containsKey(s)) {
				//verification
				for(Integer indexHit: table.get(s)) {
					if(indexHit-i>=0 && indexHit-i+p.length()<=t.length()) {
						if(t.substring(indexHit-i, indexHit-i+p.length()).equals(p)) {
							//System.out.println(indexHit-i);
							found=true;
							return indexHit-i;
						}
						
					
						
					}
				}			
				
			}
		}
		
		//if(!found)	System.out.println(-1);
		return -1;
		
		
	}

}
