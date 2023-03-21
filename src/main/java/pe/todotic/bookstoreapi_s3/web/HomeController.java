package pe.todotic.bookstoreapi_s3.web;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.todotic.bookstoreapi_s3.model.Book;
import pe.todotic.bookstoreapi_s3.model.SalesOrder;
import pe.todotic.bookstoreapi_s3.repository.BookRepository;
import pe.todotic.bookstoreapi_s3.repository.SalesOrdenRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private SalesOrdenRepository salesOrdenRepository;

    @GetMapping("/last-books")
    List<Book> getLastBooks(){
 //      Pageable pageable = PageRequest.of(0,6, Sort.by("createdAt").descending());
 //      return bookRepository.findAll(pageable).getContent();
        return bookRepository.findTop6ByOrderByCreatedAtDesc();
    }

    @GetMapping("/books")
    Page<Book> getBooks(@PageableDefault(sort = "title") Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/books/{slug}")
    Book getBook(@PathVariable String slug){
        return bookRepository.findOneBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping("/orders/{id}")
    SalesOrder getSalesOrder(@PathVariable Integer id){
        return salesOrdenRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

}
