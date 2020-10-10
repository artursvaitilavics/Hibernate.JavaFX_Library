package library.domain.book;

import java.util.List;

import library.domain.review.ReviewRepository;
import library.repository.CrudRepository;

import javax.persistence.Query;


public class BookRepository extends CrudRepository<Book> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Book findOne(Integer id) {
        return super.findOne(id, Book.class);
    }

    List<Book> findAll() {
        return super.findAll(HIBERNATE_SELECT_QUERY, Book.class);
    }
    public void delete(Integer id){
        findOne(id).getReviews().forEach(review -> {
            ReviewRepository reviewRepository = new ReviewRepository();
            reviewRepository.delete(review);
        });

        runInTransaction(session -> {
            Query query = session.createQuery("delete from Book book where book.id = :bookId")
                    .setParameter("bookId", id);
            query.executeUpdate();
        });

    }
}