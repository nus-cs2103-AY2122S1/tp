package seedu.address.logic.commands;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Command to export the current view as a csv mailing list
 * parser --> command --> return string object --> UI should handle output
 */
public class MailingListCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Exported current view";

    private final Set<Prefix> prefixToWrite;

    public MailingListCommand(Set<Prefix> prefixToWrite) {
        this.prefixToWrite = Collections.unmodifiableSet(prefixToWrite);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList().stream().collect(Collectors.toList());
        return new CommandResultExport(MESSAGE_SUCCESS,personList,prefixToWrite);
    }

}
