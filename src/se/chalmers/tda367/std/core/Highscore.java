package se.chalmers.tda367.std.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Represents the game high score.
 * Implements {@code Iterable<Score> } which means that 
 * can be used in a for-loop.
 * @author Emil Edholm
 * @modified Johan Gustafsson
 * @date Mar 25, 2012
 */
public final class Highscore implements Iterable<Score>, Serializable {
	private static final long serialVersionUID = 4903477121308462010L;
	private SortedSet<Score> highscore;

	public Highscore(){
		highscore = new TreeSet<Score>();
	}
	
	/**
	 * Adds a score to the high score.
	 * @param score the score to add. Must be null separated
	 * @return true if the score did not already exist in the high score.
	 * @throws NullPointerException if {@code score} is null
	 */
	public boolean addScore(Score score){
		if(score == null)
			throw new NullPointerException("Supplied score is null");
		return highscore.add(score);
	}
	
	/**
	 * Get the (first) score with the supplied name.
	 * @param name the name to search for. Not that the search is case insensitive.
	 * @return the score if found, else null.
	 * @throws NullPointerException if {@code name} is null
	 */
	public Score getScore(String name){
		if(name == null)
			throw new NullPointerException("Supplied name is null");
		
		for(Score s : highscore){
			if(s.getName().equals(name))
				return s;
		}
		
		return null;
	}
	
	/**
	 * Returns the highest score in the high score.
	 * @return the highest score.
	 * @throws NoSuchElementException if the high score is empty.
	 */
	public Score getHighestScore(){
		return highscore.last();
	}
	
	/**
	 * Returns the lowest score in the high score.
	 * @return the lowest score.
	 * @throws NoSuchElementException if the high score is empty.
	 */
	public Score getLowestScore(){
		return highscore.first();
	}
	
	/**
	 * Resets the high score.
	 * The high score will be empty after this
	 */
	public void resetHighscore(){
		highscore.clear();
	}
	
	/**
	 * Returns the number of scores in the high score.
	 * @return the high score size.
	 */
	public int size(){
		return highscore.size();
	}
	
	public SortedSet<Score> getScores() {
		return highscore;
	}
	
	@Override
	public String toString(){
		return Arrays.toString(highscore.toArray());
	}

	/**
	 * Returns an iterator over the elements in this set. 
	 * The elements are returned in ascending order.
	 * @return an iterator over the elements in this set.
	 */
	@Override
	public Iterator<Score> iterator() {
		return highscore.iterator();
	}
	
	/**
	 * Serialize this {@code Highscore}.
	 * @serialData a SortedSet containing instances of {@code Score} or null if no {@code Score} has been saved.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(highscore.size());
		
		for(Score score : highscore) {
			s.writeObject(score);
		}
	}
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		int numElements = s.readInt();
		
		while(numElements-- > 0) {
			highscore.add((Score) s.readObject());
		}
	}
}
