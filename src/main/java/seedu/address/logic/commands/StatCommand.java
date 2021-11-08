package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.TutorialId;
import seedu.address.model.person.Type;
import seedu.address.model.tag.Tag;

/**
 * Finds and count the number of each tag in last searched list
 */
public class StatCommand extends Command {

    public static final String COMMAND_WORD = "stat";


    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Finds and count the number of each tag in last searched list\n"
        + "Example: " + COMMAND_WORD;
    private static final String EMPTY_FILTERED_LIST_ERROR = "The Filtered Person List is Empty";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() == 0) {
            throw new CommandException(EMPTY_FILTERED_LIST_ERROR);
        }
        return new CommandResult(getStatistics(lastShownList)
            );
    }

    private String getStatistics(List<Person> lastShownList) {
        Hashtable<String, Integer> tagDic = new Hashtable<String, Integer>();
        Hashtable<String, Integer> typeDic = new Hashtable<String, Integer>();
        Hashtable<String, Integer> tutDic = new Hashtable<String, Integer>();

        for (Person person : lastShownList) {

            Type type = person.getType();
            if (typeDic.containsKey(type.toString())) {
                typeDic.put(type.toString(), typeDic.get(type.toString()) + 1);
            } else {
                typeDic.put(type.toString(), 1);
            }

            TutorialId tutid = person.getTutorialId();
            if (tutDic.containsKey(tutid.toString())) {
                tutDic.put(tutid.toString(), tutDic.get(tutid.toString()) + 1);
            } else {
                tutDic.put(tutid.toString(), 1);
            }

            Set<Tag> tempSet = person.getTags();
            if (tempSet.isEmpty()) {
                continue;
            }
            for (Tag t : tempSet) {
                if (tagDic.containsKey(t.toString())) {
                    tagDic.put(t.toString(), tagDic.get(t.toString()) + 1);
                } else {
                    tagDic.put(t.toString(), 1);
                }
            }
        }

        String tagString = "";
        for (String s : tagDic.keySet()) {
            tagString += String.format("%s : %d\n", s , tagDic.get(s));
        }

        String typeString = "";
        for (String s : typeDic.keySet()) {
            typeString += String.format("%s : %d\n", s , typeDic.get(s));
        }

        String tutString = "";
        List<String> tempList = tutDic.keySet().stream().sorted().collect(Collectors.toList());
        for (String s : tempList) {
            tutString += String.format("%s : %d\n", s , tutDic.get(s));
        }

        return String.format("Tag Count: \n%s\nType Count: \n%s\nTutorial ID Count: \n%s",
            tagString, typeString, tutString);

    }
}



