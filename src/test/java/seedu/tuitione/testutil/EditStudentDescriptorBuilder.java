package seedu.tuitione.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.tuitione.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.tuitione.model.remark.Remark;
import seedu.tuitione.model.student.Address;
import seedu.tuitione.model.student.Email;
import seedu.tuitione.model.student.Grade;
import seedu.tuitione.model.student.Name;
import seedu.tuitione.model.student.ParentContact;
import seedu.tuitione.model.student.Student;

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
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getParentContact());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setRemarks(student.getRemarks());
        descriptor.setGrade(student.getGrade());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new ParentContact(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGrade(String grade) {
        descriptor.setGrade(new Grade(grade));
        return this;
    }

    /**
     * Parses the {@code remarks} into a {@code Set<Remark>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withRemarks(String... remarks) {
        Set<Remark> remarkSet = Stream.of(remarks).map(Remark::new).collect(Collectors.toSet());
        descriptor.setRemarks(remarkSet);
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
