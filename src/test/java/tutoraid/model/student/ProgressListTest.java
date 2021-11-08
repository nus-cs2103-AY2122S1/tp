package tutoraid.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class ProgressListTest {

    private final ProgressList progressList = new ProgressList();

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Progress(null));
    }

    @Test
    public void isValidProgressList() {
        // null progress list
        Assert.assertThrows(NullPointerException.class, () -> ProgressList.isValidProgressList(null));

        // invalid progress list
        ArrayList<String> moreThanTenElements = new ArrayList<>();
        for (int i = 0; i < 11; i++) { // too many elements
            moreThanTenElements.add("abc"); // valid progress description
        }
        assertFalse(ProgressList.isValidProgressList(moreThanTenElements));

        ArrayList<String> nullElements = new ArrayList<>();
        nullElements.add(null);
        assertFalse(ProgressList.isValidProgressList(nullElements));

        ArrayList<String> invalidProgress = new ArrayList<>();
        invalidProgress.add(" "); // invalid progress description
        assertFalse(ProgressList.isValidProgressList(invalidProgress));

        // valid progress list
        ArrayList<String> lessThanTenElements = new ArrayList<>();
        for (int i = 0; i < 9; i++) { // less than 10 elements
            lessThanTenElements.add("abc"); // valid progress description
        }
        assertTrue(ProgressList.isValidProgressList(lessThanTenElements));
        ArrayList<String> tenElements = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // 10 elements
            tenElements.add("abc"); // valid progress description
        }
        assertTrue(ProgressList.isValidProgressList(tenElements));
    }

    @Test
    public void addProgress_nullProgress_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> progressList.addProgress(null));
    }

    @Test
    public void addProgress_validProgress_addsNewProgress() {
        ArrayList<String> updatedProgressListDescriptions = new ArrayList<>();
        ProgressList updatedProgressList = new ProgressList();

        for (int i = 0; i < 10; i++) {
            updatedProgressListDescriptions.add(String.valueOf(i));
            updatedProgressList.addProgress(new Progress(String.valueOf(i)));
        }

        assertTrue(updatedProgressList.equals(new ProgressList(updatedProgressListDescriptions)));
    }

    @Test
    public void addProgress_fullList_addsNewProgressAndReplaceTheLatestOne() {
        ArrayList<String> oldProgressListDescriptions = new ArrayList<>();
        ArrayList<String> updatedProgressListDescriptions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            oldProgressListDescriptions.add(String.valueOf(i));
            updatedProgressListDescriptions.add(String.valueOf(i + 1));
        }

        ProgressList oldProgressList = new ProgressList(oldProgressListDescriptions);
        ProgressList updatedProgressList = new ProgressList(updatedProgressListDescriptions);

        oldProgressList.addProgress(new Progress(String.valueOf(10)));

        assertTrue(oldProgressList.equals(updatedProgressList));
    }

    @Test
    public void deleteLatestProgress_listNotEmpty_deletesLatestProgress() {
        ArrayList<String> updatedProgressListDescriptions = new ArrayList<>();
        ProgressList updatedProgressList = new ProgressList();

        for (int i = 0; i < 10; i++) {
            updatedProgressListDescriptions.add(String.valueOf(i));
            updatedProgressList.addProgress(new Progress(String.valueOf(i)));
        }

        updatedProgressListDescriptions.remove(updatedProgressListDescriptions.size() - 1);
        updatedProgressList.deleteLatestProgress();

        assertTrue(updatedProgressList.equals(new ProgressList(updatedProgressListDescriptions)));
    }

    @Test
    public void getLatestProgress_listNotEmpty_returnsLatestProgress() {
        ArrayList<String> updatedProgressListDescriptions = new ArrayList<>();
        ProgressList updatedProgressList = new ProgressList();

        for (int i = 0; i < 10; i++) {
            updatedProgressListDescriptions.add(String.valueOf(i));
            updatedProgressList.addProgress(new Progress(String.valueOf(i)));
        }

        updatedProgressListDescriptions.remove(updatedProgressListDescriptions.size() - 1);
        updatedProgressList.deleteLatestProgress();

        Progress progressOne = updatedProgressList.getLatestProgress();
        Progress progressTwo = (new ProgressList(updatedProgressListDescriptions)).getLatestProgress();

        assertTrue(progressOne.equals(progressTwo));
    }

    @Test
    public void getAllProgressAsStringArrayList() {
        ArrayList<String> updatedProgressListDescriptions = new ArrayList<>();
        ProgressList updatedProgressList = new ProgressList();

        for (int i = 0; i < 10; i++) {
            updatedProgressListDescriptions.add(String.valueOf(i));
            updatedProgressList.addProgress(new Progress(String.valueOf(i)));
        }

        assertTrue(updatedProgressListDescriptions.equals(
                updatedProgressList.getAllProgressAsStringArrayList()));
    }

    @Test
    public void equal() {
        ArrayList<String> progressListOneDescriptions = new ArrayList<>();
        ArrayList<String> progressListTwoDescriptions = new ArrayList<>();
        ArrayList<String> progressListThreeDescriptions = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            progressListOneDescriptions.add(String.valueOf(i));
            progressListTwoDescriptions.add(String.valueOf(i));
            progressListThreeDescriptions.add(String.valueOf(9 - i));
        }

        ProgressList amyProgressList = new ProgressList(progressListOneDescriptions);
        ProgressList bobProgressList = new ProgressList(progressListTwoDescriptions); // same as amy
        ProgressList charlesProgressList = new ProgressList(progressListThreeDescriptions); // different from amy

        // same object -> returns true
        assertTrue(amyProgressList.equals(amyProgressList));

        // same values -> returns true
        assertTrue(amyProgressList.equals(bobProgressList));

        // different types -> returns false
        assertFalse(amyProgressList.equals(true));

        // null -> returns false
        assertFalse(amyProgressList.equals(null));

        // different progress -> returns false
        assertFalse(amyProgressList.equals(charlesProgressList));
    }


}
