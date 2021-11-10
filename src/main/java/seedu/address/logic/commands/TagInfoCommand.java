package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Hashtable;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.ui.ReportWindow;

public class TagInfoCommand extends Command {

    public static final String COMMAND_WORD = "tagInfo";
    public static final String MESSAGE_NO_INPUT = "No valid tag value is inputted";
    public static final String MESSAGE_SUCCESS = "TagInfo report has been generated!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Provides the number of contacts with the respective tags"
            + " Methods of usage :"
            + "1. tagInfo list : provide a list for every unique tag in the current model\n"
            + "2. tagInfo t/[TAG1], [TAG2] ... : provide a list for every existing tag inputted\n "
            + "   if the input tag is not in the model, an error message will be shown while the\n "
            + "   existing tags will still be in the list";

    private HashSet<Tag> listOfTags = new HashSet<>();
    private boolean isTesting = false;

    /**
     * Returns a new TagInfoCommand Object that search all the tags
     * @param all is string "list" that indicates all tags to be tested
     * @param isTesting is a boolean value.
     */
    public TagInfoCommand(String all, boolean isTesting) {
        assert all == "list";
        this.isTesting = isTesting;
    }

    /**
     * Returns a new TagInfoCommand Object with the inputs converted into
     * a HashSet Object
     * @param tagListInput is an array of strings containing the tag names.
     * @param isTesting is a boolean to check if the report window needs to be opened
     * @throws CommandException if invalid tag name is inputted
     */
    public TagInfoCommand(String[] tagListInput, boolean isTesting) throws CommandException {
        this.isTesting = isTesting;
        for (String tagName : tagListInput) {
            if (tagName.equals("")) {
                throw new CommandException("Empty tag is inputted!");
            }
            try {
                this.listOfTags.add(new Tag(tagName));
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
        }
    }

    public HashSet<Tag> getListOfTags() {
        return listOfTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String resultMessage = "tags Info:\n";
        String errorTags = "";
        Hashtable<Tag, Integer> tagInfoTable = model.getUniqueTagTable();

        // empty, assumed that we are looking for every tag in the model
        if (listOfTags.isEmpty()) {
            listOfTags.addAll(tagInfoTable.keySet());
        }

        for (Tag tagsToBeChecked : listOfTags) {
            if (tagInfoTable.containsKey(tagsToBeChecked)) {
                resultMessage += tagsToBeChecked.tagName
                        + " used by "
                        + tagInfoTable.get(tagsToBeChecked)
                        + " people\n";
            } else {
                errorTags += tagsToBeChecked.tagName + "\n";
            }
        }

        if (!errorTags.equals("")) {
            resultMessage += "following tags cannot be found:\n" + errorTags;
        }

        if (!this.isTesting) {
            ReportWindow.setReportMessage(resultMessage);
            ReportWindow window = new ReportWindow();
            window.show();
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TagInfoCommand) {
            TagInfoCommand compareCommand = (TagInfoCommand) o;
            for (Tag tag : compareCommand.getListOfTags()) {
                if (!getListOfTags().contains(tag)) {
                    return false;
                }
            }
        }
        return true;
    }
}
