package sentenceList;

public class SentenceController {

	public static void main(String[] args) {
		SentenceList s1a = new SentenceList();
		SentenceList s1b = new SentenceList();
		SentenceList s2 = new SentenceList("This. .String.is.Upper(hello world) is correct!");
		
		System.out.println("Demonstrating toString method");
		System.out.println(s1a.toString());
		System.out.println(s2.toString());
		
		System.out.println("\nDemonstrating getWord method");
		System.out.println(s1a.getWord());
		System.out.println(s2.getWord());
		
		System.out.println("\nDemonstrating getSize method");
		System.out.println(s1a.getSize());
		System.out.println(s2.getSize());
		
		System.out.println("\nDemonstrating getNumberOfWords method");
		System.out.println(s1a.getNumberOfWords());
		System.out.println(s2.getNumberOfWords());
		
		System.out.println("\nDemonstrating longestWord method");
		System.out.println(s1a.longestWord());
		System.out.println(s2.longestWord());
		
		s1a.addBack("Hello world! ");
		s1a.addBack("My name is Tony.");
		s1a.addBack("Fine");
		s1b.addFront("Hello world!");
		s1b.addFront(" My name is Tony.");
		s1b.addFront("Fine");
		
		System.out.println("\nDemonstrating addBack/addFront method");
		System.out.println(s1a);
		System.out.println(s1b);
		
		SentenceList s3 = s2.clone();
		SentenceList s4 = new SentenceList(((SentenceList)s2));
		
		System.out.println("\nDemonstrating clone method");
		System.out.println(s3);
		System.out.println(s4);
		
		s1a.remove("Fine");
		s1b.remove(0);
		s1b.remove(" ");
		s2.remove("String");
		
		System.out.println("\nDemonstrating remove method");
		System.out.println(s1a);
		System.out.println(s1b);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		
		System.out.println("\nDemonstrating get method");
		System.out.println(s1a.get(7).getWord());
		System.out.println(s2.get(2).getWord());
		
		SentenceList s5 = new SentenceList("Why this is atrotious!");
		SentenceList s6 = s3.merge(s5);
		SentenceList s7 = new SentenceList("Why this is atrotious!", s3);

		s3.remove(".");
		
		System.out.println("\nDemonstrating merge method");
		System.out.println(s3);
		System.out.println(s5);
		System.out.println(s6);
		System.out.println(s7);
		
		System.out.println("\nDemonstrating static method sentenceSplitter");
		String[] sen = SentenceList.sentenceSplitter(" This is my test. ");
		String[] sen1 = SentenceList.sentenceSplitter("  .");
		for(String s: sen) {
			System.out.println(s);
		}
		for(String s: sen1) {
			System.out.println(s);
		}
		
		System.out.println("\nDemonstrating checking contractions");
		SentenceList s8 = new SentenceList("I can't do this");
		System.out.println(s8);
		
		System.out.println("\nDemonstrating add method");
		s8.add(4, ", but I can help you.");
		System.out.println(s8);
	}
	
}
