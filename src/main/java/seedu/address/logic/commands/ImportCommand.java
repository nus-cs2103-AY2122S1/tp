package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class ImportCommand extends Command {

    public static final int NUMBER_OF_FIELDS = 5;
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = " contacts added successfully";

    private final File csvFile;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public ImportCommand() {
        int response;
        JFileChooser chooser = new JFileChooser("");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        response = chooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            csvFile = chooser.getSelectedFile();
        } else {
            csvFile = null;
        }

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (csvFile == null) {
            throw new CommandException("File was not selected");
        }

        int addedCounter = 0;
        ArrayList<String> wronglyFormattedEntries = new ArrayList<>();
        ArrayList<String> duplicateEntries = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                System.out.println("name: " + values[1]);
                System.out.println("phone: " + values[2]);
                System.out.println("email: " + values[3]);
                System.out.println("address: " + values[4]);


                try {
                    Name name = ParserUtil.parseName(values[1]);
                    Phone phone = ParserUtil.parsePhone(values[2]);
                    Email email = ParserUtil.parseEmail(values[3]);
                    Address address = ParserUtil.parseAddress(values[4]);
                    Set<Tag> tagList = ParserUtil.parseTags(new ArrayList<String>());


                    if (values.length == NUMBER_OF_FIELDS + 1) {
                        String[] strTags = values[5].split(" ");
                        System.out.println("tags: " + Arrays.toString(strTags));
                        tagList = ParserUtil.parseTags(Arrays.asList(strTags));
                    }

                    Person person = new Person(name, phone, email, address, tagList);
                    if (model.hasPerson(person)) {
                        duplicateEntries.add(values[0]);
                        continue;
                    }

                    model.addPerson(person);
                    addedCounter += 1;

                } catch (ParseException e) {
                    wronglyFormattedEntries.add(values[0]);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String resultString = addedCounter + MESSAGE_SUCCESS;

        if (duplicateEntries.size() > 0) {
            resultString += "\nEntries at indexes " + duplicateEntries + " were already in the Address Book";
        }

        if (wronglyFormattedEntries.size() > 0) {
            resultString += "\nEntries at indexes " + wronglyFormattedEntries + " were wrongly formatted";
        }

        return new CommandResult(resultString);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.ImportCommand // instanceof handles nulls
                && csvFile.equals(((ImportCommand) other).csvFile));
    }
}
