package entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authorId")
    private Integer Id;

    @Column(name = "authorName")
    private String name;

    @Column(name = "authorSurname")
    private String surName;

    @OneToMany(mappedBy = "authors")
    private Set<Book> books;

    public Integer getId() {
        return Id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
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