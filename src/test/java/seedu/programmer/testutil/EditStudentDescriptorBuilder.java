package seedu.programmer.testutil;

import seedu.programmer.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.programmer.model.student.ClassId;
import seedu.programmer.model.student.Email;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.Name;
import seedu.programmer.model.student.Student;
import seedu.programmer.model.student.StudentId;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code Student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setStudentId(student.getStudentId());
        descriptor.setClassId(student.getClassId());
        descriptor.setEmail(student.getEmail());
        descriptor.setLabList(student.getLabList());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentId(String studentId) {
        descriptor.setStudentId(new StudentId(studentId));
        return this;
    }

    /**
     * Sets the {@code ClassId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withClassId(String classId) {
        descriptor.setClassId(new ClassId(classId));
        return this;
    }

    /**
     * Sets the {@code email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withemail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code lab} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withLab(Integer labNum, Integer labResult) {

        descriptor.setLab(new Lab(new LabNum(labNum)), new LabResult(labResult));
        return this;
    }


    public EditStudentDescriptor build() {
        return descriptor;
    }
}
