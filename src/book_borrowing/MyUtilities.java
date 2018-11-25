package book_borrowing;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MyUtilities {

	public static boolean saveStringToFile(String fileName, String saveString) {
		boolean saved = false;
		BufferedWriter bw = null;
		
		// If file doesn't exist, i/o Exception will be thrown
		try {
			bw = new BufferedWriter(new FileWriter(fileName));
			try {
				bw.write(saveString);
				saved = true;
			} // No need for inner catch block, since the outer catch block will catch it
			finally {
				// Only need to close if the first try was successful
				// Even id this close method fails, the outer catch block will catch it
				bw.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	public static String getStringFromFile(String fileName) {
		BufferedReader br = null;
		
		// Better at concatenating strings than a regular String object
		StringBuilder sb = new StringBuilder();
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			try {
				String s;
				// Continue reading while lines exist in file
				while ((s = br.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			}
			finally {
				br.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	public static String convertToXML(MyLibrary ml) {
		XStream xstream = new XStream(new DomDriver());
		
		// Give each object a unique ID, to avoid path-wise references
		xstream.setMode(XStream.ID_REFERENCES);
		
		// Replace class names with more readable names
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("mylib", MyLibrary.class);
		return xstream.toXML(ml);
	}

	public static MyLibrary convertFromXML(String testXMLOut) {
		MyLibrary ml = null;
		XStream xstream = new XStream(new DomDriver());
		
		// Give each object a unique ID, to avoid path-wise references
		xstream.setMode(XStream.ID_REFERENCES);
		
		// Replace class names with more readable names
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("mylib", MyLibrary.class);
		Object obj = xstream.fromXML(testXMLOut);
		if (obj instanceof MyLibrary) {
			ml = (MyLibrary)obj;
		}
		return ml;
	}

	
	// Using XML (XStream
	public static boolean saveMyLibraryToXMLFile(String fileName, 
			MyLibrary ml) {
		return saveStringToFile(fileName, convertToXML(ml));
	}

	public static MyLibrary getMyLibraryFromXMLFile(String fileName) {
		return convertFromXML(getStringFromFile(fileName));
	}
	
	
	// Using Java Serialization
	// Notice: This method is very similar to the 'saveFileToFile' method
	public static boolean saveMyLibraryToSerialFile(String fileName, 
			MyLibrary ml) {
		boolean saved = false;
		try {
			// This writes byte data to a file
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			
			// Top lines could've been refactored to the following, but the code
			// gets messy:
//			ObjectOutputStream oos = new ObjectOutputStream(
//					new BufferedOutputStream(
//							new FileOutputStream(fileName)));
			
			// Inner try block
			try {
				oos.writeObject(ml);
				saved = true;
			}
			finally {
				oos.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	public static MyLibrary getMyLibraryFromSerialFile(String fileName) {
		MyLibrary ml = null;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(fileName)));
			try {
				Object obj = ois.readObject();
				if (obj instanceof MyLibrary) {
					ml = (MyLibrary)obj;
				}
			}
			finally {
				ois.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ml;
	}
	

}
