package EvaluationOct31;

import java.util.HashSet;
import java.util.Set;

public class RepeatedSubSequence {

	static Set<String> set = new HashSet<>();
	static String sequence="";

	public static void main(String[] args) {

		String inputString = "XYBYAXBY";
		System.out.println(new RepeatedSubSequence().getCombination(inputString,0));
		System.out.println("Number of pair :"+ set.size());

	}

	public Set<String> getCombination(String s, int index) {

		if(sequence.length()>1) {
			checkSequence(sequence,s.substring(index-1));
			//checkSequence(s.substring(index-1));
			System.out.println(sequence);
		}

		for(int i=index;i<s.length();i++) {

			sequence += s.charAt(i);
			getCombination(s,i+1);
			sequence = sequence.substring(0,sequence.length()-1);

		}
		return set;
//[YY, XY, YBY, BY, YB, XB, XBY]
	}


	public void checkSequence(String combination, String str) {
	//public void checkSequence(String str) {

		System.out.println(str);
		if(sequence.length() > str.length()) 
			return;

		int charPresent = -1;

		for(int i=0;i<sequence.length();i++) {
			
			str=str.substring(charPresent+1);

			charPresent=str.indexOf(sequence.charAt(i));   //Here it checks the character present in the input string or not(not means return -1)

			if(charPresent==-1) 
				return;
		}

		set.add(sequence);
		return;



	}
}
