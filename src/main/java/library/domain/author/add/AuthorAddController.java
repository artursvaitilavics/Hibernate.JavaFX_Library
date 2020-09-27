package library.domain.author.add;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.domain.author.Author;
import library.domain.author.AuthorRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthorAddController implements Initializable {

    private final AuthorRepository authorRepository = new AuthorRepository();

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField surName;

    @FXML private StackPane rootPane;
    private Integer authorId;

    private Author editable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("AUTHOR ADD CONTROLLER");
    }

    public void setEditable(Author author) {
        this.editable = author;
        this.name.setText(author.getName());
        this.surName.setText(author.getSurName());
//        this.authorId.setText(author.getAuthor().getId().toString());
    }

    @FXML
    private void addAuthor(ActionEvent event) {
        String authorName = this.name.getText();
        String authorSurName = this.surName.getText();

//        No good, if working, fix bellow
        authorRepository.save(new Author(authorName, authorSurName));


        if (authorName.isEmpty() || authorSurName.isEmpty() /*|| bookAuthorId.isEmpty()*/) {
            //TODO show user alert that all fields have to be filled
            System.out.println("Author fields are empty");
            return;
        }

//        Integer authorId = Integer.parseInt(bookAuthorId);
//        Author author = authorRepository.findOne(authorId);
//        if (author == null) {
//            //TODO author with such ID doesn't exist, display error to user!
//            return;
//        }

        if (editable == null) {
            authorRepository.save(new Author(authorName, authorSurName));
        } else {
            Author author1 = authorRepository.findOne(editable.getId());
            author1.setName(authorName);
            author1.setSurName(authorSurName);
//            author1.setAuthor(author);
            authorRepository.update(author1);
        }
        clearEntries();
        closeStage();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        name.clear();
        surName.clear();
//        authorId.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
