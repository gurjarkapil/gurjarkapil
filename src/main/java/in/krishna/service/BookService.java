package in.krishna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.krishna.entity.Book;

public interface BookService {
	
  public Book saveBook(Book b);
  
  public Optional<Book>findById(Long id);
  
 
  public List<Book>findAll();
  
  public Optional<Book> deleteById(Long id);
  
  public void deleteAll();
  
  public Book updateBook(Long id,Book b2);

  public Book findByName(String name);
  
  public Page<Book>getBooks(Pageable pageable);
}
