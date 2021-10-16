package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Availability;

public class SetMemberAvailabilityCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetMemberAvailabilityCommand(null, null));
    }

    @Test
    public void equals() throws ParseException {
        List<Index> firstIndices = new ArrayList<>();
        String firstIndicesString = "1 2 3";
        String[] firstIndicesArray = firstIndicesString.split(" ");
        for (String s : firstIndicesArray) {
            firstIndices.add(ParserUtil.parseIndex(s));
        }
        List<Index> secondIndices = new ArrayList<>();
        String secondIndicesString = "4 5 6";
        String[] secondIndicesArray = secondIndicesString.split(" ");
        for (String s : secondIndicesArray) {
            secondIndices.add(ParserUtil.parseIndex(s));
        }
        List<DayOfWeek> firstAvailability = Arrays.asList(DayOfWeek.MONDAY);
        List<DayOfWeek> secondAvailability = Arrays.asList(DayOfWeek.TUESDAY);
        SetMemberAvailabilityCommand firstCommand =
                new SetMemberAvailabilityCommand(firstIndices, new Availability(firstAvailability));
        SetMemberAvailabilityCommand secondCommand =
                new SetMemberAvailabilityCommand(secondIndices, new Availability(secondAvailability));

        assertTrue(firstCommand.equals(firstCommand));
        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(1));
    }
}
