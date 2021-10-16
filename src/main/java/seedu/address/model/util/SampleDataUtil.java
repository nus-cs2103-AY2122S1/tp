package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"), getModuleCodeSet("CS1231 T12 L02"),
                    new Phone("87438807"), new TeleHandle("@alexyeoh"), new Remark("overseas")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"), getModuleCodeSet("CS2030S T10"),
                    new Phone("99272758"), new TeleHandle("@berniceyu"), EMPTY_REMARK),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"), getModuleCodeSet("CS1231"),
                        new Phone("93210283"), new TeleHandle("@charlotteO"), EMPTY_REMARK),
            new Person(new Name("David Li"), new Email("lidavid@example.com"), getModuleCodeSet("CS2030S", "CS2040"),
                    new Phone("91031282"), new TeleHandle("@davidli"), new Remark("late")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"), getModuleCodeSet("CS2040 T05"),
                    new Phone("92492021"), new TeleHandle("@irfan"), EMPTY_REMARK),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"), getModuleCodeSet("CS2103T"),
                    new Phone("92624417"), new TeleHandle("@royBala"), new Remark("lab5 done"))
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
     * Returns a module code set containing the list of strings given.
     */
    public static Set<ModuleCode> getModuleCodeSet(String... strings) {
        return Arrays.stream(strings)
                .map(SampleDataUtil::parseModuleCode)
                .collect(Collectors.toSet());
    }

    /**
     * Helper method for {@code getModuleCodeSet} to parse {@code String moduleCode} into a {@code ModuleCode}.
     */
    public static ModuleCode parseModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        String trimmedModuleCode = moduleCode.trim();
        String[] moduleCodeArr = trimmedModuleCode.split("\\s+");
        assert moduleCodeArr.length >= 1 : "Array should not be empty\n";
        Set<Tag> tags = Arrays.stream(moduleCodeArr).skip(1).map(Tag::new).collect(Collectors.toSet());
        return new ModuleCode(moduleCodeArr[0], tags);
    }

}
