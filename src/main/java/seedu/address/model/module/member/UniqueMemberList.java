package seedu.address.model.module.member;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.member.exceptions.DuplicateMemberException;
import seedu.address.model.module.member.exceptions.MemberNotFoundException;

/**
 * A list of members that enforces uniqueness between its elements and does not allow nulls.
 * A member is considered unique by comparing using {@code Member#isSameType(Member)}. As such, adding and updating of
 * members uses Member#isSameType(Member) for equality so as to ensure that the member being added or updated is
 * unique in terms of identity in the UniqueMemberList. However, the removal of a member uses Member#equals(Object) so
 * as to ensure that the member with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Member#isSameType(Module)
 */
public class UniqueMemberList implements Iterable<Member> {

    private final ObservableList<Member> internalList = FXCollections.observableArrayList();
    private final ObservableList<Member> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent member as the given argument.
     */
    public boolean contains(Member toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameType);
    }

    /**
     * Adds a member to the list.
     * The member must not already exist in the list.
     */
    public void add(Member toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMemberException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the list.
     * The member identity of {@code editedMember} must not be the same as another existing member in the list.
     */
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MemberNotFoundException();
        }

        if (!target.isSameType(editedMember) && contains(editedMember)) {
            throw new DuplicateMemberException();
        }

        internalList.set(index, editedMember);
    }

    /**
     * Removes the equivalent member from the list.
     * The member must exist in the list.
     */
    public void remove(Member toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MemberNotFoundException();
        }
    }

    public void setMembers(UniqueMemberList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code members}.
     * {@code members} must not contain duplicate members.
     */
    public void setMembers(List<Member> members) {
        requireAllNonNull(members);
        if (!membersAreUnique(members)) {
            throw new DuplicateMemberException();
        }

        internalList.setAll(members);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Member> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Member> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMemberList // instanceof handles nulls
                        && internalList.equals(((UniqueMemberList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code members} contains only unique members.
     */
    private boolean membersAreUnique(List<Member> members) {
        for (int i = 0; i < members.size() - 1; i++) {
            for (int j = i + 1; j < members.size(); j++) {
                if (members.get(i).isSameType(members.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
