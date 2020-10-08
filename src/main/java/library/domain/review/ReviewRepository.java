package library.domain.review;

import library.repository.CrudRepository;

import java.util.List;

public class ReviewRepository extends CrudRepository<Review> {

    private static final String HIBERNATE_SELECT_QUERY = "from Book";

    public Review findOne(Integer id) {
        return super.findOne(id, Review.class);
    }

    List<Review> findAll() {
        System.out.println("----------------------List<Review> findAll() {");
        return super.findAll(HIBERNATE_SELECT_QUERY, Review.class);

    }
}