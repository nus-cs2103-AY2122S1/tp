package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUMBER_OF_PEOPLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ReserveCommand extends Command{
    public static final String COMMAND_WORD = "reserve";
    public static final String MESSAGE_USAGE = String.format(
            "%s: add a new reservation with customer's phone number, number of people and time.\n"
            + "Parameters: %sPHONE %sNUMBER_OF_PEOPLE (must be a positive integer) %sTIME\n"
            + "Example: %s %s98765432 %s2 %s24/12/2021 1930.",
            COMMAND_WORD,
            PREFIX_PHONE, PREFIX_NUMBER_OF_PEOPLE, PREFIX_TIME,
            COMMAND_WORD, PREFIX_PHONE, PREFIX_NUMBER_OF_PEOPLE, PREFIX_TIME
    );
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add reservation command not implemented yet.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
