import java.util.*;

public class NaiveExactMatching {
	
	public static void main(String[] args) {
		
		
		
	}

	//find all locations where p exists in t
	public static HashSet<Integer> find(String p, String t) { 

		HashSet<Integer> occurrences = new HashSet();

		int from = 0;
		while(from < t.length() && t.indexOf(p, from)!=-1) {
			occurrences.add(t.indexOf(p, from));
			from = t.indexOf(p, from)+1;
		}

		return occurrences;

	}

}
