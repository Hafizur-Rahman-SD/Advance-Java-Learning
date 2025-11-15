package com.assignment.solid.srp;

public class BookRepository {
    // book info sob save kora e kaj hobe
    public void saveBook(Book b){
        System.out.println("Book save in Database:" +b.getTitle());
    }
}
