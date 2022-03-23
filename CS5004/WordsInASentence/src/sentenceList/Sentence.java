package sentenceList;
/**
 * Interface for word and punctuation nodes
 * 
 * @author Robert Wilson
 *
 */
public interface Sentence {
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
	 * @return senClone (Sentence) independent clone of a sentence
	 */
	public Sentence clone();
	
	/**
	 * Merges two sentences together, preserving all punctuation.
	 * 
	 * @param other (Sentence) Sentence to merge with this sentence.
	 * @return SenNew (Sentence) New concatenated Sentence.
	 */
	public Sentence merge(Sentence other);
}
