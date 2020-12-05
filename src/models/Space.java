package models;

public class Space {
	private int id;
	private String name;
	private int capacity;

	public Space(int id, String name, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;

	};
	
	public Space( String name, int capacity) {
		this.name = name;
		this.capacity = capacity;

	};

	public Space() {

	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}