package sentenceList;

public class WordNode implements Sentence {

	private String word;
	private Sentence nextNode;
	
	public WordNode(String word, Sentence nextNode) {
		this.word = word;
		this.nextNode = nextNode;
	}
	
	@Override
	public String toString() {
		String str = word;
		return str + nextNode.toString();
	}
	
	@Override
	public int countHelper(int numWords) {
		return nextNode.countHelper(numWords + 1);
	}
	
	@Override
	public int numWordsHelper(int numWords) {
		return nextNode.numWordsHelper(numWords + 1);
	}
	
	@Override
	public int getNumberOfWords() {
		int numWords = 0;
		return numWordsHelper(numWords);
	}

	@Override
	public String longestWordHelper(String word) {
		if(this.word.length() > word.length()) {
			return nextNode.longestWordHelper(this.word);
		}
		return nextNode.longestWordHelper(word);
	}
	
	@Override
	public String longestWord() {
		return longestWordHelper(word);
	}

	@Override
	public Sentence clone() {
		String word = new String(this.word);
		WordNode other = new WordNode(word, nextNode.clone());
		return other;
	};
	
	@Override
	public Sentence merge(SentenceList other) {
		String word = new String(this.word);
		WordNode copy = new WordNode(word, nextNode.merge(other));
		return copy;
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
		this.nextNode = this.nextNode.addBack(word, isWord);
		return this;
	}

	
	@Override
	public Sentence add(int index, String word, boolean isWord) {
		if(index == 0) {
			return addFront(word, isWord);
		} else {
			this.nextNode = this.nextNode.add(index-1, word, isWord);
			return this;
		}
	}

	@Override
	public Sentence add(int index, String word) {
		if(index == 0) {
			SentenceList other = new SentenceList(word);
			if(this.nextNode instanceof EmptyNode) {
				System.out.println(other.toString());
				this.nextNode = other.getHead();
			} else {
				SentenceList temp = new SentenceList(nextNode);
				this.nextNode = other.merge(temp).getHead();
			}
			return this;
		} else {
			nextNode.add(--index, word);
			return this;
		}
	}
	
	@Override
	public int getSize() {
		return countHelper(0);
	}

	@Override
	public Sentence removeHelper(String word) {
		if(this.word.equals(word)) {
			return nextNode;
		}
		nextNode = nextNode.removeHelper(word);
		return this;
	}
	
	@Override
	public Sentence removeHelper(int index) {
		if(index == 0) {
			return nextNode;
		}
		nextNode = nextNode.removeHelper(--index);
		return this;
	}

	@Override 
	public void remove(String word) {
		nextNode = removeHelper(word);
	}
	
	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this;
		} else {
			return nextNode.get(--index);
		}
	}
	
	public String getWord() {
		return word;
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.word;
		} else {
			return nextNode.getWord(--index);
		}
	}
	
	public Sentence getNext() {
		return nextNode;
	}
}
