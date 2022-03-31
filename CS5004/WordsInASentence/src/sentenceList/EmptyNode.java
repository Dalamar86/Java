package sentenceList;

/**
 * The last node of a SentenceList which is empty.  All methods act a little different from the other nodes.
 * 
 * @author Robert Wilson
 *
 */
public class EmptyNode extends SentenceNode {
	
	public EmptyNode() {
		super("", null);
	}
	
	/**
	 * End of the List returns the empty string word and ends the recursive call.
	 * 
	 *  @return word (String) empty string.
	 */
	@Override
	public String toString() {
		return word;
	}

	/**
	 * Ends the recursive call and returns the number of words in the list.
	 * 
	 * @param numWords (int) number of words counted so far.
	 * @return numWords (int) number of words in the list.
	 */
	@Override
	public int getNumberOfWords(int numWords) {
		return numWords;
	}

	/**
	 * Ends the recursive call and returns the first longest word.
	 * 
	 * @param word (String) the longest word in the list.
	 * @return word (String) the longest word in the list.
	 */
	@Override
	public String longestWord(String word) {
		return word;
	}

	/**
	 * Ends the recursive call and returns a new EmptyNode.
	 * 
	 * @return new EmptyNode().
	 */
	@Override
	public Sentence clone() {
		return new EmptyNode();
	};
	
	/**
	 * Ends the recursive call and returns the head of a cloned list (other).
	 * 
	 * @param other (SentenceList) the list to be merged.
	 * @return other.head (Sentence) the head of the cloned list to merge.
	 */
	@Override
	public Sentence merge(SentenceList other) {
		return other.clone().getHead();
	}

	/**
	 * Ends the recursive call bay calling addFront.
	 * 
	 * @param word (String) the object to be added to the front of this node.
	 * @param isWord (boolean) true if string is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type given bay the addFront method.
	 */
	@Override
	public Sentence addBack(String word, boolean isWord) {
		return addFront(word, isWord);		
	}

	/**
	 * Ends the recursive call bay calling addFront.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the front of this node.
	 * @param isWord (boolean) true if string is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type given bay the addFront method.
	 * @throws IllegalArgumentException if an invalid index is passed. 
	 */
	@Override
	public Sentence add(int index, String word, boolean isWord) {
		if(index == 0) {
			return addFront(word, isWord);
		} 
		throw new IllegalArgumentException("Invalid index to add an element");
	}

	/**
	 * Ends the recursive call by appending the current list with the sentence parameter.
	 * 
	 * @param index (int) if 0, merge word with this sentenceList else throw error
	 * @return head (Sentence) merge of new SentenceList with the word as an argument. 
	 * @throws IllegalArgumentException if an invalid index is passed.  
	 */
	public Sentence add(int index, String word) {
		if(index == 0) {
			return merge(new SentenceList(word));
		} else {
			throw new IllegalArgumentException("Invalid index to add an element");
		}
	}
	
	/**
	 * Ends the recursive call by returning the number of nodes counted in the list.
	 * 
	 * @param numWords (int) number of words counted so far.
	 * @return numWords (int) number of nodes counted in the list. 
	 */
	@Override
	public int getSize(int numNodes) {
		return numNodes;
	}

	/**
	 * Ends the recursive call by returning this because we didn't find the word specified.
	 * 
	 * @param word (String) word being searched for.
	 * @return this (Sentence) this empty node.
	 */
	@Override
	public Sentence remove(String word) {
		return this;
	}
	
	/**
	 * Ends the recursive call by returning this because we didn't find the index specified.
	 * 
	 * @param index (int) index being searched for.
	 * @return this (Sentence) this empty node.
	 */
	@Override
	public Sentence remove(int index) {
		return this;
	}

	/**
	 * Ends the recursive call by throwing an error because we have reached the end of the list.
	 * 
	 *  @param index (int) index being looked for.
	 *  @throws IllegalArgumentException if an invalid index is passed.
	 */
	@Override
	public Sentence get(int index) throws IllegalArgumentException {
		System.out.println(index);
		if(index == 0) {
			return this;
		}
		throw new IllegalArgumentException("wrong index");
	}

	/**
	 * Ends the recursive call by throwing an error because we have reached the end of the list.
	 * 
	 *  @param index (int) index being looked for.
	 *  @throws IllegalArgumentException if an invalid index is passed.
	 */
	@Override
	public String getWord(int index) throws IllegalArgumentException {
		if(index == 0) {
			return this.word;
		}
		throw new IllegalArgumentException("wrong index");
	}
}
