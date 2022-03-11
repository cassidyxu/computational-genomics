
public class Polymerase {
	
	public static String getComplement (String str) {
		
		String complement = "";
		
		for(int i = 0; i < str.length(); i++) {
			
			if(str.charAt(i)=='A')	complement+="T";
			if(str.charAt(i)=='T')	complement+="A";
			if(str.charAt(i)=='C')	complement+="G";
			if(str.charAt(i)=='G')	complement+="C";
			
		}
		
		return complement;
				
	}

}
