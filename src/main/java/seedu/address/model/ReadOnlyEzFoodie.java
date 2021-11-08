package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.member.Member;

/**
 * Unmodifiable view of an ezFoodie.
 */
public interface ReadOnlyEzFoodie {

    /**
     * Returns an unmodifiable view of the members list.
     * This list will not contain any duplicate members.
     */
    ObservableList<Member> getMemberList();

}
