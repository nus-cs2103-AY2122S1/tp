package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.List;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * CommandResult that stores extra fields
 */
public class CommandResultExport extends CommandResult {
    private final List<Person> personList;
    private final List<Prefix> prefixes;

    /**
     * Constructs a {@code CommandResultExport} with the specified {@code feedbackToUser},
     * {@code List<Person>},{@code Collection<Prefix>}.
     */
    public CommandResultExport(String feedbackToUser, List<Person> personList, Collection<Prefix> prefixes) {
        super(feedbackToUser, false, false, true);
        requireNonNull(personList);
        requireNonNull(prefixes);
        this.personList = List.copyOf(personList);
        this.prefixes = List.copyOf(prefixes);
    }

    public List<Person> getPersonList() {
        return this.personList;
    }

    public List<Prefix> getPrefixes() {
        return this.prefixes;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) {
            return false;
        }
        CommandResultExport otherC = (CommandResultExport) other;

        return personList.equals(otherC.personList)
                && prefixes.equals(otherC.prefixes);
    }
}
