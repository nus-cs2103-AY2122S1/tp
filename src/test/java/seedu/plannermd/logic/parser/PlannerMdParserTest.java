package seedu.plannermd.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plannermd.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.ClearCommand;
import seedu.plannermd.logic.commands.ExitCommand;
import seedu.plannermd.logic.commands.HelpCommand;
import seedu.plannermd.logic.commands.addcommand.AddDoctorCommand;
import seedu.plannermd.logic.commands.addcommand.AddPatientCommand;
import seedu.plannermd.logic.commands.deletecommand.DeleteDoctorCommand;
import seedu.plannermd.logic.commands.deletecommand.DeletePatientCommand;
import seedu.plannermd.logic.commands.editcommand.EditDoctorCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.logic.commands.findcommand.FindDoctorCommand;
import seedu.plannermd.logic.commands.findcommand.FindPatientCommand;
import seedu.plannermd.logic.commands.listcommand.ListDoctorCommand;
import seedu.plannermd.logic.commands.listcommand.ListPatientCommand;
import seedu.plannermd.logic.parser.exceptions.ParseException;
import seedu.plannermd.model.Model.State;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.NameContainsKeywordsPredicate;
import seedu.plannermd.testutil.EditDoctorDescriptorBuilder;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.doctor.DoctorUtil;
import seedu.plannermd.testutil.patient.EditPatientDescriptorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;
import seedu.plannermd.testutil.patient.PatientUtil;

public class PlannerMdParserTest {

    private final PlannerMdParser parser = new PlannerMdParser();
    private final State patientState = State.PATIENT;
    private final State doctorState = State.DOCTOR;

    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand patientCommand = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddCommand(patient),
                patientState);
        assertEquals(new AddPatientCommand(patient), patientCommand);
    }

    @Test
    public void parseCommand_addDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        AddDoctorCommand doctorCommand = (AddDoctorCommand) parser.parseCommand(DoctorUtil.getAddCommand(doctor),
                doctorState);
        assertEquals(new AddDoctorCommand(doctor), doctorCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, patientState) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", patientState) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser
                .parseCommand(DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), patientState);
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteDoctor() throws Exception {
        DeleteDoctorCommand command = (DeleteDoctorCommand) parser
                .parseCommand(DeleteDoctorCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(), doctorState);
        assertEquals(new DeleteDoctorCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command = (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor),
                patientState);
        assertEquals(new EditPatientCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editDoctor() throws Exception {
        Doctor doctor = new DoctorBuilder().build();
        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(doctor).build();
        EditDoctorCommand command = (EditDoctorCommand) parser.parseCommand(EditDoctorCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + DoctorUtil.getEditDoctorDescriptorDetails(descriptor),
                doctorState);
        assertEquals(new EditDoctorCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, patientState) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", patientState) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findPatient() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPatientCommand command = (FindPatientCommand) parser.parseCommand(
                FindPatientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")),
                patientState);
        assertEquals(new FindPatientCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findDoctor() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindDoctorCommand command = (FindDoctorCommand) parser.parseCommand(
                FindDoctorCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")),
                doctorState);
        assertEquals(new FindDoctorCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, patientState) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", patientState) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listPatient() throws Exception {
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD, patientState) instanceof ListPatientCommand);
        assertTrue(parser.parseCommand(ListPatientCommand.COMMAND_WORD + " 3",
                patientState) instanceof ListPatientCommand);
    }

    @Test
    public void parseCommand_listDoctor() throws Exception {
        assertTrue(parser.parseCommand(ListDoctorCommand.COMMAND_WORD, doctorState) instanceof ListDoctorCommand);
        assertTrue(parser.parseCommand(
                ListDoctorCommand.COMMAND_WORD + " 3", doctorState) instanceof ListDoctorCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), (
            ) -> parser.parseCommand("", patientState));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, (
        )-> parser.parseCommand("unknownCommand", patientState));
    }
}
