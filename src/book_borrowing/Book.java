package book_borrowing;

public class Book {

	// public = access from any class
	// private = access from only this class
	// no modifier = access from any class in this package only!!
	// No access modifiers since we want other classes in this package to access these fields
	String title;
	String author;
	Person person; // by making the 'Person' class a field here, we are linking both classes!

	public Book(String string) {
		this.title = string;
		this.author = "unknown author";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setPerson(Person p2) {
		this.person = p2;
		
	}

	public Person getPerson() {
		return this.person;
	}
	
	public String toString() {
		String availability;
		if (this.getPerson() == null) {
			availability = "Available";
		} else {
			availability = "Checked out to " + this.getPerson().getName();
		}
		return this.getTitle() + " by " + this.getAuthor() + "; " + availability;
	}

}
