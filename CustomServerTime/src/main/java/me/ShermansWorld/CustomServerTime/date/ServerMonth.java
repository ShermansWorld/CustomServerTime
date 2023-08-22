package me.ShermansWorld.CustomServerTime.date;

public class ServerMonth {
	
	/**
	 * Data container for a month, a component of a ServerDate.
	 * Composed of an id, name and length.
	 * The id should increment in the order of the months.
	 */
	
	private int id;
	private String name;
	private int length;
	
	public ServerMonth(int id, String name, int length) {
		this.id = id;
		this.name = name;
		this.length = length;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
}
