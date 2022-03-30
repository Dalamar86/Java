package sentenceList;

public class WordNode extends SentenceNode {

	public WordNode(String word, Sentence nextNode) {
		super(word, nextNode);
	}

	@Override
	public int getNumberOfWords(int numWords) {
		return nextNode.getNumberOfWords(++numWords);
	}

	@Override
	public String longestWord(String word) {
		if(this.word.length() > word.length()) {
			return nextNode.longestWord(this.word);
		}
		return nextNode.longestWord(word);
	}
}
