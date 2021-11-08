package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Schedule;
import seedu.address.model.event.Event;
import seedu.address.model.event.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditEventDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_DATE_AMY = "2020-01-01";
    public static final String VALID_DATE_BOB = "2020-02-02";
    public static final String VALID_LOCATION_AMY = "Block 312, Amy Street 1";
    public static final String VALID_LOCATION_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_REMARK_AMY = "Likes to bake apple pie with me.";
    public static final String VALID_REMARK_BOB = "Plays golf with his buddies on Sunday.";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_STARTTIME_AMY = "1300";
    public static final String VALID_ENDTIME_AMY = "1400";
    public static final String VALID_STARTTIME_BOB = "0830";
    public static final String VALID_ENDTIME_BOB = "0900";
    public static final String VALID_TIMESLOT_AMY = VALID_STARTTIME_AMY + "-" + VALID_ENDTIME_AMY;
    public static final String VALID_TIMESLOT_BOB = VALID_STARTTIME_BOB + "-" + VALID_ENDTIME_BOB;

    public static final String VALID_STARTTIME_BLOCKEDSLOTA = "0800";
    public static final String VALID_ENDTIME_BLOCKEDSLOTA = "0900";
    public static final String VALID_TIMESLOT_BLOCKEDSLOTA = VALID_STARTTIME_BLOCKEDSLOTA + "-"
            + VALID_ENDTIME_BLOCKEDSLOTA;
    public static final String VALID_DATE_BLOCKEDSLOTA = "2020-01-01";
    public static final String VALID_STARTTIME_BLOCKEDSLOTB = "0900";
    public static final String VALID_ENDTIME_BLOCKEDSLOTB = "1000";
    public static final String VALID_TIMESLOT_BLOCKEDSLOTB = VALID_STARTTIME_BLOCKEDSLOTB + "-"
            + VALID_ENDTIME_BLOCKEDSLOTB;
    public static final String VALID_DATE_BLOCKEDSLOTB = "2020-02-02";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DATE_DESC_AMY = " " + PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + PREFIX_DATE + VALID_DATE_BOB;
    public static final String TIMESLOT_DESC_AMY = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_AMY;
    public static final String TIMESLOT_DESC_BOB = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String REMARK_DESC_AMY = " " + PREFIX_REMARK + VALID_REMARK_AMY;
    public static final String REMARK_DESC_BOB = " " + PREFIX_REMARK + VALID_REMARK_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String DATE_DESC_BLOCKEDSLOTA = " " + PREFIX_DATE + VALID_DATE_BLOCKEDSLOTA;
    public static final String DATE_DESC_BLOCKEDSLOTB = " " + PREFIX_DATE + VALID_DATE_BLOCKEDSLOTB;
    public static final String TIMESLOT_DESC_BLOCKEDSLOTA = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_BLOCKEDSLOTA;
    public static final String TIMESLOT_DESC_BLOCKEDSLOTB = " " + PREFIX_TIMESLOT + VALID_TIMESLOT_BLOCKEDSLOTB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2020a-02-02"; // 'a' not allowed in
    // dates
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIMESLOT + "15:15-16:00"; // ':' not allowed in time
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty string not allowed for locations
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditEventDescriptor DESC_AMY;
    public static final EditCommand.EditEventDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditEventDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDate(VALID_DATE_AMY).withTimeSlot(VALID_STARTTIME_AMY, VALID_ENDTIME_AMY)
                .withLocation(VALID_LOCATION_AMY)
                .withTags(VALID_TAG_FRIEND)
                .withRemark(VALID_REMARK_AMY).build();
        DESC_BOB = new EditEventDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDate(VALID_DATE_BOB).withTimeSlot(VALID_STARTTIME_BOB, VALID_ENDTIME_BOB)
                .withLocation(VALID_LOCATION_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withRemark(VALID_REMARK_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered event list and selected event in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Schedule expectedSchedule = new Schedule(actualModel.getSchedule());
        List<Event> expectedFilteredList = new ArrayList<>(actualModel.getFilteredEventList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSchedule, actualModel.getSchedule());
        assertEquals(expectedFilteredList, actualModel.getFilteredEventList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.updateFilteredEventList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

}
