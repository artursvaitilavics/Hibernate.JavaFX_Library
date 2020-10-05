package library.domain.review.add;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.domain.author.Author;
import library.domain.author.AuthorRepository;
import library.domain.book.Book;
import library.domain.book.BookRepository;
import library.domain.review.Review;
import library.domain.review.ReviewRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewAddController implements Initializable {

    private final BookRepository bookRepository = new BookRepository();
    private final ReviewRepository reviewRepository = new ReviewRepository();

    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField bookScore;
    @FXML
    private JFXTextField book;
    @FXML
    private JFXTextField reviewComment;

    @FXML
    private StackPane rootPane;

    private Review editable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setEditable(Review review) {
        this.editable = review;
        this.id.setText(review.getId().toString());
        this.bookScore.setText(review.getBookScore().toString());
        this.book.setText(review.getBook().getTitle());
        this.reviewComment.setText(review.getReviewComment());
    }

    @FXML
    private void addReview(ActionEvent event) {
        String reviewIdText = id.getText();
        String bookSoreText = bookScore.getText();
        String bookTitle = book.getText();
        String reviewCommentText = reviewComment.getText();

        if (reviewIdText.isEmpty() || bookSoreText.isEmpty() || bookTitle.isEmpty() || reviewCommentText.isEmpty()) {
//            //TODO show user alert that all fields have to be filled
            return;
        }


        Integer bookId = Integer.parseInt(book.getId());
        Book book1 = bookRepository.findOne(bookId);
        if (editable == null) {
            reviewRepository.save(new Review(Integer.parseInt(bookSoreText), book1, reviewCommentText));
        } else {
            Review review = reviewRepository.findOne(editable.getId());
            review.setBookScore(Integer.parseInt(bookSoreText));
            review.setBook(book1);
            bookRepository.update(book1);
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
        id.clear();
        bookScore.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
