package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Application;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.Status;

/**
 * Jackson-friendly version of {@link Application}.
 */
class JsonAdaptedApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Application's %s field is missing!";

    private final String company;
    private final String position;
    private final String deadline;
    private final String status;
    private final String completion;
    private final String priority;
    private final List<JsonAdaptedRequirement> requirements = new ArrayList<>();
    private final List<JsonAdaptedInterviewDateAndTime> interviewDateAndTimes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedApplication} with the given application details.
     */
    @JsonCreator
    public JsonAdaptedApplication(@JsonProperty("company") String company,
                                  @JsonProperty("position") String position,
                                  @JsonProperty("deadline") String deadline,
                                  @JsonProperty("completion") String completion,
                                  @JsonProperty("status") String status,
                                  @JsonProperty("priority") String priority,
                                  @JsonProperty("requirements") List<JsonAdaptedRequirement> requirements,
                                  @JsonProperty("interview date and time")
                                              List<JsonAdaptedInterviewDateAndTime> interviewDateAndTimes
    ) {
        this.company = company;
        this.position = position;
        this.deadline = deadline;
        this.completion = completion;
        this.status = status;
        this.priority = priority;
        if (requirements != null) {
            this.requirements.addAll(requirements);
        }
        if (interviewDateAndTimes != null) {
            this.interviewDateAndTimes.addAll(interviewDateAndTimes);
        }
    }

    /**
     * Converts a given {@code Application} into this class for Jackson use.
     */
    public JsonAdaptedApplication(Application source) {
        company = source.getCompany().fullCompanyName;
        position = source.getPosition().value;
        deadline = source.getDeadline().value;
        completion = source.getCompletion().value;
        status = source.getStatus().value;
        priority = source.getPriority().value;

        requirements.addAll(source.getRequirements().stream()
                .map(JsonAdaptedRequirement::new)
                .collect(Collectors.toList()));
        interviewDateAndTimes.addAll(source.getInterviewDateAndTime().stream()
                .map(JsonAdaptedInterviewDateAndTime::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted application object into the model's {@code Application} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted application.
     */
    public Application toModelType() throws IllegalValueException {
        final List<Requirement> applicationRequirements = new ArrayList<>();
        for (JsonAdaptedRequirement requirement : requirements) {
            applicationRequirements.add(requirement.toModelType());
        }
        final List<InterviewDateAndTime> dateAndTimes = new ArrayList<>();
        for (JsonAdaptedInterviewDateAndTime interviewDateAndTime : interviewDateAndTimes) {
            dateAndTimes.add(interviewDateAndTime.toModelType());
        }
        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Company.class.getSimpleName()));
        }
        if (!Company.isValidCompanyName(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelName = new Company(company);

        if (position == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Position.class.getSimpleName()));
        }
        if (!Position.isValidPosition(position)) {
            throw new IllegalValueException(Position.MESSAGE_CONSTRAINTS);
        }
        final Position modelPosition = new Position(position);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (completion == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Completion.class.getSimpleName()));
        }
        if (!Completion.isValidCompletion(completion)) {
            throw new IllegalValueException(Completion.MESSAGE_CONSTRAINTS);
        }
        final Completion modelCompletion = new Completion(completion);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        final Set<Requirement> modelRequirement = new HashSet<>(applicationRequirements);

        final Set<InterviewDateAndTime> modelInterviewDateAndTime = new HashSet<>(dateAndTimes);

        return new Application(modelName, modelPosition, modelDeadline, modelCompletion, modelStatus, modelPriority,
                modelRequirement, modelInterviewDateAndTime);
    }

}
