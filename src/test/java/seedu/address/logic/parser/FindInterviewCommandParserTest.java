package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.interview.FindInterviewCommand;
import seedu.address.model.interview.FindInterviewCommandPredicate;
import seedu.address.model.interview.Interview;

public class FindInterviewCommandParserTest {

    private FindInterviewCommandParser parser = new FindInterviewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindInterviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindInterviewCommand expectedFindInterviewCommand;
        expectedFindInterviewCommand = new FindInterviewCommand(new FindInterviewCommandPredicate(
                Arrays.asList("Alex", "Mark"),
                Arrays.asList("2021", "09"),
                Arrays.asList("complete", "pending"),
                Arrays.asList("accountant"),
                Arrays.asList(LocalTime.of(12, 00))
        ));
        assertParseSuccess(parser, " "
                        + PREFIX_CANDIDATE_INDEX + "Alex Mark "
                        + PREFIX_DATE + "2021 09 "
                        + PREFIX_INTERVIEW_STATUS + "complete pending "
                        + PREFIX_POSITION + "accountant "
                        + PREFIX_TIME + "1200",
                expectedFindInterviewCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " "
                        + PREFIX_CANDIDATE_INDEX + "\n Alex  \n \t  Mark "
                        + PREFIX_DATE + "2021 09 "
                        + PREFIX_INTERVIEW_STATUS + "  \n \t  complete pending "
                        + PREFIX_POSITION + " \n \t  accountant  \n \t "
                        + PREFIX_TIME + " \n \t 1200 \n \t ",
                expectedFindInterviewCommand);


        //find by status pending
        expectedFindInterviewCommand =
                new FindInterviewCommand(
                        new FindInterviewCommandPredicate(
                                Arrays.asList(),
                                Arrays.asList(),
                                Arrays.asList(Interview.InterviewStatus.PENDING.toString()),
                                Arrays.asList(),
                                Arrays.asList()
                        )
                );
        assertParseSuccess(parser, " " + PREFIX_INTERVIEW_STATUS + Interview.InterviewStatus.PENDING.toString(),
                expectedFindInterviewCommand);

        //find by status Completed
        expectedFindInterviewCommand =
                new FindInterviewCommand(new FindInterviewCommandPredicate(
                        Arrays.asList(),
                        Arrays.asList(),
                        new ArrayList<String>(Arrays.asList(Interview.InterviewStatus.COMPLETED.toString())),
                        Arrays.asList(),
                        Arrays.asList()
                ));
        assertParseSuccess(parser, " " + PREFIX_INTERVIEW_STATUS + Interview.InterviewStatus.COMPLETED,
                expectedFindInterviewCommand);
    }

}
