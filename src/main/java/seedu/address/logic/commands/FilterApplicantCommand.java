package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.application.Application.ApplicationStatus;
import seedu.address.model.position.Title;

/*
 * Filters the current list of applicants by the specified criteria.
 */
public class FilterApplicantCommand extends Command {

    public static final String COMMAND_WORD = "filter-applicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters the current list of applicants by the specified criteria. "
            + "Parameters: "
            + "[" + PREFIX_POSITION + "POSITION] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "Example: " + COMMAND_WORD
            + PREFIX_POSITION + "software engineer "
            + PREFIX_STATUS + "pending";

    public static final String MESSAGE_SUCCESS = "Applicants filtered by: %1$s";
    public static final String MESSAGE_INVALID_FILTER = "The following filters are invalid: %1$s";
    public static final String MESSAGE_NOT_FILTERED =
            "At least one valid filter criteria must be provided. Usage:\n" + MESSAGE_USAGE;

    private final FilterApplicantDescriptor filterApplicantDescriptor;

    /**
     * Creates an AddPositionCommand to add the specified {@code Position}
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
        if (invalidFilterApplicantDescriptor.isAnyFieldEdited()) {
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

    /**
     * Stores the details to filter the applicant list with. Each non-empty field value will
     * apply an additional filtering criterion to the applicant list.
     */
    public static class FilterApplicantDescriptor {
        private Title positionTitle;
        private ApplicationStatus applicationStatus;

        public FilterApplicantDescriptor() {}

        /**
         * Copy constructor.
         */
        public FilterApplicantDescriptor(FilterApplicantDescriptor toCopy) {
            setPositionTitle(toCopy.positionTitle);
            setApplicationStatus(toCopy.applicationStatus);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(positionTitle, applicationStatus);
        }

        public void setPositionTitle(Title positionTitle) {
            this.positionTitle = positionTitle;
        }

        public Optional<Title> getPositionTitle() {
            return Optional.ofNullable(positionTitle);
        }

        public void setApplicationStatus(ApplicationStatus applicationStatus) {
            this.applicationStatus = applicationStatus;
        }

        public Optional<ApplicationStatus> getApplicationStatus() {
            return Optional.ofNullable(applicationStatus);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FilterApplicantDescriptor)) {
                return false;
            }

            // state check
            FilterApplicantDescriptor e = (FilterApplicantDescriptor) other;

            return positionTitle.equals(e.positionTitle)
                    && applicationStatus.equals(e.applicationStatus);
        }

        @Override
        public int hashCode() {
            return Objects.hash(positionTitle, applicationStatus);
        }

        @Override
        public String toString() {
            Stream<String> filterDescriptions = Stream.of(getPositionTitle(), getApplicationStatus())
                    .flatMap(Optional::stream)
                    .map(filter -> filter.getClass().getSimpleName() + ": " + filter);
            return filterDescriptions.collect(Collectors.joining("; ", "", "."));
        }
    }
}
