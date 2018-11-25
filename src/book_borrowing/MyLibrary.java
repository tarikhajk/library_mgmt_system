package book_borrowing;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @author Tarik Haj-Khalil
 * @version 1.0
 * The MyLibrary class represents a collection of a given 
 * group of People and Books and keeps track of what book
 * has been checked out to what person.
 * 
 */

public class MyLibrary implements Serializable{
	/**
	 * Generated SerialUID by Eclipse
	 */
	private static final long serialVersionUID = -5755277311442558695L;
	
	String name; // Name of MyLibrary instance
	ArrayList<Book> books; // List of possible books to checkout
	ArrayList<Person> people; // List of people who can checkout books


	public MyLibrary(String name) {
		this.name = name;
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
	}

	public String getName() {
		return name;
	}


	public ArrayList<Book> getBooks() {
		return books;
	}


	public ArrayList<Person> getPeople() {
		return people;
	}

	public void addBook(Book b1) {
		this.books.add(b1);
		
	}

	public void removeBook(Book b1) {
		this.books.remove(b1);
	}
	
	public void addPerson(Person p1) {
		this.people.add(p1);
		
	}

	public void removePerson(Person p1) {
		this.people.remove(p1);
	}

	public boolean checkOut(Book b1, Person p1) {
		if (b1.getPerson() == null 
				&& (this.getBooksForPerson(p1).size() < p1.getMaxBooks())) {
			b1.setPerson(p1);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkIn(Book b1) {
		if (b1.getPerson() != null) {
			b1.setPerson(null);
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Book> getBooksForPerson(Person p1) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if((aBook.getPerson() != null) && 
					aBook.getPerson().getName().equals(p1.getName())) {
				result.add(aBook);
			}
		}
		return result;
		
	}

	public ArrayList<Book> getAvailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook: this.getBooks()) {
			if (aBook.getPerson() == null) {
				result.add(aBook);
			}
		}
		return result;
	}

	public ArrayList<Book> getUnavailableBooks() {
		// maybe i can use the array intersection-ish instead here?
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook: this.getBooks()) {
			if (aBook.getPerson() != null) {
				result.add(aBook);
			}
		}
		return result;
	}
	
	public String toString() {
		return this.getName() + ": " + this.getBooks().size() + " book(s); "
				+ this.getPeople().size() + " people";
	}
	
	
	// Main method
	public static void main(String[] args) {
		// create new library
		MyLibrary testLibrary = new MyLibrary("Test Drive Library");
		Book b1 = new Book("War and Peace");
		Book b2 = new Book("Great Expectations");
		b1.setAuthor("Tolstoy"); b2.setAuthor("Dickens");
		Person jim = new Person(); jim.setName("Jim");
		Person sue = new Person(); sue.setName("Sue");
		
		testLibrary.addBook(b1); testLibrary.addBook(b2);
		testLibrary.addPerson(jim); testLibrary.addPerson(sue);
		System.out.println("Just created new library");
		testLibrary.printStatus();
		
		System.out.println("Check out War and Peace to Sue");
		testLibrary.checkOut(b1, sue);
		testLibrary.printStatus();
		
		System.out.println("Do some more stuff");
		testLibrary.checkIn(b1);
		testLibrary.checkOut(b2, jim);
		testLibrary.printStatus();
		
		// Save library to XML file and get library back
		MyUtilities.saveMyLibraryToXMLFile("testxml.xml", testLibrary);
		MyLibrary xmlMyLibrary = 
				MyUtilities.getMyLibraryFromXMLFile("testxml.xml");
		
		System.out.println("Printing info from saved XML file...");
		xmlMyLibrary.printStatus();
		
		// Save library using Java Serialization and get library back
		MyUtilities.saveMyLibraryToSerialFile("testserial.xml", testLibrary);
		MyLibrary serialMyLibrary = 
				MyUtilities.getMyLibraryFromSerialFile("testserial.xml");
		
		System.out.println("Printing info from Java Serialization...");
		serialMyLibrary.printStatus();
	}

	private void printStatus() {
		System.out.println("Status report of MyLibrary: \n" + this.toString());
		for (Book thisBook : this.getBooks()) {
			System.out.println(thisBook);
		}
		for (Person p : this.getPeople()) {
			int bookCount = this.getBooksForPerson(p).size();
			System.out.println(p + " has " + bookCount + " of my books");
		}
		System.out.println("Books available: " + this.getAvailableBooks().size());
		System.out.println("*--- End of Status Report ---* \n");
	}
	

}
