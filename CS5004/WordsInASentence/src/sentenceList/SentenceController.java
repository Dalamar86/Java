package sentenceList;

public class SentenceController {

	public static void main(String[] args) {
		Sentence s1 = new SentenceList();
		Sentence s2 = new SentenceList("This .String.is.Upper(hello world) is correct!");
		
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		
		System.out.println(s1.getWord());
		System.out.println(s2.getWord());
		
		System.out.println(s1.getSize());
		System.out.println(s2.getSize());
		
		System.out.println(s1.getNumberOfWords());
		System.out.println(s2.getNumberOfWords());
		
		System.out.println(s1.longestWord());
		System.out.println(s2.longestWord());
		
		s1.addBack("Hello world!", true);
		Sentence s3 = s2.clone();
		Sentence s4 = new SentenceList(((SentenceList)s2));
		
		System.out.println(s1.toString());
		s1.remove(null);
		s2.remove("String");
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		System.out.println(s3.toString());
		System.out.println(s4.toString());
		
		Sentence s5 = new SentenceList("Why this is atrotious!");
		Sentence s6 = s3.merge(s5);

		s3.remove(".");
		
		System.out.println(s3.toString());
		System.out.println(s5.toString());
		System.out.println(s6.toString());
		
		
	}
	
}
