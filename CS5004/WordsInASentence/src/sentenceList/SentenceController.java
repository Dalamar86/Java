package sentenceList;

import java.util.regex.Pattern;

public class SentenceController {

	public static void main(String[] args) {
		Sentence s1 = new SentenceList();
		Sentence s2 = new SentenceList("Hello There§");
		
		System.out.println(s1.getWord());
		System.out.println(s1.getSize());
		String word = "!";
		System.out.println( Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word));
	}
	
}
