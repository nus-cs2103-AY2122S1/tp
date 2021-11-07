package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import safeforhall.commons.core.LogsCenter;
import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.event.Event;
import safeforhall.model.event.ResidentList;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;
import safeforhall.model.person.exceptions.DuplicatePersonException;

/**
 * Imports the csv and replaces the existing address book with the available information
 * provided a correctly formatted csv was found at "/data/"
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String PARAMETERS = "CSV_NAME";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports resident information from the specified csv "
            + "file located within the /data folder\n"
            + "Parameters: "
            + "NAME_OF_CSV\n"
            + "Example: " + COMMAND_WORD + " "
            + "safeforhall\n"
            + "Note: \n"
            + "     1. 8 comma separated values for each row in order; \n"
            + "             name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate\n"
            + "     2. The first row will be discarded as column headings\n"
            + "     3. LastFetDate and LastCollectionDate are optional (can be left as empty space)\n"
            + "     4. Resident lists of all events will be wiped\n"
            + "Parameters: "
            + PARAMETERS + "\n"
            + "Example: " + COMMAND_WORD + " "
            + "safeforhall";

    public static final String MESSAGE_SUCCESS = "Imported resident information from csv";
    public static final String MESSAGE_FILE_NOT_FOUND = "Specified csv file was not found within the /data folder.";
    public static final String MESSAGE_ERROR_READING = "Error reading row %1d: ";
    public static final String MESSAGE_INCORRECT_CSV_FORMAT = "File is in an incorrect csv format";
    public static final String MESSAGE_INCORRECT_FIELDS = "8 fields of comma separated values not found";
    public static final String MESSAGE_NO_RESIDENTS = "No resident information was found";
    public static final String MESSAGE_DUPLICATE_RESIDENT = "Duplicate resident information found: ";
    public static final String MESSAGE_CONSTRAINTS = "Filename should be a single word";
    public static final String DEFAULT_FILENAME = "safeforhall";

    private final Logger logger = LogsCenter.getLogger(ImportCommand.class);
    private final Path filepath;
    private final String filename;

    /**
     * Constructs an ImportCommand.
     *
     * @param filename filename of csv to be read
     */
    public ImportCommand(String filename) {
        this.filename = filename;
        this.filepath = Paths.get("data", filename + ".csv");
    }

    /**
     * Constructs an ImportCommand.
     *
     * @param path path of csv to be read
     */
    public ImportCommand(Path path) {
        this.filename = DEFAULT_FILENAME;
        this.filepath = path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook newAddressBook = readCsv();
        if (newAddressBook.getPersonList().isEmpty()) {
            logger.warning("Unable to read a single valid resident: Importing aborted!");
            throw new CommandException(MESSAGE_NO_RESIDENTS);
        }
        List<Event> eventList = model.getAddressBook().getEventList();
        ArrayList<Event> eventListRemovedResidents = new ArrayList<>();
        for (Event event: eventList) {
            Event newEvent = new Event(event.getEventName(), event.getEventDate(), event.getEventTime(),
                    event.getVenue(), event.getCapacity(),
                    new ResidentList(ResidentList.DEFAULT_LIST, ResidentList.DEFAULT_LIST));
            eventListRemovedResidents.add(newEvent);
        }
        newAddressBook.setEvents(eventListRemovedResidents);
        model.setAddressBook(newAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Opens and reads the contents of the user-provided csv.
     *
     * @return A new AddressBook with the content of the csv if reading was a success
     * @throws CommandException If an error occurs during command execution.
     */
    private AddressBook readCsv() throws CommandException {
        ArrayList<Person> persons = new ArrayList<Person>();
        try (CSVReader reader = new CSVReader(new FileReader(this.filepath.toString()))) {
            List<String[]> rows = reader.readAll();
            // Remove column headings row
            rows.remove(0);
            for (String[] row: rows) {
                Person personToAdd;
                try {
                    // Skip empty rows
                    if (row.length == 1 && row[0].equals("")) {
                        continue;
                    }
                    personToAdd = createPerson(row);
                } catch (IllegalArgumentException e) {
                    // Index + 2 to account for discarded first row and zero-indexing
                    throw new CommandException(String.format(MESSAGE_ERROR_READING, rows.indexOf(row) + 2)
                        + e.getMessage());
                }
                persons.add(personToAdd);
            }

            AddressBook importedData = new AddressBook();
            for (Person singlePerson : persons) {
                try {
                    importedData.addPerson(singlePerson);
                } catch (DuplicatePersonException e) {
                    throw new CommandException(MESSAGE_DUPLICATE_RESIDENT + singlePerson.toString());
                }
            }
            return importedData;
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (CsvException e) {
            e.printStackTrace();
            throw new CommandException(MESSAGE_INCORRECT_CSV_FORMAT);
        }
    }

    private static Person createPerson(String[] row) throws IllegalArgumentException {
        if (row.length != 8) {
            throw new IllegalArgumentException(MESSAGE_INCORRECT_FIELDS);
        }

        Name name = new Name(row[0]);
        Room room = new Room(row[1]);
        Phone phone = new Phone(row[2]);
        Email email = new Email(row[3]);
        VaccStatus vaccStatus = new VaccStatus(row[4]);
        Faculty faculty = new Faculty(row[5]);
        LastDate lastFet = null;
        LastDate lastCollection = null;
        if (!row[6].trim().isEmpty()) {
            lastFet = new LastDate(row[6]);
        }
        if (!row[7].trim().isEmpty()) {
            lastCollection = new LastDate(row[7]);
        }
        LastDate defaultDate = new LastDate(LastDate.DEFAULT_DATE);
        return new Person(name, room, phone, email, vaccStatus, faculty,
                lastFet == null ? defaultDate : lastFet,
                lastCollection == null ? defaultDate : lastCollection);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && this.filename.equals(((ImportCommand) other).filename)); // state check
    }
}
