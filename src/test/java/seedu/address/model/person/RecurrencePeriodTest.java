package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.PersonTestUtil.createPeriod;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.RecurrencePeriod;

public class RecurrencePeriodTest {

    @Test
    public void test_unionBy_duration() {
        RecurrencePeriod test = createRecurrencePeriod(4, 4);
        Collection<RecurrencePeriod> periods =
                List.of(createRecurrencePeriod(1, 3), createRecurrencePeriod(5, 6));
        Collection<RecurrencePeriod> result = test.unionByDuration(periods);
        Collection<RecurrencePeriod> expected = List.of(createRecurrencePeriod(1, 6));
        assertTrue(expected.containsAll(result));

    }

    private RecurrencePeriod createRecurrencePeriod(int i, int j) {
        return new RecurrencePeriod(createPeriod(i, j), Slot.MORNING);
    }

}
