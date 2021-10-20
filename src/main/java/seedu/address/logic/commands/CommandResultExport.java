package seedu.address.logic.commands;

import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class CommandResultExport extends CommandResult{
    private final List<Person> personList;
    public CommandResultExport(String feedbackToUser, List<Person> personList) {
        super(feedbackToUser,false,false,true);
        this.personList = personList;
    }

    public List<Person> getPersonList(){
        return  this.personList;
    }

}
