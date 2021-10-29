package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Tests that a {@code Student}'s {@code TutorialGroup} matches the given TutorialGroup.
 */
public class GroupMemberPredicate implements Predicate<Student> {
    private final TutorialGroup tutorialGroup;

    public GroupMemberPredicate(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    @Override
    public boolean test(Student student) {
        return student.getTutorialGroups().contains(tutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same instance
                || other instanceof GroupMemberPredicate // instanceof handles null
                && tutorialGroup.equals(((GroupMemberPredicate) other).getTutorialGroup()); // state check
    }
}
