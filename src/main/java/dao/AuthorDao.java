package dao;

import configuration.DbSessionHolder;
import entity.Author;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDao {
    public void createAuthor(Author author) {
        Transaction transaction = null;
        try {
            Session session = DbSessionHolder.getInstance().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.save(author);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void updatePerson(Author author){
        Transaction transaction = null;
        try {
            Session session = DbSessionHolder.getInstance().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.update(author);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deletePerson(Author author){
        Transaction transaction = null;
        try {
            Session session = DbSessionHolder.getInstance().openSession();
            // start a transaction
            transaction = session.beginTransaction();
            // save the person object
            session.delete(author);
            // commit transaction
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public Author getAuthor(Integer id){
        try {
            Session session = DbSessionHolder.getInstance().openSession();
            Author author = session.find(Author.class, id);
            return author;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public List<Author> getPersons() {
        try {
            Session session = DbSessionHolder.getInstance().openSession();
            List<Author> authors = session.createQuery("from Person", Author.class).list();
            return authors;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
