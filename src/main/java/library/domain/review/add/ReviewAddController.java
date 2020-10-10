package library.domain.review.add;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import library.domain.book.Book;
import library.domain.book.BookRepository;
import library.domain.main.MainController;
import library.domain.review.Review;
import library.domain.review.ReviewController;
import library.domain.review.ReviewRepository;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReviewAddController implements Initializable {

    private final BookRepository bookRepository = new BookRepository();
    private final ReviewRepository reviewRepository = new ReviewRepository();




    //    @FXML
//    private JFXTextField id;
    @FXML
    private JFXTextField bookScore;
    @FXML
    private JFXTextField bookId;
    @FXML
    private JFXTextField reviewComment;

    @FXML
    private StackPane rootPane;

    private Review editable;

    private  Runnable closeDialogCallback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addPostOperationCallback(Runnable runnable){
        this.closeDialogCallback = runnable;
    }

    public void setEditable(Review review) {
        this.editable = review;
        this.bookScore.setText(String.valueOf(review.getBookScore()));
        this.bookId.setText(String.valueOf(review.getBook().getId()));
        this.reviewComment.setText(review.getReviewComment());
    }

    @FXML
    private void addReview(ActionEvent event)  {
        String bookSoreText = bookScore.getText();
        String bookId = this.bookId.getText();
        String reviewCommentText = reviewComment.getText();

        if (bookSoreText.isEmpty() || bookId.isEmpty() || reviewCommentText.isEmpty()) {
            //TODO show user alert that all fields have to be filled
            return;
        }


        Book book2 = bookRepository.findOne(Integer.parseInt(bookId));

        if (editable == null) {
            reviewRepository.save(new Review(Integer.parseInt(bookSoreText), book2, reviewCommentText));
        } else {
            Review review = reviewRepository.findOne(editable.getId());
            review.setBookScore(Integer.parseInt(bookSoreText));
            review.setBook(editable.getBook());
            review.setReviewComment(reviewCommentText);
            reviewRepository.merge(review);
        }

        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        bookScore.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
