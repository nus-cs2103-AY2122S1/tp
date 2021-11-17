package socialite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import socialite.commons.core.Messages;
import socialite.commons.core.index.Index;
import socialite.logic.commands.exceptions.CommandException;
import socialite.model.Model;
import socialite.model.person.Person;
import socialite.model.person.ProfilePicture;
import socialite.ui.MainWindow;

public class PictureCommand extends Command {

    public static final String COMMAND_WORD = "picture";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a profile picture to the person identified by the index number"
            + " used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_HELP_GUIDE = "Enter 'help picture' for in-app guidance.";
    public static final String MESSAGE_PICTURE_PERSON_SUCCESS = "Changed Profile Picture of Person: %1$s";
    public static final String MESSAGE_COMMAND_ABORTED = "Command Aborted";
    private final Index index;
    private final boolean useGui;
    private File picture = null;

    /**
     * Creates a command that adds a picture to a person
     * @param index Index of person to add picture to
     */
    public PictureCommand(Index index, boolean useGui) {
        requireNonNull(index);

        this.index = index;
        this.useGui = useGui;
    }

    /**
     * Constructor with a provided picture used for testing
     */
    public PictureCommand(Index index, boolean useGui, File picture) {
        requireNonNull(index);

        this.index = index;
        this.useGui = useGui;
        this.picture = picture;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddPic = lastShownList.get(index.getZeroBased());
        if (useGui) {
            picture = getPic();
        }
        if (picture == null) {
            throw new CommandException(MESSAGE_COMMAND_ABORTED);
        }

        Person personWithPic = addPicToPerson(personToAddPic, picture, model);

        model.setPerson(personToAddPic, personWithPic);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(MESSAGE_PICTURE_PERSON_SUCCESS, personToAddPic), false, false, true);
    }

    private File getPic() {
        MainWindow window = MainWindow.getWindow();
        File file = window.getFile();
        return file;
    }

    /**
     * Adds picture to person provided
     */
    public Person addPicToPerson(Person person, File file, Model model) {
        if (!person.getProfilePicture().equals(ProfilePicture.DEFAULT_PICTURE)) {
            // delete file if not default picture
            model.deleteProfilePicture(person.getProfilePicture().value);
        }

        // add new file, change person's profile picture
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss"));
        model.saveProfilePicture(file, filename);
        person.setProfilePicture(Paths.get(filename));
        return person;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof PictureCommand) {
            PictureCommand otherCommand = (PictureCommand) other;
            if (picture == null) {
                return index.equals(otherCommand.index)
                        && (useGui == otherCommand.useGui)
                        && otherCommand.picture == null;
            } else {
                return index.equals(otherCommand.index)
                        && (useGui == otherCommand.useGui)
                        && picture.equals(otherCommand.picture);
            }
        }
        return false;
    }

}
