package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.summary.Summary;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class SelectedPersonCard extends UiPart<Region> {

    private static final String FXML = "SelectedPersonCard.fxml";
    private final Logger logger = LogsCenter.getLogger(SelectedPersonCard.class);

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private Person person;
    private Summary summary;

    @FXML
    private HBox cardPane;
    @FXML
    private Label category;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label review;
    @FXML
    private FlowPane tags;
    @FXML
    private Label rating;
    @FXML
    private PieChart pieChartRating; // summarises category codes
    @FXML
    private PieChart pieChartCategory;
    @FXML
    private Label totalContacts;
    @FXML
    private Label summaryHeader;

    /**
     * Creates an empty {@code PersonCode}.
     */
    public SelectedPersonCard() {
        super(FXML);
        this.person = null;
    }

    /**
     * Sets the person of Selected Person with the given {@code Person}.
     */
    public void updatePerson(Person person) {
        this.person = person;
        logger.info("Updated SelectedPersonCard");
    }

    /**
     * Sets the Summary of the AddressBook.
     */
    public void updateSummary(Summary summary) {
        this.summary = summary;
    }


    /**
     * Sets the details of Selected Person with the given {@code Person}.
     */
    public void setPersonDetails() {
        if (person == null) {

        } else {
            setPersonVisible();
            category.setText("Category: " + person.getCategoryCode().getFullCode());
            name.setText(person.getName().fullName);
            phone.setText("Phone: " + person.getPhone().value);
            address.setText("Address: " + person.getAddress().value);
            review.setText("Review: " + person.getReview().value);
            email.setText("Email: " + person.getEmail().value);
            tags.getChildren().clear();
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
            rating.setText(person.getRating().value + "\u2B50");

            address.setTextAlignment(TextAlignment.JUSTIFY);
            review.setTextAlignment(TextAlignment.JUSTIFY);
            logger.info("Person details set " + person);
        }
    }

    /**
     * Sets the Summary of the AddressBook
     */
    public void setSummary() {
        setSummaryVisible();

        summaryHeader.setText("Your Contact List Statistics:");

        ObservableList<PieChart.Data> pieChartCategoryData = summary.getPercentageCategoryGui();
        pieChartCategory.setData(pieChartCategoryData);
        pieChartCategory.setTitle("Category");

        ObservableList<PieChart.Data> pieChartRatingData = summary.getPercentageRatingsGui();
        pieChartRating.setData(pieChartRatingData);
        pieChartRating.setTitle("Rating");

        totalContacts.setText(summary.getNumberOfContactsGui());

        logger.info("Summary Set");
    }

    private void setPersonVisible() {
        category.setVisible(true);
        name.setVisible(true);
        phone.setVisible(true);
        address.setVisible(true);
        review.setVisible(true);
        email.setVisible(true);
        tags.setVisible(true);
        rating.setVisible(true);
        pieChartRating.setVisible(false);
        pieChartCategory.setVisible(false);
        totalContacts.setVisible(false);
        summaryHeader.setVisible(false);
    }

    private void setSummaryVisible() {
        category.setVisible(false);
        name.setVisible(false);
        phone.setVisible(false);
        address.setVisible(false);
        review.setVisible(false);
        email.setVisible(false);
        tags.setVisible(false);
        rating.setVisible(false);
        pieChartRating.setVisible(true);
        pieChartCategory.setVisible(true);
        totalContacts.setVisible(true);
        summaryHeader.setVisible(true);
    }

    /**
     * Returns the selected person.
     */
    public Person getPerson() {
        return person;
    }
}
