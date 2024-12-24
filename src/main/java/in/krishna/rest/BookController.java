package in.krishna.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.krishna.entity.Book;
import in.krishna.service.BookServiceImpl;

@RestController
public class BookController {

	@Autowired
	private BookServiceImpl bookService;
	
	@PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody Book b) {
        Book saveBook = bookService.saveBook(b);
        return new ResponseEntity<>(saveBook, HttpStatus.CREATED);
    }
	 @GetMapping("book/{id}")
	public ResponseEntity<Optional<Book>>findById(@PathVariable Long id){
		Optional<Book> byId = bookService.findById(id);
		return new ResponseEntity<>(byId,HttpStatus.OK);
	}
	 @GetMapping("name/{name}") 
	 public ResponseEntity<Book>findByName(@PathVariable String name){
		 Book list=bookService.findByName(name); 
	     return new ResponseEntity<>(list,HttpStatus.OK); }
	 
	 @GetMapping("/books")		
	public ResponseEntity <List<Book>>findByAll(){
		List<Book> all = bookService.findAll();
		return new ResponseEntity<>(all,HttpStatus.OK);
	}
	@PutMapping("/{id2}")
	 public ResponseEntity<Book> UpdateBook(@PathVariable Long id2,@RequestBody Book b2) {
        Book updateBook = bookService.updateBook(id2, b2);
        return new ResponseEntity<>(updateBook, HttpStatus.CREATED);
    }
	@DeleteMapping("/{id}")
	public ResponseEntity<Optional<Book>>deleteById(@PathVariable Long id){
		Optional<Book> deleteById = bookService.deleteById(id);
		return new ResponseEntity<>(deleteById,HttpStatus.OK);
	}
	@DeleteMapping("/")
	public ResponseEntity deleteAll(){
		bookService.deleteAll();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * @GetMapping("/page") public ResponseEntity<Page<Book>> getBookss(Pageable
	 * pageable) { Page<Book> books = bookService.getBooks(pageable); return new
	 * ResponseEntity<>(books, HttpStatus.OK); }
	 */
	/*
	 * @GetMapping("/page") public
	 * ResponseEntity<Page<Book>>getBooks(@RequestParam(defaultValue="0") int page,
	 * 
	 * @RequestParam(defaultValue="10")int size){
	 *  PageRequest pageable=PageRequest.of(page, size);
	 * Page<Book>books=bookService.getBooks(pageable); return new
	 * ResponseEntity<>(books,HttpStatus.OK); 
	 * }
	 */
	@GetMapping("/page")
	public ResponseEntity<Page<Book>>getBooks(@RequestParam(defaultValue="0")int page,
	 @RequestParam(defaultValue="10")int size,
	 @RequestParam(defaultValue="id") String sortBy,
	 @RequestParam(defaultValue="asc") String sortDir){
		Sort sort=sortDir.equalsIgnoreCase("asc") ?
		Sort.by(sortBy).ascending()	:
		Sort.by(sortBy).descending();
		Pageable pageable=PageRequest.of(page, size,sort);
		Page<Book>books=bookService.getBooks(pageable);
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
}
