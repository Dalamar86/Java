package sentenceList;

public class SentenceController {

	public static void main(String[] args) {
		Sentence s1a = new SentenceList();
		Sentence s1b = new SentenceList();
		Sentence s2 = new SentenceList("This String.is.Upper(hello world) is correct!");
		
		System.out.println(s1a.toString());
		System.out.println(s2.toString());
		
		System.out.println(s1a.getWord());
		System.out.println(s2.getWord());
		
		System.out.println(s1a.getSize());
		System.out.println(s2.getSize());
		
		System.out.println(s1a.getNumberOfWords());
		System.out.println(s2.getNumberOfWords());
		
		System.out.println(s1a.longestWord());
		System.out.println(s2.longestWord());
		
		s1a.addBack("Hello world!");
		s1a.addBack("My name is Tony.");
		s1a.addBack("Fine", true);
		s1b.addFront("Hello world!");
		s1b.addFront("My name is Tony.");
		s1b.addFront("Fine", true);
		
		Sentence s3 = s2.clone();
		Sentence s4 = new SentenceList(((SentenceList)s2));
		
		System.out.println(s1a.toString());
		System.out.println(s1b.toString());
		s1a.remove("Fine");
		s1b.remove("Fine");
		s2.remove("String");
		System.out.println(s1a.toString());
		System.out.println(s1a.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());
		System.out.println(s4.toString());
		
		Sentence s5 = new SentenceList("Why this is atrotious!");
		Sentence s6 = s3.merge(s5);
		Sentence s7 = new SentenceList("Why this is atrotious!", s3);

		s3.remove(".");
		
		System.out.println(s3.toString());
		System.out.println(s5.toString());
		System.out.println(s6.toString());
		System.out.println(s7.toString());
		
		String[] sen = SentenceList.sentenceSplitter("This is my test.");
		for(String s: sen) {
			System.out.println(s.toString());
		}
		Sentence s8 = new SentenceList("I can.t do this");
		System.out.println(s8.toString());		
	}
	
}
