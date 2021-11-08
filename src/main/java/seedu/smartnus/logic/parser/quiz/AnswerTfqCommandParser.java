package seedu.smartnus.logic.parser.quiz;

import static seedu.smartnus.commons.core.Messages.MESSAGE_CONTINUE_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_END_OF_QUIZ;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_TF_ANSWER_FORMAT;
import static seedu.smartnus.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.smartnus.logic.commands.quiz.AnswerTfqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.quiz.QuizManager;

public class AnswerTfqCommandParser implements QuizParser<AnswerTfqCommand> {

    private static final String TF_REGEX = "(?i)(^true$|^false$|^[tf]$)";

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AnswerTfqCommand parse(String args, QuizManager quizManager) throws ParseException {
        if (args.matches(TF_REGEX)) {
            return new AnswerTfqCommand(args, quizManager);
        } else if (quizManager.isCurrentQuestionAnswered()) {
            String endMessage = quizManager.isLastQuestion() ? MESSAGE_END_OF_QUIZ : MESSAGE_CONTINUE_QUIZ;
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND + "\n" + endMessage);
        } else {
            throw new ParseException(MESSAGE_INVALID_TF_ANSWER_FORMAT);
        }
    }
}
