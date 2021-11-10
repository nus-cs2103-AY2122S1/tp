package tutoraid.model.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import tutoraid.commons.util.AppUtil;
import tutoraid.model.student.exceptions.EmptyProgressListException;

/**
 * Represents a student's list of progress entries in TutorAid. Keeps track of at most 10 Progress objects
 * per ProgressList. Adding a new Progress object will remove the oldest Progress object in this list before
 * adding the new Progress object.
 */
public class ProgressList {

    public static final String MESSAGE_CONSTRAINTS =
            "ProgressList constructor either takes in no argument "
            + "or takes in a String ArrayList of size no more than 10";

    public final ArrayList<Progress> progressList;

    /**
     * Constructs a {@code ProgressList}.
     */
    public ProgressList() {
        progressList = new ArrayList<>();
    }

    /**
     * Constructs a {@code ProgressList}.
     *
     * @param progressStringArrayList an arraylist of progress descriptions of the student
     */
    public ProgressList(ArrayList<String> progressStringArrayList) {
        requireNonNull(progressStringArrayList);
        AppUtil.checkArgument(isValidProgressList(progressStringArrayList), MESSAGE_CONSTRAINTS);

        this.progressList = new ArrayList<>();

        for (String progressString : progressStringArrayList) {
            Progress currentProgress = new Progress(progressString);
            addProgress(currentProgress);
        }
    }

    /**
     * Checks if a given string ArrayList is a valid list of progress descriptions.
     *
     * @param progressListInStringArrayList an ArrayList of strings
     * @return true if all elements are valid progress description, false otherwise
     */
    public static boolean isValidProgressList(ArrayList<String> progressListInStringArrayList) {
        if (progressListInStringArrayList.size() > 10) {
            return false;
        }

        for (int i = 0; i < progressListInStringArrayList.size(); i++) {
            if (progressListInStringArrayList.get(i) == null) {
                return false;
            }
        }

        for (int j = 0; j < progressListInStringArrayList.size(); j++) {
            if (!Progress.isValidProgress(progressListInStringArrayList.get(j))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds a new progress entry to this list.
     *
     * @param progressToAdd the progress entry to be added
     */
    public void addProgress(Progress progressToAdd) {
        requireNonNull(progressToAdd);

        if (progressList.size() == 10) {
            progressList.remove(0);
        }

        progressList.add(progressToAdd);

    }

    /**
     * Deletes the latest progress entry from this list. The list of progress entries must not be empty.
     */
    public Progress deleteLatestProgress() {
        if (progressList.size() == 0) {
            throw new EmptyProgressListException();
        }

        return progressList.remove(progressList.size() - 1);
    }

    /**
     * Returns the latest progress entry if there is at least one progress entry in this list,
     * else returns an EMPTY_PROGRESS.
     */
    public Progress getLatestProgress() {
        return progressList.size() == 0
                ? Progress.getEmptyProgress()
                : progressList.get(progressList.size() - 1);
    }

    /**
     * Returns a string Array that contains all the progress descriptions in the correct order.
     */
    public ArrayList<String> getAllProgressAsStringArrayList() {
        ArrayList<String> allProgressAsStringArrayList = new ArrayList<>();
        for (int i = 0; i < progressList.size(); i++) {
            String currentProgressDescription = progressList.get(i).toString();
            allProgressAsStringArrayList.add(currentProgressDescription);
        }
        return allProgressAsStringArrayList;
    }

    /**
     * Checks if this list of progress entries is empty.
     *
     * @return True if there is no progress entry in the list.
     */
    public boolean isProgressListEmpty() {
        return progressList.size() == 0;
    }

    @Override
    public String toString() {
        if (progressList.size() == 0) {
            return "No Progress";
        }
        String allProgress = "";

        for (int i = 1; i < progressList.size() + 1; i++) {
            String progressToPrint = progressList.get(i - 1).toString();
            allProgress = allProgress + "\t" + i + ". " + progressToPrint + "\n";
        }
        return "\n" + allProgress;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProgressList // instanceof handles nulls
                && this.progressList.equals(((ProgressList) other).progressList)); // state check
    }

    @Override
    public int hashCode() {
        return progressList.hashCode();
    }
}
