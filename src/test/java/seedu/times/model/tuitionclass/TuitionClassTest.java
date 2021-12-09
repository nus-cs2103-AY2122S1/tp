package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_STUDENTLIST_IB_MATHS;
import static seedu.times.testutil.TypicalTimestable.IB_MATHS;
import static seedu.times.testutil.TypicalTimestable.IB_PHYSICS;

import org.junit.jupiter.api.Test;

import seedu.times.testutil.TuitionClassBuilder;

public class TuitionClassTest {
    private ClassName className = new ClassName("test");
    private Location location = new Location("test");
    private Rate rate = new Rate("50");

    @Test
    public void isSameClass() {
        //same object -> returns true
        assertTrue(IB_PHYSICS.isSameClass(IB_PHYSICS));

        //null -> returns false
        assertFalse(IB_PHYSICS.isSameClass(null));

        //same class timing, all other attribtues different -> returns true
        TuitionClass editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withClassName(VALID_CLASSNAME_IB_MATHS)
                .withLocation(VALID_LOCATION_IB_MATHS).withRate(VALID_RATE_IB_MATHS)
                .withStudentList(VALID_STUDENTLIST_IB_MATHS).build();
        assertTrue(IB_PHYSICS.isSameClass(editedIbPhysics));

        //different class timing, all other attributes same -> returns false
        editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_MATHS).build();
        assertFalse(IB_PHYSICS.isSameClass(editedIbPhysics));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TuitionClass iBPhysicsCopy = new TuitionClassBuilder(IB_PHYSICS).build();
        assertTrue(IB_PHYSICS.equals(iBPhysicsCopy));

        // same object -> returns true
        assertTrue(IB_PHYSICS.equals(IB_PHYSICS));

        // null -> returns false
        assertFalse(IB_PHYSICS.equals(null));

        // different type -> returns false
        assertFalse(IB_PHYSICS.equals(5));

        // different classes -> returns false
        assertFalse(IB_PHYSICS.equals(IB_MATHS));

        // different class name -> returns false
        TuitionClass editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withClassName(VALID_CLASSNAME_IB_MATHS)
                .build();
        assertFalse(IB_PHYSICS.equals(editedIbPhysics));

        // different class timing -> returns false
        editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_MATHS)
                .build();
        assertFalse(IB_PHYSICS.equals(editedIbPhysics));

        // different rate -> returns false
        editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withRate(VALID_RATE_IB_MATHS)
                .build();
        assertFalse(IB_PHYSICS.equals(editedIbPhysics));

        // different location -> returns false
        editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withLocation(VALID_LOCATION_IB_MATHS)
                .build();
        assertFalse(IB_PHYSICS.equals(editedIbPhysics));

        // different student list -> returns false
        editedIbPhysics = new TuitionClassBuilder(IB_PHYSICS).withStudentList(VALID_STUDENTLIST_IB_MATHS)
                .build();
        assertFalse(IB_PHYSICS.equals(editedIbPhysics));
    }


    //// isOverlapping
    @Test
    public void isOverlapping_overlappingTiming_returnsTrue() {
        TuitionClass class1 = new TuitionClass(className, new ClassTiming("MON 16:00-18:00"), location, rate);
        TuitionClass class2 = new TuitionClass(className, new ClassTiming("MON 17:00-18:00"), location, rate);
        assertTrue(class1.isOverlapping(class2));
        assertTrue(class2.isOverlapping(class1));
    }

    @Test
    public void isOverlapping_oneClassWithinOther_returnsTrue() {
        TuitionClass class1 = new TuitionClass(className, new ClassTiming("MON 16:00-18:00"), location, rate);
        TuitionClass class2 = new TuitionClass(className, new ClassTiming("MON 15:00-19:00"), location, rate);
        assertTrue(class1.isOverlapping(class2));
        assertTrue(class2.isOverlapping(class1));
    }

    @Test
    public void isOverlapping_differentDay_returnsFalse() {
        TuitionClass class1 = new TuitionClass(className, new ClassTiming("MON 16:00-18:00"), location, rate);
        TuitionClass class2 = new TuitionClass(className, new ClassTiming("TUE 17:00-18:00"), location, rate);
        assertFalse(class1.isOverlapping(class2));
        assertFalse(class2.isOverlapping(class1));
    }

    @Test
    public void isOverlapping_sameDayNoOverlap_returnsFalse() {
        TuitionClass class1 = new TuitionClass(className, new ClassTiming("MON 15:00-16:00"), location, rate);
        TuitionClass class2 = new TuitionClass(className, new ClassTiming("MON 17:00-18:00"), location, rate);
        assertFalse(class1.isOverlapping(class2));
        assertFalse(class2.isOverlapping(class1));
    }

    @Test
    public void isOverlapping_sameDayBackToBack_returnsFalse() {
        TuitionClass class1 = new TuitionClass(className, new ClassTiming("MON 15:00-17:00"), location, rate);
        TuitionClass class2 = new TuitionClass(className, new ClassTiming("MON 17:00-18:00"), location, rate);
        assertFalse(class1.isOverlapping(class2));
        assertFalse(class2.isOverlapping(class1));
    }
}
