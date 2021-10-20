package seedu.address.logic.commands;

import javafx.stage.FileChooser;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import javax.swing.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Command to export the current view as a csv mailing list
 * parser --> command --> return string object --> UI should handle output
 */
public class MailingListCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Exported current view";

    private final Set<Prefix> prefixToWrite;

    public MailingListCommand(Set<Prefix> prefixToWrite) {
        // this.UI = UI --> rely on UI to provide the export hook
        // so if we write a location it can location
        // if we don't specify, it will just export to a local connections folder
        this.prefixToWrite = Collections.unmodifiableSet(prefixToWrite);
    }




    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // based on current list view
        // create the csv
        // pass it to UI
        // something something UI
        // if fail --> throw CommandFail

        List<Person> personList = model.getFilteredPersonList().stream().collect(Collectors.toList());

//        return new CommandResult(MESSAGE_SUCCESS,false,false,true);
        return new CommandResultExport(MESSAGE_SUCCESS,personList);

        }

}
