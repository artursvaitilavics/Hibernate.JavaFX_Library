package library.domain.review;

import library.domain.author.Author;
import library.domain.book.Book;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Integer id;

    @Column(name = "bookScore")
    private Integer bookScore;

    @Column(name = "reviewComment")
    private String reviewComment;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;


    public Review() {
    }

    public Review(Integer bookScore, Book book, String reviewComment) {
        this.bookScore = bookScore;
        this.book = book;
        this.reviewComment = reviewComment;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookScore() {
        return bookScore;
    }

    public void setBookScore(Integer bookScore) {
        this.bookScore = bookScore;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }


}






