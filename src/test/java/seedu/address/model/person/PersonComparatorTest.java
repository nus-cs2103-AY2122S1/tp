package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonComparatorTest {

    private Person alex = new PersonBuilder()
            .withName("Alex Marcus")
            .withPhone("91234567")
            .withEmail("e0000007@u.nus.edu")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withTags("friends")
            .withGitHubId("alex-marcus")
            .withNusNetworkId("e0000007")
            .withType("student")
            .withStudentId("A0000010X")
            .withTutorialId("00")
            .build();

    private Person carol = new PersonBuilder()
            .withName("George Best")
            .withPhone("97897897")
            .withEmail("e0000009@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carol-heinz")
            .withNusNetworkId("e0000009")
            .withType("student")
            .withStudentId("A0001000X")
            .withTutorialId("00")
            .build();

    @Test
    public void compare_prefixName_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("n/"));
        assertTrue(comparator.compare(carol, alex) < 0);
    }

    @Test
    public void compare_tutorialId_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("T/"));
        assertTrue(comparator.compare(alex, carol) < 0);
    }
}
