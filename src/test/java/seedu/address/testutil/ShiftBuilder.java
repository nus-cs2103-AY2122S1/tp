package seedu.address.testutil;

import static seedu.address.model.person.PersonTestUtil.createPeriod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.EmptyShift;
import seedu.address.model.person.Period;
import seedu.address.model.person.Shift;
import seedu.address.model.person.Slot;
import seedu.address.model.util.SampleDataUtil;

/**
 * Ultility class to help with building shift objects.
 */
public class ShiftBuilder {
    private static final List<Period> DEFAULT_HISTORY = List.of(createPeriod(1, 7), createPeriod(15, 20));
    private static final Slot DEFAULT_SLOT = Slot.MORNING;
    private static final boolean DEFAULT_IS_WORKING = true;
    private static final LocalDate DEFAULT_START = LocalDate.of(2, 6, 1);

    private DayOfWeek day;
    private List<Period> history;
    private LocalDate startDate;
    private Slot slot;
    private boolean isWorking;

    /**
     * Default shift builder.
     */
    public ShiftBuilder(int date) {
        this.history = DEFAULT_HISTORY;
        this.isWorking = DEFAULT_IS_WORKING;
        this.slot = DEFAULT_SLOT;
        this.startDate = DEFAULT_START;
        this.day = DayOfWeek.of(date);
    }

    /**
     * Creates a {@code ScheduleBuilder} with the data of the {@code scheduleToCopy}.
     */
    public ShiftBuilder(Shift shiftToCopy) {
        this.day = shiftToCopy.getDayOfWeek();
        this.history = shiftToCopy.getHistory();
        this.isWorking = !shiftToCopy.isEmpty();
        this.slot = shiftToCopy.getSlot();
        this.startDate = shiftToCopy.getStartDate();
    }

    /**
     * Creates the {@code Shift} based off the data in the ShiftBuilder.
     */
    public Shift build() {
        if (!isWorking) {
            return buildEmpty();
        }
        return buildShift();
    }

    private Shift buildEmpty() {
        assert isWorking == false;
        return new EmptyShift(day, slot, history);

    }

    private Shift buildShift() {
        assert isWorking;
        return new Shift(day, slot, startDate, history);
    }

    /**
     * Sets the {@code dayOfWeek} of the shift we are building.
     */
    public void withDay(String date) {
        this.day = DayOfWeek.valueOf(date);
    }

    /**
     * Sets the {@code startDate} of the shift we are building.
     */
    public void withStartDate(String date) {
        LocalDate result = LocalDate.parse(date);
        this.startDate = result;
    }

    /**
     * Sets the {@code slot} of the shift we are building.
     */
    public void withSlot(String slot) {
        this.slot = Slot.translateStringToSlot(slot);
    }

    /**
     * Sets the {@code history} of the shift we are building.
     */
    public void withHistory(String... periods) {
        this.history = SampleDataUtil.getPeriodSet(periods)
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Sets the {@code isWorking} of the shift we are building.
     */
    public void withIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

}
