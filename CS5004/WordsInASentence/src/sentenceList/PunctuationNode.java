package sentenceList;

/**
 * Node which holds the punctuation of the sentence.
 * 
 * @author Robert Wilson
 *
 */
public class PunctuationNode extends SentenceNode {

	public PunctuationNode(String punctuation, Sentence nextNode) {
		super(punctuation, nextNode);
	}
}
