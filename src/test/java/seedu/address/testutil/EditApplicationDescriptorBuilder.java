package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;

/**
 * A utility class to help with building EditApplicationDescriptor objects.
 */
public class EditApplicationDescriptorBuilder {

    private EditApplicationDescriptor descriptor;

    public EditApplicationDescriptorBuilder() {
        descriptor = new EditApplicationDescriptor();
    }

    public EditApplicationDescriptorBuilder(EditApplicationDescriptor descriptor) {
        this.descriptor = new EditApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicationDescriptor} with fields containing {@code application}'s details
     */
    public EditApplicationDescriptorBuilder(Application application) {
        descriptor = new EditApplicationDescriptor();
        descriptor.setCompany(application.getCompany());
        descriptor.setPosition(application.getPosition());
        descriptor.setDeadline(application.getDeadline());
        descriptor.setDeadline(application.getDeadline());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code Requirements} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withRequirements(String... requirements) {
        Set<Requirement> requirementSet = Stream.of(requirements).map(Requirement::new).collect(Collectors.toSet());
        descriptor.setRequirements(requirementSet);
        return this;
    }

    /**
     * Sets the {@code InterviewDateAndTime} of the {@code EditApplicationDescriptor} that we are building.
     */
    public EditApplicationDescriptorBuilder withInterviewDateAndTime(String... dt) {
        Set<InterviewDateAndTime> dtSet = Stream.of(dt).map(InterviewDateAndTime::new).collect(Collectors.toSet());
        descriptor.setInterviewDateAndTimes(dtSet);
        return this;
    }

    public EditApplicationDescriptor build() {
        return descriptor;
    }
}
