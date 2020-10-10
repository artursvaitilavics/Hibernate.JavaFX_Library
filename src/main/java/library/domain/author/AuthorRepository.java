package library.domain.author;
import library.repository.CrudRepository;

import javax.persistence.Query;
import java.util.List;

public class AuthorRepository extends CrudRepository<Author> {
    private static final String HIBERNATE_SELECT_QUERY = "from Author";

    public Author findOne(Integer id) {
        return super.findOne(id, Author.class);
    }

    public void delete(Integer id){
        runInTransaction(session -> {
            Query query = session.createQuery("delete from Author author where author.id = :authorId")
                    .setParameter("authorId", id);
            query.executeUpdate();
        });
    }

    public List<Author> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Author.class);
    }
}
