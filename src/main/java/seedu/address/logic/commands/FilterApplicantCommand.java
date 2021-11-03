package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.descriptors.FilterApplicantDescriptor;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

/*
 * Filters the current list of applicants by the specified criteria.
 */
public class FilterApplicantCommand extends Command {

    public static final String COMMAND_WORD = "filter-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the current list of applicants by the specified criteria. " + "\n"
            + "Parameters: "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_STATUS + "STATUS] " + "\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_POSITION + "software engineer "
            + PREFIX_STATUS + "pending";

    public static final String MESSAGE_SUCCESS = "Applicants filtered by: %1$s";
    public static final String MESSAGE_INVALID_FILTER = "The following filters are invalid: %1$s";

    private final FilterApplicantDescriptor filterApplicantDescriptor;

    /**
     * Creates a FilterApplicantCommand according to the specified {@code filterApplicantDescriptor}.
     */
    public FilterApplicantCommand(FilterApplicantDescriptor filterApplicantDescriptor) {
        requireNonNull(filterApplicantDescriptor);
        this.filterApplicantDescriptor = new FilterApplicantDescriptor(filterApplicantDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        FilterApplicantDescriptor invalidFilterApplicantDescriptor =
                createInvalidFilterApplicantDescriptor(model, filterApplicantDescriptor);
        if (invalidFilterApplicantDescriptor.hasAnyFilter()) {
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER, invalidFilterApplicantDescriptor));
        }

        model.updateFilteredApplicantList(applicant -> applicantMatchesFilter(applicant, filterApplicantDescriptor));

        return new CommandResult(String.format(MESSAGE_SUCCESS, filterApplicantDescriptor));

    }

    /**
     * Verifies the given filters & returns a descriptor of all invalid filters.
     * The returned descriptor will be empty (verifiable with {@code FilterApplicantDescriptor::isAnyFieldEdited})
     * if the given filters are all valid.
     */
    private FilterApplicantDescriptor createInvalidFilterApplicantDescriptor(
            Model model, FilterApplicantDescriptor descriptor) {
        FilterApplicantDescriptor invalidFilterApplicantDescriptor = new FilterApplicantDescriptor();

        if (!passesFilter(descriptor::getPositionTitle, model::hasPositionWithTitle)) {
            invalidFilterApplicantDescriptor.setPositionTitle(descriptor.getPositionTitle().get());
        }

        return invalidFilterApplicantDescriptor;
    }

    /**
     * Returns true if the given Applicant passes the specified filters.
     */
    private static boolean applicantMatchesFilter(Applicant applicant, FilterApplicantDescriptor descriptor) {
        return passesFilter(descriptor::getPositionTitle, applicant::isApplyingToPositionWithTitle)
                && passesFilter(descriptor::getApplicationStatus, applicant::hasApplicationStatus);
    }

    /**
     * Convenience method to test if an optional object passes a specified filter.
     * If the object is not present (i.e. Optional.empty), the filter is passed by default.
     */
    private static <T> boolean passesFilter(Supplier<Optional<T>> toTest, Function<T, Boolean> filter) {
        return toTest.get().map(filter).orElse(true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterApplicantCommand // instanceof handles nulls
                && filterApplicantDescriptor.equals(((FilterApplicantCommand) other).filterApplicantDescriptor));
    }

}
