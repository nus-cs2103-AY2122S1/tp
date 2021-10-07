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
    public static final String MESSAGE_CSV_FILE_IS_EMPTY = "Failed!\nCsv file is empty";
    public static final String MESSAGE_TOO_MANY_COLUMNS = "Too many columns are present";
    public static final String MESSAGE_MISSING_COLUMNS = "There are missing columns";
    public static final String MESSAGE_WRONG_FILE_TYPE = "File selected is not a csv file";
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";
    public static final String MESSAGE_FILE_UNREADABLE = "File could not be read";
    public static final String MESSAGE_WRONGLY_FORMATTED_HEADER = "Failed! "
            + "Entries at following rows are wrongly formatted:";

    private final File csvFile;
    private final ArrayList<String> wronglyFormattedEntries = new ArrayList<>();
    private final ArrayList<Name> nameList = new ArrayList<>();
    private final ArrayList<Phone> phoneList = new ArrayList<>();
    private final ArrayList<Email> emailList = new ArrayList<>();
    private final ArrayList<Address> addressList = new ArrayList<>();
    private final ArrayList<Set<Tag>> tagList = new ArrayList<>();


    /**
     * Creates an ImportCommand to batch import contacts from a csv file
     */
    public ImportCommand() {
        JFileChooser chooser = new JFileChooser("docs/assets/templates");
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

    protected ImportCommand(File file) {
        csvFile = file;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (csvFile == null) {
            throw new CommandException(MESSAGE_FILE_NOT_SELECTED);
        }

        if (!csvFile.getName().endsWith(".csv")) {
            throw new CommandException(MESSAGE_WRONG_FILE_TYPE);
        }

        try {
            csvParse();
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FILE_UNREADABLE);
        }

        if (wronglyFormattedEntries.size() > 0) {
            StringBuilder errorString = new StringBuilder(MESSAGE_WRONGLY_FORMATTED_HEADER);

            for (String errors: wronglyFormattedEntries) {
                errorString.append("\n");
                errorString.append(errors);
            }

            throw new CommandException(errorString.toString());
        }

        addAllEntries(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private void csvParse() throws IOException, CommandException {
        String line;
        int rowCounter = 0;
        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        //Skip header row
        br.readLine();
        rowCounter += 1;

        //@@author Scott Robinson-reused
        //Reused regex from https://stackabuse.com/regex-splitting-by-character-unless-in-quotes/
        String regex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        while ((line = br.readLine()) != null) {
            String[] values = line.split(regex);
            rowCounter += 1;

            try {
                if (values.length > NUMBER_OF_FIELDS) {
                    throw new ParseException(MESSAGE_TOO_MANY_COLUMNS);
                }

                if (values.length < NUMBER_OF_FIELDS - 1) {
                    throw new ParseException(MESSAGE_MISSING_COLUMNS);
                }

                Name name = ParserUtil.parseName(values[0]);
                nameList.add(name);

                Phone phone = ParserUtil.parsePhone(values[1]);
                phoneList.add(phone);

                Email email = ParserUtil.parseEmail(values[2]);
                emailList.add(email);

                String strAddress = values[3];
                if (strAddress.startsWith("\"") && strAddress.endsWith("\"")) {
                    strAddress = strAddress.substring(1, strAddress.length() - 1);
                }
                Address address = ParserUtil.parseAddress(strAddress);
                addressList.add(address);

                Set<Tag> tags = ParserUtil.parseTags(new ArrayList<String>());

                //Row has tags
                if (values.length == NUMBER_OF_FIELDS) {
                    String[] strTags = values[4].split(" ");
                    tags = ParserUtil.parseTags(Arrays.asList(strTags));
                }

                tagList.add(tags);

            } catch (ParseException e) {
                wronglyFormattedEntries.add("Row" + rowCounter + " : " + e.getLocalizedMessage());
            }
        }
        if (rowCounter == 1) {
            throw new CommandException(MESSAGE_CSV_FILE_IS_EMPTY);
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
