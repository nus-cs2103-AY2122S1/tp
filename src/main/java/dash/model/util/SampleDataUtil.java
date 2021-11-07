package dash.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import dash.model.AddressBook;
import dash.model.ReadOnlyAddressBook;
import dash.model.UserInputList;
import dash.model.person.Address;
import dash.model.person.Email;
import dash.model.person.Name;
import dash.model.person.Person;
import dash.model.person.Phone;
import dash.model.tag.Tag;
import dash.model.task.CompletionStatus;
import dash.model.task.Task;
import dash.model.task.TaskDate;
import dash.model.task.TaskDescription;
import dash.model.task.TaskList;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[]{
            new Task(new TaskDescription("Submit CS2100 Assignment by 23:59"), new CompletionStatus(false),
                    new TaskDate("21/10/2021", false), getPersonSetFromIndices(1, 2), getTagSet("homework")),
            new Task(new TaskDescription("ST2334 quiz before Friday"), new CompletionStatus(false),
                    new TaskDate("21/01/2021, 1900", false), getPersonSetFromIndices(0, 3), getTagSet("homework")),
            new Task(new TaskDescription("Do PR review"), new CompletionStatus(true),
                    new TaskDate("05/10/2021, 07:00 PM", false), getPersonSetFromIndices(4, 5), getTagSet("groupwork")),
            new Task(new TaskDescription("Catch up with ST lectures"), new CompletionStatus(false),
                    new TaskDate("1500", false), getPersonSetFromIndices(3, 4, 5), getTagSet("classmates"))
        };
    }

    public static String[] getSampleUserInputs() {
        return new String[] { "complete 3", "add d/CS2100 Tutorial 7", "contacts", "add n/Bob" };
    }

    public static TaskList getSampleTaskList() {
        TaskList sampleTl = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTl.add(sampleTask);
        }
        return sampleTl;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static UserInputList getSampleUserInputList() {
        UserInputList sampleUil = new UserInputList();
        for (String userInput : getSampleUserInputs()) {
            sampleUil.add(userInput);
        }
        return sampleUil;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a person set from the people given.
     */
    public static Set<Person> getPersonSet(Person... people) {
        return new HashSet<>(Arrays.asList(people));
    }

    /**
     * Returns a person set from the indices given.
     */
    public static Set<Person> getPersonSetFromIndices(Integer... indices) {
        return Arrays.stream(indices)
                .map(i -> getSamplePersons()[i])
                .collect(Collectors.toSet());
    }

}
