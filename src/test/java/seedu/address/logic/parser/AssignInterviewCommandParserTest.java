package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.interview.AssignInterviewCommand;

public class AssignInterviewCommandParserTest {

    private AssignInterviewCommandParser parser = new AssignInterviewCommandParser();

    @Test
    public void parse_validArgs_returnsAssignCommand() {
        Set<Index> indexes = new HashSet<>();
        indexes.add(INDEX_FIRST_PERSON);
        indexes.add(INDEX_SECOND_PERSON);

        assertParseSuccess(parser, " i=1 c=1 2", new AssignInterviewCommand(INDEX_FIRST_INTERVIEW, indexes));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "i=a c=1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignInterviewCommand.MESSAGE_USAGE));
    }

}
