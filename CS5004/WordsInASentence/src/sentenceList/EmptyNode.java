package sentenceList;

public class EmptyNode implements Sentence {
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public int countHelper(int numWords) {
		return numWords;
	}
	
	@Override
	public int numWordsHelper(int numWords) {
		return numWords;
	}
	
	@Override
	public int getNumberOfWords() {
		int numWords = 0;
		return numWordsHelper(numWords);
	}

	@Override
	public String longestWordHelper(String word) {
		return word;
	}
	
	@Override
	public String longestWord() {
		return longestWordHelper("");
	}

	@Override
	public Sentence clone() {
		return new EmptyNode();
	};
	
	@Override
	public Sentence merge(Sentence other) {
		Sentence copy = ((SentenceList)other).clone();
		Sentence head = ((SentenceList)copy).getHead();
		return head;
	}

	@Override
	public Sentence addFront(String word, boolean isWord) {
		if(isWord) {
			return new WordNode(word, this);
		} else {
			if(word.isBlank()) {
				return new SpaceNode(word, this);
			}
			return new PunctuationNode(word, this);
		}
	}

	@Override
	public Sentence addBack(String word, boolean isWord) {
		return addFront(word, isWord);		
	}

	@Override
	public Sentence add(int index, String word, boolean isWord) {
		if(index == 0) {
			return addFront(word, isWord);
		} 
		throw new IllegalArgumentException("Invalid index to add an element");		
	}

	@Override
	public int getSize() {
		return countHelper(0);
	}

	@Override
	public Sentence removeHelper(String word) {
		return this;
	}

	@Override 
	public void remove(String word) {
		removeHelper(word);
	}

	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		throw new IllegalArgumentException("wrong index");
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		throw new IllegalArgumentException("wrong index");
	}

	@Override
	public String getWord() {
		return "";
	}

	@Override
	public void addFront(String word) {}

	@Override
	public void addBack(String word) {}
}
