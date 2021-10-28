package tutoraid.logic.commands;

/**
 * Parent class of EditStudentCommand and EditLessonCommand.
 */
public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
}
