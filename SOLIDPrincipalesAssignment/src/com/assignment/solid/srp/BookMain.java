package com.assignment.solid.srp;

public class BookMain {
    public static void main(String[] args) {

        Book book = new Book ("Clean code", "Hafizur Rahman", 999);

        BookPrinter bookPrinter = new BookPrinter();
        bookPrinter.printBook(book);

        BookRepository repo = new BookRepository();
        repo.saveBook(book);
    }
}
//output done SOLID