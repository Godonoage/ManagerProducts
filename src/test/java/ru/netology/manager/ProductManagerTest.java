package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book firstBook = new Book(1, "Финансист", 370, "Теодор Драйзер");
    private Book secondBook = new Book(2, "Гарри Поттер и филосовский камень", 1569, "Джоан Роулинг");
    private Book thirdBook = new Book(3, "1984", 325, "Джордж Оруэлл");
    private Smartphone firstSmart = new Smartphone(4, "Айфон 13 ПРО МАХ", 197990, "Самсунг");
    private Smartphone secondSmart = new Smartphone(5, "Айфон 13 ПРО МАХ", 197990, "Эйпл");
    private Smartphone thirdSmart = new Smartphone(6, "Редми Ноут 10", 25990, "Ксяоми");
    private Smartphone fourthSmart = new Smartphone(7, "Редми Ноут 9", 19990, "Ксяоми");

    @BeforeEach
    void add() {
        manager.add(firstBook);
        manager.add(secondBook);
        manager.add(thirdBook);
        manager.add(firstSmart);
        manager.add(secondSmart);
        manager.add(thirdSmart);
        manager.add(fourthSmart);
    }

    @Test
    void shouldFindAll() {
        Product[] expected = new Product[]{
                firstBook, secondBook, thirdBook, firstSmart, secondSmart, thirdSmart, fourthSmart
        };
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByAuthorBook() {
        Product[] expected = new Product[]{firstBook};
        Product[] actual = manager.searchBy("Теодор Драйзер");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByNameBook() {
        Product[] expected = new Product[]{secondBook};
        Product[] actual = manager.searchBy("Гарри Поттер и филосовский камень");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldFindByNameSmartphone() {
        Product[] expected = new Product[]{secondSmart};
        Product[] actual = manager.searchBy("Эйпл");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindByMakerSmartphone() {
        Product[] expected = new Product[]{firstSmart};
        Product[] actual = manager.searchBy("Самсунг");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSomeFindByNameSmartphone() {
        Product[] expected = new Product[]{firstSmart, secondSmart};
        Product[] actual = manager.searchBy("Айфон 13 ПРО МАХ");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSomeFindBySmartMaker() {
        Product[] expected = new Product[]{thirdSmart, fourthSmart};
        Product[] actual = manager.searchBy("Ксяоми");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFound() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Something");
        assertArrayEquals(expected, actual);
    }
    @Test
    void shouldReturnTrueOnBook() {
        assertTrue(firstBook.matches("Финансист"));
    }

    @Test
    void shouldReturnFalseOnBook() {
        assertFalse(firstBook.matches("О дивный новый мир"));
    }

    @Test
    void shouldReturnTrueOnSmartphone() {
        assertTrue(thirdSmart.matches("Редми Ноут 10"));
    }

    @Test
    void shouldReturnFalseOnSmartphone() {
        assertFalse(fourthSmart.matches("Хуавэй"));
    }


}