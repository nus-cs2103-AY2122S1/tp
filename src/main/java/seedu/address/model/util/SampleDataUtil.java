package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.Date;
import seedu.address.model.Label;
import seedu.address.model.OrderBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyOrderBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.TaskBook;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Measurement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TaskTag;
import seedu.address.model.task.Task;


/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Gender("M"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Measurement("180_90_110"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Remark("son of Anthony"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Gender("Female"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Measurement("160_50_60_70"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Gender("f"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Measurement("165_60_80_90"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Remark("high schooler"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Gender("male"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Measurement("170_80_90"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Remark("only free on Friday"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Gender("m"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Measurement("175_90_110"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Gender("male"), new Phone("92624417"),
                    new Email("royb@example.com"), new Measurement("172_85_105"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                    getTagSet("colleagues"))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Label("Perform alterations on blazer"), new Date("2020-11-10"), new TaskTag("SO1")),
            new Task(new Label("Remind customer about payment"), new Date("2017-11-02"), new TaskTag("SO2")),
            new Task(new Label("Cut and measure fabric"), new Date("2017-10-02"), new TaskTag("General")),
        };
    }

    public static Order[] getSampleOrders() {
        return new Order[] {
            new Order(new Label("Blue blazer"), new Customer("Alex Yeoh"),
                    new Date("2020-12-10"), new Amount("10"), 1),
            new Order(new Label("Waistcoat"), new Customer("David Li"),
                    new Date("2020-12-10"), new Amount("10"), 2),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    public static ReadOnlyTaskBook getSampleTaskBook() {
        TaskBook simpleTL = new TaskBook();
        for (Task sampleTask : getSampleTasks()) {
            simpleTL.addTask(sampleTask);
        }
        return simpleTL;
    }

    public static ReadOnlyOrderBook getSampleOrderBook() {
        OrderBook simpleSB = new OrderBook();
        for (Order sampleOrders : getSampleOrders()) {
            simpleSB.addOrder(sampleOrders);
        }
        return simpleSB;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
