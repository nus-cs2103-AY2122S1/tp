package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.module.Name;
import seedu.address.model.module.member.Address;
import seedu.address.model.module.member.Email;
import seedu.address.model.module.member.Member;
import seedu.address.model.module.member.Phone;
import seedu.address.model.module.member.position.Position;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;
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
        email = memberToCopy.getEmail().get();
        address = memberToCopy.getAddress().get();
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
        if (address != null) {
            this.address = new Address(address);
        } else {
            this.address = null;
        }
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
        if (email != null) {
            this.email = new Email(email);
        } else {
            this.email = null;
        }
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
