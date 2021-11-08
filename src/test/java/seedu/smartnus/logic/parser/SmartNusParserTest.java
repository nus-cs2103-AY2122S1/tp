package seedu.smartnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.smartnus.logic.commands.CommandTestUtil.ANSWER_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.FALSE_ANSWER_DESC;
import static seedu.smartnus.logic.commands.CommandTestUtil.IMPORTANCE_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.OPTION_DESC_5;
import static seedu.smartnus.logic.commands.CommandTestUtil.QUESTION_DESC_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_ANSWER_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_5;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalIndexes.INDEX_FIRST_QUESTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.smartnus.commons.core.theme.LightTheme;
import seedu.smartnus.logic.commands.ClearCommand;
import seedu.smartnus.logic.commands.DeleteCommand;
import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.FindCommand;
import seedu.smartnus.logic.commands.HelpCommand;
import seedu.smartnus.logic.commands.ListCommand;
import seedu.smartnus.logic.commands.ThemeCommand;
import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.logic.commands.questions.AddTfqCommand;
import seedu.smartnus.logic.commands.quiz.QuizCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.question.predicates.NameContainsKeywordsPredicate;
import seedu.smartnus.testutil.EditQuestionDescriptorBuilder;
import seedu.smartnus.testutil.QuestionBuilder;
import seedu.smartnus.testutil.QuestionUtil;

public class SmartNusParserTest {

    private final SmartNusParser parser = new SmartNusParser();

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " question " + INDEX_FIRST_QUESTION.getOneBased());
        assertEquals(new DeleteCommand("question", INDEX_FIRST_QUESTION), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Question question = new QuestionBuilder().build();
        EditCommand.EditQuestionDescriptor descriptor = new EditQuestionDescriptorBuilder(question).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_QUESTION.getOneBased() + " " + QuestionUtil.getEditQuestionDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_QUESTION, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        ArrayList<Predicate<Question>> arr = new ArrayList<>();
        arr.add(new NameContainsKeywordsPredicate(keywords));
        assertEquals(new FindCommand(arr), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " note") instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " question") instanceof ListCommand);
    }

    @Test
    public void parseCommand_quiz() throws Exception {
        assertTrue(parser.parseCommand(QuizCommand.COMMAND_WORD) instanceof QuizCommand);
    }

    @Test
    public void parseCommand_mcq() throws Exception {
        AddMcqCommand command = (AddMcqCommand) parser.parseCommand(AddMcqCommand.COMMAND_WORD
                + QUESTION_DESC_1 + OPTION_DESC_5 + OPTION_DESC_4 + OPTION_DESC_3 + ANSWER_DESC_1 + IMPORTANCE_DESC_1);
        assertNotNull(command);
        Set<Choice> expectedChoices = new HashSet<>();
        expectedChoices.add(new Choice(VALID_OPTION_5, false));
        expectedChoices.add(new Choice(VALID_OPTION_4, false));
        expectedChoices.add(new Choice(VALID_OPTION_3, false));
        expectedChoices.add(new Choice(VALID_ANSWER_1, true));

        MultipleChoiceQuestion expectedQuestion = new MultipleChoiceQuestion(new Name(VALID_QUESTION_1),
                new Importance(VALID_IMPORTANCE_1), new HashSet<>(), expectedChoices);
        assertEquals(new AddMcqCommand(expectedQuestion), command);
    }

    @Test
    public void parseCommand_tfq() throws Exception {
        AddTfqCommand command = (AddTfqCommand) parser.parseCommand(
                AddTfqCommand.COMMAND_WORD + QUESTION_DESC_1 + FALSE_ANSWER_DESC + IMPORTANCE_DESC_1);
        assertNotNull(command);
        Set<Choice> correctChoices = new HashSet<>();
        correctChoices.add(new Choice(TRUE_CHOICE_TITLE, false));
        correctChoices.add(new Choice(FALSE_CHOICE_TITLE, true));
        TrueFalseQuestion expectedQuestion = new TrueFalseQuestion(new Name(VALID_QUESTION_1),
                new Importance(VALID_IMPORTANCE_1), new HashSet<>(), correctChoices);
        assertEquals(new AddTfqCommand(expectedQuestion), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_theme() throws Exception {
        ThemeCommand command = (ThemeCommand) parser.parseCommand(
                ThemeCommand.COMMAND_WORD + " " + ThemeCommand.LIGHT_KEYWORD);
        assertEquals(new ThemeCommand(new LightTheme()), command);
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(ThemeCommand.COMMAND_WORD + " abc"));
    }
}
