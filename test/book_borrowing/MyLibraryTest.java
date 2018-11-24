package book_borrowing;

import java.util.ArrayList;

import junit.framework.TestCase;

public class MyLibraryTest extends TestCase {

	private Book b1;
	private Book b2;
	private MyLibrary ml;
	private Person p1;
	private Person p2;

	// test constructor
	public void testMyLibrary() { // tests NEED to begin with the word 'test'
		MyLibrary ml = new MyLibrary("Test");
		assertEquals("Test", ml.name);
		assertTrue(ml.books instanceof ArrayList);
		assertTrue(ml.people instanceof ArrayList);
	}
	
	public void setup() {
		b1 = new Book("Book1");
		b2 = new Book("Book 2");
		
		p1 = new Person(); p1.setName("Fred");
		p2 = new Person(); p2.setName("Sue");
		
		ml = new MyLibrary("Test");
	}
	
	public void testAddBook() {
		// create test objects
		setup();
		
		// test initial size is 0
		assertEquals(0, ml.getBooks().size());
		
		ml.addBook(b1);
		ml.addBook(b2);
		assertEquals(2, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b1));
		assertEquals(1, ml.getBooks().indexOf(b2));
		
		ml.removeBook(b1);
		assertEquals(1, ml.getBooks().size());
		assertEquals(0, ml.getBooks().indexOf(b2));
		ml.removeBook(b2);
		assertEquals(0, ml.getBooks().size());
	}
	
	public void testCheckOut() {
		setup();
		addItems();
		// prints this message out if .checkOut returns false
		assertTrue("Book did not check out correctly.", ml.checkOut(b1,p1));
		
		assertEquals("Fred", b1.getPerson().getName());
		
		assertFalse("Book was already checked out.", ml.checkOut(b1,p1));
		
		assertTrue("Book check in failed.", ml.checkIn(b1));
		assertFalse("Book was already checked in.", ml.checkIn(b1));
		assertFalse("Book was never checked out.", ml.checkIn(b2));
		
		setup(); // refresh library and book assignments
		addItems();
		// check that max books per person function works
		p1.setMaxBooks(1);
		assertTrue("First book did not check out.", ml.checkOut(b2, p1));
		assertFalse("Second book should not have checked out!", ml.checkOut(b1, p1));
	}

	private void addItems() {
		ml.addBook(b1); ml.addBook(b2);
		ml.addPerson(p1); ml.addPerson(p2);
	}
	
	public void testGetBooksForPerson() {
		setup();
		addItems();
		assertEquals(0, ml.getBooksForPerson(p1).size()); // should be no books to start
		
		// checkout first book
		ml.checkOut(b1, p1);
		ArrayList<Book> testBooks = ml.getBooksForPerson(p1);
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b1));
		
		// check out second book
		ml.checkOut(b2, p1);
		testBooks = ml.getBooksForPerson(p1);
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
	}
	
	public void testAvailableBooks() {
		setup();
		addItems();
		ArrayList<Book> testBooks = ml.getAvailableBooks();
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
		
		// remove first book
		ml.checkOut(b1, p1);
		testBooks = ml.getAvailableBooks();
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b2));
		
		// remove second book
		ml.checkOut(b2, p1);
		testBooks = ml.getAvailableBooks();
		assertEquals(0, testBooks.size());
	}
	
	public void testGetUnavailable() {
		setup();
		addItems();
		assertEquals(0, ml.getUnavailableBooks().size()); // should be no books to start
		
		// checkout first book
		ml.checkOut(b1, p1);
		ArrayList<Book> testBooks = ml.getUnavailableBooks();
		assertEquals(1, testBooks.size());
		assertEquals(0, testBooks.indexOf(b1));
		
		// check out second book
		ml.checkOut(b2, p2);
		testBooks = ml.getUnavailableBooks();
		assertEquals(2, testBooks.size());
		assertEquals(1, testBooks.indexOf(b2));
	}
	
	public void testToString() {
		setup();
		addItems();
		assertEquals("Test: 2 book(s); 2 people", ml.toString());
		
	}
}
