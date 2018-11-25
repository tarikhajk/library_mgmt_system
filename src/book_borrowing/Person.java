package book_borrowing;

import java.io.Serializable;

/*
 * @author Tarik Haj-Khalil
 * @version 1.0
 * The Person class represents the people who
 * check out books from a MyLibrary instance.
 * 
 */

public class Person implements Serializable{
	/**
	 * Generated SerialUID by Eclipse
	 */
	private static final long serialVersionUID = 2600130568284062476L;
	
	// fields
	private String name; // Person's name
	private int maxBooks; // Max number of books they can checkout
	
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
