package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CsvParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Batch imports contacts to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String TEMPLATE_PATH = "docs/assets/templates";

    public static final String MESSAGE_SUCCESS = "Contacts added successfully";
    public static final String MESSAGE_WRONG_FILE_TYPE = "File selected is not a csv file";
    public static final String MESSAGE_FILE_NOT_SELECTED = "File was not selected";
    public static final String MESSAGE_FILE_UNREADABLE = "File could not be read";

    private CsvParser parser;

    private List<Name> names;
    private List<Phone> phoneNumbers;
    private List<Email> emails;
    private List<Address> addresses;
    private List<Set<Tag>> tags;


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            parser = new CsvParser(chooseFile());
            parser.parse();
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FILE_UNREADABLE);
        } catch (ParseException e) {
            throw new CommandException(e.getLocalizedMessage());
        }

        retrieveParsedValues();
        addAllEntries(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    private File chooseFile() throws CommandException {
        JFileChooser chooser = new JFileChooser(TEMPLATE_PATH);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Filter .csv files only", "csv");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int response = chooser.showOpenDialog(null);

        if (response != JFileChooser.APPROVE_OPTION) {
            throw new CommandException(MESSAGE_FILE_NOT_SELECTED);
        }

        File csvFile = chooser.getSelectedFile();

        if (!csvFile.getName().endsWith(".csv")) {
            throw new CommandException(MESSAGE_WRONG_FILE_TYPE);
        }

        return csvFile;
    }

    private void retrieveParsedValues() {
        names = parser.getNames();
        phoneNumbers = parser.getPhoneNumbers();
        emails = parser.getEmails();
        addresses = parser.getAddresses();
        tags = parser.getTags();
    }

    private void addAllEntries (Model model) {
        for (int i = 0; i < names.size(); i++) {
            Name name = names.get(i);
            Phone phone = phoneNumbers.get(i);
            Email email = emails.get(i);
            Address address = addresses.get(i);
            Set<Tag> tags = this.tags.get(i);

            Person person = new Person(name, phone, email, address, tags);

            if (model.hasPerson(person)) {
                continue;
            }

            model.addPerson(person);

        }
    }
}
