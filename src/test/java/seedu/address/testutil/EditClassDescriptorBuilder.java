package seedu.address.testutil;

import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tuition.ClassLimit;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * A utility class to help with building EditClassDescriptor objects.
 */
public class EditClassDescriptorBuilder {

    private EditClassCommand.EditClassDescriptor descriptor;

    public EditClassDescriptorBuilder() {
        descriptor = new EditClassCommand.EditClassDescriptor();
    }

    public EditClassDescriptorBuilder(EditClassCommand.EditClassDescriptor descriptor) {
        this.descriptor = new EditClassCommand.EditClassDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClassDescriptor} with fields containing {@code tuitionClass}'s details
     */
    public EditClassDescriptorBuilder(TuitionClass tuitionClass) {
        descriptor = new EditClassCommand.EditClassDescriptor();
        descriptor.setLimit(tuitionClass.getLimit());
        descriptor.setTimeslot(tuitionClass.getTimeslot());
        descriptor.setClassName(tuitionClass.getName());
    }

    /**
     * Sets the {@code ClassName} of the {@code EditClassDescriptor} that we are building.
     */
    public seedu.address.testutil.EditClassDescriptorBuilder withName(String name) {
        descriptor.setClassName(new ClassName(name));
        return this;
    }

    /**
     * Sets the {@code ClassLimit} of the {@code EditClassDescriptor} that we are building.
     */
    public seedu.address.testutil.EditClassDescriptorBuilder withLimit(String limit) {
        ClassLimit size;
        try {
            size = ParserUtil.parseLimit(limit);
            descriptor.setLimit(size);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code Timeslot} of the {@code EditClassDescriptor} that we are building.
     */
    public seedu.address.testutil.EditClassDescriptorBuilder withTimeslot(String timeslot) {
        Timeslot time;
        try {
            time = ParserUtil.parseTimeslot(timeslot.trim());
            descriptor.setTimeslot(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this;
    }

    public EditClassCommand.EditClassDescriptor build() {
        return descriptor;
    }

}
