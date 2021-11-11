package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPLETION_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STATUS_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_AMAZON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPLETION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.FindCommandParser.COMPLETION_MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.FindCommandParser.PRIORITY_MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.FindCommandParser.STATUS_MESSAGE_CONSTRAINTS;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.application.Company;
import seedu.address.model.application.CompletionContainsKeywordsPredicate;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.Position;
import seedu.address.model.application.PositionContainsKeywordsPredicate;
import seedu.address.model.application.PriorityContainsKeywordsPredicate;
import seedu.address.model.application.Requirement;
import seedu.address.model.application.RequirementsContainsKeywordsPredicate;
import seedu.address.model.application.StatusContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOnePrefix_throwsParseException() {

        assertParseFailure(parser, NAME_DESC_AMAZON + POSITION_DESC_AMAZON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Google", "Shopee")));
        assertParseSuccess(parser, " " + PREFIX_COMPANY_NAME + "Google Shopee", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_COMPANY_NAME + " \n Google \n \t Shopee  \t",
                expectedFindCommand);
    }

    @Test
    public void parse_validPositionArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PositionContainsKeywordsPredicate(Arrays.asList("engineer", "designer")));
        assertParseSuccess(parser, " " + PREFIX_INTERNSHIP_POSITION + "engineer designer", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_INTERNSHIP_POSITION
                + " \n engineer \n \t designer  \t", expectedFindCommand);
    }

    @Test
    public void parse_validCompletionArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompletionContainsKeywordsPredicate(Arrays.asList("completed")));
        assertParseSuccess(parser, " " + PREFIX_COMPLETION + "completed", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_COMPLETION
                + " \n completed \n ", expectedFindCommand);
    }

    @Test
    public void parse_validStatusArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList("accepted")));
        assertParseSuccess(parser, " " + PREFIX_STATUS + "accepted", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_STATUS + " \n accepted \n ", expectedFindCommand);
    }

    @Test
    public void parse_validPriorityArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList("high")));
        assertParseSuccess(parser, " " + PREFIX_PRIORITY + "high", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_PRIORITY + " \n high \n ", expectedFindCommand);
    }

    @Test
    public void parse_validRequirementsArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new RequirementsContainsKeywordsPredicate(Arrays.asList("cv", "resume")));
        assertParseSuccess(parser, " " + PREFIX_REQUIREMENT + "cv resume", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_REQUIREMENT
                + " \n cv \n \t resume  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgument_throwsParseException() {

        //invalid company name
        assertParseFailure(parser, INVALID_NAME_DESC, Company.MESSAGE_CONSTRAINTS);

        //invalid position
        assertParseFailure(parser, INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS);

        //invalid priority
        assertParseFailure(parser, INVALID_PRIORITY_DESC, PRIORITY_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_PRIORITY_DESC_2, PRIORITY_MESSAGE_CONSTRAINTS);

        //invalid completion
        assertParseFailure(parser, INVALID_COMPLETION_DESC, COMPLETION_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_COMPLETION_DESC_2, COMPLETION_MESSAGE_CONSTRAINTS);

        //invalid status
        assertParseFailure(parser, INVALID_STATUS_DESC, STATUS_MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_STATUS_DESC_2, STATUS_MESSAGE_CONSTRAINTS);

        //invalid requirement
        assertParseFailure(parser, INVALID_REQUIREMENT_DESC, Requirement.MESSAGE_CONSTRAINTS);

    }


}
