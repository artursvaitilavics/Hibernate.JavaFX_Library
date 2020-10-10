package library.domain.author;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.domain.author.add.AuthorAddController;
import library.domain.book.Book;
import library.domain.book.BookRepository;
import library.view.ViewLoader;

public class AuthorController implements Initializable {

    private final AuthorRepository authorRepository = new AuthorRepository();

    @FXML private TableView<Author> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    @FXML
    private void addAuthor(ActionEvent event) {
        ViewLoader.load(getClass().getResource("/ui/author/add_author.fxml"), "Add add_author");
    }

    @FXML
    private void editAuthor(ActionEvent event) {
        Author author = table.getSelectionModel().getSelectedItem();
        if (author == null) {
            return;
        }
        AuthorAddController controller = (AuthorAddController) ViewLoader.load(getClass()
                .getResource("/ui/author/add_author.fxml"), "Edit author");
        controller.setEditable(author);
    }

    @FXML
    private void deleteAuthor(ActionEvent event) {
        Author author = table.getSelectionModel().getSelectedItem();
        if (author == null) {
            return;
        }
        authorRepository.delete(author.getId());
        populateTable();
    }

    private void configureTable() {
        TableColumn<Author, Integer> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Author, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Author, String> column3 = new TableColumn<>("Surname");
        column3.setCellValueFactory(new PropertyValueFactory<>("surName"));


        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
    }

    private void populateTable() {
        ObservableList<Author> list = FXCollections.observableArrayList();
        list.addAll(authorRepository.findAll());
        table.setItems(list);
    }
}
