package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;
import seedu.address.testutil.PersonBuilder;

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

    private Person alice = new PersonBuilder()
            .withName("Alice Pauline")
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
            .withName("Carol Heinz")
            .withPhone("97897897")
            .withEmail("e0000009@u.nus.edu")
            .withAddress("wall street")
            .withGitHubId("carol-heinz")
            .withNusNetworkId("e0000009")
            .withType("tutor")
            .withStudentId("A0001000X")
            .withTutorialId("01")
            .build();


    @Test
    public void compare_prefixNameReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("n/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixNameReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("n/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixPhoneReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("p/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixPhoneReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("p/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixPhoneValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("p/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixEmailReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("e/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixEmailReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("e/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixEmailValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("e/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixAddressReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("a/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixAddressReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("a/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixAddressValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("a/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixTagReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("t/"), false);
        assertTrue(comparator.compare(carol, alex) < 0);
    }

    @Test
    public void compare_prefixTagReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("t/"), true);
        assertTrue(comparator.compare(carol, alex) > 0);
    }

    @Test
    public void compare_prefixTagValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("t/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixStudentIdReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("s/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixStudentIdReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("s/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixStudentIdValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("s/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixGithubIdReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("g/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixGithubIdReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("g/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixGithubIdValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("g/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }


    @Test
    public void compare_prefixTutorialIdReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("T/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixTutorialIdReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("T/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixTutorialIdValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("T/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixTypeReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("r/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixTypeReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("r/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixTypeValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("r/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

    @Test
    public void compare_prefixNusNetworkIdReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("N/"), false);
        assertTrue(comparator.compare(alex, carol) < 0);
    }

    @Test
    public void compare_prefixNusNetworkIdReverseTrue_moreThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("N/"), true);
        assertTrue(comparator.compare(alex, carol) > 0);
    }

    @Test
    public void compare_prefixNusNetworkIdValueEqualReverseFalse_lessThan() {
        PersonComparator comparator = new PersonComparator(new Prefix("N/"), false);
        assertTrue(comparator.compare(alex, alice) < 0);
    }

}
