package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_MISSING_ARGUMENT;
import static seedu.programmer.logic.commands.CommandTestUtil.LAB_NUM;
import static seedu.programmer.logic.commands.CommandTestUtil.VALID_LAB_NO;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.programmer.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.programmer.logic.commands.DeleteLabCommand;
import seedu.programmer.model.student.Lab;
import seedu.programmer.testutil.LabBuilder;


public class DeleteLabCommandParserTest {

    private DeleteLabCommandParser parser = new DeleteLabCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        Lab deleteLab = new LabBuilder().withLabNum(VALID_LAB_NO).build();
        assertParseSuccess(parser, LAB_NUM, new DeleteLabCommand(deleteLab));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_MISSING_ARGUMENT,
                DeleteLabCommand.MESSAGE_USAGE));
    }
}
