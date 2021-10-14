package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.data.member.Address;
import seedu.address.model.data.member.Email;
import seedu.address.model.data.member.Member;
import seedu.address.model.task.Task;

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
    private Label id;
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
        id.setText(displayedIndex + ". ");
        name.setText(member.getName().fullName);
        phone.setText(member.getPhone().value);
        address.setText(member.getAddress().orElse(new Address("Not provided")).value);
        email.setText(member.getEmail().orElse(new Email("noprovide@email.com")).value);
        member.getPositions().stream()
                .sorted(Comparator.comparing(position -> position.positionName))
                .forEach(position -> positions.getChildren().add(new Label(position.positionName)));
        member.getTaskList().asUnmodifiableObservableList().stream()
                .sorted(Comparator.comparing(Task::getTaskName))
                .forEach(task -> tasks.getChildren().add(new Label(task.getTaskName())));
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
        return id.getText().equals(card.id.getText())
                && member.equals(card.member);
    }
}
