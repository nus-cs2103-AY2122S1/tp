package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

public class AddClientCommandParserTest {
    private final AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_compulsoryFieldsMissing_throwsParseException() {
        // empty args
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse(" "));

        // name is not provided
        assertThrows(ParseException.class, () -> parser.parse("-pn 12345678"));
        assertThrows(ParseException.class, () -> parser.parse(" -pn 42665"));
        assertThrows(ParseException.class, () -> parser.parse("-pn 545465 -e email@email, -a address"));

        // phone number is not provided
        assertThrows(ParseException.class, () -> parser.parse("random name"));
        assertThrows(ParseException.class, () -> parser.parse("random name -pn"));
        assertThrows(ParseException.class, () -> parser.parse("random name -pn "));
        assertThrows(ParseException.class, () -> parser.parse("random name -p 1546424"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn -e email@email.com -a address"));
        assertThrows(ParseException.class, () -> parser.parse("name -e email@email.com -a address"));
    }

    @Test
    public void parse_invalidAttributes_throwsParseException() {
        // invalid phone number
        assertThrows(ParseException.class, () -> parser.parse("name -pn phone"));
        assertThrows(ParseException.class, () -> parser.parse(
                "name -pn phone -e email@email.com -a address"));

        // invalid email
        assertThrows(ParseException.class, () -> parser.parse("name -pn 43530251 -e email"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 43532510 -e @email"));
        assertThrows(ParseException.class, () -> parser.parse(
                "name -pn 43532510 -e @email -a Singapore"));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Name name = new Name("John Doe");
        PhoneNumber phoneNumber = new PhoneNumber("12345678");
        Email email = new Email("john.d@email.com");
        Address address = new Address("Singapore");
        AddClientCommand.AddClientDescriptor descriptor =
                new AddClientCommand.AddClientDescriptor(name, phoneNumber);

        // name and phone number are provided
        testValidAttributes("John Doe -pn 12345678", descriptor);

        // name, phone number and email are provided
        descriptor.setEmail(email);
        testValidAttributes("John Doe -pn 12345678 -e john.d@email.com", descriptor);

        // name, phone number, email and address are provided
        descriptor.setAddress(address);
        testValidAttributes("John Doe -pn 12345678 -e john.d@email.com -a Singapore", descriptor);

        // name, phone number and address are provided
        descriptor.setEmail(null);
        testValidAttributes("John Doe -pn 12345678 -a Singapore", descriptor);
    }

    private void testValidAttributes(String args, AddClientCommand.AddClientDescriptor descriptor) {
        try {
            CommandResult actualResult = parser.parse(args).execute(new ModelStub());
            CommandResult expectedResult = new AddClientCommand(descriptor).execute(new ModelStub());
            // compare the feedback to user excluding the id.
            String actualString = actualResult.getFeedbackToUser();
            actualString = actualString.substring(actualString.indexOf("Name"));
            String expectedString = expectedResult.getFeedbackToUser();
            expectedString = expectedString.substring(expectedString.indexOf("Name"));
            if (!actualString.equals(expectedString)) {
                fail();
            }
            if (expectedResult.isShowHelp() != actualResult.isShowHelp()) {
                fail();
            }
            if (expectedResult.isExit() != actualResult.isExit()) {
                fail();
            }
        } catch (ParseException | CommandException e) {
            fail();
        }
    }

    public static class ModelStub extends ModelManager {
        /**
         * Assume there are no duplicate clients, return false.
         *
         * @param client the client to be checked.
         * @return False.
         */
        @Override
        public boolean hasClient(Client client) {
            return false;
        }
    }
}
