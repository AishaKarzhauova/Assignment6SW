package assignment6;

import java.util.HashMap;

interface LibraryFacade {
    void borrowBook(String title, String author, String user);
    void returnBook(String title, String author);
    void searchBooks(String query);
    boolean checkAvailability(String title, String author);
}

class LibrarySystem implements LibraryFacade {
    private BookInventorySystem bookInventorySystem;
    private UserManagementSystem userManagementSystem;

    public LibrarySystem() {
        this.bookInventorySystem = new BookInventorySystem();
        this.userManagementSystem = new UserManagementSystem();
    }

    @Override
    public void borrowBook(String title, String author, String user) {
        if (checkAvailability(title, author)) {
            bookInventorySystem.borrowBook(title, author);
            userManagementSystem.addBookToUser(user, title, author);
            System.out.println("Book '" + title + "' borrowed successfully by " + user);
        } else {
            System.out.println("Sorry, the book '" + title + "' is not available for borrowing.");
        }
    }

    @Override
    public void returnBook(String title, String author) {
        bookInventorySystem.returnBook(title, author);
        System.out.println("Book '" + title + "' returned successfully.");
    }

    @Override
    public void searchBooks(String query) {
        bookInventorySystem.searchBooks(query);
    }

    @Override
    public boolean checkAvailability(String title, String author) {
        return bookInventorySystem.checkAvailability(title, author);
    }
}


class BookInventorySystem {
    private HashMap<String, String> bookInventory;

    public BookInventorySystem() {
        bookInventory = new HashMap<>();
        // we can also create databse with book titles and their authors
        bookInventory.put("The Great Gatsby", "F. Scott Fitzgerald");
        bookInventory.put("Harry Potter", "J.K. Rowling");
        bookInventory.put("To Kill a Mockingbird", "Harper Lee");
    }

    void borrowBook(String title, String author) {
        bookInventory.remove(title);
        System.out.println("Book '" + title + "' borrowed successfully.");
    }

    void returnBook(String title, String author) {
        bookInventory.put(title, author);
        System.out.println("Book '" + title + "' returned successfully.");
    }

    void searchBooks(String query) {

        for (String title : bookInventory.keySet()) {
            if (title.toLowerCase().contains(query.toLowerCase())) {
                System.out.println("Found book: " + title + " by " + bookInventory.get(title));
            }
        }
    }

    boolean checkAvailability(String title, String author) {
        return bookInventory.containsKey(title) && bookInventory.get(title).equals(author);
    }
}

class UserManagementSystem {
    void addBookToUser(String user, String title, String author) {

        System.out.println("Book '" + title + "' added to user '" + user + "' account.");
    }
}

public class Assignment6Ex1 {
    public static void main(String[] args) {
        LibraryFacade libraryFacade = new LibrarySystem();

        libraryFacade.borrowBook("The Great Gatsby", "F. Scott Fitzgerald", "John Doe");

        libraryFacade.returnBook("The Great Gatsby", "F. Scott Fitzgerald");

        libraryFacade.searchBooks("Harry Potter");

        boolean available = libraryFacade.checkAvailability("Harry Potter", "J.K. Rowling");
        System.out.println("Is Harry Potter available? " + (available ? "Yes" : "No"));
    }
}

// The book inventory system uses Hashmap for storing all the authors and books, for more complex cases we can use a hashmap
// where the authors will be mapped to the list of books. The librarySystem class implements the Libraryfacade interface and it
// internally delegates the tasks to appropriate systems, in this case bookInventorySystem and UserManagementSystem.