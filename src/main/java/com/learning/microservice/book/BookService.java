package com.learning.microservice.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookService {
	
	private static List<Book> books = new ArrayList<>();
	
	static{
		books.add(new Book(1,"Java Guide","James G","Samba","Technical"));
		books.add(new Book(2,"VB Guide","Thomas G","Samba","Technical"));
	}
	
	public List<Book> getBooks(){
		return books;
	}

	public Book getBook(int bookId){

		for(Book b : books){
			if(bookId == b.getBookId()){
				return b;
			}
		}
		return null;
	}
	
	public Book save(Book book){
		int bookId=0;
		for(Book b : books){
			bookId = b.getBookId();
		}
		book.setBookId(bookId+1);
		books.add(book);
		return book;
	}

	public Book deleteBook(int bookId) {

		for(Book b : books){
			if(bookId == b.getBookId()){
				books.remove(b);
				return b;
			}
		}
		return null;
	}

	public Book update(Book book) {
		for(Book b : books){
			if(book.getBookId() == b.getBookId()){
				b.setAuthor(book.getAuthor());
				b.setBookName(book.getBookName());
				b.setGenre(book.getGenre());
				return book;
			}
		}
		return null;
	}
}
