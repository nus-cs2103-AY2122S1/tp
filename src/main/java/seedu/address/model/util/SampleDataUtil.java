package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.person.employee.Employee;
import seedu.address.model.person.employee.JobTitle;
import seedu.address.model.person.employee.Leaves;
import seedu.address.model.person.employee.Salary;
import seedu.address.model.person.supplier.DeliveryDetails;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplyType;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"))
        };
    }

    public static Supplier[] getSampleSuppliers() {
        return new Supplier[] {
            new Supplier(new Name("Supplier1"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("friends"),
                    new SupplyType("Chicken"), new DeliveryDetails("7th of every month")),
            new Supplier(new Name("Supplier2"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends"), new SupplyType("Rice"),
                    new DeliveryDetails("Every Tuesday")),
            new Supplier(new Name("Supplier3"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getTagSet("neighbours"),
                    new SupplyType("Noodles"), new DeliveryDetails("Every 3 months")),
            new Supplier(new Name("Supplier4"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("family"),
                    new SupplyType("Cutlery"), new DeliveryDetails("Every Wednesday")),
            new Supplier(new Name("Supplier5"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Address("Blk 47 Tampines Street 20, #17-35"), getTagSet("classmates"),
                    new SupplyType("Potatoes"), new DeliveryDetails("Every last friday of the month")),
            new Supplier(new Name("Supplier6"), new Phone("92624417"), new Email("royb@example.com"),
                    new Address("Blk 45 Aljunied Street 85, #11-31"), getTagSet("colleagues"),
                    new SupplyType("Flour"), new DeliveryDetails("Everyday 6pm"))
        };
    }

    public static Employee[] getSampleEmployees() {
        return new Employee[]{
            new Employee(new Name("Javier Phon"), new Phone("81234567"), new Email("javphon@example.com"),
                new Address("Blk 5 Bukit Batok St 2, #177-35"), getTagSet("Employee"), new Leaves("14"),
                new Salary("8000"), new JobTitle("Team Lead")),
            new Employee(new Name("Chetwin Low"), new Phone("85555555"), new Email("chetlqh@example.com"),
                new Address("Blk 7 Yew Tee St 10, #10-35"), getTagSet("Employee"), new Leaves("10"),
                new Salary("1200"), new JobTitle("Intern")),
            new Employee(new Name("Clement Kong"), new Phone("99999999"), new Email("clementk@example.com"),
                new Address("Blk 20 Bishan St 37, #03-10"), getTagSet("Employee", "Myself"), new Leaves("14"),
                new Salary("5500"), new JobTitle("Project Manager")),
            new Employee(new Name("Pham Ba Thang"), new Phone("82974023"), new Email("pham@example.com"),
                new Address("Blk 17 Bukit Gombat St 20, #01-02"), getTagSet("Employee"), new Leaves("14"),
                new Salary("6000"), new JobTitle("Senior Developer")),
            new Employee(new Name("Lee Hern Ping"), new Phone("90000000"), new Email("Leehp@example.com"),
                new Address("Blk 1 Seragoon St 3, #05-05"), getTagSet("Employee"), new Leaves("14"),
                new Salary("5000"), new JobTitle("Junior Developer"))
        };
    }

    public static Customer[] getSampleCustomers() {
        return new Customer[] {
            new Customer(new Name("Javier Phon"), new Phone("87438807"), new Email("imphonofyou@example"
                        + ".com"), new Address("Blk 30 Geylang Street 29, #06-40"), new LoyaltyPoints("1000"),
                        getAllergySet("McSpicy", "Pineapples"), getSpecialRequestSet("Doesn't eat meat"),
                        getTagSet("boss")),
            new Customer(new Name("Pham Ba Thang"), new Phone("99272758"), new Email("igotchupham"
                        + "@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new LoyaltyPoints("10000"), getAllergySet("Peanuts", "milk"),
                        getSpecialRequestSet("Nothing under 100 dollars"),
                        getTagSet("most handsome", "friends")),
            new Customer(new Name("Clement Kong"), new Phone("93210283"), new Email("kingkongbingbong"
                        + "@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new LoyaltyPoints("5000"), getAllergySet("Cheese", "rice"),
                        getSpecialRequestSet("Loves window seats"),
                        getTagSet("mouse hunt specialist")),
            new Customer(new Name("Lee Hern Ping"), new Phone("91031282"), new Email("wohernhandsome"
                        + "@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new LoyaltyPoints("1000"), getAllergySet("Apples"),
                        getSpecialRequestSet("Every visit is his birthday"),
                        getTagSet("second most handsome after thang")),
            new Customer(new Name("Chetwin Low"), new Phone("92492021"), new Email("chetwinchickenwing"
                        + "@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                        new LoyaltyPoints("0500"),
                        getAllergySet("coriander"),
                        getSpecialRequestSet("Everything should be deep fried"),
                        getTagSet("sick of AB3"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        for (Supplier sampleSupplier : getSampleSuppliers()) {
            sampleAb.addSupplier(sampleSupplier);
        }
        return sampleAb;
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
     * Returns an allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a special request set containing the list of strings given.
     */
    public static Set<SpecialRequest> getSpecialRequestSet(String... strings) {
        return Arrays.stream(strings)
                .map(SpecialRequest::new)
                .collect(Collectors.toSet());
    }

}
