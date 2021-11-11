package nustracker.logic.parser;

import static nustracker.commons.core.Messages.MESSAGE_INVALID_MAJOR;
import static nustracker.commons.core.Messages.MESSAGE_INVALID_YEAR;
import static nustracker.commons.core.Messages.MESSAGE_MULTIPLE_FILTER_FIELDS;
import static nustracker.logic.parser.CliSyntax.PREFIX_EVENT;
import static nustracker.logic.parser.CliSyntax.PREFIX_MAJOR;
import static nustracker.logic.parser.CliSyntax.PREFIX_NAME;
import static nustracker.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static nustracker.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustracker.commons.core.Messages;
import nustracker.logic.commands.FilterCommand;
import nustracker.logic.commands.FilterEventCommand;
import nustracker.logic.commands.FilterIdCommand;
import nustracker.logic.commands.FilterMajorCommand;
import nustracker.logic.commands.FilterNameCommand;
import nustracker.logic.commands.FilterYearCommand;
import nustracker.model.event.EventName;
import nustracker.model.student.Major;
import nustracker.model.student.MajorContainsKeywordsPredicate;
import nustracker.model.student.NameContainsKeywordsPredicate;
import nustracker.model.student.StudentId;
import nustracker.model.student.StudentIdContainsKeywordsPredicate;
import nustracker.model.student.Year;
import nustracker.model.student.YearContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyStudentIdPrefix() {
        CommandParserTestUtil.assertParseFailure(parser, " " + PREFIX_STUDENTID,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_multipleFileds() {
        CommandParserTestUtil.assertParseFailure(parser,
                " " + PREFIX_STUDENTID + "e0000000 " + PREFIX_NAME + " Alice", MESSAGE_MULTIPLE_FILTER_FIELDS);
    }

    @Test
    public void parse_validArgs_returnsFilterNameCommand() {
        FilterCommand expectedFilterCommand =
                new FilterNameCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterIdCommand() {
        FilterCommand expectedFilterCommand =
                new FilterIdCommand(new StudentIdContainsKeywordsPredicate(
                        Arrays.asList(new StudentId("e1234567"), new StudentId("e2345678"))));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENTID + "e1234567 e2345678",
                expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENTID + " \n e1234567 \n \t e2345678  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_validArgs_returnsFilterYearCommand() {
        FilterCommand expectedFilterCommand =
                new FilterYearCommand(new YearContainsKeywordsPredicate(
                        Arrays.asList(new Year("2"), new Year("3"))));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_YEAR + "2 3",
                expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_YEAR + " \n 2 \n \t 3  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_invalidYear_throwsException() {
        CommandParserTestUtil.assertParseFailure(parser, " " + PREFIX_YEAR + "7",
                String.format(MESSAGE_INVALID_YEAR + "\n" + Year.MESSAGE_CONSTRAINTS, "7"));
    }

    @Test
    public void parse_validArgs_returnsFilterMajorCommand() {
        FilterCommand expectedFilterCommand =
                new FilterMajorCommand(new MajorContainsKeywordsPredicate(
                        Arrays.asList(new Major("CS"), new Major("IS"))));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_MAJOR + "CS IS",
                expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_MAJOR + " \n CS \n \t IS  \t",
                expectedFilterCommand);
    }

    @Test
    public void parse_invalidMajor_throwsException() {
        CommandParserTestUtil.assertParseFailure(parser, " " + PREFIX_MAJOR + "BIZ",
                String.format(MESSAGE_INVALID_MAJOR + "\n" + Major.MESSAGE_CONSTRAINTS, "BIZ"));
    }

    @Test
    public void parse_validArgs_returnsFilterEventCommand() {
        FilterCommand expectedFilterCommand =
                new FilterEventCommand(new EventName("event name"));
        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + "event name", expectedFilterCommand);

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EVENT + " \n\t event name \n",
                expectedFilterCommand);
    }



}
