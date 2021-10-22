package seedu.address.logic.commands;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * CommandResult that stores extra fields
 */
public class CommandResultExport extends CommandResult{
    private final List<Person> personList;
    private final List<Prefix> prefixes;
    public CommandResultExport(String feedbackToUser, List<Person> personList, Collection<Prefix> prefixes) {
        super(feedbackToUser,false,false,true);
        requireNonNull(personList);
        requireNonNull(prefixes);
        this.personList = List.copyOf(personList);
        this.prefixes = List.copyOf(prefixes);
    }

    public List<Person> getPersonList(){
        return  this.personList;
    }

    public List<Prefix> getPrefixes(){
        return  this.prefixes;
    }
}
