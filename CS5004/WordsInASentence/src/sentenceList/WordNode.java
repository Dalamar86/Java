package sentenceList;

public class WordNode implements Sentence {

	private String word;
	private Sentence nextNode;
	
	public WordNode(String word, Sentence nextNode) {
		this.word = word;
		this.nextNode = nextNode;
	}
	
	@Override
	public int countHelper(int numWords) {
		System.out.println("Node: " + numWords);
		return nextNode.countHelper(numWords + 1);
	}
	
	@Override
	public int getNumberOfWords() {
		int numWords = 0;
		return countHelper(numWords++);
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
		return word;
	}

	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.word;
		} else {
			String str = nextNode.get(index--).getWord(index);
			return str;
		}
	}
}
