package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitHubId;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetworkId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    private static final Person alexYeoh = createPerson("Alex Yeoh", "87438807", "e0123456@u.nus.edu",
            "Blk 30 Geylang Street 29, #06-40", "test1", "e0123456", "student",
            "A0123456A", "01", "friends");

    private static final Person berniceYu = createPerson("Bernice Yu", "99272758",
            "e0123457@u.nus.edu", "Blk 30 Lorong 3 Serangoon Gardens, #07-18", "test2",
            "e0123457", "student", "A0123456B", "02", "colleagues", "friends");

    private static final Person charlotteOliveiro = createPerson("Charlotte Oliveiro", "93210283",
            "e0123458@u.nus.edu", "Blk 11 Ang Mo Kio Street 74, #11-04", "test3",
            "e0123458", "student", "A0123456C", "03", "neighbours");

    private static final Person davidLi = createPerson("Irfan Ibrahim", "92492021",
            "e0123450@u.nus.edu", "Blk 47 Tampines Street 20, #17-35", "test5",
            "e0123450", "student", "A0123456E", "05", "classmates");

    private static final Person irfanIbrahim = createPerson("Irfan Ibrahim", "92492021",
            "e0123450@u.nus.edu", "Blk 47 Tampines Street 20, #17-35" , "test5",
            "e0123450", "student", "A0123456E", "05", "classmates");

    private static final Person royBalakrishnan = createPerson("Roy Balakrishnan", "92624417",
            "e0123451@u.nus.edu", "Blk 45 Aljunied Street 85, #11-31", "test6",
            "e0123451", "student", "A0123456F", "06", "colleagues");

    public static Person[] getSamplePersons() {
        return new Person[] { alexYeoh, berniceYu, charlotteOliveiro, davidLi, irfanIbrahim, royBalakrishnan };
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
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Create a Person with the given values
     * @param name Name of the person
     * @param phone Phone of the person
     * @param email Email of the person
     * @param address address of the person
     * @param gitHubId gitHubId of the person
     * @param nusNetworkId nusNetworkId of the person
     * @param type tupe of the person
     * @param studentId studentId of the person
     * @param tutorialId tutorialId of the person
     * @param tags tags of the person
     * @return A person
     */
    public static Person createPerson(String name, String phone, String email, String address, String gitHubId,
                                      String nusNetworkId, String type, String studentId, String tutorialId,
                                      String... tags) {
        return new Person(new Name(name), new Phone(phone), new Email(email), new Address(address), getTagSet(tags),
                new GitHubId(gitHubId), new NusNetworkId(nusNetworkId), new Type(type), new StudentId(studentId),
                new TutorialId(tutorialId));
    }
}
