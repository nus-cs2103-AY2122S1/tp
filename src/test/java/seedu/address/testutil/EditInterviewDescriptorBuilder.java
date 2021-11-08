package seedu.address.testutil;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.logic.interview.EditInterviewCommand.EditInterviewDescriptor;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

/**
 * A utility class to help with building EditInterviewDescriptor objects.
 */
public class EditInterviewDescriptorBuilder {

    private EditInterviewDescriptor descriptor;

    public EditInterviewDescriptorBuilder() {
        descriptor = new EditInterviewDescriptor();
    }

    public EditInterviewDescriptorBuilder(EditInterviewDescriptor descriptor) {
        this.descriptor = new EditInterviewDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInterviewDescriptor} with fields containing {@code Interview}'s details.
     */
    public EditInterviewDescriptorBuilder(Interview interview) {
        descriptor = new EditInterviewDescriptor();
        descriptor.setPosition(interview.getPosition());
        descriptor.setDate(interview.getDate());
        descriptor.setStartTime(interview.getStartTime());
        descriptor.setDuration(interview.getDuration());
        descriptor.setStatus(interview.getStatus());
    }

    /**
     * Sets the {@code Position} of the {@code EditInterviewDescriptor}.
     */
    public EditInterviewDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(new Title(position)));
        return this;
    }

    /**
     * Sets the {@code LocalDate} of the {@code EditInterviewDescriptor}.
     */
    public EditInterviewDescriptorBuilder withDate(String date) {
        String[] foundDate = date.split("/");
        int year = Integer.parseInt(foundDate[2]);
        int month = Integer.parseInt(foundDate[1]);
        int day = Integer.parseInt(foundDate[0]);
        descriptor.setDate(LocalDate.of(year, month, day));
        return this;
    }

    /**
     * Sets the {@code LocalTime} of the {@code EditInterviewDescriptor}.
     */
    public EditInterviewDescriptorBuilder withStartTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2));
        descriptor.setStartTime(LocalTime.of(hour, min));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditInterviewDescriptor}.
     */
    public EditInterviewDescriptorBuilder withDuration(String duration) {
        Long actualDuration = Long.parseLong(duration);
        descriptor.setDuration(Duration.ofMinutes(actualDuration));
        return this;
    }

    /**
     * Sets the {@code InterviewStatus} of the {@code EditInterviewDescriptor}.
     */
    public EditInterviewDescriptorBuilder withStatus(Interview.InterviewStatus status) {
        descriptor.setStatus(status);
        return this;
    }

    public EditInterviewDescriptor build() {
        return descriptor;
    }

}
