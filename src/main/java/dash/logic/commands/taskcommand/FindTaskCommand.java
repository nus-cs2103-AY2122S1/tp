package dash.logic.commands.taskcommand;

import static dash.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static dash.logic.parser.CliSyntax.PREFIX_PERSON;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import dash.commons.core.Messages;
import dash.commons.util.CollectionUtil;
import dash.logic.commands.Command;
import dash.logic.commands.CommandResult;
import dash.model.Model;
import dash.model.task.CompletionStatusContainsKeywordsPredicate;
import dash.model.task.DateContainsKeywordsPredicate;
import dash.model.task.DescriptionContainsKeywordsPredicate;
import dash.model.task.PersonContainsKeywordsPredicate;
import dash.model.task.TagTaskContainsKeywordsPredicate;
import dash.model.task.Task;
import dash.model.task.TaskDate;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD
            + " [" + PREFIX_TASK_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_TASK_DATE + "DATE] "
            + "[" + PREFIX_TASK_DATE + "TIME] "
            + "[" + PREFIX_TASK_DATE + "DATE, TIME] "
            + "[" + PREFIX_COMPLETION_STATUS + "COMPLETION_STATUS (true/false)] "
            + "[" + PREFIX_PERSON + "PERSON_INDEX]... "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_DESCRIPTION + "Assignment "
            + PREFIX_TAG + "urgent\n";

    private final FindTaskDescriptor findTaskDescriptor;
    private final Predicate<Task> predicate;

    /**
     * Constructor for the FindPersonCommand that takes in a FindPersonDescriptor.
     */
    public FindTaskCommand(FindTaskDescriptor findTaskDescriptor) {
        requireNonNull(findTaskDescriptor);

        this.findTaskDescriptor = findTaskDescriptor;
        this.predicate = findTaskDescriptor.combinePredicates();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCommand // instanceof handles nulls
                && findTaskDescriptor.equals(((FindTaskCommand) other).findTaskDescriptor)); // state check
    }

    /**
     * Stores the predicates to find a task with. Each non-empty field value will determine
     * the fields to search for a specific task.
     */
    public static class FindTaskDescriptor {
        private DescriptionContainsKeywordsPredicate descPredicate;
        private TagTaskContainsKeywordsPredicate tagPredicate;
        private DateContainsKeywordsPredicate datePredicate;
        private PersonContainsKeywordsPredicate personPredicate;
        private CompletionStatusContainsKeywordsPredicate completionStatusPredicate;

        public FindTaskDescriptor() {
        }

        /**
         * Returns true if at least one predicate is present.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(descPredicate, tagPredicate);
        }

        public void setDesc(List<String> descPredicate) {
            this.descPredicate = new DescriptionContainsKeywordsPredicate(descPredicate);
        }

        public Optional<DescriptionContainsKeywordsPredicate> getDesc() {
            return Optional.ofNullable(descPredicate);
        }

        public void setTags(List<String> tagPredicate) {
            this.tagPredicate = new TagTaskContainsKeywordsPredicate(tagPredicate);
        }

        public Optional<TagTaskContainsKeywordsPredicate> getTags() {
            return Optional.ofNullable(tagPredicate);
        }

        public void setDate(TaskDate datePredicate) {
            this.datePredicate = new DateContainsKeywordsPredicate(datePredicate);
        }

        public Optional<DateContainsKeywordsPredicate> getDate() {
            return Optional.ofNullable(datePredicate);
        }

        public void setPerson(List<String> personPredicate) {
            this.personPredicate = new PersonContainsKeywordsPredicate(personPredicate);
        }

        public Optional<PersonContainsKeywordsPredicate> getPerson() {
            return Optional.ofNullable(personPredicate);
        }

        public void setCompletionStatus(boolean completionStatusPredicate) {
            this.completionStatusPredicate = new CompletionStatusContainsKeywordsPredicate(completionStatusPredicate);
        }

        public Optional<CompletionStatusContainsKeywordsPredicate> getCompletionStatus() {
            return Optional.ofNullable(completionStatusPredicate);
        }

        /**
         * This method takes all the conditions to check and combines them into one predicate.
         *
         * @return A combined predicate object to use on a filteredlist.
         */
        public Predicate<Task> combinePredicates() {
            Predicate<Task> result = x -> true;
            if (descPredicate != null) {
                result = result.and(descPredicate);
            }
            if (tagPredicate != null) {
                result = result.and(tagPredicate);
            }
            if (personPredicate != null) {
                result = result.and(personPredicate);
            }
            if (datePredicate != null) {
                result = result.and(datePredicate);
            }
            if (completionStatusPredicate != null) {
                result = result.and(completionStatusPredicate);
            }
            return result;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindTaskDescriptor)) {
                return false;
            }

            // state check
            FindTaskDescriptor f = (FindTaskDescriptor) other;

            return getDesc().equals(f.getDesc())
                    && getTags().equals(f.getTags())
                    && getCompletionStatus().equals(getCompletionStatus())
                    && getDate().equals(getDate());
        }
    }
}
