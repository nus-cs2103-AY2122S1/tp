package dash.logic.commands.taskcommand;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import dash.commons.core.Messages;
import dash.commons.util.CollectionUtil;
import dash.logic.commands.Command;
import dash.logic.commands.CommandResult;
import dash.logic.parser.CliSyntax;
import dash.model.Model;
import dash.model.task.DescriptionContainsKeywordsPredicate;
import dash.model.task.TagTaskContainsKeywordsPredicate;
import dash.model.task.Task;


/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTaskCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters Type 1: KEYWORD\n"
            + "Parameters Type 2: [KEYWORD]"
            + "[" + CliSyntax.PREFIX_TAG + "TAG]... (at least 1)\n"
            + "Example 1: " + COMMAND_WORD + " CS2103T Homework\n"
            + "Example 2: " + COMMAND_WORD + " [" + CliSyntax.PREFIX_TAG + "] " + " Groupwork\n";

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
     * Stores the predicates to find a person with. Each non-empty field value will determine
     * the fields to search for a specific person.
     */
    public static class FindTaskDescriptor {
        private DescriptionContainsKeywordsPredicate descPredicate;
        private TagTaskContainsKeywordsPredicate tagPredicate;

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
                    && getTags().equals(f.getTags());
        }
    }
}
