package in.krishna.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import in.krishna.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
	
	Book findByName(String name);
   
}
