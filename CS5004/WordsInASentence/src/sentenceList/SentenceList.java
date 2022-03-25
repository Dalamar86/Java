package sentenceList;

import java.util.Arrays;
import java.util.regex.Pattern;

public class SentenceList implements Sentence{

	// Create a Sentence list
	private Sentence head;
	
	public SentenceList() {
		head = new EmptyNode();
	}
	
	public SentenceList(String sen) {
		if(!(sen.trim()).isEmpty()) {
			if((sen.trim()).contains(" ")) {
				String[] words = sentenceSplitter(sen.trim(), " ");
				System.out.println(words[0] + " : " + words[1]);
				nodeBuilder(words, 0);
			} 
			head = new WordNode(sen.trim(), new EmptyNode());
		} else {
			head = new EmptyNode();
		}
	}

	private String[] sentenceSplitter(String head, String splitter) {;
		return head.split(splitter);
	}
	
	private void nodeBuilder(String[] words, int index) {
		System.out.println("index: " + index + "\tlength: " + words.length);
		//String[] wordpun = sentenceSplitter(words[index], "");
		//String word = punctuationChecker(wordpun, 0);
		if(index == words.length-1) {
			System.out.println(words[index]);
			if(punctuationChecker(words[index])) {
				removepunctuation(words);
			} else {
				this.head = new WordNode(words[index], new EmptyNode());
			}
			return;
		} if(punctuationChecker(words[index])) {
			removepunctuation(words);
			nodeBuilder(words, index+1);
		} else {
			this.head = new WordNode(words[index], head);
			nodeBuilder(words, index+1);
		}		
	}
	
	private void removepunctuation(String[] words) {
		// TODO Auto-generated method stub
		
	}

	private boolean punctuationChecker(String word) {
		System.out.println("made it here first: " + word);
		if (Pattern.matches(".*[\\p{Punct}\\p{IsPunctuation}]", word)) { 
			this.head = new PunctuationNode((String.valueOf(word.charAt(word.length()-1))), head);
			// ]$", word)) {
			//
			//word[index].replace(getWord(), getWord())
			System.out.println("made it here second: " + word);
			return true;			
		}
		return false;
		/*
		System.out.println(word);
		if(index == word.length()-1) {
			
		}
		if (Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
			
			return true;
		} return false;
		*/
	}
	
	@Override
	public int getNumberOfWords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String longestWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Sentence clone() {
		
		return null;
	}
	
	@Override
	public Sentence merge(Sentence other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFront(String word) {
		head.addFront(word);
	}

	@Override
	public void addBack(String word) {
		head.addBack(word);
	}

	@Override
	public void add(int index, String word) {
		head.add(index, word);		
	}

	@Override
	public int countHelper(int numWords) {return head.getSize();}
	
	@Override
	public int getSize() {
		int acc = 0;
		acc = countHelper(acc);
		return acc;
	}

	@Override
	public void remove(String word) {
		head.remove(word);		
	}

	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index < getSize())) {
		      return head.get(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	};
	
	@Override
	public String getWord(int index) throws IllegalArgumentException {
		System.out.println("List: " + getSize());
		if ((index >= 0) && (index < getSize())) {
		      return head.get(index).getWord(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}

	@Override
	public String getWord() {
		return head.getWord();
	};
}
