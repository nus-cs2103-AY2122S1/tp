package seedu.smartnus.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.logic.commands.EditCommand;
import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_IMPORTANCE, PREFIX_TAG, PREFIX_OPTION,
                        PREFIX_ANSWER);

        Index index = parseEditIndex(argMultimap);

        EditQuestionDescriptor editQuestionDescriptor = new EditCommand.EditQuestionDescriptor();

        setDescriptorQuestion(argMultimap, editQuestionDescriptor);
        setDescriptorImportance(argMultimap, editQuestionDescriptor);
        setDescriptorTags(argMultimap, editQuestionDescriptor);
        setDescriptorAnswers(argMultimap, editQuestionDescriptor);
        setDescriptorWrongChoices(argMultimap, editQuestionDescriptor);

        checkValidChoicesForEdit(editQuestionDescriptor);

        checkAtLeastOneFieldEdited(editQuestionDescriptor);

        return new EditCommand(index, editQuestionDescriptor);
    }

    private Index parseEditIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
    }

    private void setDescriptorQuestion(ArgumentMultimap argMultimap, EditQuestionDescriptor editQuestionDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editQuestionDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
    }

    private void setDescriptorImportance(ArgumentMultimap argMultimap, EditQuestionDescriptor editQuestionDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_IMPORTANCE).isPresent()) {
            editQuestionDescriptor.setImportance(ParserUtil.parseImportance(
                    argMultimap.getValue(PREFIX_IMPORTANCE).get()));
        }
    }

    private void setDescriptorTags(ArgumentMultimap argMultimap, EditQuestionDescriptor editQuestionDescriptor)
            throws ParseException {
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editQuestionDescriptor::setTags);
    }

    private void setDescriptorAnswers(ArgumentMultimap argMultimap, EditQuestionDescriptor editQuestionDescriptor)
            throws ParseException {
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            Choice answer = ParserUtil.parseAnswerForEdit(argMultimap.getValue(PREFIX_ANSWER).get());
            editQuestionDescriptor.setAnswer(answer);
            // this is necessary since Parser does not have access to Model/Question subtype
            // TFQ choices require additional parsing; user input (e.g. T) is not the same as choice title (e.g. True)
            Set<Choice> tfqChoices = ParserUtil.parseTrueFalseAnswerForEdit(answer.getTitle());
            editQuestionDescriptor.setTfqChoices(tfqChoices);
            // SAQ answer also requires additional parsing
            Set<Choice> saqChoices = ParserUtil.parseEditSaqAnswer(answer);
            editQuestionDescriptor.setSaqChoices(saqChoices);
        }
    }

    private void setDescriptorWrongChoices(ArgumentMultimap argMultimap, EditQuestionDescriptor editQuestionDescriptor)
            throws ParseException {
        Set<Choice> wrongChoices = ParserUtil.parseWrongChoicesForEdit(argMultimap.getAllValues(PREFIX_OPTION));
        if (!wrongChoices.isEmpty()) { // incorrect options are specified
            editQuestionDescriptor.setWrongChoices(wrongChoices);
        }
    }

    /**
     * Throws ParseException if there are duplicate choice titles between the incorrect and correct choices.
     * If either the incorrect choices or correct choices are not present, the method returns immediately as
     * duplicate titles within the incorrect options are already checked for during parsing and current question
     * types only support one answer.
     *
     * @param editQuestionDescriptor The descriptor that stores the updated correct and wrong choices.
     * @throws ParseException if there are duplicate choice titles between the incorrect and correct choices.
     */
    private void checkValidChoicesForEdit(EditQuestionDescriptor editQuestionDescriptor) throws ParseException {
        if (!editQuestionDescriptor.getWrongChoices().isPresent() || !editQuestionDescriptor.getAnswer().isPresent()) {
            return;
        }
        Set<Choice> updatedChoices = new HashSet<>(editQuestionDescriptor.getWrongChoices().get());
        updatedChoices.add(editQuestionDescriptor.getAnswer().get());
        if (!ParserUtil.isValidChoiceTitles(updatedChoices)) {
            throw new ParseException(Question.MESSAGE_DUPLICATE_CHOICES);
        }
    }

    private void checkAtLeastOneFieldEdited(EditQuestionDescriptor editQuestionDescriptor) throws ParseException {
        if (!editQuestionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
