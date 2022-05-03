package main;

import java.awt.Rectangle;

/**
 * Holder of the events from the event handler.
 * 
 * @author Robert Wilson
 *
 */
public class EventRect extends Rectangle {
	// Default stats of the event location and duration
	private int eventRectDefaultX, eventRectDefaultY;
	private boolean eventFinished = false;
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	/**
	 * Returns the events default x position.
	 * 
	 * @return eventRectDefaultX (int) 
	 */
	public int getEventRectDefaultX() {
		return eventRectDefaultX;
	}
	
	/**
	 * Sets the events default x position.
	 * 
	 * @param eventRectDefaultX (int)
	 */
	public void setEventRectDefaultX(int eventRectDefaultX) {
		this.eventRectDefaultX = eventRectDefaultX;
	}
	
	/**
	 * Returns the events default y position.
	 * 
	 * @return eventRectDefaultY (int) 
	 */
	public int getEventRectDefaultY() {
		return eventRectDefaultY;
	}
	
	/**
	 * Sets the events default y position.
	 * 
	 * @param eventRectDefaultY (int)
	 */
	public void setEventRectDefaultY(int eventRectDefaultY) {
		this.eventRectDefaultY = eventRectDefaultY;
	}
	
	/**
	 * Returns whether an event has finished. True if it has and false otherwise.
	 * 
	 * @return eventFinished (boolean)
	 */
	public boolean isEventFinished() {
		return eventFinished;
	}
	
	/**
	 * Sets whether an event has finished. true if it has and false otherwise.
	 * 
	 * @param eventFinished (boolean)
	 */
	public void setEventFinished(boolean eventFinished) {
		this.eventFinished = eventFinished;
	}

}
