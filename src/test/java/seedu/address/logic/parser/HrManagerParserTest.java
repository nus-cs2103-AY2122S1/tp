package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.candidate.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_ADMIN_ASSISTANT;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_PENDING;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERVIEW;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Command;
import seedu.address.logic.candidate.AddCandidateCommand;
import seedu.address.logic.candidate.DeleteCandidateCommand;
import seedu.address.logic.candidate.EditCandidateCommand;
import seedu.address.logic.candidate.EditCandidateCommand.EditPersonDescriptor;
import seedu.address.logic.candidate.FindCandidateCommand;
import seedu.address.logic.candidate.ListCandidateCommand;
import seedu.address.logic.candidate.RemarkCandidateCommand;
import seedu.address.logic.general.ClearCommand;
import seedu.address.logic.general.ExitCommand;
import seedu.address.logic.general.HelpCommand;
import seedu.address.logic.interview.AddInterviewCommand;
import seedu.address.logic.interview.DeleteInterviewCommand;
import seedu.address.logic.interview.EditInterviewCommand;
import seedu.address.logic.interview.EditInterviewCommand.EditInterviewDescriptor;
import seedu.address.logic.interview.ListInterviewCommand;
import seedu.address.logic.interview.UnassignInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.position.AddPositionCommand;
import seedu.address.logic.position.DeletePositionCommand;
import seedu.address.logic.position.EditPositionCommand;
import seedu.address.logic.position.EditPositionCommand.EditPositionDescriptor;
import seedu.address.logic.position.FindPositionCommand;
import seedu.address.logic.position.ListPositionCommand;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.FindCandidateCommandPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.position.FindPositionCommandPredicate;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditInterviewDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.InterviewBuilder;
import seedu.address.testutil.InterviewUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.PositionBuilder;
import seedu.address.testutil.PositionUtil;
import seedu.address.testutil.TypicalPersons;

public class HrManagerParserTest {

    private final HrManagerParser parser = new HrManagerParser();

    @Test
    public void parseCommand_addCandidate() throws Exception {
        Person person = new PersonBuilder().withRemark("").build();
        AddCandidateCommand command =
                (AddCandidateCommand) parser.parseCommand(PersonUtil.getAddCandidateCommand(person));
        assertEquals(new AddCandidateCommand(person), command);
    }

    @Test
    public void parseCommand_addPosition() throws Exception {
        Position position = new PositionBuilder().build();
        AddPositionCommand command =
                (AddPositionCommand) parser.parseCommand(PositionUtil.getAddPositionCommand(position));
        assertEquals(new AddPositionCommand(position), command);
    }

