package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.commons.RepoName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;
import seedu.address.model.student.UserName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private final EditStudentCommand.EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentCommand.EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentCommand.EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentCommand.EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setEmail(student.getEmail());
        descriptor.setUserName(student.getUserName());
        descriptor.setRepoName(student.getRepoName());
        descriptor.setStudentNumber(student.getStudentNumber());
        descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code StudentNumber} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentNumber(String studentNumber) {
        descriptor.setStudentNumber(new StudentNumber(studentNumber));
        return this;
    }

    /**
     * Sets the {@code UserName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withUserName(String userName) {
        descriptor.setUserName(new UserName(userName));
        return this;
    }

    /**
     * Sets the {@code RepoName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withRepoName(String repoName) {
        descriptor.setRepoName(new RepoName(repoName));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditStudentCommand.EditStudentDescriptor build() {
        return descriptor;
    }
}
