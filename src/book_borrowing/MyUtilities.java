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
		return xstream.toXML(ml);
	}

	public static MyLibrary convertFromXML(String testXMLOut) {
		MyLibrary ml = null;
		XStream xstream = new XStream(new DomDriver());
		Object obj = xstream.fromXML(testXMLOut);
		if (obj instanceof MyLibrary) {
			ml = (MyLibrary)obj;
		}
		return ml;
	}
	

}
