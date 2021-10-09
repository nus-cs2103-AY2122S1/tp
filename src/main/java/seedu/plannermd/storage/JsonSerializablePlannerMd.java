package seedu.plannermd.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.ReadOnlyPlannerMd;
import seedu.plannermd.model.patient.Patient;

/**
 * An Immutable PlannerMd that is serializable to JSON format.
 */
@JsonRootName(value = "plannermd")
class JsonSerializablePlannerMd {

    public static final String MESSAGE_DUPLICATE_PATIENT = "Patients list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "Placeholdertext";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePlannerMd} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePlannerMd(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyPlannerMd} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlannerMd}.
     */
    public JsonSerializablePlannerMd(ReadOnlyPlannerMd source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this plannermd into the model's {@code PlannerMd} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PlannerMd toModelType() throws IllegalValueException {
        PlannerMd plannerMd = new PlannerMd();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (plannerMd.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            plannerMd.addPatient(patient);
        }
        return plannerMd;
    }
}

