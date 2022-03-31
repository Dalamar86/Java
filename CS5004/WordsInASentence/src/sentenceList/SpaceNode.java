package sentenceList;

/**
 * Node which holds the spaces in the sentence.
 * 
 * @author Robert Wilson
 *
 */
public class SpaceNode extends SentenceNode {

	public SpaceNode(String punctuation, Sentence nextNode) {
		super(punctuation, nextNode);
	}
}
