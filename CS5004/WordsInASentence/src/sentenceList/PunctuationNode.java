package sentenceList;

public class PunctuationNode implements Sentence {

	private String punc;
	private Sentence nextNode;
	
	public PunctuationNode(String punctuation, Sentence nextNode) {
		this.punc = punctuation;
		this.nextNode = nextNode;
	}

	@Override
	public int countHelper(int numWords) {
		
		return nextNode.countHelper(numWords);
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
	};
	
	@Override
	public Sentence merge(Sentence other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFront(String word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBack(String word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(int index, String word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSize() {
		return countHelper(0);
	}

	@Override
	public void remove(String word) {
		// TODO Auto-generated method stub
		
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
		return punc;
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.punc;
		} else {
			String str = nextNode.get(index--).getWord(index);
			return str;
		}
	}
}
