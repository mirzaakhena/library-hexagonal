package io.wkrzywiec.hexagonal.library.inventory.infrastructure;

import io.wkrzywiec.hexagonal.library.TestData;
import io.wkrzywiec.hexagonal.library.inventory.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class InventoryDatabaseAdapterITCase {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private InventoryDatabaseAdapter database;

    @BeforeEach
    public void init() {
        database = new InventoryDatabaseAdapter(bookRepository);
    }

    @Test
    @DisplayName("Save new book in database")
    public void givenBook_whenSaveIt_thenBookIsSaved() {
        //given
        Book homoDeusBook = TestData.homoDeusBook();

        //when
        Book savedBook = database.save(homoDeusBook);

        //then
        Long savedBookId = jdbcTemplate.queryForObject(
                "SELECT id FROM book WHERE id = ?",
                Long.class,
                savedBook.getIdAsLong());

        assertEquals(savedBook.getIdAsLong(), savedBookId);
    }
}
