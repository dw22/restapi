package com.learning.microservice.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	
	List<Book> findAll();
	
	Optional<Book> findByBookId(Integer id);

}