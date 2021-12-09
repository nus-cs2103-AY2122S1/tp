package seedu.times.logic.commands.classcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.times.logic.commands.CommandTestUtil.DESC_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.DESC_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSNAME_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_CLASSTIMING_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_LOCATION_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_MATHS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_RATE_IB_PHYSICS;
import static seedu.times.logic.commands.CommandTestUtil.VALID_STUDENTLIST_IB_MATHS;
import static seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;

import org.junit.jupiter.api.Test;

import seedu.times.testutil.EditClassDescriptorBuilder;

public class EditClassDescriptorTest {


    @Test
    public void equals() {
        // shallow copy -> returns true
        EditClassDescriptor descriptorWithSameValues = new EditClassDescriptor(DESC_IB_MATHS);
        assertTrue(DESC_IB_MATHS.equals(descriptorWithSameValues));

        //deep copy with  null studentNameList -> returns true
        EditClassDescriptor editClassDescriptor1 = new EditClassDescriptorBuilder()
                .withClassName(VALID_CLASSNAME_IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_PHYSICS)
                .withRate(VALID_RATE_IB_PHYSICS).withLocation(VALID_LOCATION_IB_PHYSICS).build();

        EditClassDescriptor editClassDescriptor2 = new EditClassDescriptorBuilder()
                .withClassName(VALID_CLASSNAME_IB_PHYSICS).withClassTiming(VALID_CLASSTIMING_IB_PHYSICS)
                .withRate(VALID_RATE_IB_PHYSICS).withLocation(VALID_LOCATION_IB_PHYSICS).build();

        assertTrue(editClassDescriptor1.equals(editClassDescriptor2));

        // same object -> returns true
        assertTrue(DESC_IB_MATHS.equals(DESC_IB_MATHS));

        // null -> returns false
        assertFalse(DESC_IB_MATHS.equals(null));

        // different types -> returns false
        assertFalse(DESC_IB_MATHS.equals(5));

        // different values -> returns false
        assertFalse(DESC_IB_MATHS.equals(DESC_IB_PHYSICS));

        // different class name -> returns false
        EditClassDescriptor editedIbPhysics = new EditClassDescriptorBuilder(DESC_IB_PHYSICS)
                .withClassName(VALID_CLASSNAME_IB_MATHS).build();
        assertFalse(DESC_IB_PHYSICS.equals(editedIbPhysics));

        // different class timing -> returns false
        editedIbPhysics = new EditClassDescriptorBuilder(DESC_IB_PHYSICS)
                .withClassTiming(VALID_CLASSTIMING_IB_MATHS).build();
        assertFalse(DESC_IB_PHYSICS.equals(editedIbPhysics));

        // different rate -> returns false
        editedIbPhysics = new EditClassDescriptorBuilder(DESC_IB_PHYSICS)
                .withRate(VALID_RATE_IB_MATHS).build();
        assertFalse(DESC_IB_PHYSICS.equals(editedIbPhysics));

        //different location -> returns false
        editedIbPhysics = new EditClassDescriptorBuilder(DESC_IB_PHYSICS)
                .withLocation(VALID_LOCATION_IB_MATHS).build();
        assertFalse(DESC_IB_PHYSICS.equals(editedIbPhysics));

        //different student list -> returns false
        editedIbPhysics = new EditClassDescriptorBuilder(DESC_IB_PHYSICS)
                .withStudentList(VALID_STUDENTLIST_IB_MATHS).build();
        assertFalse(DESC_IB_PHYSICS.equals(editedIbPhysics));
    }
}
