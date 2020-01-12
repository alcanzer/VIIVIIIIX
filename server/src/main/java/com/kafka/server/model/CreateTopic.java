package com.kafka.server.model;

/**
 * @author Alcanzer
 *
 */
public class CreateTopic {
	private String name;
	private int numPartitions;
	private short replications;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumPartitions() {
		return numPartitions;
	}
	public void setNumPartitions(int numPartitions) {
		this.numPartitions = numPartitions;
	}
	public short getReplications() {
		return replications;
	}
	public void setReplications(short replications) {
		this.replications = replications;
	}
	@Override
	public String toString() {
		return "CreateTopic [name=" + name + ", numPartitions=" + numPartitions + ", replications=" + replications
				+ "]";
	}
	
	
}
