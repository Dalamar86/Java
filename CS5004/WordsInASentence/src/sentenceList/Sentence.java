package sentenceList;

/**
 * Interface for word, punctuation, and space nodes.
 * 
 * @author Robert Wilson
 *
 */
public interface Sentence {
	
	/**
     * Add an object to the front of this list.
     * 
     * @param word (String) the object to be added to the front of this list.
     * @param isWord (boolean) true if word is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type.
     */
	Sentence addFront(String word, boolean isWord);

	/**
	 * Add an object to the back of this list (so it is the last object in the
	 * list.
	 * 
	 * @param word (String) the object to be added to the back of this list.
     * @param isWord (boolean) true if word is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type.
	 */
	Sentence addBack(String word, boolean isWord);

	/**
	 * Add an object to this list so that it occupies the provided index. Index
	 * begins with 0.  Boolean isWord determines which new node to add.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the list.
	 * @param isWord (boolean) true if string is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type.
	 */
	Sentence add(int index, String word, boolean isWord);
	
	/**
	 * Add object to list at the specified index. If string is a sentence, the merge function is called.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the list.
	 * @return new (Sentence) node of the appropriate type.
	 */
	Sentence add(int index, String word);

	/**
	 * Return the number of objects currently in this list.  This includes punctuation and space nodes.
	 * 
	 * @param numNodes(int) the number of nodes counted so far.
	 * @return size (int) the size of the complete list.
	 */
	int getSize(int numNodes);

	/**
	 * Remove the first instance of this object from this list.
	 * 
	 * @param word (String) the object to be removed.
	 * @return this (Sentence) returns the list as it is without the removed node.
	 */
	Sentence remove(String word);
	
	/**
	 * Remove the object from this list at the given index.
	 * 
	 * @param index (int) the index of the object to be removed.
	 * @return this (Sentence) returns the list as it is without the removed node.
	 */
	Sentence remove(int index);

	/**
	 * Get the (index)th object in this list.
	 * 
	 * @param index (int) the index of the object to be returned.
	 * @return Sentence (Sentence) the object at the given index.
	 * @throws IllegalArgumentException if an invalid index is passed.
	 */
	Sentence get(int index) throws IllegalArgumentException;
	
	/**
	 * Get the string of the current object.
	 * 
	 * @return word (String) the string of the current object.
	 */
	String getWord();
	
	/**
	 * Get the String of the (index)th object in this list.
	 * 
	 * @param index (int) the index of the object to be returned.
	 * @return word (String) the string of the object at the given index.
	 * @throws IllegalArgumentException if an invalid index is passed.
	 */
	String getWord(int index) throws IllegalArgumentException;
	
	/**
	 * Methods which computes and returns the number of words in a sentence.  This excludes punctuation nodes.
	 * 
	 * @param numWords (int) number of words so far.
	 * @return numWords (int) represents the number of words in the sentence.
	 */
	public int getNumberOfWords(int numWords);
	
	/**
	 * Determines and returns the longest word in a sentence.  Returns an empty string if sentence if empty.
	 * If there are two words of the same size it returns the first word of that size. 
	 * 
	 * @param word (String) longest word so far.
	 * @return longWord (String) longest word in a sentence, empty string on an empty sentence.
	 */
	public String longestWord(String word);
	
	/**
	 * Overrides the object toString, which returns a string to print.
	 * 
	 * @return str (String) string to print in event of System call.
	 */
	@Override
	public String toString();
	
	/**
	 * This method returns a clone of the sentence as an independent object.
	 * 
	 * @return other (Sentence) independent clone of a sentence.
	 */
	public Sentence clone();
	
	/**
	 * Merges two sentences together, preserving all punctuation.
	 * 
	 * @param other (SentenceList) SentenceList to merge with this sentence.
	 * @return copy (Sentence) New concatenated SentenceList.
	 */
	public Sentence merge(SentenceList other);
}
