package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Phone;

import java.time.LocalDateTime;

public class ReserveCommand extends Command{
    public static final String COMMAND_WORD = "reserve";
    public static final String MESSAGE_USAGE = String.format(
            "%1$s: add a new reservation with customer's phone number, number of people and time.\n"
            + "Parameters: NUMBER_OF_PEOPLE %2$sPHONE (must be a positive integer) %3$sTIME\n"
            + "Example: %1$s 2 %2$s98765432 %3$s24/12/2021 1930.",
            COMMAND_WORD,
            PREFIX_PHONE, PREFIX_TIME
    );

    Phone phone;
    int numberOfPeople;
    LocalDateTime time;

    public ReserveCommand(Phone phone, int numberOfPeople, LocalDateTime time) {
        requireAllNonNull(phone, time);
        this.phone = phone;
        this.numberOfPeople = numberOfPeople;
        this.time = time;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format("Reserve: phone=%s, numberOfPeople=%d, time=%s", phone, numberOfPeople, time)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReserveCommand)) {
            return false;
        }

        ReserveCommand that = (ReserveCommand) o;
        return phone.equals(that.phone)
                && numberOfPeople == that.numberOfPeople
                && time.equals(that.time);
    }
}
