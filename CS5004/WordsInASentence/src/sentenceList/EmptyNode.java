package sentenceList;

public class EmptyNode implements Sentence {

	@Override
	public int countHelper(int numWords) {
		System.out.println("empty: " + numWords);
		return numWords;
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
