package seedu.address.logic.commands;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;


/**
 * Lists all persons in the address book to the user.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all inputs for a specific category, "
            + "based on the given prefix "
            + "and displays them as a list.\n"
            + "Parameters: PREFIX/KEYWORDS \n"
            + "Example: " + COMMAND_WORD + " r/";

    private final Prefix prefix;

    public ShowCommand(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) {

        requireNonNull(model);

        String message = getUniqueCategoryInputs(model);

        return new CommandResult(message);
    }

    private String getUniqueCategoryInputs(Model model) {

        String prefixString = prefix.getPrefix();

        if (prefixString.equals(PREFIX_NAME.getPrefix())) {

            List<String> names = model.getFilteredPersonList().stream()
                    .map(x -> x.getName().toString()).collect(Collectors.toList());

            StringBuilder sb = new StringBuilder("Here are all the names present:\n");
            for (String name: names) {
                sb.append(name);
                sb.append("\n");
            }

            return sb.toString();
        }

        if (prefixString.equals(PREFIX_PHONE.getPrefix())) {

            List<String> phoneNumbers = model.getFilteredPersonList().stream()
                    .map(x -> x.getPhone().toString()).collect(Collectors.toList());

            StringBuilder sb = new StringBuilder("Here are all the phone numbers present:\n");
            for (String phoneNumber: phoneNumbers) {
                sb.append(phoneNumber);
                sb.append("\n");
            }

            return sb.toString();
        }

        if (prefixString.equals(PREFIX_EMAIL.getPrefix())) {

            List<String> emails = model.getFilteredPersonList().stream()
                    .map(x -> x.getEmail().toString()).collect(Collectors.toList());

            StringBuilder sb = new StringBuilder("Here are all the emails present:\n");
            for (String email: emails) {
                sb.append(email);
                sb.append("\n");
            }

            return sb.toString();
        }

        if (prefixString.equals(PREFIX_ROLE.getPrefix())) {

            Set<String> roles = model.getFilteredPersonList().stream()
                    .map(x -> x.getRole().toString()).collect(Collectors.toSet());

            StringBuilder sb = new StringBuilder("Here are all the roles present:\n");
            for (String role: roles) {
                sb.append(role);
                sb.append("\n");
            }

            return sb.toString();
        }

        if (prefixString.equals(PREFIX_EMPLOYMENT_TYPE.getPrefix())) {

            Set<String> employmentTypes = model.getFilteredPersonList().stream()
                    .map(x -> x.getEmploymentType().toString()).collect(Collectors.toSet());

            StringBuilder sb = new StringBuilder("Here are all the employment types present:\n");
            for (String employmentType: employmentTypes) {
                sb.append(employmentType);
                sb.append("\n");
            }

            return sb.toString();
        }

        return "";
    }
}
