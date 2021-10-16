package seedu.fast.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fast.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_APPOINTMENT_VENUE;
import static seedu.fast.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.AddCommand;
import seedu.fast.logic.commands.AppointmentCommand;
import seedu.fast.logic.commands.ClearCommand;
import seedu.fast.logic.commands.DeleteAppointmentCommand;
import seedu.fast.logic.commands.DeleteCommand;
import seedu.fast.logic.commands.EditAppointmentCommand;
import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.logic.commands.EditCommand;
import seedu.fast.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.fast.logic.commands.ExitCommand;
import seedu.fast.logic.commands.FindCommand;
import seedu.fast.logic.commands.HelpCommand;
import seedu.fast.logic.commands.ListCommand;
import seedu.fast.logic.commands.MarkAppointmentCommand;
import seedu.fast.logic.commands.RemarkCommand;
import seedu.fast.logic.parser.exceptions.ParseException;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.NameContainsKeywordsPredicate;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Remark;
import seedu.fast.testutil.EditPersonDescriptorBuilder;
import seedu.fast.testutil.PersonBuilder;
import seedu.fast.testutil.PersonUtil;

public class FastParserTest {

    private final FastParser parser = new FastParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + remark);
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, remark), command);
    }

    @Test
    public void parseCommand_appointment() throws Exception {
        final LocalDate date = LocalDate.parse("2021-10-10");
        final String dateString = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        final Appointment appt = new Appointment(dateString, Appointment.NO_TIME, Appointment.NO_VENUE);
        AppointmentCommand command = (AppointmentCommand) parser.parseCommand(
                AppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_APPOINTMENT + "2021-10-10");
        assertEquals(new AppointmentCommand(INDEX_FIRST_PERSON, appt), command);
    }

    @Test
    public void parseCommand_deleteAppointment() throws Exception {
        final Appointment appt = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        DeleteAppointmentCommand command = (DeleteAppointmentCommand) parser.parseCommand(
                DeleteAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteAppointmentCommand(INDEX_FIRST_PERSON, appt), command);
    }

    @Test
    public void parseCommand_editAppointment() throws Exception {
        final LocalDate date = LocalDate.parse("2021-10-10");
        final String dateString = date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        final String venue = "Clementi Mall";

        final EditAppointmentDescriptor appt = new EditAppointmentDescriptor();
        appt.setDate(dateString);
        appt.setVenue(venue);

        EditAppointmentCommand command = (EditAppointmentCommand) parser.parseCommand(
                EditAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_APPOINTMENT + "2021-10-10" + " " + PREFIX_APPOINTMENT_VENUE + venue);
        assertEquals(new EditAppointmentCommand(INDEX_FIRST_PERSON, appt), command);
    }

    @Test
    public void parseCommand_markAppointment() throws Exception {
        final Appointment appt = new Appointment(Appointment.NO_APPOINTMENT, Appointment.NO_TIME,
                Appointment.NO_VENUE);
        MarkAppointmentCommand command = (MarkAppointmentCommand) parser.parseCommand(
                MarkAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new MarkAppointmentCommand(INDEX_FIRST_PERSON, appt), command);
    }
}
