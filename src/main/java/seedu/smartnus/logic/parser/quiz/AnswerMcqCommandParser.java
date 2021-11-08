package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_END_OF_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_MCQ_ANSWER_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.smartnus.logic.commands.quiz.AnswerMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.quiz.QuizManager;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class AnswerMcqCommandParser implements QuizParser<AnswerMcqCommand> {

    private static final String MCQ_REGEX = "^[a-dA-D]$";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerMcqCommand parse(String args, QuizManager quizManager) throws ParseException {
        if (args.matches(MCQ_REGEX)) {
            return new AnswerMcqCommand(args, quizManager);
        } else if (quizManager.isCurrentQuestionAnswered()) {
            String endMessage = quizManager.isLastQuestion() ? MESSAGE_END_OF_QUIZ : MESSAGE_CONTINUE_QUIZ;
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND + "\n" + endMessage);
        } else {
            throw new ParseException(MESSAGE_INVALID_MCQ_ANSWER_FORMAT);
        }
    }

}
