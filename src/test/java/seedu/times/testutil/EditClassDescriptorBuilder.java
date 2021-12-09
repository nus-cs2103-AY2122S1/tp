package seedu.times.testutil;

import seedu.times.logic.commands.classcommands.EditClassCommand.EditClassDescriptor;
import seedu.times.model.tuitionclass.ClassName;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;
import seedu.times.model.tuitionclass.StudentNameList;
import seedu.times.model.tuitionclass.TuitionClass;

public class EditClassDescriptorBuilder {
    private EditClassDescriptor descriptor;

    public EditClassDescriptorBuilder() {
        descriptor = new EditClassDescriptor();
    }

    public EditClassDescriptorBuilder(EditClassDescriptor descriptor) {
        this.descriptor = new EditClassDescriptor(descriptor);
    }

    /**
     * Constructs an EditClassDescriptorBuilder with the information of the TuitionClass object.
     *
     * @param tuitionClass TuitionClass object to build the EditClassDescriptorBuilder with.
     */
    public EditClassDescriptorBuilder(TuitionClass tuitionClass) {
        descriptor = new EditClassDescriptor();

        descriptor.setClassName(tuitionClass.getClassName());
        descriptor.setClassTiming(tuitionClass.getClassTiming());
        descriptor.setRate(tuitionClass.getRate());
        descriptor.setLocation(tuitionClass.getLocation());
        descriptor.setStudentList(tuitionClass.getStudentList());
    }

    /**
     * Sets the class name of the EditClassDescriptorBuilder object to the given class name.
     *
     * @param className Class name to be set to.
     * @return EditClassDescriptorBuilder object with the class name set to the given class name.
     */
    public EditClassDescriptorBuilder withClassName(String className) {
        descriptor.setClassName(new ClassName(className));
        return this;
    }

    /**
     * Sets the class timing of the EditClassDescriptorBuilder object to the given class timing.
     *
     * @param classTiming Class timing to be set to.
     * @return EditClassDescriptorBuilder object with the class timing set to the given class timing.
     */
    public EditClassDescriptorBuilder withClassTiming(String classTiming) {
        descriptor.setClassTiming(new ClassTiming(classTiming));
        return this;
    }

    /**
     * Sets the rate of the EditClassDescriptorBuilder object to the given rate.
     *
     * @param rate Rate to be set to.
     * @return EditClassDescriptorBuilder object with the rate set to the given rate.
     */
    public EditClassDescriptorBuilder withRate(String rate) {
        descriptor.setRate(new Rate(rate));
        return this;
    }

    /**
     * Sets the location of the EditClassDescriptorBuilder object to the given location.
     *
     * @param location Location to be set to.
     * @return EditClassDescriptorBuilder object with the location set to the given location.
     */
    public EditClassDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the student list of the EditClassDescriptorBuilder object to the given student list.
     *
     * @param studentList Student list to be set to.
     * @return EditClassDescriptorBuilder object with the student list set to the given student list.
     */
    public EditClassDescriptorBuilder withStudentList(String... studentList) {
        descriptor.setStudentList(new StudentNameList(studentList));
        return this;
    }

    /**
     * Builds the EditClassDescriptor with the information stored in the EditClassDescriptorBuilder object.
     *
     * @return EditClassDescriptor built with the information stored in the EditClassDescriptorBuilder object.
     */
    public EditClassDescriptor build() {
        return descriptor;
    }
}
