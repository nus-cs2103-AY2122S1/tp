package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.testutil.TypicalPersons.ALICE;
import static seedu.track2gather.testutil.TypicalPersons.BENSON;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;

public class ClearCommandTest {

    private Clock clock = Clock.fixed(Instant.parse("2021-01-01T00:00:00.00Z"), ZoneId.of("UTC"));

    @Test
    public void execute_emptyTrack2Gather_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(clock), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTrack2Gather_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(ALICE);
        expectedModel.deletePerson(BENSON);

        assertCommandSuccess(new ClearCommand(clock), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        Clock defaultClock = Clock.systemDefaultZone();

        ClearCommand firstCommand = new ClearCommand();

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same Clock -> returns true
        ClearCommand defaultClockCommand = new ClearCommand(defaultClock);
        assertTrue(firstCommand.equals(defaultClockCommand));

        // different timezones -> returns false
        ZoneId zoneCalcutta = ZoneId.of("Asia/Calcutta");
        Clock clockCalcutta = defaultClock.withZone(zoneCalcutta);
        ClearCommand differentTimeZoneClockCommand = new ClearCommand(clockCalcutta);
        assertFalse(firstCommand.equals(differentTimeZoneClockCommand));

        // time set differently, same zone
        ClearCommand differentOffsetClockCommand = new ClearCommand(Clock.offset(defaultClock, Duration.ofHours(72)));
        assertFalse(firstCommand.equals(differentOffsetClockCommand));

        // null -> returns false
        assertFalse(firstCommand.equals(null));
    }
}
