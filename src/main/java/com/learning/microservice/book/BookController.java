package com.learning.microservice.book;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.microservice.exception.BookNotFoundException;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MessageSource messageSource;
	
	//Internationalization
	@GetMapping("/book-api")
	public String welcome(){
		return messageSource.getMessage("startup.message", null, LocaleContextHolder.getLocale());
	}
	
	@GetMapping("/books")
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	
	@GetMapping("/book/{bookId}")
	public Book getBook(@PathVariable int bookId){
		Optional<Book> book = bookRepository.findByBookId(Integer.valueOf(bookId));
		if(book.isPresent())
			return book.get();
		else
			throw new BookNotFoundException("BookId - "+bookId);
		
	}

	@PostMapping("/books")
	public ResponseEntity<Book> createBook(@Valid @RequestBody Book book){
		Book b = bookRepository.save(book);
		
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("{bookId}")
		.buildAndExpand(b.getBookId()).toUri();
		
		return ResponseEntity.created(location).body(b);
	}
	
	
	@DeleteMapping("/book/{bookId}")
	public String deleteBook(@PathVariable int bookId){
		bookRepository.deleteById(Integer.valueOf(bookId));
		//bookRepository.delete(Integer.valueOf(bookId));
		
		//if(book==null)
		//throw new BookNotFoundException("BookId - "+bookId);
		return "Book Successfully Deleted";
	}
	
	
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable int bookId){
		Optional<Book> b = bookRepository.findByBookId(Integer.valueOf(bookId));
		if(b.isPresent()){
			book.setBookId(bookId);
			Book updatedBook = bookRepository.save(book);
			return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
		}
		else
			throw new BookNotFoundException("BookId - "+bookId);
	}
	
}
