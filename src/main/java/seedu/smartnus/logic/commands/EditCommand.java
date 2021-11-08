package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.commons.core.Messages.MESSAGE_NOT_IN_QUESTION_PANEL;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.smartnus.commons.core.Messages;
import seedu.smartnus.commons.core.index.Index;
import seedu.smartnus.commons.util.CollectionUtil;
import seedu.smartnus.logic.commands.exceptions.CommandException;
import seedu.smartnus.model.Model;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.ui.panel.QuestionListPanel;

/**
 * Edits the details of an existing question in SmartNus.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the question identified "
            + "by the index number used in the displayed question list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer between 1 and 2147483647 both inclusive)\n"
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_IMPORTANCE + "IMPORTANCE] "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_ANSWER + "ANSWER] "
            + "[" + PREFIX_OPTION + "OPTION]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_IMPORTANCE + "3 "
            + PREFIX_TAG + "CS2103T ";

    public static final String MESSAGE_UNRECOGNISED_QUESTION_TYPE = "Unrecognised question type to edit.";
    public static final String MESSAGE_EDIT_QUESTION_SUCCESS = "Edited Question: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_QUESTION = "This question already exists in SmartNUS.";

    public static final String MESSAGE_NO_ANSWER = "Answer (ans/) must be specified.";
    public static final String MESSAGE_NO_OPTIONS = "Options (opt/) must be specified.";

    private final Index index;
    private final EditQuestionDescriptor editQuestionDescriptor;

    /**
     * @param index of the question in the filtered question list to edit
     * @param editQuestionDescriptor details to edit the question with
     */
    public EditCommand(Index index, EditQuestionDescriptor editQuestionDescriptor) {
        requireNonNull(index);
        requireNonNull(editQuestionDescriptor);

        this.index = index;
        this.editQuestionDescriptor = new EditQuestionDescriptor(editQuestionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.getPanel().equals(QuestionListPanel.QUESTION_PANEL)) {
            throw new CommandException(MESSAGE_NOT_IN_QUESTION_PANEL);
        }

        List<Question> lastShownList = model.getFilteredQuestionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_QUESTION_DISPLAYED_INDEX);
        }

        Question questionToEdit = lastShownList.get(index.getZeroBased());
        assert questionToEdit != null;

        Name updatedName = editQuestionDescriptor.getName().orElse(questionToEdit.getName());
        Importance updatedImportance = editQuestionDescriptor.getImportance().orElse(questionToEdit.getImportance());
        Set<Tag> updatedTags = editQuestionDescriptor.getTags().orElse(questionToEdit.getTags());

        Question editedQuestion = null;
        if (questionToEdit instanceof MultipleChoiceQuestion) {
            editedQuestion = createEditedMcq(questionToEdit, updatedName, updatedImportance, updatedTags);
        } else if (questionToEdit instanceof TrueFalseQuestion) {
            editedQuestion = createEditedTf(questionToEdit, updatedName, updatedImportance, updatedTags);
        } else if (questionToEdit instanceof ShortAnswerQuestion) {
            editedQuestion = createEditedSaq(questionToEdit, updatedName, updatedImportance, updatedTags);
        }
        assert editedQuestion != null : MESSAGE_UNRECOGNISED_QUESTION_TYPE;

        if (!questionToEdit.isSameQuestion(editedQuestion) && model.hasQuestion(editedQuestion)) {
            throw new CommandException(MESSAGE_DUPLICATE_QUESTION);
        }

        model.setQuestion(questionToEdit, editedQuestion);
        return new CommandResult(String.format(MESSAGE_EDIT_QUESTION_SUCCESS, editedQuestion));
    }

    /**
     * Creates and returns a {@code Question} with the details of {@code questionToEdit}
     * edited with {@code editQuestionDescriptor}.
     */
    private MultipleChoiceQuestion createEditedMcq(Question questionToEdit,
                                                          Name updatedName,
                                                          Importance updatedImportance, Set<Tag> updatedTags)
            throws CommandException {
        Set<Choice> wrongChoices = editQuestionDescriptor.getWrongChoices().orElse(questionToEdit.getWrongChoices());
        Choice answer = editQuestionDescriptor.getAnswer().orElse(questionToEdit.getCorrectChoice());
        // Both incorrect options and correct answer must be present for mcq
        if (editQuestionDescriptor.getWrongChoices().isPresent()) {
            if (!editQuestionDescriptor.getAnswer().isPresent()) {
                throw new CommandException(MESSAGE_NO_ANSWER);
            }
        } else if (editQuestionDescriptor.getAnswer().isPresent()) {
            throw new CommandException(MESSAGE_NO_OPTIONS);
        }
        Set<Choice> updatedChoices = new HashSet<>(wrongChoices);
        updatedChoices.add(answer);
        MultipleChoiceQuestion updatedMcq = new MultipleChoiceQuestion(updatedName, updatedImportance,
                updatedTags, updatedChoices, questionToEdit.getStatistic());
        if (!updatedMcq.isValidQuestion()) {
            throw new CommandException(MultipleChoiceQuestion.MESSAGE_VALID_MCQ);
        }
        return updatedMcq;
    }

    private ShortAnswerQuestion createEditedSaq(Question questionToEdit,
                                                   Name updatedName,
                                                   Importance updatedImportance, Set<Tag> updatedTags)
            throws CommandException {
        // Incorrect options should not be specified for SAQ
        if (editQuestionDescriptor.getWrongChoices().isPresent()) {
            throw new CommandException(ShortAnswerQuestion.MESSAGE_OPTIONS_INVALID);
        }
        Set<Choice> updatedChoices = editQuestionDescriptor.getSaqChoices().orElse(questionToEdit.getChoices());
        ShortAnswerQuestion updatedSaq = new ShortAnswerQuestion(updatedName, updatedImportance,
                updatedTags, updatedChoices, questionToEdit.getStatistic());
        if (!updatedSaq.isValidQuestion()) {
            throw new CommandException(ShortAnswerQuestion.MESSAGE_VALID_SAQ);
        }
        return updatedSaq;
    }

    private TrueFalseQuestion createEditedTf(Question questionToEdit,
                                             Name updatedName,
                                             Importance updatedImportance, Set<Tag> updatedTags)
            throws CommandException {
        // Incorrect options should not be specified for T/F Questions
        if (editQuestionDescriptor.getWrongChoices().isPresent()) {
            throw new CommandException(TrueFalseQuestion.MESSAGE_OPTIONS_INVALID);
        }
        Set<Choice> updatedChoices = editQuestionDescriptor.getTfqChoices().orElse(questionToEdit.getChoices());
        TrueFalseQuestion updatedTfq = new TrueFalseQuestion(updatedName, updatedImportance, updatedTags,
                updatedChoices, questionToEdit.getStatistic());
        if (!updatedTfq.isValidQuestion()) {
            throw new CommandException(TrueFalseQuestion.MESSAGE_ANSWER_INVALID);
        }
        return updatedTfq;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editQuestionDescriptor.equals(e.editQuestionDescriptor);
    }

    /**
     * Stores the details to edit the question with. Each non-empty field value will replace the
     * corresponding field value of the question.
     */
    public static class EditQuestionDescriptor {
        private Name name;
        private Importance importance;
        private Set<Tag> tags;
        private Set<Choice> wrongChoices; // options specified after opt/ that do not need additional parsing
        private Choice answer; // answer specified after ans/ that does not need additional parsing
        private Set<Choice> parsedTfqChoices;
        private Set<Choice> parsedSaqChoices;

        public EditQuestionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditQuestionDescriptor(EditQuestionDescriptor toCopy) {
            setName(toCopy.name);
            setImportance(toCopy.importance);
            setTags(toCopy.tags);
            setWrongChoices(toCopy.wrongChoices);
            setAnswer(toCopy.answer);
            setTfqChoices(toCopy.parsedTfqChoices);
            setSaqChoices(toCopy.parsedSaqChoices);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, importance, tags, wrongChoices, answer);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setImportance(Importance importance) {
            this.importance = importance;
        }

        public Optional<Importance> getImportance() {
            return Optional.ofNullable(importance);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code choices} to this object's {@code choices}.
         * A defensive copy of {@code choices} is used internally.
         */
        public void setWrongChoices(Set<Choice> choices) {
            this.wrongChoices = (choices != null) ? new HashSet<>(choices) : null;
        }

        /**
         * Returns an unmodifiable set of wrong choices, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code choices} is null.
         */
        public Optional<Set<Choice>> getWrongChoices() {
            return (wrongChoices != null) ? Optional.of(Collections.unmodifiableSet(wrongChoices)) : Optional.empty();
        }

        public void setAnswer(Choice answer) {
            this.answer = (answer != null) ? answer : null;
        }

        public Optional<Choice> getAnswer() {
            return (answer != null) ? Optional.of(answer) : Optional.empty();
        }

        public void setTfqChoices(Set<Choice> choices) {
            parsedTfqChoices = (choices != null) ? new HashSet<>(choices) : null;
        }

        public Optional<Set<Choice>> getTfqChoices() {
            return (parsedTfqChoices != null) ? Optional.of(Collections.unmodifiableSet(parsedTfqChoices))
                    : Optional.empty();
        }

        public void setSaqChoices(Set<Choice> choices) {
            parsedSaqChoices = (choices != null) ? new HashSet<>(choices) : null;
        }

        public Optional<Set<Choice>> getSaqChoices() {
            return (parsedSaqChoices != null) ? Optional.of(Collections.unmodifiableSet(parsedSaqChoices))
                    : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditQuestionDescriptor)) {
                return false;
            }

            // state check
            EditQuestionDescriptor e = (EditQuestionDescriptor) other;

            return getName().equals(e.getName())
                    && getImportance().equals(e.getImportance())
                    && getTags().equals(e.getTags())
                    && getWrongChoices().equals(e.getWrongChoices())
                    && getAnswer().equals(e.getAnswer());
        }
    }
}
