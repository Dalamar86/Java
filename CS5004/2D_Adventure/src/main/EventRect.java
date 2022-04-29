package main;

import java.awt.Rectangle;

public class EventRect extends Rectangle {
	
	private int eventRectDefaultX, eventRectDefaultY;
	private boolean eventFinished = false;
	
	//#####################################################################
	// 							Getters and Setters
	//#####################################################################
	
	public int getEventRectDefaultX() {
		return eventRectDefaultX;
	}
	public void setEventRectDefaultX(int eventRectDefaultX) {
		this.eventRectDefaultX = eventRectDefaultX;
	}
	public int getEventRectDefaultY() {
		return eventRectDefaultY;
	}
	public void setEventRectDefaultY(int eventRectDefaultY) {
		this.eventRectDefaultY = eventRectDefaultY;
	}
	public boolean isEventFinished() {
		return eventFinished;
	}
	public void setEventFinished(boolean eventFinished) {
		this.eventFinished = eventFinished;
	}

}
