package seedu.address.storage;

import static seedu.address.model.person.Slot.isValidSlot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.exceptions.InvalidShiftTimeException;
import seedu.address.model.RecurrencePeriod;
import seedu.address.model.person.EmptyShift;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;

public class JsonAdaptedShift {
    private static final String BOOLEAN_CONSTRAINTS = "Shift should have a true or false value to indicate if "
            + "this shift is active";

    private final String dayOfWeek;
    private final String slot;
    private final boolean isEmpty;
    private final List<JsonAdaptedRecurrencePeriod> history = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedRole} with the given Shift details.
     */
    @JsonCreator
    public JsonAdaptedShift(@JsonProperty("dayOfWeek") String dayOfWeek, @JsonProperty("slot") String slot,
                            @JsonProperty("history") List<JsonAdaptedRecurrencePeriod> history,
                            @JsonProperty("isEmpty") boolean isEmpty) {
        this.slot = slot;
        this.dayOfWeek = dayOfWeek;
        if (history != null) {
            this.history.addAll(history);
        }
        this.isEmpty = isEmpty;
    }

    /**
     * Constructs a {@code JsonAdaptedShift} with the given Shift source.
     */
    public JsonAdaptedShift(Shift shift) {
        assert shift != null;
        this.dayOfWeek = shift.getDayOfWeek().toString();
        this.slot = shift.getSlot().getValue();
        isEmpty = shift.isEmpty();
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
        DayOfWeek modelDayOfWeek = toModelTypeDayOfWeek();
        List<RecurrencePeriod> periods = new ArrayList<>();
        for (JsonAdaptedRecurrencePeriod period : history) {
            RecurrencePeriod toAdd = period.toModelType();
            try {
                Shift.checkTimeOrder(toAdd.getStartTime(), toAdd.getEndTime(), modelSlot.getOrder());
            } catch (InvalidShiftTimeException e) {
                throw new IllegalValueException(e.getMessage());
            }
            periods.add(toAdd);
        }

        //empty case
        if (isEmpty) {
            //create empty
            return new EmptyShift(modelDayOfWeek, modelSlot);
        }
        Shift result = new Shift(modelDayOfWeek, modelSlot, periods);
        return result;
    }

    private DayOfWeek toModelTypeDayOfWeek() throws IllegalValueException {
        try {
            return DayOfWeek.valueOf(dayOfWeek);
        } catch (IllegalArgumentException re) {
            throw new IllegalValueException(re.getMessage());
        }
    }

}
