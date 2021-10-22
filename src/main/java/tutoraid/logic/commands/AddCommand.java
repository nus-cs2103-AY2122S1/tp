package tutoraid.logic.commands;

/**
 * Parent class of AddStudentCommand and AddLessonCommand.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
}
