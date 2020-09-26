package entity;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Integer reviewId;

    @Column(name = "bookScore")
    private Integer bookScore;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;


    @Column(name = "reviewComment")
    private String reviewComment;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
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






