package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.data.Name;
import seedu.address.model.data.member.Address;
import seedu.address.model.data.member.Email;
import seedu.address.model.data.member.Member;
import seedu.address.model.data.member.Phone;
import seedu.address.model.position.Position;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Member objects.
 */
public class MemberBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Position> positions;
    private TaskList taskList;

    /**
     * Creates a {@code MemberBuilder} with the default details.
     */
    public MemberBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        positions = new HashSet<>();
        taskList = new TaskList();
    }

    /**
     * Initializes the MemberBuilder with the data of {@code memberToCopy}.
     */
    public MemberBuilder(Member memberToCopy) {
        name = memberToCopy.getName();
        phone = memberToCopy.getPhone();
        email = memberToCopy.getEmail();
        address = memberToCopy.getAddress();
        positions = new HashSet<>(memberToCopy.getPositions());
        taskList = new TaskList();
        taskList.setTasks(memberToCopy.getTaskList());
    }

    /**
     * Sets the {@code Name} of the {@code Member} that we are building.
     */
    public MemberBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code positions} into a {@code Set<Position>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withPositions(String ... positions) {
        this.positions = SampleDataUtil.getPositionSet(positions);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Member} that we are building.
     */
    public MemberBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Member} that we are building.
     */
    public MemberBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Member} that we are building.
     */
    public MemberBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code taskList} of the {@code Member} that we are building.
     */
    public MemberBuilder withTaskList(Task ... tasks) {
        this.taskList.setTasks(SampleDataUtil.getTaskList(tasks));
        return this;
    }

    /**
     * Sets the {@code taskList} of the {@code Member} that we are building.
     */
    public MemberBuilder withTaskList(List<Task> tasks) {
        this.taskList.setTasks(tasks);
        return this;
    }

    public Member build() {
        return new Member(name, phone, email, address, positions, taskList);
    }

}
