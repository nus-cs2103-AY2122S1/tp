package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandUtilTest {

    @Test
    public void checkMaxSizeOfCommands_success() {
        for (int i = 0; i <= 40; i++) {
            CommandUtil.addCommand("command" + i);
        }
        assertEquals(30, CommandUtil.getSize());
    }

    @Test
    public void getPreCommand_empty_success() {
        for (int i = 0; i <= 40; i++) {
            CommandUtil.addCommand("command" + i);
        }
        for (int i = 0; i <= 40; i++) {
            CommandUtil.getPreCommand();
        }
        assertEquals("", CommandUtil.getPreCommand());
    }

    @Test
    public void getPreCommand_success() {
        for (int i = 0; i <= 40; i++) {
            CommandUtil.addCommand("command" + i);
        }
        for (int i = 0; i <= 50; i++) {
            CommandUtil.getNextCommand();
        }
        assertEquals("command40", CommandUtil.getPreCommand());
    }

    @Test
    public void getNextCommand_empty_success() {
        for (int i = 0; i <= 40; i++) {
            CommandUtil.addCommand("command" + i);
        }
        assertEquals("", CommandUtil.getNextCommand());
    }

    @Test
    public void getNextCommand_success() {
        for (int i = 0; i <= 40; i++) {
            CommandUtil.addCommand("command" + i);
        }
        for (int i = 0; i <= 40; i++) {
            CommandUtil.getPreCommand();
        }
        assertEquals("command11", CommandUtil.getNextCommand());
    }
}
