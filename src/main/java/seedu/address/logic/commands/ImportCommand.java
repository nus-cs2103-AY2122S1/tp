package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ImportCommand extends Command {

    public static final int NUMBER_OF_FIELDS = 5;
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts added successfully";

    private final File csvFile;
    private final ArrayList<Integer> wronglyFormattedEntries = new ArrayList<>();
    private final ArrayList<Name> nameList = new ArrayList<>();
    private final ArrayList<Phone> phoneList = new ArrayList<>();
    private final ArrayList<Email> emailList = new ArrayList<>();
    private final ArrayList<Address> addressList = new ArrayList<>();
    private final ArrayList<Set<Tag>> tagList = new ArrayList<>();


    /**
     * Creates an ImportCommand to batch import contacts from a csv file
     */
    public ImportCommand() {
        JFileChooser chooser = new JFileChooser("");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = chooser.showOpenDialog(null);

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

        try {
            csvParse();
        } catch (IOException e) {
            throw new CommandException("File could not be read");
        }

        if (wronglyFormattedEntries.size() > 0) {
            String errorString = "Failed! \nEntries at row " + wronglyFormattedEntries + " were wrongly formatted";
            throw new CommandException(errorString);
        }

        addAllEntries(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void csvParse() throws IOException {
        int rowCounter = 0;

        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;

        //Skip header row
        br.readLine();
        rowCounter += 1;

        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            rowCounter += 1;

            try {
                if (values.length > NUMBER_OF_FIELDS) {
                    throw new ParseException("Too many columns");
                }

                Name name = ParserUtil.parseName(values[0]);
                nameList.add(name);
                Phone phone = ParserUtil.parsePhone(values[1]);
                phoneList.add(phone);
                Email email = ParserUtil.parseEmail(values[2]);
                emailList.add(email);
                Address address = ParserUtil.parseAddress(values[3]);
                addressList.add(address);
                Set<Tag> tags = ParserUtil.parseTags(new ArrayList<String>());

                //Row has tags
                if (values.length == NUMBER_OF_FIELDS) {
                    String[] strTags = values[4].split(" ");
                    System.out.println("tags: " + Arrays.toString(strTags));
                    tags = ParserUtil.parseTags(Arrays.asList(strTags));
                }

                tagList.add(tags);

            } catch (ParseException e) {
                wronglyFormattedEntries.add(rowCounter);
            }

        }
    }

    private void addAllEntries (Model model) {
        for (int i = 0; i < nameList.size(); i++) {
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
    }


    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof seedu.address.logic.commands.ImportCommand
                && csvFile.equals(((ImportCommand) other).csvFile));
    }
}
