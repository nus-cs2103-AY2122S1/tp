package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.interview.Interview;

/**
 * An Immutable HrManager that is serializable to JSON format.
 */
@JsonRootName(value = "HrManagerInterviews")
public class JsonSerializableHrManagerInterviews {
    public static final String MESSAGE_DUPLICATE_INTERVIEWS = "Interviews list contains duplicate interview(s).";

    private final List<JsonAdaptedInterview> interviews = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHrManagerInterviews} with the given interviews.
     */
    @JsonCreator
    public JsonSerializableHrManagerInterviews(@JsonProperty("interviews") List<JsonAdaptedInterview> interviews) {
        this.interviews.addAll(interviews);
    }

    /**
     * Converts a given {@code HrManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHrManagerInterviews}.
     */
    public JsonSerializableHrManagerInterviews(ReadOnlyHrManager source) {
        interviews.addAll(source.getInterviewList().stream().map(JsonAdaptedInterview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this serializable HrManagerInterviews into the model's {@code HrManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HrManager toModelType() throws IllegalValueException {
        HrManager hrManager = new HrManager();
        for (JsonAdaptedInterview jsonAdaptedInterview : interviews) {
            Interview interview = jsonAdaptedInterview.toModelType();
            if (hrManager.hasInterview(interview)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_INTERVIEWS);
            }
            hrManager.addInterview(interview);
        }
        return hrManager;
    }
}
