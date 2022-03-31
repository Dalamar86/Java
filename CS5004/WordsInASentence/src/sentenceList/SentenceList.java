package sentenceList;

import java.util.regex.Pattern;

/**
 * SentenceList implementation.  This class implements a single-linked list of words, punctuation, and spaces which makes
 * up a sentence.
 * 
 * @author Robert Wilson
 *
 */
public class SentenceList {

	// The head/beginning of the SentenceList
	private Sentence head;
	
	/*#################################################
	 * 					Constructors
	 *///##############################################
	
	public SentenceList() {
		head = new EmptyNode();
	}
	
	public SentenceList(String sen) {
		String sen1 = sen.trim();
		head = new EmptyNode();
		if(!sen1.isEmpty()) {
			_sentenceSplitter(sen);
		} 
	}

	public SentenceList(String sen, SentenceList node) {
		String sen1 = sen.trim();
		head = new EmptyNode();
		if(!sen1.isEmpty()) {
			_sentenceSplitter(sen);
		} 
		SentenceList copy = merge(node);
		this.head = copy.head;
	}
	
	public SentenceList(SentenceList other) {
		head = other.head;
	}
	
	public SentenceList(Sentence other) {
		head = other;
	}
	
	/*#################################################
	 * 				Constructor Helpers
	 *///##############################################
	
	/**
	 * Helper function which splits the sentence 'head' into space delimited array.  Each 'word' in the array is passed 
	 * to _splitWord method. A space is added to the list after each 'word' and the last one is removed if the original 
	 * sentence did not end with one.
	 * 
	 * @param head (String) Sentence to split and add to the Sentence list.
	 */
	private void _sentenceSplitter(String head) {
		String[] tempWords = head.split(" ");
		for(String word: tempWords) {
			//System.out.println("word: " + word);
			_splitWord(word);
			this.head = this.head.addBack(" ", false);
		}
		if(!head.endsWith(" ")) {
			this.head.remove(this.getSize()-1);
		}
	}
	
