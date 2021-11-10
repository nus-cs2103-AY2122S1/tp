package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;

public class ReportCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());
    private String expectedResponse = "Current status for contacts: \n"
            + "5 confirmed, 1 pending, 1 declined\n"
            + "\n"
            + "Current status for tags: \n"
            + "[Florist]: 1 confirmed, 0 pending, 0 declined\n"
            + "[friends]: 2 confirmed, 1 pending, 0 declined\n"
            + "[owesMoney]: 0 confirmed, 1 pending, 0 declined\n"
            + "[caterer]: 1 confirmed, 0 pending, 0 declined\n"
            + "[Colleague]: 0 confirmed, 0 pending, 1 declined\n"
            + "[Photographer]: 1 confirmed, 0 pending, 0 declined\n"
            + "\n"
            + "Your wedding overall cost is $1200.00\n"
            + "Total price for [Florist] is $300.00\n"
            + "Total price for [owesMoney] is $0.00\n"
            + "Total price for [caterer] is $650.00\n"
            + "Total price for [Photographer] is $250.00\n"
            + "Total price for [Colleague] is $0.00\n"
            + "Total price for [friends] is $950.00\n";

    @Test
    public void execute_createReport_success() throws CommandException {
        String reportString = new ReportCommand().createReport(model);
        String expectedReportString = expectedResponse;
        assertEquals(expectedReportString, reportString);
    }

    @Test
    public void equals() {
        ReportCommand firstReportCommand = new ReportCommand();
        ReportCommand secondReportCommand = new ReportCommand();

        // same object -> returns true
        assertTrue(firstReportCommand.equals(firstReportCommand));

        // same values -> returns true
        assertTrue(firstReportCommand.equals(secondReportCommand));
    }
}
