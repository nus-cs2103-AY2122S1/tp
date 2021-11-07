package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.member.Member;

/**
 * A UI component that displays information of a {@code Member}.
 */
public class MemberCard extends UiPart<Region> {

    private static final String FXML = "MemberListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Member member;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane positions;
    @FXML
    private FlowPane tasks;

    /**
     * Creates a {@code MemberCode} with the given {@code Member} and index to display.
     */
    public MemberCard(Member member, int displayedIndex) {
        super(FXML);
        this.member = member;
        index.setText(displayedIndex + ". ");
        name.setText(member.getName().fullName);
        phone.setText("Phone: " + member.getPhone().value);
        member.getAddress().ifPresentOrElse(a -> {
            address.setText("Address: " + a.value);
        }, () -> address.setText("Address: NIL"));
        member.getEmail().ifPresentOrElse(e -> {
            email.setText("Email: " + e.value);
        }, () -> email.setText("Email: NIL"));
        member.getPositions().stream()
                .sorted(Comparator.comparing(position -> position.positionName))
                .forEach(position -> {
                    Label positionLabel = new Label(position.positionName);
                    positionLabel.setMaxWidth(100);
                    positionLabel.setWrapText(true);
                    positions.getChildren().add(positionLabel);
                });
        member.getTaskList().asUnmodifiableObservableList().stream()
                .sorted(Comparator.comparing(task -> task.getName().toString()))
                .forEach(task -> {
                    Label taskLabel = new Label(task.getName().toString());
                    if (task.isDone()) {
                        taskLabel.setStyle("-fx-background-color: #3f7318");
                    } else {
                        taskLabel.setStyle("-fx-background-color: #7c0236");
                    }
                    taskLabel.setMaxWidth(100);
                    taskLabel.setWrapText(true);
                    tasks.getChildren().add(taskLabel);
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberCard)) {
            return false;
        }

        // state check
        MemberCard card = (MemberCard) other;
        return index.getText().equals(card.index.getText())
                && member.equals(card.member);
    }
}
