package seedu.smartnus.testutil;

import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * A utility class for Question.
 */
public class QuestionUtil {

    /**
     * Returns the part of command string for the given {@code question}'s details.
     */
    public static String getQuestionDetails(Question question) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + question.getName().fullName + " ");
        sb.append(PREFIX_IMPORTANCE + question.getImportance().value + " ");
        question.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditQuestionDescriptor}'s details.
     */
    public static String getEditQuestionDescriptorDetails(EditQuestionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_QUESTION).append(name.fullName).append(" "));
        descriptor.getImportance().ifPresent(importance -> sb.append(PREFIX_IMPORTANCE).append(importance.value)
                .append(" "));
        if (descriptor.getWrongChoices().isPresent()) {
            Set<Choice> wrongChoices = descriptor.getWrongChoices().get();
            wrongChoices.forEach(wc -> sb.append(PREFIX_OPTION).append(wc.getTitle()).append(" "));
        }
        if (descriptor.getAnswer().isPresent()) {
            Choice answer = descriptor.getAnswer().get();
            sb.append(PREFIX_ANSWER).append(answer.getTitle()).append(" ");
        }
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
