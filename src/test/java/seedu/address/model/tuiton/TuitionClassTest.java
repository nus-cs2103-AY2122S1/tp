package seedu.address.model.tuiton;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;



public class TuitionClassTest {

    @Test
    public void isSameTuitionClass() {
        TuitionClass tuitionClass = new TuitionClass(new ClassName("CS2103"),
                new ClassLimit(10), new Timeslot("Mon 14:00-16:00"), null, null);
        TuitionClass tuitionClass1 = new TuitionClass(new ClassName("CS2103"),
                new ClassLimit(10), new Timeslot("Tue 14:00-16:00"), null, null);
        TuitionClass tuitionClass2 = new TuitionClass(new ClassName("CS2105"),
                new ClassLimit(10), new Timeslot("Mon 15:00-16:00"), null, null);

        // same object -> returns true
        assertTrue(tuitionClass.isSameTuition(tuitionClass));

        // null -> returns false
        assertFalse(tuitionClass.isSameTuition(null));

        //same name different time slot -> return false
        assertFalse(tuitionClass.isSameTuition(tuitionClass1));

        //different name but time slots overlap -> return True
        assertTrue(tuitionClass.isSameTuition(tuitionClass2));
    }
}
