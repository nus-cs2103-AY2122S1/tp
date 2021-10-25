package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Slot;
import seedu.address.testutil.PersonBuilder;

public class SwapShiftCommandTest {

    @Test
    public void execute_staffDoesNotExist_throwsCommandException() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000000").withStatus("fulltime")
                .withTags("friends").build();

        model.addPerson(alice);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.STAFF_NOT_FOUND, "Alex Yeoh"));
    }

    @Test
    public void execute_shiftDoesNotExist_throwsCommandException() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000000").withStatus("fulltime")
                .withTags("friends").build();

        Person alex = new PersonBuilder().withName("Alex Yeoh")
                .withAddress("124, Yishun West Ave 6, #08-111").withEmail("alex@example.com")
                .withPhone("94384736").withRoles("kitchen").withSalary("4500000").withStatus("parttime")
                .withTags("friends").build();

        model.addPerson(alice);
        model.addPerson(alex);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.SHIFT_CANT_SWAP, "Alice Pauline",
                DayOfWeek.MONDAY, Slot.MORNING));
    }

    @Test
    public void execute_duplicateShift_throwsCommandException() {
        Schedule aliceSchedule = new Schedule();
        Schedule alexSchedule = new Schedule();
        aliceSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING);
        alexSchedule.addShift(DayOfWeek.TUESDAY, Slot.AFTERNOON);
        alexSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING);

        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withRoles("floor").withSalary("1000000").withStatus("fulltime")
                .withTags("friends").withSchedule(aliceSchedule.toString()).build();

        Person alex = new PersonBuilder().withName("Alex Yeoh")
                .withAddress("124, Yishun West Ave 6, #08-111").withEmail("alex@example.com")
                .withPhone("94384736").withRoles("kitchen").withSalary("4500000").withStatus("parttime")
                .withTags("friends").withSchedule(alexSchedule.toString()).build();

        model.addPerson(alice);
        model.addPerson(alex);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.SHIFT_ALREADY_EXISTS, "Alex Yeoh",
                DayOfWeek.MONDAY, Slot.MORNING));
    }
}
