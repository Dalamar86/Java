package sentenceList;
/**
 * Interface for word and punctuation nodes
 * 
 * @author Robert Wilson
 *
 */
public interface Sentence {
	
	/**
     * Add an object to the front of this list.
     * 
     * @param word (String) the object to be added to the front of this list.
	 * @return 
     */
	Sentence addFront(String word, boolean isWord);

	/**
	 * Add an object to the back of this list (so it is the last object in the
	 * list.
	 * 
	 * @param word (String) the object to be added to teh back of this list.
	 */
	Sentence addBack(String word, boolean isWord);

	/**
	 * Add an object to this list so that it occupies the provided index. Index
	 * begins with 0.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the list.
	 */
	Sentence add(int index, String word, boolean isWord);

	/**
	 * Return the number of objects currently in this list.
	 * 
	 * @return size (int) the size of the complete list.
	 */
	int getSize();

	/**
	 * Remove the first instance of this object from this list.
	 * 
	 * @param b (Generic) the object to be removed.
	 */
	void remove(String word);

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
	 * @return word/punc (String) the string of the current object.
	 */
	String getWord();
	
	/**
	 * Get the String of the (index)th object in this list.
	 * 
	 * @param index (int) the index of the object to be returned.
	 * @return word/punc (String) the string of the object at the given index.
	 * @throws IllegalArgumentException if an invalid index is passed.
	 */
	String getWord(int index) throws IllegalArgumentException;
	
	/**
	 * Methods which computes and returns the number of words in a sentence.  This excludes punctuation nodes.
	 * 
	 * @return numWords (int) represents the number of words in the sentence.
	 */
	public int getNumberOfWords();
	
	/**
	 *Determines and returns the longest word in a sentence.  Returns an empty string if sentence if empty. 
	 * 
	 * @return longWord (String) longest word in a sentence, empty string on an empty sentence.
	 */
	public String longestWord();
	
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
	 * @return senClone (Sentence) independent clone of a sentence.
	 */
	public Sentence clone();
	
	/**
	 * Merges two sentences together, preserving all punctuation.
	 * 
	 * @param other (Sentence) Sentence to merge with this sentence.
	 * @return SenNew (Sentence) New concatenated Sentence.
	 */
	public Sentence merge(Sentence other);
	
	/**
	 * 
	 * @param numWords
	 * @return
	 */
	int countHelper(int numWords);
	
	/**
	 * 
	 * @param numWords
	 * @return
	 */
	int numWordsHelper(int numWords);
	
	/**
	 * 
	 * @param word
	 * @return
	 */
	String longestWordHelper(String word);
	
	/**
	 * 
	 * @param word
	 * @param prevNode
	 */
	Sentence removeHelper(String word);
	
	public void addFront(String word);
	public void addBack(String word);
}
