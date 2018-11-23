package book_borrowing;

import junit.framework.TestCase;

public class PersonTest extends TestCase {

	public void testPerson() {
		Person p1 = new Person();
		assertEquals("unknown name", p1.getName());
		assertEquals(3, p1.getMaxBooks());
	}

	public void testSetName() {
		Person p2 = new Person();
		p2.setName("Fred");
		assertEquals("Fred", p2.getName());
	}

	public void testSetMaxBooks() {
		Person p3 = new Person();
		p3.setMaxBooks(10);
		assertEquals(10, p3.getMaxBooks());
	}
	
	public void testToString() {
		Person p4 = new Person();
		p4.setName("Tarik Haj-Khalil");
		p4.setMaxBooks(7);
		//Test-Driven Development -> lets say we want the toString() method to
		//print out "Tarik Haj-Khalil (7 books)"
		String testString = "Tarik Haj-Khalil (7 books max)";
		assertEquals(testString, p4.toString());
	}

}
