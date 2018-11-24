package book_borrowing;

import java.util.ArrayList;

public class MyLibrary {

	String name;
	ArrayList<Book> books;
	ArrayList<Person> people;


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
