package tutoraid.testutil;

import tutoraid.logic.commands.EditStudentCommand.EditStudentDescriptor;
import tutoraid.model.student.ParentName;
import tutoraid.model.student.Phone;
import tutoraid.model.student.Student;
import tutoraid.model.student.StudentName;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private final EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setStudentName(student.getStudentName());
        descriptor.setStudentPhone(student.getStudentPhone());
        descriptor.setParentName(student.getParentName());
        descriptor.setParentPhone(student.getParentPhone());
    }

    /**
     * Sets the {@code StudentName} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentName(String name) {
        descriptor.setStudentName(new StudentName(name));
        return this;
    }

    /**
     * Sets the student's {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentPhone(String phone) {
        descriptor.setStudentPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code ParentName} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withParentName(String name) {
        descriptor.setParentName(new ParentName(name));
        return this;
    }

    /**
     * Sets the parent's {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withParentPhone(String phone) {
        descriptor.setParentPhone(new Phone(phone));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
