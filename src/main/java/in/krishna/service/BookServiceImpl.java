package in.krishna.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.krishna.entity.Book;
import in.krishna.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public Book saveBook(Book b) {
		if(b!=null) {	
			bookRepo.save(b);
		}
		return null;
	}

	@Override
	public Optional<Book> findById(Long id) {
		Optional<Book> byId = bookRepo.findById(id);
		return bookRepo.findById(id);
	}

	@Override
	public List<Book> findAll() {
		
		return bookRepo.findAll();
	}

	@Override
	public Optional<Book> deleteById(Long id) {
		
		return bookRepo.findById(id);
	}

	@Override
	public void deleteAll() {
		bookRepo.deleteAll();
		
	}

	@Override
	public Book updateBook(Long id, Book updateBook) {
		Optional<Book> byId = bookRepo.findById(id);
		if(byId.isPresent()) {
			Book book = byId.get();
			book.setTitle(updateBook.getAuthor());
			book.setPrice(updateBook.getPrice());
			book.setAuthor(updateBook.getAuthor());
		return bookRepo.save(updateBook);
		}else {
			throw new RuntimeException("Book not found with id" + id);
			
		}
	}

	
	  @Override public Book findByName(String name) { return
	     bookRepo.findByName(name); }

	@Override
	public Page<Book> getBooks(Pageable pageable) {
		return bookRepo.findAll(pageable);
	}
	 
	
}
