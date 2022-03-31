package sentenceList;

/**
 * Abstract class for the nodes.  Holds the defaults methods for the various sentenceNodes.
 * 
 * @author Robert Wilson
 *
 */
public abstract class SentenceNode implements Sentence {

	// Word stored in the node
	protected String word;
	
	// The next node in the list
	protected Sentence nextNode;
	
	public SentenceNode(String word, Sentence nextNode) {
		this.word = word;
		this.nextNode = nextNode;
	}
	
	@Override
	public String toString() {
		return word + nextNode.toString();
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
		nextNode = nextNode.addBack(word, isWord);
		return this;
	}

	@Override
	public Sentence add(int index, String word, boolean isWord) {
		if(index == 0) {
			return addFront(word, isWord);
		} else {
			nextNode = nextNode.add(index-1, word, isWord);
			return this;
		}
	}

	@Override
	public Sentence add(int index, String word) {
		if(index == 0) {
			SentenceList other = new SentenceList(word);
			if(this.nextNode instanceof EmptyNode) {
				this.nextNode = other.getHead();
			} else {
				SentenceList temp = new SentenceList(nextNode);
				this.nextNode = other.merge(temp).getHead();
			}
			return this;
		} else {
			nextNode = nextNode.add(--index, word);
			return this;
		}
	}

	@Override
	public int getSize(int numNodes) {
		return nextNode.getSize(++numNodes);
	}

	@Override
	public Sentence remove(String word) {
		if(this.word.equals(word)) {
			return nextNode;
		}
		nextNode = nextNode.remove(word);
		return this;
	}

	@Override
	public Sentence remove(int index) {
		if(index == 0) {
			return nextNode;
		}
		nextNode = nextNode.remove(--index);
		return this;
	}

	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this;
		} else {
			return nextNode.get(--index);
		}
	}

	@Override
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

	@Override
	public int getNumberOfWords(int numWords) {
		return nextNode.getNumberOfWords(numWords);
	}

	@Override
	public String longestWord(String word) {
		return nextNode.longestWord(word);
	}

	@Override
	public Sentence clone() {
		String word = new String(this.word);
		WordNode other = new WordNode(word, nextNode.clone());
		return other;
	}

	@Override
	public Sentence merge(SentenceList other) {
		String word = new String(this.word);
		WordNode copy = new WordNode(word, nextNode.merge(other));
		return copy;
	}

}
