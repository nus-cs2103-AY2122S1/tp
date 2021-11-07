package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL_OF_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_EMPLOYMENT_TYPE + "EMPLOYMENT TYPE "
            + PREFIX_EXPECTED_SALARY + "EXPECTED SALARY "
            + PREFIX_LEVEL_OF_EDUCATION + "LEVEL OF EDUCATION "
            + PREFIX_EXPERIENCE + "YEARS OF EXPERIENCE "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_INTERVIEW + "INTERVIEW TIME]"
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ROLE + "Software Engineer "
            + PREFIX_EMPLOYMENT_TYPE + "Full time "
            + PREFIX_EXPECTED_SALARY + "3500 "
            + PREFIX_LEVEL_OF_EDUCATION + "PhD "
            + PREFIX_EXPERIENCE + "3 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_INTERVIEW + "2021-10-21, 12:00 "
            + PREFIX_NOTES + "This applicant is a good candidate for the job! ";


    public static final String MESSAGE_SUCCESS = "New person added: %1$s";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     *
     * @param person Person to be added
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            List<Person> duplicates = model.getDuplicate(toAdd);
            assert !duplicates.isEmpty() : "There should be at least 1 duplicate.";

            throw new CommandException(createDuplicateMessage(duplicates, toAdd));
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Creates a UI message informing user of existing duplicate applicants.
     * {@code duplicates} provided must contain at least 1 applicant.
     *
     * @param duplicates List of applicants who share the same phone number and email with {@code editedPerson}
     * @param toCheck applicant to be checked for duplicates with
     * @return String accumulation of all duplicate applicants
     */
    private String createDuplicateMessage(List<Person> duplicates, Person toCheck) {
        assert toCheck != null;
        assert duplicates != null;
        assert !duplicates.isEmpty() : "There should be at least 1 duplicate";
        StringBuilder stringBuilder = new StringBuilder();
        for (Person duplicate : duplicates) {
            stringBuilder.append(duplicate);
        }
        return "The applicant to be added " + toCheck
                + " shares either the same phone number or email as the following applicant(s):\n"
                + stringBuilder;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
