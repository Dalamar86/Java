package sentenceList;

public class PunctuationNode implements Sentence {

	private String punc;
	private Sentence nextNode;
	
	public PunctuationNode(String punctuation, Sentence nextNode) {
		this.punc = punctuation;
		this.nextNode = nextNode;
	}

	@Override
	public String toString() {
		String str = punc;
		/*
		if(nextNode instanceof PunctuationNode || nextNode instanceof EmptyNode) {
			return str + nextNode.toString();
		} 
		*/
		return str + nextNode.toString();
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
		String punc = new String(this.punc);
		PunctuationNode other = new PunctuationNode(punc, nextNode.clone());
		return other;
	};
	
	@Override
	public Sentence merge(SentenceList other) {
		String punc = new String(this.punc);
		PunctuationNode copy = new PunctuationNode(punc, nextNode.merge(other));
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
		if(this.punc.equals(word)) {
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

	public String getWord() {
		return punc;
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.punc;
		} else {
			return nextNode.getWord(--index);
		}
	}
}
