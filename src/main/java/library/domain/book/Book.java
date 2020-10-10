package library.domain.book;

import library.domain.review.Review;
import library.domain.author.Author;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer id;

    @Column(name = "bookTitle")
    private String title;

    @Column(name = "bookDescription")
    private String description;


    @OneToMany(mappedBy = "book", orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;


    public Book() {
    }

    public Book(String title, String description, Author author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getAuthorFullName() {
        return getAuthor().getSurName();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
