package com.learning.microservice.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="This is the book object for api service") // This is used for swagger doc that comes in definition section of swaggerurl http://localhost:8080/v2/api-docs
@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private int bookId;
	
	@ApiModelProperty(notes ="Book Name should not be less than 2 characters") // This is for swagger documentaiton
	@Size(min=2 , message="Book Name should have atleast 2 characters")
	private String bookName;
	private String author;
	private String publisherName;
	private String genre;
	
	//@Past   // here the date should not in future 
	//private Date bookpublishDate;
	
	protected Book(){}
	
	public Book(int bookId, String bookName, String author, String publisherName, String genre) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
		this.publisherName = publisherName;
		this.genre = genre;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", author=" + author + ", publisherName="
				+ publisherName + ", genre=" + genre + "]";
	}
	

}
