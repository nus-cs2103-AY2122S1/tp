package seedu.address.logic.commands.modulelesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2040S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2030S_T12;
import static seedu.address.model.util.SampleDataUtil.parseModuleCode;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.EditLessonDescriptor;
import seedu.address.model.modulelesson.LessonDay;
import seedu.address.model.modulelesson.LessonTime;
import seedu.address.model.person.Remark;
import seedu.address.testutil.EditLessonDescriptorBuilder;

public class EditLessonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditLessonDescriptor descriptor = new EditLessonDescriptor(DESC_CS2040S);
        assertTrue(descriptor.equals(DESC_CS2040S));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false;
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different module -> returns false
        EditLessonDescriptor diffModuleDescriptor = new EditLessonDescriptorBuilder(DESC_CS2040S)
                .withModuleCode(parseModuleCode(VALID_MODULE_CODE_CS2030S_T12)).build();
        assertFalse(descriptor.equals(diffModuleDescriptor));

        // different day -> returns false
        EditLessonDescriptor diffDayDescriptor = new EditLessonDescriptorBuilder(DESC_CS2040S)
                .withLessonDay(new LessonDay("3")).build();
        assertFalse(descriptor.equals(diffDayDescriptor));

        // different time -> returns false
        EditLessonDescriptor diffTimeDescriptor = new EditLessonDescriptorBuilder(DESC_CS2040S)
                .withLessonStartTime(new LessonTime("08:00")).build();
        assertFalse(descriptor.equals(diffTimeDescriptor));

        // different remark -> returns false
        EditLessonDescriptor diffRemarkDescriptor = new EditLessonDescriptorBuilder(DESC_CS2040S)
                .withRemark(new Remark("DEBUGGING SUCKS")).build();
        assertFalse(descriptor.equals(diffRemarkDescriptor));
    }
}
