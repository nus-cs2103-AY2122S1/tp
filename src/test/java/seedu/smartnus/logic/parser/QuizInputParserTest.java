package seedu.smartnus.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_3;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.smartnus.logic.Logic;
import seedu.smartnus.logic.LogicManager;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.HelpCommand;
import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.commands.quiz.AnswerTfqCommand;
import seedu.smartnus.logic.commands.quiz.NextQuestionCommand;
import seedu.smartnus.logic.commands.quiz.PrevQuestionCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.ModelManager;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.quiz.QuizManager;
import seedu.smartnus.storage.JsonSmartNusStorage;
import seedu.smartnus.storage.JsonUserPrefsStorage;
import seedu.smartnus.storage.StorageManager;


public class QuizInputParserTest {

    @TempDir
    public Path temporaryFolder;

    private QuizManager quizManager;

    private final QuizInputParser parser = new QuizInputParser();

    @BeforeEach
    public void setUp() {
        JsonSmartNusStorage smartNusStorage =
                new JsonSmartNusStorage(temporaryFolder.resolve("typicalQuestionsSmartNus.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder
                .resolve("TypicalUserPref.json"));
        StorageManager storage = new StorageManager(smartNusStorage, userPrefsStorage);
        Logic logic = new LogicManager(new ModelManager(), storage);
        quizManager = new QuizManager(logic.getFilteredQuizQuestionList());
    }

    @Test
    public void parseCommand_next() throws Exception {
        NextQuestionCommand command =
                (NextQuestionCommand) parser.parseCommand(NextQuestionCommand.COMMAND_WORD, quizManager);
        assertEquals(new NextQuestionCommand(quizManager), command);
    }

    @Test
    public void parseCommand_prev() throws Exception {
        PrevQuestionCommand command =
                (PrevQuestionCommand) parser.parseCommand(PrevQuestionCommand.COMMAND_WORD, quizManager);
        assertEquals(new PrevQuestionCommand(quizManager), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, quizManager) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", quizManager) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, quizManager) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", quizManager) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_answerMcqQuestion() throws ParseException {
        List<Question> typicalQuestions = new ArrayList<>();
        typicalQuestions.add(MCQ_QUESTION_3);
        QuizManager oneQuestionQuiz = new QuizManager(typicalQuestions);
        assertTrue(parser.parseCommand("a", oneQuestionQuiz) instanceof AnswerMcqCommand);
    }

    @Test
    public void parseCommand_answerTfQuestion() throws ParseException {
        Set<Choice> validChoices = new HashSet<>();
        validChoices.add(new Choice(Choice.TRUE_CHOICE_TITLE, false));
        validChoices.add(new Choice(Choice.FALSE_CHOICE_TITLE, true));
        List<Question> typicalQuestions = new ArrayList<>();
        typicalQuestions.add(new TrueFalseQuestion(new Name(VALID_QUESTION_1), new Importance(VALID_IMPORTANCE_1),
                new HashSet<>(), validChoices));
        QuizManager oneQuestionQuiz = new QuizManager(typicalQuestions);
        assertTrue(parser.parseCommand("T", oneQuestionQuiz) instanceof AnswerTfqCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand("", quizManager));
    }

}
