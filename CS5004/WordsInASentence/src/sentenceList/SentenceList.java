package sentenceList;

import java.util.regex.Pattern;

public class SentenceList {

	// Create a Sentence list
	private Sentence head;
	
	public SentenceList() {
		head = new EmptyNode();
	}
	
	public SentenceList(String sen) {
		String sen1 = sen.trim();
		head = new EmptyNode();
		if(!sen1.isEmpty()) {
			_sentenceSplitter(sen);
		} 
	}

	public SentenceList(String sen, SentenceList node) {
		String sen1 = sen.trim();
		head = new EmptyNode();
		if(!sen1.isEmpty()) {
			_sentenceSplitter(sen);
		} 
		SentenceList copy = merge(node);
		this.head = copy.head;
	}
	
	public SentenceList(SentenceList other) {
		head = other.head;
	}
	
	public SentenceList(Sentence other) {
		head = other;
	}
	
	private void _sentenceSplitter(String head) {
		String[] tempWords = head.split(" ");
		for(String word: tempWords) {
			//System.out.println("word: " + word);
			_splitWord(word);
			this.head = this.head.addBack(" ", false);
		}
		if(!head.endsWith(" ")) {
			this.head.remove(this.getSize()-1);
		}
	}
	
	private void _splitWord(String word) {
		if(!word.isEmpty()) {
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(!Character.isLetter(c)) {
					String punc = Character.toString(c);
					String[] tempWord = word.split("\\"+ punc);
					if(tempWord.length > 0) {
						if(!tempWord[0].isEmpty()) {
							this.head = head.addBack(tempWord[0], true);
						}
					}
					this.head = head.addBack(punc, false);
					for(int j = 1; j < tempWord.length; j++) {
						_splitWord(tempWord[j]);
						if(j < tempWord.length) {
							this.head = head.addBack(punc, false);
						}
					}
					if(!word.endsWith(punc)) {
						this.head.remove(this.getSize()-1);
					}
					return;
				}
			}
			this.head = head.addBack(word, true);
			return;
		}
		return;
	}

	public int getNumberOfWords() {
		int numWords = 0;
		return head.getNumberOfWords(numWords);
	}
		
	public String longestWord() {
		return head.longestWord("");
	}
		
	public SentenceList clone() {
		SentenceList other = new SentenceList(this);
		other.head = ((SentenceList)other).head.clone();
		return other;
	}
		
	public SentenceList merge(SentenceList other) {
		SentenceList copy = this.clone();
		copy.head = copy.head.merge(other);
		return copy;
	}

	public void addFront(String word, boolean isWord) {
		head = head.addFront(word, isWord);
	}
	
	public void addFront(String word) {
		String[] words = SentenceList.sentenceSplitter(word);
		if(words.length > 1) {
			SentenceList other = new SentenceList(word);
			if(this.head instanceof EmptyNode) {
				this.head = other.head;
			} else {
				this.head = other.merge(this).head;
			}
		} else {
			//https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
			if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
				addFront(word, false);
			} else{
				addFront(word, true);
			}
		}
	}

	public void addBack(String word, boolean isWord) {
		head = head.addBack(word, isWord);
	}
		
	public void addBack(String word) {
		String[] words = SentenceList.sentenceSplitter(word);
		if(words.length > 1) {
			SentenceList other = new SentenceList(word);
			if(this.head instanceof EmptyNode) {
				this.head = other.head;
			} else {
				this.head = merge(other).head;
			}
		} else {
			//https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
			if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
				addBack(word, false);
			} else{
				addBack(word, true);
			}
		}
	}
	
	public void add(int index, String word, boolean isWord) {
		head = head.add(index, word, isWord);	
	}
	
	public void add(int index, String word) {
		if(!word.isEmpty()) {
			if(index == 0) {
				addFront(word);
			} else {
				String[] words = SentenceList.sentenceSplitter(word);
				if(words.length > 1) {
					head = head.add(index, word);
				} else {
					//https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
					if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
						add(index, word, false);
					} else{
						add(index, word, true);
					}
				}
			}
		}
	}

	public int getSize() {
		int acc = 0;
		return head.getSize(acc);
	}
	
	public void remove(String word) {
		head = head.remove(word);		
	}
	
	public void remove(int index) {
		head = head.remove(index);		
	}
	
	public Sentence get(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index < getSize())) {
		      return head.get(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}	
	
	public String getWord() {
		return head.getWord();
	}
	
	public String getWord(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index < getSize())) {
		      return head.getWord(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}
	
	public String toString() {
		return head.toString();
	}
	
	public Sentence getHead() {
		return head;
	}

	static String[] sentenceSplitter(String head) {
		int index = 0;
		String[] words = new String[head.length()+1];
		String[] tempWords = head.split(" ");
		for(String word: tempWords) {
			index = splitWord(words, word, index);
			if(head.length() > 1) {
				words[index++] = " ";
			}
		}
		if(!head.endsWith(" ") && head.length() > 1) {
			words[--index] = null;
		}
		String[] finishedWord = new String[index];
		int i;
		for(i = 0; i < words.length; i++) {
			if(words[i] != null) {
				finishedWord[i] = words[i];
			}
		}
		return finishedWord;
	}
	
	private static int splitWord(String[] words, String word, int index) {
		if(!word.isEmpty()) {
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(!Character.isLetter(c)) {
					String punc = Character.toString(c);
					String[] tempWord = word.split("\\"+ punc);
					if(tempWord.length > 0) {
						if(!tempWord[0].isEmpty()) {
							words[index++] = tempWord[0];
						}
					}
					words[index++] = punc;
					for(int j = 1; j < tempWord.length; j++) {
						index = splitWord(words, tempWord[j], index);
						if(j+1 < tempWord.length) {
							words[index++] = punc;
						}
					}
					return index;
				}
			}
			words[index++] = word;
			return index;
		}
		return index;
	}
}
