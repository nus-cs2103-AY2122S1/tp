package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_G2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REPONAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_G1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_G2;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.commons.RepoName;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;
import seedu.address.model.group.LinkYear;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {

    public static final Group GROUP1 = new GroupBuilder().withName(VALID_GROUPNAME_G1).withYear(VALID_YEAR_G1)
            .withRepo(VALID_REPONAME_AMY).withMembers(BENSON, CARL, DANIEL).build();
    public static final Group GROUP2 = new GroupBuilder().withName(VALID_GROUPNAME_G2).withMembers(ELLE, FIONA)
            .withRepo(VALID_REPONAME_BOB).withYear(VALID_YEAR_G2).withTags(VALID_TAG_HUSBAND).build();
    public static final Group GROUP3 = new GroupBuilder().withName("m16-6").withRepo("ip").withYear("AY20192020")
            .build();

    public static final RepoName REPONAME1 = new RepoName("tp");
    public static final RepoName REPONAME2 = new RepoName("2103_tp-project");

    public static final LinkYear YEAR1 = new LinkYear("AY20212022");
    public static final LinkYear YEAR2 = new LinkYear("AY20222023");

    public static final GroupName GROUPNAME1 = new GroupName("W14-4");
    public static final GroupName GROUPNAME2 = new GroupName("W14-5");

    public static final GroupName VALID_UNINSTATITATED_GROUP = new GroupName("m11-1");

    private TypicalGroups() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBookWithGroups() {
        AddressBook ab = new AddressBook();
        for (Group group : getTypicalGroups()) {
            ab.addGroup(group);
        }
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(GROUP1, GROUP2, GROUP3));
    }
}
