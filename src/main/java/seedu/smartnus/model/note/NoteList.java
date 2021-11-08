package seedu.smartnus.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartnus.model.note.exceptions.DuplicateNoteException;
import seedu.smartnus.model.note.exceptions.NoteNotFoundException;

public class NoteList implements Iterable<Note> {
    private final ObservableList<Note> internalList = FXCollections.observableArrayList();
    private final ObservableList<Note> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a note to the list.
     */
    public void add(Note toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateNoteException();
        }
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent question as the given argument.
     */
    public boolean contains(Note toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameNote);
    }

    /**
     * Replaces the note {@code target} in the list with {@code editedNote}.
     * {@code target} must exist in the list.
     * The note identity of {@code editedNote} must not be the same as another existing question in the list.
     */
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NoteNotFoundException();
        }

        if (!target.isSameNote(editedNote) && contains(editedNote)) {
            throw new DuplicateNoteException();
        }

        internalList.set(index, editedNote);
    }

    public void setNotes(NoteList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNotes(List<Note> notes) {
        requireAllNonNull(notes);
        if (!notesAreUnique(notes)) {
            throw new DuplicateNoteException();
        }

        internalList.setAll(notes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Note> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Note> iterator() {
        return internalList.iterator();
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNoteList(List<Note> notes) {
        requireAllNonNull(notes);
        internalList.setAll(notes);
    }

    /**
     * Removes the equivalent question from the list.
     * The question must exist in the list.
     */
    public void remove(Note toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NoteNotFoundException();
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteList // instanceof handles nulls
                && internalList.equals(((NoteList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code notes} contains only unique questions.
     */
    private boolean notesAreUnique(List<Note> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                if (notes.get(i).isSameNote(notes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
