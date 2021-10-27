package tutoraid.logic.commands;

/**
 * Parent class of AddStudentCommand, AddLessonCommand and AddProgressCommand.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
}
