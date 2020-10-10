package library.domain.review;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.domain.review.add.ReviewAddController;
import library.view.ViewLoader;

public class ReviewController implements Initializable {

    private final ReviewRepository reviewRepository = new ReviewRepository();

    @FXML
    private TableView<Review> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    @FXML
    private void addReview(ActionEvent event) {
        ReviewAddController controller = (ReviewAddController) ViewLoader
        .load(getClass().getResource("/ui/review/add_review.fxml"), "Add review");
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void editReview(ActionEvent event) {
        Review review = table.getSelectionModel().getSelectedItem();
        if (review == null) {
            return;
        }
        ReviewAddController controller = (ReviewAddController) ViewLoader.load(getClass()
                .getResource("/ui/review/add_review.fxml"), "Edit review");
        controller.setEditable(review);
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void deleteReview(ActionEvent event) {
        Review review = table.getSelectionModel().getSelectedItem();
        if (review == null) {
            return;
        }
        reviewRepository.delete(review);
        populateTable();
    }

    private void configureTable() {

        TableColumn<Review, Long> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Review, String> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("bookScore"));

        TableColumn<Review, String> column3 = new TableColumn<>("Book");
        column3.setCellValueFactory(new PropertyValueFactory<>("book"));

        TableColumn<Review, String> column4 = new TableColumn<>("Comment");
        column4.setCellValueFactory(new PropertyValueFactory<>("reviewComment"));


        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);

    }

    public void populateTable() {
        ObservableList<Review> list = FXCollections.observableArrayList();
        list.addAll(reviewRepository.findAll());
        table.setItems(list);
    }


}
