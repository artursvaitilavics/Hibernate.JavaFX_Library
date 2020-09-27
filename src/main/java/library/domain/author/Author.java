package library.domain.author;

import library.domain.book.Book;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorId")
    private Integer Id;

    @Column(name = "authorName")
    private String name;


    @Column(name = "authorSurname")
    private String surName;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}