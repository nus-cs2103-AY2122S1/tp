package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.member.EmailContainsKeywordsPredicate;
import seedu.address.model.member.IdContainsKeywordsPredicate;
import seedu.address.model.member.Member;
import seedu.address.model.member.NameContainsKeywordsPredicate;
import seedu.address.model.member.PhoneContainsKeywordsPredicate;
import seedu.address.model.member.RegistrationDateContainsKeywordsPredicate;

/**
 * Finds and lists all members in ezFoodie whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    /**
     * Stands for find command.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * Stands for the message of find command.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all members whose ids, names, phones or emails contain any of "
            + "the specified keywords (case-insensitive) or within a specific of registration dates "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: (can be multiple keywords)\n"
            + "Find by member ID: " + PREFIX_MEMBER + " [" + PREFIX_ID + "ID]...\n"
            + "Find by name: " + PREFIX_MEMBER + " [" + PREFIX_NAME + "NAME]...\n"
            + "Find by phone: " + PREFIX_MEMBER + " [" + PREFIX_PHONE + "PHONE]...\n"
            + "Find by email: " + PREFIX_MEMBER + " [" + PREFIX_EMAIL + "EMAIL]...\n"
            + "Find by registration date: " + PREFIX_MEMBER + " [" + PREFIX_DATE + "REGISTRATION_DATE]...\n"
            + "Example:\n"
            + "Find by member ID: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_ID + "00001 00002\n"
            + "Find by name: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_NAME + "Alex Yu\n"
            + "Find by phone: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_PHONE + "87438807 93210283\n"
            + "Find by email: " + COMMAND_WORD + " " + PREFIX_MEMBER + " " + PREFIX_EMAIL
            + "alexyeoh@example.com irfan@example.com\n"
            + "Find by registration date: " + COMMAND_WORD + " " + PREFIX_MEMBER
            + " " + PREFIX_DATE + " 2021-01-02 2021-01-03";

    private final Predicate<Member> predicate;

    /**
     * Constructs FindCommand through Id from input {@code predicate}.
     */
    public FindCommand(IdContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Constructs FindCommand through Name {@code predicate}.
     *
     * @param predicate the details of contain key words for name.
     */
    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Constructs FindCommand through Phone.
     *
     * @param predicate the details of contain key words for phone.
     */
    public FindCommand(PhoneContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Constructs FindCommand through Email {@code predicate}.
     *
     * @param predicate the details of contain key words for email.
     */
    public FindCommand(EmailContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Constructs FindCommand through RegistrationDate {@code predicate}.
     *
     * @param predicate the details of contain key words for registration date.
     */
    public FindCommand(RegistrationDateContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to find command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMemberList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_MEMBERS_LISTED_OVERVIEW, model.getUpdatedMemberList().size()));
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
