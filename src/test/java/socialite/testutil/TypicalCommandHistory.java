package socialite.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import socialite.model.CommandHistory;

/**
 * A utility class containing a list of command string to be used in tests.
 */
public class TypicalCommandHistory {

    public static final String ADD =
            "add n/Alex Yeoh p/87438807 t/friends fb/alex.yeoh ig/alex.yeoh tele/alyeoh tiktok/alex.yeoh";
    public static final String EDIT = "edit 1 date/Meeting:2021-09-14";
    public static final String PICTURE = "picture 1";
    public static final String REMARK = "remark 1 r/Likes to swim";
    public static final String DELETE = "delete 3";
    public static final String CLEAR = "clear";
    public static final String EXIT = "exit";
    public static final String FIND = "find p/facebook";
    public static final String LIST = "list";
    public static final String PIN = "pin 2";
    public static final String SHARE = "share 5";
    public static final String UNPIN = "unpin 1";
    public static final String HELP = "help";
    public static final String UNKNOWN = "hello world";

    public static CommandHistory getTypicalCommandHistory() {
        CommandHistory ch = new CommandHistory();
        for (String command : getTypicalCommand()) {
            ch.addCommand(command);
        }
        return ch;
    }

    public static List<String> getTypicalCommand() {
        return new ArrayList<>(Arrays.asList(ADD, EDIT, PICTURE, REMARK, DELETE, CLEAR, EXIT, FIND, LIST, PIN, SHARE,
                UNPIN, HELP, UNKNOWN));
    }
}
