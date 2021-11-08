package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.smartnus.logic.commands.Command;
import seedu.smartnus.logic.commands.ExitCommand;
import seedu.smartnus.logic.commands.HelpCommand;
import seedu.smartnus.logic.commands.quiz.NextQuestionCommand;
import seedu.smartnus.logic.commands.quiz.PrevQuestionCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.logic.parser.quiz.AnswerMcqCommandParser;
import seedu.smartnus.logic.parser.quiz.AnswerSaqCommandParser;
import seedu.smartnus.logic.parser.quiz.AnswerTfqCommandParser;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.quiz.QuizManager;




/**
 * Parses user input during a quiz.
 */
public class QuizInputParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, QuizManager quizManager) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case NextQuestionCommand.COMMAND_WORD:
            return new NextQuestionCommand(quizManager);

        case PrevQuestionCommand.COMMAND_WORD:
            return new PrevQuestionCommand(quizManager);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:

        }
        if (quizManager.currQuestion() instanceof MultipleChoiceQuestion) {
            return new AnswerMcqCommandParser().parse(userInput, quizManager);
        } else if (quizManager.currQuestion() instanceof TrueFalseQuestion) {
            return new AnswerTfqCommandParser().parse(userInput, quizManager);
        } else if (quizManager.currQuestion() instanceof ShortAnswerQuestion) {
            return new AnswerSaqCommandParser().parse(userInput, quizManager);
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
