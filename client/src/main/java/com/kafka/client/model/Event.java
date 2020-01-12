package com.kafka.client.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alcanzer
 *
 */

@Document
public class Event {
	private long session;
	private String link;
	private int width;
	private int height;
	private int xAxis;
	private int yAxis;
	private long timestamp;
	public long getSession() {
		return session;
	}
	public void setSession(long session) {
		this.session = session;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Event [session=" + session + ", link=" + link + ", width=" + width + ", height=" + height + ", xAxis="
				+ xAxis + ", yAxis=" + yAxis + ", timestamp=" + timestamp + "]";
	}
	
	
}
