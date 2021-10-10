package seedu.academydirectory.logic.parser;

import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_ADD_PARTICIPATON;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_STUDIO_SESSION;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.academydirectory.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.ParticipationCommand;

public class ParticipationCommandParserTest {
    private ParticipationCommandParser parser = new ParticipationCommandParser();
    private final String nonEmptySession = " 1 ";
    private final String participationCount = " 1 ";

    @Test
    public void parse_indexSpecified_success() {
        // have attendance
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_STUDIO_SESSION + nonEmptySession
                + PREFIX_ADD_PARTICIPATON
                + participationCount;
        ArrayList<Index> indexArrayList = new ArrayList<>();
        indexArrayList.add(INDEX_FIRST_STUDENT);
        ParticipationCommand expectedCommand =
                new ParticipationCommand(1, 1, indexArrayList);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ParticipationCommand.MESSAGE_USAGE);

        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInputNoIndex = " "
                + PREFIX_STUDIO_SESSION
                + nonEmptySession
                + PREFIX_ADD_PARTICIPATON
                + participationCount;
        String userInputNoSession = targetIndex.getOneBased()
                + " "
                + PREFIX_STUDIO_SESSION
                + " "
                + PREFIX_ADD_PARTICIPATON
                + participationCount;
        String userInputNoParticipationCount = targetIndex.getOneBased()
                + " "
                + PREFIX_STUDIO_SESSION
                + nonEmptySession
                + PREFIX_ADD_PARTICIPATON;

        // no parameters
        assertParseFailure(parser, ParticipationCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, userInputNoIndex, expectedMessage);

        // no session
        assertParseFailure(parser, userInputNoSession, expectedMessage);

        // no participation count
        assertParseFailure(parser, userInputNoParticipationCount, expectedMessage);

    }
}
