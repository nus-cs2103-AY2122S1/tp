package seedu.address.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Records a series of states of Internship as the data inside gets modified
 * Extends Internship with an undo/redo history, which is stored internally as
 * an internshipStateList and currentStatePointer
 */
public class VersionedInternship extends Internship {

    private List<Internship> internshipStateList = new ArrayList<>();

    private int currentStatePointer;

    public VersionedInternship() {};

    /**
     * Constructs a versionedInternship to keep track of a series of states displayed by Internship
     * Initialized by ModelManager to contain a single state
     * Initializes the currentStatePointer to 0, i.e., pointing to the single start state
     * @param initialInternshipList the version of Internship retrieved from the Internship storage
     */
    public VersionedInternship(Internship initialInternshipList) {
        internshipStateList.add(initialInternshipList);
        currentStatePointer = 0;
    }

    /**
     * Commits the changes to the internalStateList of versionInternship
     * This method is only called when the user command had actually made some changes to the current
     * Internship data
     * @param currentVersion the new state of the Internship Application list as a result of change
     */
    public void commit(ReadOnlyInternship currentVersion) {
        if (currentStatePointer != internshipStateList.size() - 1) {
            purgeRedundantStates();
        }
        Internship copied = new Internship(currentVersion); //make a copy of the current version
        internshipStateList.add(copied); //add the copied version to the state list
        currentStatePointer++;
    }

    /**
     * Undo the most recent change to the Internship data
     * @return The previous state of Internship as retrieved from the internalStateList
     */
    public ReadOnlyInternship undo() {
        currentStatePointer--;
        return getCurrentVersion();
    }

    /**
     * Determines whether the undo action can be done
     * Undo action is not allowed if the currentStatePointer is at 0, i.e., no further history to be recovered
     * @return true if the undo action can be done, false otherwise
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Redo the most recent change caused by the undo action
     * @return The next state of Internship as retrieved from the internalStateList
     */
    public ReadOnlyInternship redo() {
        currentStatePointer++;
        return getCurrentVersion();
    }


    /**
     * Determines whether the redo action can be done
     * Redo action is not allowed if the currentStatePointer is at the end of the internalStateList,
     * i.e., no previous undo action to be 'redone'
     * @return true if the redo action can be done, false otherwise
     */
    public boolean canRedo() {
        return currentStatePointer < internshipStateList.size() - 1;
    }

    /**
     * Returns the current version of Internship
     * It is retrieved from the state pointed to by the currentStatePointer
     * @return the current version of Internship,
     */
    public ReadOnlyInternship getCurrentVersion() {
        return internshipStateList.get(currentStatePointer);
    }

    /** Clears the redundant states in the internalStateList
     * Happens when there's an attempt to make a new commit to the internalStateList,
     * but the currentStatePointer is not pointing to the end of the list
     * This action is needed under this situation because it no longer makes sense to redo the
     * previous commands once a new commit is made, so all the states after the currentStatePointer
     * becomes 'redundant' which are to be purged
     */
    public void purgeRedundantStates() {
        internshipStateList.subList(currentStatePointer + 1, internshipStateList.size()).clear();
    }

    /**
     * Shifts the current state pointer by a certain amount
     * Used for testing purposes only.
     * @param amount the amount by which the current state pointer is shifted
     */
    public void shiftCurrentStatePointer(int amount) {
        currentStatePointer += amount;
    }

}
