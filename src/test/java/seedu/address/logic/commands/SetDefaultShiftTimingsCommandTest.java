package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class SetDefaultShiftTimingsCommandTest {
    @Test
    public void equals() {
        // Test equals
        SetDefaultShiftTimingsCommand expectedCommand1 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        SetDefaultShiftTimingsCommand expectedCommand2 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        assertTrue(expectedCommand1.equals(expectedCommand2));

        // Test not equals
        SetDefaultShiftTimingsCommand expectedCommand3 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        SetDefaultShiftTimingsCommand expectedCommand4 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(22, 0)}
        );
        assertFalse(expectedCommand3.equals(expectedCommand4));

        // Test not equals
        SetDefaultShiftTimingsCommand expectedCommand5 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0),
                LocalTime.of(22, 0)}
        );
        SetDefaultShiftTimingsCommand expectedCommand6 = new SetDefaultShiftTimingsCommand(new LocalTime[]{
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(22, 0)}
        );
        assertFalse(expectedCommand5.equals(expectedCommand6));
    }
}
