package seedu.modulink.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.modulink.model.AddressBook;
import seedu.modulink.model.ReadOnlyAddressBook;
import seedu.modulink.model.person.Email;
import seedu.modulink.model.person.GitHubUsername;
import seedu.modulink.model.person.Name;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.Phone;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.model.person.TelegramHandle;
import seedu.modulink.model.tag.Mod;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Your name"), new StudentId("A0000000A"), new Phone("81234567"),
                new Email("youremail@email.com"), new GitHubUsername("your_github_user"),
                new TelegramHandle("yourtelehandle"), false,
                new HashSet<>(), true),
            new Person(new Name("Alex Yeoh"), new StudentId("A1234567R"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new GitHubUsername("alex_yeoh_y"),
                new TelegramHandle("alexyeoh"), false,
                getTagSet("CS2103T", "CS2106", "CS2101 need member"), true),
            new Person(new Name("Zachary Lau"), new StudentId("A1234567H"), new Phone("96523487"),
                new Email("zachlau@example.com"), new GitHubUsername("zachattach"),
                new TelegramHandle("zacharrryyyy"), false,
                getTagSet("CS2100", "CS2106", "CS2101 need group"), false),
            new Person(new Name("Ng Jia Yuan"), new StudentId("A1234567N"), new Phone("98432567"),
                new Email("ngjy@example.com"), new GitHubUsername("yuan"),
                new TelegramHandle("yuan"), false,
                getTagSet("CS5210 need group", "CS2106", "CS2100 need member"), false),
            new Person(new Name("Charlton Tan"), new StudentId("A1234567L"), new Phone("97324615"),
                new Email("charles@example.com"), new GitHubUsername("C-dollarsign"),
                new TelegramHandle("followmyspotify"), false,
                getTagSet("CS2101 need group", "IS1101"), false),
            new Person(new Name("Ahmad bin Ali"), new StudentId("A1234567B"), new Phone("95238541"),
                new Email("aba@example.com"), new GitHubUsername("alibinahmad"),
                new TelegramHandle("diversity"), false,
                getTagSet("CS2100", "CS2106", "CS2103T need group"), false),
            new Person(new Name("Bernice Yu"), new StudentId("A1234568X"), new Phone("99272758"),
                new Email("berniceyu@example.com"), new GitHubUsername(null),
                new TelegramHandle("berniceYuuu"), false,
                getTagSet("CS2100", "CS3230 need group", "CS4234 need member"), false),
            new Person(new Name("Charlotte Oliveiro"), new StudentId("A1234569Y"), new Phone("93210283"),
                new Email("charlotte@example.com"), new GitHubUsername("charlotte_oliveiro"),
                new TelegramHandle("charlotte24"), false,
                getTagSet("CS1101S", "CS3230 need member"), false),
            new Person(new Name("David Li"), new StudentId("A1234570Z"), new Phone("91031282"),
                new Email("lidavid@example.com"), new GitHubUsername("Davidli"),
                new TelegramHandle(null), false,
                getTagSet("CS2101"), false),
            new Person(new Name("Irfan Ibrahim"), new StudentId("A1234571R"), new Phone("92492021"),
                new Email("irfan@example.com"), new GitHubUsername("Irfanib"),
                new TelegramHandle("irfanibrahim"), false,
                getTagSet("CS2101 need member", "ES2660 need group", "CS2103T"), false),
            new Person(new Name("Roy Balakrishnan"), new StudentId("A1234572X"), new Phone("92624417"),
                new Email("royb@example.com"), new GitHubUsername("Roybalakrishnan"),
                new TelegramHandle("heyitsroy"), false,
                getTagSet("CS2100", "CS2103T", "CS2101 need group"), false),
            new Person(new Name("Nicole Wong"), new StudentId("A1234567Q"), new Phone("96523546"),
                new Email("niolewong@example.com"), new GitHubUsername("NicoleW"),
                new TelegramHandle("yellowNicole"), false,
                getTagSet("CS2101 need member", "MA2001"), false),
            new Person(new Name("Jeremy Tan"), new StudentId("A1234567W"), new Phone("96875439"),
                new Email("jTan2000@example.com"), new GitHubUsername("jTanremy"),
                new TelegramHandle("jeret"), false,
                getTagSet("CS2101", "CS1101S need member", "CS2103T"), false),
            new Person(new Name("Aniq Nathaniel"), new StudentId("A1234567E"), new Phone("92584685"),
                new Email("aniqNathan@example.com"), new GitHubUsername("NathAniq"),
                new TelegramHandle("Aniqle4urThoughts"), false,
                getTagSet("CS2101 need group", "CS4234", "CS2103T need group"), false),
            new Person(new Name("Timmy Turner"), new StudentId("A1234567E"), new Phone("95483265"),
                new Email("timmyturns@example.com"), new GitHubUsername("TimTam"),
                new TelegramHandle(null), false,
                getTagSet("ST2334 need group", "GEA1000 need member"), false)
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Mod> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Mod::new)
                .collect(Collectors.toSet());
    }

}