    @Test
    public void parseCommand_addInterview() throws Exception {
        //use the prompt message from the main app, a string as it is
        String standardInput = "add_i position=Accountant "
                + "c=1 date=15/10/2021 time=1400 duration=120 interviewed=pending";
        Command command = parser.parseCommand(standardInput);
        assertTrue(command instanceof AddInterviewCommand);

        Interview interview =
                new InterviewBuilder().withCandidates(new HashSet<>(List.of(TypicalPersons.BENSON))).build();
        Command otherCommand = parser.parseCommand(InterviewUtil.getAddInterviewCommand(interview));
        assertTrue(otherCommand instanceof AddInterviewCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteCandidate() throws Exception {
        DeleteCandidateCommand command = (DeleteCandidateCommand) parser.parseCommand(
                DeleteCandidateCommand.COMMAND_WORD + " " + INDEX_FIRST_POSITION.getOneBased());
        assertEquals(new DeleteCandidateCommand(INDEX_FIRST_POSITION), command);
    }

    @Test
    public void parseCommand_deletePosition() throws Exception {
        DeletePositionCommand command = (DeletePositionCommand) parser.parseCommand(
                DeletePositionCommand.COMMAND_WORD + " " + INDEX_FIRST_POSITION.getOneBased());
        assertEquals(new DeletePositionCommand(INDEX_FIRST_POSITION), command);
    }

    @Test
    public void parseCommand_deleteInterview() throws Exception {
        DeleteInterviewCommand command = (DeleteInterviewCommand) parser.parseCommand(
                DeleteInterviewCommand.COMMAND_WORD + " " + INDEX_FIRST_INTERVIEW.getOneBased());
        assertEquals(new DeleteInterviewCommand(INDEX_FIRST_INTERVIEW), command);
    }

    @Test
    public void parseCommand_editCandidate() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCandidateCommand command = (EditCandidateCommand)
                parser.parseCommand(EditCandidateCommand.COMMAND_WORD
                + " " + INDEX_FIRST_POSITION.getOneBased()
                        + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCandidateCommand(INDEX_FIRST_POSITION, descriptor), command);
    }

    @Test
    public void parseCommand_editInterview() throws Exception {
        String userInput = EditInterviewCommand.COMMAND_WORD + " " + "1" + " "
                + PREFIX_POSITION + VALID_POSITION_ADMIN_ASSISTANT
                + " " + PREFIX_DATE + VALID_DATE + " " + PREFIX_TIME + VALID_TIME + " "
                + " " + PREFIX_DURATION + VALID_DURATION_TIME + " " + PREFIX_INTERVIEW_STATUS
                + VALID_STATUS_PENDING;
        EditInterviewDescriptor descriptor =
                new EditInterviewDescriptorBuilder()
                        .withPosition(VALID_POSITION_ADMIN_ASSISTANT)
                        .withDate(VALID_DATE)
                        .withStartTime(VALID_TIME)
                        .withDuration(VALID_DURATION_TIME)
                        .withStatus(VALID_STATUS_PENDING).build();
        EditInterviewCommand command = (EditInterviewCommand)
                parser.parseCommand(userInput);
        assertEquals(new EditInterviewCommand(INDEX_FIRST_INTERVIEW, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_remarkCandidate() throws Exception {
        String exampleInput = RemarkCandidateCommand.COMMAND_WORD + " 3";
        String anotherInput = RemarkCandidateCommand.COMMAND_WORD + " 3 " + PREFIX_REMARK + VALID_REMARK_AMY;
        assertTrue(parser.parseCommand(exampleInput) instanceof RemarkCandidateCommand);
        assertTrue(parser.parseCommand(anotherInput) instanceof RemarkCandidateCommand);
    }

    @Test
    public void parseCommand_findCandidate() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCandidateCommand command = (FindCandidateCommand) parser.parseCommand(
                FindCandidateCommand.COMMAND_WORD + " "
                        + PREFIX_NAME + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCandidateCommand(new FindCandidateCommandPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findPosition() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindPositionCommand command = (FindPositionCommand) parser.parseCommand(
                FindPositionCommand.COMMAND_WORD + " "
                        + PREFIX_TITLE + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindPositionCommand(new FindPositionCommandPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_candidateList() throws Exception {
        assertTrue(parser.parseCommand(ListCandidateCommand.COMMAND_WORD) instanceof ListCandidateCommand);
        assertTrue(parser.parseCommand(ListCandidateCommand.COMMAND_WORD
                + " 3") instanceof ListCandidateCommand);
    }

    @Test
    public void parseCommand_positionList() throws Exception {
        assertTrue(parser.parseCommand(ListPositionCommand.COMMAND_WORD) instanceof ListPositionCommand);
        assertTrue(parser.parseCommand(ListPositionCommand.COMMAND_WORD
                + " 3") instanceof ListPositionCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_editPosition() throws Exception {
        Position position = new PositionBuilder().build();
        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder(position).build();
        EditPositionCommand command = (EditPositionCommand)
                parser.parseCommand(EditPositionCommand.COMMAND_WORD
                        + " " + INDEX_FIRST_POSITION.getOneBased()
                        + " " + PositionUtil.getEditPositionDescriptorDetails(descriptor));
        assertEquals(new EditPositionCommand(INDEX_FIRST_POSITION, descriptor), command);
    }

    @Test
    public void parseCommand_interviewList() throws Exception {
        assertTrue(parser.parseCommand(ListInterviewCommand.COMMAND_WORD) instanceof ListInterviewCommand);
        assertTrue(parser.parseCommand(ListInterviewCommand.COMMAND_WORD
                + " 3") instanceof ListInterviewCommand);
    }

    @Test
    public void parseCommand_unassign() throws Exception {
        //unassign 1 candidate
        Command command = parser.parseCommand(UnassignInterviewCommand.COMMAND_WORD + " "
                + PREFIX_INTERVIEW_INDEX + "1 " + PREFIX_CANDIDATE_INDEX + "1");
        assertEquals(new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, new HashSet<>()), command);

        //unassign all candidates
        Command otherCommand = parser.parseCommand(UnassignInterviewCommand.COMMAND_WORD + " "
                + PREFIX_INTERVIEW_INDEX + "1 " + PREFIX_CANDIDATE_INDEX + "*");
        assertEquals(new UnassignInterviewCommand(INDEX_FIRST_INTERVIEW, true), otherCommand);
    }
}
