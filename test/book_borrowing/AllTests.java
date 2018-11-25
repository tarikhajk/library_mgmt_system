package book_borrowing;

import org.junit.runner.*;
import org.junit.runners.*;

// new way (JUnit 4)
@RunWith(Suite.class) // annotations (@) used by JUnit4 to identify test classes
@Suite.SuiteClasses(value = {
		PersonTest.class,
		BookTest.class,
		MyLibraryTest.class,
		MyUtilitiesTest.class
})

public class AllTests {
	

	// Old --> JUnit 3
/* 
	public static Test suite() {
		TestSuite suite = new TestSuite("Test for org.totalbeginner.tutorial");
		//$JUnit-BEGIN$
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(PersonTest.class);
		suite.addTestSuite(MyLibraryTest.class);
		//$JUnit-END$
		return suite;
	}
*/
}
