package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_FALSE;
import static seedu.smartnus.logic.parser.AddTfqCommandParser.ANSWER_TRUE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.note.NoteNameContainsKeywordsPredicate;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.testutil.EditQuestionDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_IMPORTANCE_1 = "1";
    public static final String VALID_IMPORTANCE_2 = "2";
    public static final String VALID_QUESTION_1 = "What is 1+1?";
    public static final String VALID_QUESTION_2 = "2+2 = ?";
    public static final String VALID_QUESTION_3 = "2*2 = ?";
    public static final String VALID_QUESTION_4 = "What is the name of harry's mom?";
    public static final String VALID_QUESTION_5 = "Is 1+1 = 2?";
    public static final String VALID_QUESTION_6 = "Is 2*4 = 4?";
    public static final String VALID_QUESTION_7 = "Who died in the Triwizard tournament?";
    public static final String VALID_ANSWER_1 = "2";
    public static final String VALID_ANSWER_2 = "4";
    public static final String VALID_ANSWER_3 = "3";
    public static final String VALID_TRUE_FALSE_ANSWER_1 = ANSWER_TRUE;
    public static final String VALID_TRUE_FALSE_ANSWER_2 = ANSWER_FALSE;
    public static final String VALID_SAQ_ANSWER_1 = "J. K. Rowling";
    public static final String VALID_OPTION_1 = "1";
    public static final String VALID_OPTION_3 = "3";
    public static final String VALID_OPTION_4 = "4";
    public static final String VALID_OPTION_5 = "5";
    public static final String VALID_SAQ_KEYWORD_1 = "Rowling";
    public static final String VALID_SAQ_KEYWORD_2 = "j";
    public static final String VALID_SAQ_KEYWORD_3 = "Cedric";
    public static final String VALID_SAQ_KEYWORD_4 = "Diggory";
    public static final String VALID_TAG_1 = "CS2100";
    public static final String VALID_TAG_2 = "ST2334";
    public static final String VALID_TAG_3 = "harry";
    public static final String VALID_TAG_4 = "CS2103T";
    public static final String VALID_NOTE_1 = "CS2103T is a fun mod";
    public static final String VALID_NOTE_2 = "CS2100 is not a fun mod";

    public static final String NOTE_DESC_1 = " " + PREFIX_NOTE + VALID_NOTE_1;
    public static final String NOTE_DESC_2 = " " + PREFIX_NOTE + VALID_NOTE_2;
    public static final String STAT_DESC_1 = " " + PREFIX_TAG + VALID_TAG_1;
    public static final String STAT_DESC_2 = STAT_DESC_1 + " " + PREFIX_TAG + VALID_TAG_2;
    public static final String TAG_DESC_3 = " " + PREFIX_TAG + VALID_TAG_3;
    public static final String TAG_DESC_4 = " " + PREFIX_TAG + VALID_TAG_4;
    public static final String IMPORTANCE_DESC_1 = " " + PREFIX_IMPORTANCE + VALID_IMPORTANCE_1;
    public static final String IMPORTANCE_DESC_2 = " " + PREFIX_IMPORTANCE + VALID_IMPORTANCE_2;
    public static final String QUESTION_DESC_1 = " " + PREFIX_QUESTION + VALID_QUESTION_1;
    public static final String QUESTION_DESC_2 = " " + PREFIX_QUESTION + VALID_QUESTION_2;
    public static final String QUESTION_DESC_3 = " " + PREFIX_QUESTION + VALID_QUESTION_3;
    public static final String QUESTION_DESC_4 = " " + PREFIX_QUESTION + VALID_QUESTION_6;
    public static final String QUESTION_DESC_5 = " " + PREFIX_QUESTION + VALID_QUESTION_7;
    public static final String ANSWER_DESC_1 = " " + PREFIX_ANSWER + VALID_ANSWER_1;
    public static final String ANSWER_DESC_2 = " " + PREFIX_ANSWER + VALID_ANSWER_2;
    public static final String ANSWER_DESC_3 = " " + PREFIX_ANSWER + VALID_ANSWER_3;
    public static final String TRUE_ANSWER_DESC = " " + PREFIX_ANSWER + VALID_TRUE_FALSE_ANSWER_1;
    public static final String FALSE_ANSWER_DESC = " " + PREFIX_ANSWER + VALID_TRUE_FALSE_ANSWER_2;
    public static final String KEYWORD_DESC_1 = " " + PREFIX_KEYWORD + VALID_SAQ_KEYWORD_1;
    public static final String SAQ_ANSWER_DESC_1 = " " + PREFIX_ANSWER + "J. K." + KEYWORD_DESC_1;
    public static final String SAQ_ANSWER_DESC_2 = " " + PREFIX_ANSWER + PREFIX_KEYWORD + VALID_SAQ_KEYWORD_3 + "#12 "
            + PREFIX_KEYWORD + "#!@" + VALID_SAQ_KEYWORD_4 + "(,)><";
    public static final String OPTION_DESC_1 = " " + PREFIX_OPTION + VALID_OPTION_1;
    public static final String OPTION_DESC_3 = " " + PREFIX_OPTION + VALID_OPTION_3;
    public static final String OPTION_DESC_4 = " " + PREFIX_OPTION + VALID_OPTION_4;
    public static final String OPTION_DESC_5 = " " + PREFIX_OPTION + VALID_OPTION_5;
    public static final String OPTIONS_DESC_1 = " " + OPTION_DESC_1 + OPTION_DESC_3 + OPTION_DESC_4;
    public static final String OPTIONS_DESC_2 = OPTIONS_DESC_1 + OPTION_DESC_5;

    public static final String INVALID_NAME_DESC = " " + PREFIX_QUESTION + " "; // empty string not allowed for names
    public static final String INVALID_KEYWORD_DESC_1 = " " + PREFIX_KEYWORD + " "; // blank keyword not allowed
    // keywords must contain alphanumeric characters
    public static final String INVALID_KEYWORD_DESC_2 = " " + PREFIX_KEYWORD + "!@#$%^&*()-+_=+";
    // 'a' not allowed in importance
    public static final String INVALID_IMPORTANCE_DESC = " " + PREFIX_IMPORTANCE + "1a";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION; // empty string not allowed for question
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER; // empty string not allowed for answer
    public static final String INVALID_OPTION_DESC_1 = " " + PREFIX_OPTION; // empty string not allowed for option
    public static final String INVALID_OPTION_DESC_2 = " " + OPTION_DESC_1 + OPTION_DESC_1 + OPTION_DESC_4; // duplicate

    public static final String INVALID_NOTE_TAG_DESC = " " + PREFIX_TAG + "truth:)"; // ':)' not allowed in tags
    public static final String INVALID_NOTE_DESC = " " + PREFIX_NOTE; // empty string not allowed for question

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditQuestionDescriptor DESC_AMY;
    public static final EditCommand.EditQuestionDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditQuestionDescriptorBuilder().withName(VALID_QUESTION_3)
                .withImportance(VALID_IMPORTANCE_1).withTags(VALID_TAG_4).build();
        DESC_BOB = new EditQuestionDescriptorBuilder().withName(VALID_QUESTION_4)
                .withImportance(VALID_IMPORTANCE_2)
                .withTags(VALID_TAG_3, VALID_TAG_4).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the {@code SmartNus}, filtered question list and selected question in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        SmartNus expectedSmartNus = new SmartNus(actualModel.getSmartNus());
        List<Question> expectedFilteredList = new ArrayList<>(actualModel.getFilteredQuestionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSmartNus, actualModel.getSmartNus());
        assertEquals(expectedFilteredList, actualModel.getFilteredQuestionList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the question at the given {@code targetIndex} in the
     * {@code model}'s list of questions.
     */
    public static void showQuestionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredQuestionList().size());

        Question question = model.getFilteredQuestionList().get(targetIndex.getZeroBased());
        final String[] splitName = question.getName().fullName.split("\\s+");
        model.updateFilteredQuestionList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredQuestionList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the note at the given {@code targetIndex} in the
     * {@code model}'s list of notes.
     */
    public static void showNoteAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredNoteList().size());

        Note note = model.getFilteredNoteList().get(targetIndex.getZeroBased());
        final String[] splitName = note.getTitle().split("\\s+");
        model.updateFilteredNoteList(new NoteNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredNoteList().size());
    }

}
