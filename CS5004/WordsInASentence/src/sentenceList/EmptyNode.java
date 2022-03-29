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
	public Sentence merge(SentenceList other) {
		SentenceList copy = other.clone();
		Sentence head = copy.getHead();
		//Sentence head = new SpaceNode(" ", copy.getHead());
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

	public Sentence add(int index, String word) {
		if(index == 0) {
			SentenceList other = new SentenceList(word);
			System.out.println(other.toString());
			Sentence sen = merge(other);
			System.out.println(sen.toString());
			return sen;
		} else {
			throw new IllegalArgumentException("Invalid index to add an element");
		}
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
	public Sentence removeHelper(int index) {
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
}
