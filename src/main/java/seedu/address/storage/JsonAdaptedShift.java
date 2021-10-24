package seedu.address.storage;

import static seedu.address.model.person.Slot.isValidSlot;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EmptyShift;
import seedu.address.model.person.Period;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;

public class JsonAdaptedShift {
    private static final String BOOLEAN_CONSTRAINTS = "Shift should have a true or false value to indicate if "
            + "this shift is active";

    private final String dayOfWeek;
    private final String slot;
    private final String startDate;
    private final String isWorking;
    private final List<JsonAdaptedPeriod> history = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedShift(@JsonProperty("dayOfWeek") String dayOfWeek, @JsonProperty("slot") String slot,
                            @JsonProperty("history") List<JsonAdaptedPeriod> history,
                            @JsonProperty("startDate") String startDate, @JsonProperty("isWorking") String isWorking) {
        this.slot = slot;
        this.dayOfWeek = dayOfWeek;
        if (history != null) {
            this.history.addAll(history);
        }
        this.startDate = startDate;
        this.isWorking = isWorking;
    }

    public JsonAdaptedShift(Shift shift) {
        assert shift != null;
        this.dayOfWeek = shift.getDayOfWeek().toString();
        this.slot = shift.getSlot().getValue();
        this.startDate = shift.getStartDate().toString();
        this.isWorking = String.valueOf(shift.isWorking);
        List<Period> history = shift.getHistory();
        this.history.addAll(history
                .stream()
                .map(JsonAdaptedPeriod::new)
                .collect(Collectors.toList()));


    }

    public Shift toModelType() throws IllegalValueException {
        if (!isValidSlot(slot)) {
            throw new IllegalValueException(Slot.MESSAGE_CONSTRAINTS);
        }
        Slot modelSlot = Slot.translateStringToSlot(slot);
        DayOfWeek modelDayOfWeek = DayOfWeek.valueOf(dayOfWeek);
        List<Period> periods = new ArrayList<>();
        for (JsonAdaptedPeriod period :  history) {
            periods.add(period.toModelType());
        }
        if (!isWorking.equals("true") && !isWorking.equals("false")) {
            throw new IllegalValueException(BOOLEAN_CONSTRAINTS);
        }
        //empty case
        if (isWorking.equals("true")) {
            //create empty
            return new EmptyShift(modelDayOfWeek, modelSlot, periods);
        }
        LocalDate startDate = LocalDate.parse(this.startDate);
        return new Shift(modelDayOfWeek, modelSlot, startDate, periods);



    }





}
