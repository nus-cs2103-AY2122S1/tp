package seedu.address.ui;

import seedu.address.model.student.Student;

public class MemberListCard extends StudentCard {

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public MemberListCard(Student student, int displayedIndex) {
        super(student, displayedIndex);
        if (student.hasGroupName() && super.tags.getChildren().size() > 0) {
            super.tags.getChildren().remove(0);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MemberListCard)) {
            return false;
        }

        // state check
        MemberListCard card = (MemberListCard) other;
        return student.equals(card.student);
    }
}
