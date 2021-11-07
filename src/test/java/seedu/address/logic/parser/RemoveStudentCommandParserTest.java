package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDICES;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_INTEGER;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.DUPLICATE_INDICES;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_CLASS_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_INDEX_ZERO;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.INVALID_STUDENT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.SPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_CLASS_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_CLASS_INDICES;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_STUDENT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.VALID_STUDENT_INDICES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveStudentCommand;

public class RemoveStudentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RemoveStudentCommand.MESSAGE_USAGE);
    private RemoveStudentCommandParser parser = new RemoveStudentCommandParser();

    @Test
    public void parse_compulsoryFieldsMissing_failure() {

        //only student index is present, missing tuition class index
        assertParseFailure(parser, VALID_STUDENT_INDEX, MESSAGE_INVALID_FORMAT);

        //only tuition class index is present, missing student index
        assertParseFailure(parser, VALID_CLASS_INDEX, MESSAGE_INVALID_FORMAT);

        //all fields missing
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //student INDEX prefix present with no value
        assertParseFailure(parser, PREFIX_STUDENT + SPACE + VALID_CLASS_INDEX, MESSAGE_INVALID_FORMAT);

        //tuition class prefix present with no value
        assertParseFailure(parser, VALID_STUDENT_INDICES + PREFIX_TUITION_CLASS, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {

        //random string before prefix
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_STUDENT_INDICES + VALID_CLASS_INDEX,
                MESSAGE_INVALID_FORMAT);

        //random integer before prefix
        assertParseFailure(parser, PREAMBLE_INTEGER + VALID_STUDENT_INDICES + VALID_CLASS_INDEX,
                MESSAGE_INVALID_FORMAT);

        //invalid prefix
        assertParseFailure(parser, VALID_STUDENT_INDEX + VALID_CLASS_INDEX + INVALID_PREFIX,
                MESSAGE_INVALID_INDEX);

        //valid but non-required prefix
        assertParseFailure(parser, VALID_STUDENT_INDICES + VALID_CLASS_INDEX + SPACE + PREFIX_LIMIT,
                MESSAGE_INVALID_INDEX);

        //multiple tuition class indexes
        assertParseFailure(parser, VALID_CLASS_INDICES + SPACE + VALID_CLASS_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //zero student index
        assertParseFailure(parser, SPACE + PREFIX_STUDENT_INDEX + INVALID_INDEX_ZERO + SPACE
                + VALID_CLASS_INDEX, MESSAGE_INVALID_INDICES);

        //negative student index
        assertParseFailure(parser, INVALID_STUDENT_INDEX + VALID_CLASS_INDEX, MESSAGE_INVALID_INDICES);

        //zero tuition class index
        assertParseFailure(parser, VALID_STUDENT_INDEX + SPACE + PREFIX_TUITION_CLASS + INVALID_INDEX_ZERO,
                MESSAGE_INVALID_INDEX);

        //negative tuition class index
        assertParseFailure(parser, VALID_STUDENT_INDEX + INVALID_CLASS_INDEX, MESSAGE_INVALID_INDEX);

    }

    @Test
    public void parse_removeStudentIndex_success() {
        //remove one index
        List<Index> indexList = new ArrayList<>();
        indexList.add(INDEX_FIRST);
        RemoveStudentCommand removeOneIndex = getCommand(indexList, INDEX_FIRST);
        assertParseSuccess(parser, VALID_STUDENT_INDEX + VALID_CLASS_INDEX, removeOneIndex);

        //remove multiple indexes
        List<Index> multipleIndexes = new ArrayList<>();
        multipleIndexes.add(INDEX_FIRST);
        multipleIndexes.add(INDEX_SECOND);
        RemoveStudentCommand removeIndexes = getCommand(multipleIndexes, INDEX_FIRST);
        assertParseSuccess(parser, VALID_STUDENT_INDICES + VALID_CLASS_INDEX, removeIndexes);

        //remove duplicate indexes
        List<Index> repetitiveIndexes = new ArrayList<>();
        repetitiveIndexes.add(INDEX_FIRST);
        repetitiveIndexes.add(INDEX_SECOND);
        RemoveStudentCommand indices = getCommand(repetitiveIndexes, INDEX_FIRST);
        assertParseSuccess(parser, SPACE + PREFIX_STUDENT_INDEX + DUPLICATE_INDICES + VALID_CLASS_INDEX , indices);
    }

    private RemoveStudentCommand getCommand(List<Index> students, Index tuitionClass) {
        return new RemoveStudentCommand(students, tuitionClass);
    }

}
