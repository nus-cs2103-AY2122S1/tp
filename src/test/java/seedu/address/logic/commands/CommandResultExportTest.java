package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CommandResultExportTest {


    @Test
    void testEquals() {
        List<Person> personList = List.of(new PersonBuilder().build());
        Set<Prefix> prefixes = Set.of(PREFIX_PHONE);

        CommandResultExport commandResultExport = new CommandResultExport("feedback", personList, prefixes);

        List<Person> samePersonList = List.of(new PersonBuilder().build());
        Set<Prefix> samePrefixes = Set.of(PREFIX_PHONE);

        List<Person> diffPersonList = List.of(
                new PersonBuilder().build(),
                new PersonBuilder().withName("new person").build());
        Set<Prefix> diffPrefixes = Set.of(PREFIX_PHONE, PREFIX_EMAIL);


        assertEquals(commandResultExport,
                commandResultExport);

        assertEquals(commandResultExport,
                new CommandResultExport("feedback", samePersonList, samePrefixes));

        assertNotEquals(commandResultExport,
                new CommandResultExport("notFeedback", personList, prefixes));

        assertNotEquals(commandResultExport,
                new CommandResultExport("feedback", diffPersonList, prefixes));

        assertNotEquals(commandResultExport,
                new CommandResultExport("feedback", personList, diffPrefixes));
    }
}
