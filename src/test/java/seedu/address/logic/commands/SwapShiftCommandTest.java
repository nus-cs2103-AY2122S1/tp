package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import java.time.DayOfWeek;
import java.time.LocalDate;
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
import seedu.address.testutil.ScheduleBuilder;

public class SwapShiftCommandTest {

    private static final LocalDate START_DATE = LocalDate.now();
    private static final LocalDate END_DATE = START_DATE.plusDays(7);

    @Test
    public void execute_staffDoesNotExist_throwsCommandException() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000")
                .withStatus("fulltime").withTags("friends").build();

        model.addPerson(alice);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList, START_DATE, END_DATE);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.STAFF_NOT_FOUND, "Alex Yeoh"));
    }

    @Test
    public void execute_shiftDoesNotExist_throwsCommandException() {
        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000")
                .withStatus("fulltime").withTags("friends").build();


        Person alex = new PersonBuilder().withName("Alex Yeoh")
                .withEmail("alex@example.com").withPhone("94384736").withRoles("kitchen").withSalary("4500")
                .withStatus("parttime").withTags("friends").build();


        model.addPerson(alice);
        model.addPerson(alex);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList, START_DATE, END_DATE);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.SHIFT_CANT_SWAP, "Alice Pauline",
                DayOfWeek.MONDAY, Slot.MORNING));
    }

    @Test
    public void execute_duplicateShift_throwsCommandException() {
        Schedule aliceSchedule = new Schedule();
        Schedule alexSchedule = new Schedule();
        aliceSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING, START_DATE, END_DATE);
        alexSchedule.addShift(DayOfWeek.TUESDAY, Slot.AFTERNOON, START_DATE, END_DATE);
        alexSchedule.addShift(DayOfWeek.MONDAY, Slot.MORNING, START_DATE, END_DATE);

        Model model = new ModelManager();

        Person alice = new PersonBuilder().withName("Alice Pauline")
                .withEmail("alice@example.com").withPhone("94351253").withRoles("floor").withSalary("1000")
                .withStatus("fulltime").withTags("friends").withSchedule(new ScheduleBuilder(aliceSchedule)).build();

        Person alex = new PersonBuilder().withName("Alex Yeoh")
                .withEmail("alex@example.com").withPhone("94384736").withRoles("kitchen").withSalary("4500")
                .withStatus("parttime").withTags("friends").withSchedule(new ScheduleBuilder(alexSchedule)).build();

        model.addPerson(alice);
        model.addPerson(alex);

        List<Name> nameList = Arrays.asList(new Name("Alice Pauline"), new Name("Alex Yeoh"));
        List<String> shiftList = Arrays.asList("monday-0", "tuesday-1");
        SwapShiftCommand command = new SwapShiftCommand(nameList, shiftList, START_DATE, END_DATE);

        assertCommandFailure(command, model, String.format(SwapShiftCommand.SHIFT_ALREADY_EXISTS, "Alex Yeoh",
                DayOfWeek.MONDAY, Slot.MORNING));
    }
}
