package sentenceList;

public class SpaceNode extends SentenceList {

	private String space;
	private Sentence nextNode;
	
	public SpaceNode(String punctuation, Sentence nextNode) {
		this.space = punctuation;
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		String str = space;
		return str + nextNode.toString();
	}
	
	@Override
	public int countHelper(int numWords) {
		return nextNode.countHelper(numWords);
	}
	
	@Override
	public int numWordsHelper(int numWords) {
		return nextNode.numWordsHelper(numWords);
	}
	
	@Override
	public int getNumberOfWords() {
		int numWords = 0;
		return numWordsHelper(numWords);
	}

	@Override
	public String longestWordHelper(String word) {
		return nextNode.longestWordHelper(word);
	}
	
	@Override
	public String longestWord() {
		return longestWordHelper("");
	}

	@Override
	public Sentence clone() {
		String space = new String(this.space);
		SpaceNode other = new SpaceNode(space, nextNode.clone());
		return other; 
	};
	
	@Override
	public Sentence merge(Sentence other) {
		String space = new String(this.space);
		SpaceNode copy = new SpaceNode(space, nextNode.merge(other));
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
			return new SpaceNode(word, this);
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
	public int getSize() {
		return countHelper(0);
	}

	@Override
	public Sentence removeHelper(String word) {
		if(this.space == word) {
			return nextNode;
		}
		nextNode = nextNode.removeHelper(word);
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
			return nextNode.get(index--);
		}
	}

	public String getWord() {
		return space;
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.space;
		} else {
			String str = nextNode.get(index--).getWord(index);
			return str;
		}
	}
	
	public Sentence getNext() {
		return nextNode;
	}
}