	/**
	 * Recursively called helper method for splitting each word into individual characters to check for punctuation.
	 * Each word and punctuation is added to the list in the order they were encountered. 
	 * 
	 * @param word (String) space delimited words to add to the list.
	 */
	private void _splitWord(String word) {
		if(!word.isEmpty()) {
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(!Character.isLetter(c)) {
					String punc = Character.toString(c);
					String[] tempWord = word.split("\\"+ punc);
					if(tempWord.length > 0) {
						if(!tempWord[0].isEmpty()) {
							this.head = head.addBack(tempWord[0], true);
						}
					}
					this.head = head.addBack(punc, false);
					for(int j = 1; j < tempWord.length; j++) {
						_splitWord(tempWord[j]);
						if(j < tempWord.length) {
							this.head = head.addBack(punc, false);
						}
					}
					if(!word.endsWith(punc)) {
						this.head.remove(this.getSize()-1);
					}
					return;
				}
			}
			this.head = head.addBack(word, true);
			return;
		}
		return;
	}

	/*#################################################
	 * 					Methods
	 *///##############################################
	
	/**
	 * Methods which computes and returns the number of words in a sentence.  This excludes punctuation nodes.
	 * 
	 * @return numWords (int) represents the number of words in the sentence.
	 */
	public int getNumberOfWords() {
		int numWords = 0;
		return head.getNumberOfWords(numWords);
	}
	
	/**
	 * Determines and returns the longest word in a sentence.  Returns an empty string if sentence if empty.
	 * If there are two words of the same size it returns the first word of that size. 
	 * 
	 * @return longWord (String) longest word in a sentence, empty string on an empty sentence.
	 */
	public String longestWord() {
		return head.longestWord("");
	}
	
	/**
	 * This method returns a clone of the sentence as an independent object.
	 * 
	 * @return other (Sentence) independent clone of a sentence.
	 */
	public SentenceList clone() {
		SentenceList other = new SentenceList(this);
		other.head = ((SentenceList)other).head.clone();
		return other;
	}
	
	/**
	 * Merges two sentences together, preserving all punctuation.
	 * 
	 * @param other (SentenceList) SentenceList to merge with this sentence.
	 * @return copy (Sentence) New concatenated SentenceList.
	 */
	public SentenceList merge(SentenceList other) {
		SentenceList copy = this.clone();
		copy.head = copy.head.merge(other);
		return copy;
	}

	/**
     * Add an object to the front of this list by setting the head to that object.
     * 
     * @param word (String) the object to be added to the front of this list.
     * @param isWord (boolean) true if word is a word, false otherwise.
     */
	private void addFront(String word, boolean isWord) {
		head = head.addFront(word, isWord);
	}
	
	/**
     * Add an object to the front of this list.  If 'word' is multiple words then make a new SentenceList and 
     * add it to the front of this list.  Otherwise call the addFront(String, boolean) version with the proper 
     * parameters.
     * 
     * @param word (String) the object to be added to the front of this list.
     */
	public void addFront(String word) {
		String[] words = SentenceList.sentenceSplitter(word);
		if(words.length > 1) {
			SentenceList other = new SentenceList(word);
			if(this.head instanceof EmptyNode) {
				this.head = other.head;
			} else {
				this.head = other.merge(this).head;
			}
		} else {
			// Found this solution in this thread.
			// https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
			if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
				addFront(word, false);
			} else{
				addFront(word, true);
			}
		}
	}

	/**
     * Add an object to the back of this list by setting the head to the returned object.
     * 
     * @param word (String) the object to be added to the back of this list.
     * @param isWord (boolean) true if word is a word, false otherwise.
     */
	private void addBack(String word, boolean isWord) {
		head = head.addBack(word, isWord);
	}
	
	/**
     * Add an object to the back of this list.  If 'word' is multiple words then make a new SentenceList and 
     * add it to the back of this list.  Otherwise call the addBack(String, boolean) version with the proper 
     * parameters.
     * 
     * @param word (String) the object to be added to the back of this list.
     */
	public void addBack(String word) {
		String[] words = SentenceList.sentenceSplitter(word);
		if(words.length > 1) {
			SentenceList other = new SentenceList(word);
			if(this.head instanceof EmptyNode) {
				this.head = other.head;
			} else {
				this.head = merge(other).head;
			}
		} else {
			// Found this solution in this thread.
			//https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
			if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
				addBack(word, false);
			} else{
				addBack(word, true);
			}
		}
	}
	
	/**
	 * Add an object to this list so that it occupies the provided index. Index
	 * begins with 0.  Boolean isWord determines which new node to add.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the list.
	 * @param isWord (boolean) true if string is a word, false otherwise.
	 * @return new (Sentence) node of the appropriate type.
	 */
	private void add(int index, String word, boolean isWord) {
		head = head.add(index, word, isWord);	
	}
	
	/**
	 * Add object to list at the specified index. If string is a sentence, the merge function is called. 
	 * If the index is 0 then the object is added to the front of this list.
	 * 
	 * @param index (int) the index to be occupied by this object, beginning at 0.
	 * @param word (String) the object to be added to the list.
	 * @return new (Sentence) node of the appropriate type.
	 */
	public void add(int index, String word) {
		if(!word.isEmpty()) {
			if(index == 0) {
				addFront(word);
			} else {
				String[] words = SentenceList.sentenceSplitter(word);
				if(words.length > 1) {
					head = head.add(index, word);
				} else {
					// Found this solution in this thread.
					//https://stackoverflow.com/questions/13925454/check-if-string-is-a-punctuation-character
					if(word.isBlank() || Pattern.matches("[\\p{Punct}\\p{IsPunctuation}]", word)) {
						add(index, word, false);
					} else{
						add(index, word, true);
					}
				}
			}
		}
	}

	/**
	 * Return the number of objects currently in this list.  This includes punctuation and space nodes.  
	 * Calls the head's getSize() method with a 0 to start.
	 * 
	 * @return size (int) the size of the complete list.
	 */
	public int getSize() {
		int acc = 0;
		return head.getSize(acc);
	}
	
	/**
	 * Remove the first instance of this object from this list.  Calls head's remove(String) method with the 
	 * word argument.
	 * 
	 * @param word (String) the object to be removed.
	 */
	public void remove(String word) {
		head = head.remove(word);		
	}
	
	/**
	 * Remove the object from this list at the given index.  Calls the head's remove(int) method with the 
	 * given index.
	 * 
	 * @param index (int) the index of the object to be removed.
	 */
	public void remove(int index) {
		head = head.remove(index);		
	}
	
	/**
	 * Get the (index)th object in this list.  Calls the head's get(int) method with the given index.
	 * 
	 * @param index (int) the index of the object to be returned.
	 * @return Sentence (Sentence) the object at the given index.
	 * @throws IllegalArgumentException if an invalid index is passed.
	 */
	public Sentence get(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index <= getSize())) {
		      return head.get(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}	
	
	/**
	 * Get the string of the head's object.
	 * 
	 * @return word (String) the string of the head's object.
	 */
	public String getWord() {
		return head.getWord();
	}
	
	/**
	 * Get the String of the (index)th object in this list.  Calls the head's getWord(int) method with the 
	 * given index.
	 * 
	 * @param index (int) the index of the object to be returned.
	 * @return word (String) the string of the object at the given index.
	 * @throws IllegalArgumentException if an invalid index is passed.
	 */
	public String getWord(int index) throws IllegalArgumentException {
		if ((index >= 0) && (index <= getSize())) {
		      return head.getWord(index);
		} else {
			throw new IllegalArgumentException("Invalid index");
		}
	}
	
	/**
	 * Calls the head's toString method and returns it as a String.
	 * 
	 * @return str (String) string to print in event of System call.
	 */
	public String toString() {
		return head.toString();
	}
	
	/**
	 * Returns the head of this object.
	 * 
	 * @return head (Sentence) start of the list.
	 */
	public Sentence getHead() {
		return head;
	}
	
	/**
	 * Sets the head of this object.
	 * 
	 */
	public void setHead(Sentence head) {
		this.head = head;
	}
	
	/*#################################################
	 * 					Static Methods
	 *///##############################################
	
	/**
	 * Static version of the constructor helper function _sentenceSplitter(String).  
	 * This method takes in a sentence as a String and returns a String Array of all
	 * elements of the sentence.
	 * 
	 * @param head (String) sentence to split into array
	 * @return words (String[]) array of all elements in the sentence.
	 */
	static String[] sentenceSplitter(String head) {
		int index = 0;
		String[] words = new String[head.length()+1];
		String[] tempWords = head.split(" ");
		for(String word: tempWords) {
			index = splitWord(words, word, index);
			if(head.length() > 1) {
				words[index++] = " ";
			}
		}
		if(!head.endsWith(" ") && head.length() > 1) {
			words[--index] = null;
		}
		String[] finishedWord = new String[index];
		int i;
		for(i = 0; i < words.length; i++) {
			if(words[i] != null) {
				finishedWord[i] = words[i];
			}
		}
		return finishedWord;
	}
	
	/**
	 * Static version of the constructor helper function _splitWord(String).  
	 * This method takes in a sentence as a String, the words array, and an index to indicate position
	 * in the array to place the current object being worked on.  This method returns a String Array 'word', which
	 * now contains more elements of the sentence.
	 * 
	 * @param words (String[]) array to add the word or punctuation into.
	 * @param head (String) sentence to split into array.
	 * @param index (int) index to add the element to in the array words.
	 * @return words (String[]) array of all elements in the sentence.
	 */
	private static int splitWord(String[] words, String word, int index) {
		if(!word.isEmpty()) {
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if(!Character.isLetter(c)) {
					String punc = Character.toString(c);
					String[] tempWord = word.split("\\"+ punc);
					if(tempWord.length > 0) {
						if(!tempWord[0].isEmpty()) {
							words[index++] = tempWord[0];
						}
					}
					words[index++] = punc;
					for(int j = 1; j < tempWord.length; j++) {
						index = splitWord(words, tempWord[j], index);
						if(j+1 < tempWord.length) {
							words[index++] = punc;
						}
					}
					return index;
				}
			}
			words[index++] = word;
			return index;
		}
		return index;
	}
}
