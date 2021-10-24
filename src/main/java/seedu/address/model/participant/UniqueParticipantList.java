package seedu.address.model.participant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.participant.exceptions.DuplicateParticipantException;
import seedu.address.model.participant.exceptions.ParticipantNotFoundException;

/**
 * A list of participants that enforces uniqueness between its elements and does not allow nulls.
 * A participant is considered unique by comparing using {@code Participant#isSameParticipant(Participant)}.
 * As such, adding and updating of participants uses Participant#isSameParticipant(Participant) for equality so
 * as to ensure that the participant being added or updated is unique in terms of identity in the
 * UniqueParticipantList. However, the removal of a participant uses Participant#equals(Object) so
 * as to ensure that the participant with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Participant#isSameParticipant(Participant)
 */
public class UniqueParticipantList implements Iterable<Participant> {

    private final ObservableList<Participant> internalList = FXCollections.observableList(
            new ArrayList<>(), (Participant p) -> new Observable[]{p.getNextOfKins()});
    private final ObservableList<Participant> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent participant as the given argument.
     */
    public boolean contains(Participant toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameParticipant);
    }

    /**
     * Adds a participant to the list.
     * The participant must not already exist in the list.
     */
    public void add(Participant toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateParticipantException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the participant {@code target} in the list with {@code editedParticipant}.
     * {@code target} must exist in the list.
     * The participant identity of {@code editedParticipant} must not be the same
     * as another existing participant in the list.
     */
    public void setParticipant(Participant target, Participant editedParticipant) {
        requireAllNonNull(target, editedParticipant);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ParticipantNotFoundException();
        }

        if (!target.isSameParticipant(editedParticipant) && contains(editedParticipant)) {
            throw new DuplicateParticipantException();
        }

        internalList.set(index, editedParticipant);
    }

    /**
     * Removes the equivalent participant from the list.
     * The participant must exist in the list.
     */
    public void remove(Participant toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ParticipantNotFoundException();
        }
    }

    public void setParticipants(UniqueParticipantList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code participants}.
     * {@code participants} must not contain duplicate participants.
     */
    public void setParticipants(List<Participant> participants) {
        requireAllNonNull(participants);
        if (!participantsAreUnique(participants)) {
            throw new DuplicateParticipantException();
        }

        internalList.setAll(participants);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Participant> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Participant> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueParticipantList// instanceof handles nulls
            && internalList.equals(((UniqueParticipantList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code participants} contains only unique participants.
     */
    private boolean participantsAreUnique(List<Participant> participants) {
        for (int i = 0; i < participants.size() - 1; i++) {
            for (int j = i + 1; j < participants.size(); j++) {
                if (participants.get(i).isSameParticipant(participants.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
