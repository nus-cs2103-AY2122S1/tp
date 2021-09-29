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

    public static final int NUMBER_OF_FIELDS = 4;
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts added successfully";

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

        int rowCounter = 0;
        ArrayList<Integer> wronglyFormattedEntries = new ArrayList<>();
        ArrayList<Name> nameList = new ArrayList<>();
        ArrayList<Phone> phoneList = new ArrayList<>();
        ArrayList<Email> emailList = new ArrayList<>();
        ArrayList<Address> addressList = new ArrayList<>();
        ArrayList<Set<Tag>> tagList = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine();
            rowCounter += 1;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                rowCounter += 1;

                try {
                    Name name = ParserUtil.parseName(values[0]);
                    nameList.add(name);
                    Phone phone = ParserUtil.parsePhone(values[1]);
                    phoneList.add(phone);
                    Email email = ParserUtil.parseEmail(values[2]);
                    emailList.add(email);
                    Address address = ParserUtil.parseAddress(values[3]);
                    addressList.add(address);
                    Set<Tag> tags = ParserUtil.parseTags(new ArrayList<String>());

                    if (values.length == NUMBER_OF_FIELDS + 1) {
                        String[] strTags = values[4].split(" ");
                        System.out.println("tags: " + Arrays.toString(strTags));
                        tags = ParserUtil.parseTags(Arrays.asList(strTags));
                    }

                    tagList.add(tags);

                } catch (ParseException e) {
                    e.printStackTrace();
                    wronglyFormattedEntries.add(rowCounter);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (wronglyFormattedEntries.size() > 0) {
            String errorString = "Failed! \nEntries at row " + wronglyFormattedEntries + " were wrongly formatted";
            throw new CommandException(errorString);
        }

        for (int i = 0; i < rowCounter - 1; i++) {
            Name name = nameList.get(i);
            Phone phone = phoneList.get(i);
            Email email = emailList.get(i);
            Address address = addressList.get(i);
            Set<Tag> tags = tagList.get(i);

            Person person = new Person(name, phone, email, address, tags);
            if (model.hasPerson(person)) {
                continue;
            }

            model.addPerson(person);

        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.ImportCommand // instanceof handles nulls
                && csvFile.equals(((ImportCommand) other).csvFile));
    }
}
