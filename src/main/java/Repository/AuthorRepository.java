package Repository;

import entity.Author;

import java.util.List;

public class AuthorRepository extends CrudRepository<Author> {
    private static final String HIBERNATE_SELECT_QUERY = "from Author";

    public Author findOne(Integer id) {
        return super.findOne(id, Author.class);
    }


    public List<Author> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Author.class);
    }
}
