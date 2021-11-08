package seedu.address.testutil;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Status;

public class AssignmentBuilder {
    public static final String DEFAULT_DESCRIPTION = "Assignment description";
    public static final String DEFAULT_DATE = "28/09/2021";
    public static final String DEFAULT_TIME = "1800";
    public static final Status DEFAULT_STATUS = Status.createPendingStatus();

    private Description description;
    private DueDate dueDate;
    private Status status;


    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        dueDate = new DueDate(DEFAULT_DATE, DEFAULT_TIME);
        status = DEFAULT_STATUS;
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignmentToCopy}.
     */
    public AssignmentBuilder(Assignment assignmentToCopy) {
        description = assignmentToCopy.getDescription();
        dueDate = assignmentToCopy.getDueDate();
        status = assignmentToCopy.getStatus();
    }

    /**
     * Sets the {@code Description} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code DueDate} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDueDate(String date, String time) {
        this.dueDate = new DueDate(date, time);
        return this;
    }

    /**
     * Sets the {@code Status} to completed of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withCompletedStatus() {
        this.status = Status.createCompletedStatus();
        return this;
    }

    /**
     * Sets the {@code Status} to pending of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withPendingStatus() {
        this.status = Status.createPendingStatus();
        return this;
    }

    public Assignment build() {
        return new Assignment(description, dueDate, status);
    }

}
