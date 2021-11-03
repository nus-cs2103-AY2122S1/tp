package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.predicates.PersonContainsFieldsPredicate;

public class ViewCommandParserTest {
    private static final String COMMAND_WORD = ViewCommand.COMMAND_WORD;
    private static final String DEFAULT_TEST_NAME = "Alex Yeoh";
    private static final String DEFAULT_TEST_ADDRESS = "311, Clementi Ave 2, #02-25";
    private static final String DEFAULT_TEST_ADDRESS_TWO = "311, Clementi Ave 2, #02-25";
    private static final String DEFAULT_TEST_EMAIL = "alice@example.com";
    private static final String DEFAULT_TEST_PHONE = "94351253";
    private static final String DEFAULT_TEST_ROLE = "bartender";
    private static final String DEFAULT_TEST_ROLE_TWO = "floor";

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void test_no_input() {
        String userInput = COMMAND_WORD;
        ViewCommandParser parser = new ViewCommandParser();
        assertParseFailure(parser, userInput, ViewCommand.MESSAGE_USAGE);
    }

    @Test
    public void test_single_input() {
        String nameInput = inputFormat(PREFIX_DASH_NAME, DEFAULT_TEST_NAME);
        ViewCommand nameExpectedCommand = commandFromString(DEFAULT_TEST_NAME, Name::new);
        assertParseSuccess(parser, nameInput, nameExpectedCommand);

        String phoneInput = inputFormat(PREFIX_DASH_PHONE, DEFAULT_TEST_PHONE);
        ViewCommand phoneExpectedCommand = commandFromString(DEFAULT_TEST_PHONE, Phone::new);
        assertParseSuccess(parser, phoneInput, phoneExpectedCommand);

        String emailInput = inputFormat(PREFIX_DASH_EMAIL, DEFAULT_TEST_EMAIL);
        ViewCommand emailExpectedCommand = commandFromString(DEFAULT_TEST_EMAIL, Email::new);
        assertParseSuccess(parser, emailInput, emailExpectedCommand);

        String addressInput = inputFormat(PREFIX_DASH_ADDRESS, DEFAULT_TEST_ADDRESS);
        ViewCommand addressExpectedCommand = commandFromString(DEFAULT_TEST_ADDRESS, Address::new);
        assertParseSuccess(parser, addressInput, addressExpectedCommand);
    }

    private static ViewCommand commandFromString(String name, Function<String, Field> function) {
        return new ViewCommand(new PersonContainsFieldsPredicate(function.apply(name)));
    }

    @Test
    public void parse_multipleInputs() {
        String userInput = COMMAND_WORD + " " + PREFIX_DASH_EMAIL + DEFAULT_TEST_EMAIL + " "
                + PREFIX_DASH_ADDRESS + DEFAULT_TEST_ADDRESS + " "
                + PREFIX_DASH_PHONE + DEFAULT_TEST_PHONE;
        PersonContainsFieldsPredicate predicate = new PersonContainsFieldsPredicate(new Email(DEFAULT_TEST_EMAIL),
                new Address(DEFAULT_TEST_ADDRESS), new Phone(DEFAULT_TEST_PHONE));
        ViewCommand result = new ViewCommand(predicate);
        assertParseSuccess(parser, userInput, result);
    }

    @Test
    public void parse_multipleInputsSameFieldIndividualValue_onlyLastInput() {
        String userInput = COMMAND_WORD + " " + PREFIX_DASH_ADDRESS + DEFAULT_TEST_ADDRESS + " "
                + PREFIX_DASH_ADDRESS + DEFAULT_TEST_ADDRESS_TWO;
        PersonContainsFieldsPredicate predicate =
                new PersonContainsFieldsPredicate(new Address(DEFAULT_TEST_ADDRESS_TWO));
        ViewCommand result = new ViewCommand(predicate);
        assertParseSuccess(parser, userInput, result);
    }

    @Test
    public void parse_multipleInputsSameFieldMultipleValue_allInputs() {
        String userInput = COMMAND_WORD + " " + PREFIX_DASH_ROLE + DEFAULT_TEST_ROLE + " "
                + PREFIX_DASH_ROLE + DEFAULT_TEST_ROLE_TWO;
        PersonContainsFieldsPredicate predicate =
                new PersonContainsFieldsPredicate(Role.translateStringToRole(DEFAULT_TEST_ROLE),
                Role.translateStringToRole(DEFAULT_TEST_ROLE_TWO));
        ViewCommand result = new ViewCommand(predicate);
        assertParseSuccess(parser, userInput, result);
    }

    private String inputFormat(Prefix prefixName, String defaultTestName) {
        String singleInput = COMMAND_WORD + " %1$s";
        return String.format(singleInput, prefixName.getPrefix() + defaultTestName);
    }
}
