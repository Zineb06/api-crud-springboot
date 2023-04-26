package test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import model.Book;
import repo.BookRep;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = test.TestClass.class)
@DataJpaTest
public class TestClass {

    @Autowired
    private BookRep bookRepository;

    @Test
    public void testCreateBook() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void testReadBook() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);

        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        assertThat(optionalBook).isPresent();

        Book foundBook = optionalBook.get();
        assertThat(foundBook.getTitle()).isEqualTo("Test Book");
        assertThat(foundBook.getAuthor()).isEqualTo("Test Author");
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);

        book.setTitle("Updated Test Book");
        book.setAuthor("Updated Test Author");
        bookRepository.save(book);

        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        assertThat(optionalBook).isPresent();

        Book updatedBook = optionalBook.get();
        assertThat(updatedBook.getTitle()).isEqualTo("Updated Test Book");
        assertThat(updatedBook.getAuthor()).isEqualTo("Updated Test Author");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("Test Book", "Test Author");
        bookRepository.save(book);

        bookRepository.delete(book);

        Optional<Book> optionalBook = bookRepository.findById(book.getId());
        assertThat(optionalBook).isNotPresent();
    }

    @Test
    public void testListBooks() {
        Book book1 = new Book("Test Book 1", "Test Author 1");
        Book book2 = new Book("Test Book 2", "Test Author 2");
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getTitle()).isEqualTo("Test Book 1");
        assertThat(books.get(1).getTitle()).isEqualTo("Test Book 2");
    }

}
