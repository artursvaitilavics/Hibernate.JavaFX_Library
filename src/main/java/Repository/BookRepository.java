package Repository;

import entity.Book;

import java.util.List;

public class BookRepository extends CrudRepository<Book> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Book findOne(Integer id) {
        return super.findOne(id, Book.class);
    }

    List<Book> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Book.class);
    }
}