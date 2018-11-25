package book_borrowing;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;

public class MyUtilitiesTest {

	// In JUnit4, we name test method same as method being tested
	
	// Checking if saveStringFromFile and getStringFromFile work appropriately
	@Test
	public void saveStringToFile() {
		String saveString = "This is test line one\n" +
							"This is test line two\n";
		File testFile = new File("testsavetostring.txt");
		testFile.delete();
		assertFalse("File should not exist.", testFile.exists());
		
		assertTrue("String should have been saved to file.",
				MyUtilities.saveStringToFile("testsavetostring.txt", saveString));
		
		String newString = MyUtilities.getStringFromFile("testsavetostring.txt");
		assertTrue("Save and get string should've been the same.", 
				saveString.equals(newString));
		
		// ...What if file doesn't exist?
		
		// We don't want program to crash, we just want it to return false
		// NOTE: method creates a file automatically, so you need to give it a bad 
		//		 directory for the try/catch block to catch this case, not just a 
		//		 non-existent file.
		assertFalse("File should not have been saved.", 
				MyUtilities.saveStringToFile("directory not found/bad.txt",saveString));
		
		// Retrieving a string from a file that doesn't exist
		String emptyString = MyUtilities.getStringFromFile("bad-file.txt");
		assertTrue("Hmm.. string should've been empty.", emptyString.length() == 0);
	}
	
	// Create and populate a MyLibrary object to test with
	public MyLibrary createMyLibrary() {
		Book b1;
		Book b2;
		MyLibrary ml;
		Person p1;
		Person p2;
		b1 = new Book("Book1");
		b2 = new Book("Book 2");
		p1 = new Person(); p1.setName("Fred");
		p2 = new Person(); p2.setName("Sue");
		ml = new MyLibrary("Test");
		ml.addBook(b1); ml.addBook(b2);
		ml.addPerson(p1); ml.addPerson(p2);
		
		// Checkout book b1 to person p1
		ml.checkOut(b1, p1);
		
		return ml;
	}
	
	@Test
	public void convertToXML() {
		MyLibrary startMyLibrary = createMyLibrary();
		String testXMLOut = MyUtilities.convertToXML(startMyLibrary);
		MyLibrary endMyLibrary = MyUtilities.convertFromXML(testXMLOut);
		
		// Check that endMyLibrary object has all the same attributes as
		// the startMyLibrary object.
		assertEquals("Test",endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().get(0).getPerson().getName());
		
		// The lines below don't work, as a custom .equals() method was 
		// not implemented for the MyLibrary object class.
//		assertTrue("Start and End library objects should've been the same.",
//				startMyLibrary.equals(endMyLibrary));
		
	}
	
	@Test
	public void saveToXMLFile() {
		MyLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.xml";
		File testFile = new File(fileName);
		testFile.delete();
		assertFalse("File should not exist.", testFile.exists());
		assertTrue("File should have saved.", 
				MyUtilities.saveMyLibraryToXMLFile(fileName, startMyLibrary));
		
		MyLibrary endMyLibrary = MyUtilities.getMyLibraryFromXMLFile(fileName);
		
		// Check that endMyLibrary object has all the same attributes as
		// the startMyLibrary object.
		assertEquals("Test",endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().get(0).getPerson().getName());
	}

}
