package seedu.address.storage;

import static seedu.address.model.person.Slot.isValidSlot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EmptyShift;
import seedu.address.model.RecurrencePeriod;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;

public class JsonAdaptedShift {
    private static final String BOOLEAN_CONSTRAINTS = "Shift should have a true or false value to indicate if "
            + "this shift is active";

    private final String dayOfWeek;
    private final String slot;
    private final String isWorking;
    private final List<JsonAdaptedRecurrencePeriod> history = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRole} with the given Shift details.
     */
    @JsonCreator
    public JsonAdaptedShift(@JsonProperty("dayOfWeek") String dayOfWeek, @JsonProperty("slot") String slot,
                            @JsonProperty("history") List<JsonAdaptedRecurrencePeriod> history,
                            @JsonProperty("isWorking") String isWorking) {
        this.slot = slot;
        this.dayOfWeek = dayOfWeek;
        if (history != null) {
            this.history.addAll(history);
        }
        this.isWorking = isWorking;
    }

    /**
     * Constructs a {@code JsonAdaptedShift} with the given Shift source.
     */
    public JsonAdaptedShift(Shift shift) {
        assert shift != null;
        this.dayOfWeek = shift.getDayOfWeek().toString();
        this.slot = shift.getSlot().getValue();
        this.isWorking = String.valueOf(!shift.isEmpty());
        List<RecurrencePeriod> history = shift.getRecurrences();
        this.history.addAll(history
                .stream()
                .map(JsonAdaptedRecurrencePeriod::new)
                .collect(Collectors.toList()));


    }

    /**
     * Converts this Jackson-friendly adapted role object into the model's {@code Shift} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted role.
     */
    public Shift toModelType() throws IllegalValueException {
        if (!isValidSlot(slot)) {
            throw new IllegalValueException(Slot.MESSAGE_CONSTRAINTS);
        }
        Slot modelSlot = Slot.translateStringToSlot(slot);
        DayOfWeek modelDayOfWeek = DayOfWeek.valueOf(dayOfWeek);
        List<RecurrencePeriod> periods = new ArrayList<>();
        for (JsonAdaptedRecurrencePeriod period : history) {
            periods.add(period.toModelType());
        }
        if (!isWorking.equals("true") && !isWorking.equals("false")) {
            throw new IllegalValueException(BOOLEAN_CONSTRAINTS);
        }
        //empty case
        if (isWorking.equals("false")) {
            //create empty
            return new EmptyShift(modelDayOfWeek, modelSlot);
        }
        Shift result = new Shift(modelDayOfWeek, modelSlot, periods);
        return result;

    }





}
