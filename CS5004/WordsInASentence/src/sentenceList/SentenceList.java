package sentenceList;

public class SentenceList implements Sentence{

	// Create a Sentence list
	private Sentence head;
	
	public SentenceList() {
		head = new EmptyNode();
	}
	
	public SentenceList(String sen) {
		sen = sen.trim();
		head = new EmptyNode();
		if(!sen.isEmpty()) {
			sentenceSplitter(sen);
		} 
	}

	public SentenceList(String sen, Sentence node) {
		sen = sen.trim();
		head = new EmptyNode();
		if(!sen.isEmpty()) {
			sentenceSplitter(sen);
		} 
	}
	
	public SentenceList(SentenceList other) {
		head = other.head;
	}
	
	private void sentenceSplitter(String head) {
		int index = 0;
		String[] tempWords = head.split(" ");
		for(String word: tempWords) {
			splitWord(word);
			if(index < tempWords.length-1) {
				this.head = this.head.addBack(" ", false);
				index++;
			}
		}
	}
	
	private void splitWord(String word) {
		if(!word.isEmpty()) {
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(!Character.isLetter(c)) {
					String punc = Character.toString(c);
					String[] tempWord = word.split("\\"+ punc);
					if(!tempWord[0].isEmpty()) {
						this.head = head.addBack(tempWord[0], true);
					}
					this.head = head.addBack(punc, false);
					for(int j = 1; j < tempWord.length; j++) {
						splitWord(tempWord[j]);
						if(j+1 < tempWord.length) {
							this.head = head.addBack(punc, false);
						}
					}
					return;
				}
			}
			this.head = head.addBack(word, true);
			return;
		}
		return;
	}
	
	public int numWordsHelper(int numWord) {
		return head.getNumberOfWords();
	}
	
	@Override
	public int getNumberOfWords() {
		return numWordsHelper(0);
	}

	@Override
	public String longestWordHelper(String word) {
		return head.longestWordHelper(word);
	}
	
	@Override
	public String longestWord() {
		return head.longestWordHelper("");
	}
	
	@Override
	public Sentence clone() {
		SentenceList other = new SentenceList(this);
		other.head = ((SentenceList)other).head.clone();
		return other;
	}
	
	@Override
	public Sentence merge(Sentence other) {
		Sentence copy = (SentenceList)this.clone();
		copy = ((SentenceList)copy).head.merge(other);
		return copy;
	}

	@Override
	public Sentence addFront(String word, boolean isWord) {
		head = head.addFront(word, isWord);
		return this;
	}

	@Override
	public Sentence addBack(String word, boolean isWord) {
		head = head.addBack(word, isWord);
		return this.head;
	}

	@Override
	public Sentence add(int index, String word, boolean isWord) {
		head = head.add(index, word, isWord);	
		return this;
	}

	@Override
	public int countHelper(int numWords) {return head.getSize();}
	
	@Override
	public int getSize() {
		int acc = 0;
		return countHelper(acc);
	}

	@Override
	public Sentence removeHelper(String word) {
		return head.removeHelper(word);
	}
	
	@Override
	public void remove(String word) {
		head = removeHelper(word);		
	}

	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index < getSize())) {
		      return head.get(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	};
	
	@Override
	public String getWord(int index) throws IllegalArgumentException {
		System.out.println("List: " + getSize());
		if ((index >= 0) && (index < getSize())) {
		      return head.get(index).getWord(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}

	@Override
	public String toString() {
		return head.toString();
	}

	@Override
	public String getWord() {
		return head.getWord();
	}
	
	public Sentence getHead() {
		return head;
	}
}
