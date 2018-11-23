package book_borrowing;

public class Person {
	// fields
	private String name;
	private int maxBooks;
	
	// constructor
	public Person() {
		name = "unknown name";
		maxBooks = 3;
	}
	
	// methods
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getMaxBooks() {
		return maxBooks;
	}

	public void setMaxBooks(int maxBooks) {
		this.maxBooks = maxBooks;
	}
	
	public String toString() {
		return this.getName() + " (" + this.getMaxBooks() + " books max)";
	}
	
}
