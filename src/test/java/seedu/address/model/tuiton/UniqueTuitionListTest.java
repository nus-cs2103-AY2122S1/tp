package seedu.address.model.tuiton;

import org.junit.jupiter.api.Test;
import seedu.address.model.tuition.*;
import seedu.address.model.tuition.exceptions.DuplicateTuitionException;
import seedu.address.model.tuition.exceptions.TuitionNotFoundException;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class UniqueTuitionListTest {
    private final UniqueTuitionList uniqueTuitionList = new UniqueTuitionList();
    TuitionClass tuitionClass = new TuitionClass(new ClassName("CS2103"),
            new ClassLimit(10), new Timeslot("Mon 14:00-16:00"), null, null);
    TuitionClass tuitionClass1 = new TuitionClass(new ClassName("CS2103"),
            new ClassLimit(10), new Timeslot("Tue 14:00-16:00"), null, null);
    TuitionClass tuitionClass2 = new TuitionClass(new ClassName("CS2105"),
            new ClassLimit(10), new Timeslot("Mon 15:00-16:00"), null, null);

    @Test
    public void contains_nullTuition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.contains(null));
    }

    @Test
    public void contains_TuitionNotInList_returnsFalse() {
        assertFalse(uniqueTuitionList.contains(tuitionClass));
    }

    @Test
    public void contains_TuitionInList_returnsTrue() {
        uniqueTuitionList.add(tuitionClass);
        assertTrue(uniqueTuitionList.contains(tuitionClass));
    }


    @Test
    public void add_nullTuition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.add(null));
    }

    @Test
    public void add_duplicateTuition_throwsDuplicateTuitionException() {
        uniqueTuitionList.add(tuitionClass);
        assertThrows(DuplicateTuitionException.class, () -> uniqueTuitionList.add(tuitionClass));
    }



    @Test
    public void remove_nullTuition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.remove(null));
    }

    @Test
    public void remove_TuitionDoesNotExist_throwsTuitionNotFoundException() {
        assertThrows(TuitionNotFoundException.class, () -> uniqueTuitionList.remove(tuitionClass));
    }



    @Test
    public void setTuitions_nullUniqueTuitionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setTuitions((UniqueTuitionList) null));
    }
    

    @Test
    public void setTuitions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTuitionList.setTuitions((List<TuitionClass>) null));
    }


    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTuitionList.asUnmodifiableObservableList().remove(0));
    }
}
