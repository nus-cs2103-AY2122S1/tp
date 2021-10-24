package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
import safeforhall.model.event.*;
import safeforhall.model.person.Email;
import safeforhall.model.person.Faculty;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.Name;
import safeforhall.model.person.Person;
import safeforhall.model.person.Phone;
import safeforhall.model.person.Room;
import safeforhall.model.person.VaccStatus;

/**
 * Imports the csv and replaces the existing address book with the available information
 * provided a correctly formatted csv was found at "/data/"
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports resident information from the specified csv "
            + "file located within the /data folder\n"
            + "Note: \n"
            + "     1. 8 comma separated values for each row in order; \n"
            + "             name, room, phone, email, vaccStatus, faculty, lastFetDate, lastCollectionDate\n"
            + "     2. The first row will be discarded as column headings\n"
            + "     3. LastFetDate and LastCollectionDate are optional (can be left as empty space)\n"
            + "Parameters: "
            + "NAME_OF_CSV\n"
            + "Example: " + COMMAND_WORD + " "
            + "safeforhall";

    public static final String MESSAGE_SUCCESS = "Imported resident information from csv";
    public static final String MESSAGE_FILE_NOT_FOUND = "Specified csv file was not found within the /data folder.";
    public static final String MESSAGE_ERROR_READING = "Error reading row %1d: ";
    public static final String MESSAGE_INCORRECT_CSV_FORMAT = "File is in an incorrect csv format";
    public static final String MESSAGE_INCORRECT_FIELDS = "8 fields of comma separated values not found";
    public static final String DEFAULT_FILENAME = "safeforhall";

    private final String filename;

    public ImportCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook importedData = readCsv();
        List<Event> eventList = model.getAddressBook().getEventList();
        for (Event event: eventList) {
//            event.clearResidentList();
        }
        importedData.setEvents(eventList);        
        model.setAddressBook(importedData);
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
        Path what = Paths.get("data" , this.filename + ".csv");
        try (CSVReader reader = new CSVReader(new FileReader(what.toString()))) {
            List<String[]> r = reader.readAll();
            // Remove column headings row
            r.remove(0);
            for (String[] x: r) {
                Person personToAdd;
                try {
                    personToAdd = createPerson(x);
                } catch (IllegalArgumentException e) {
                    // Index + 2 to account for discarded first row and zero-indexing
                    throw new CommandException(String.format(MESSAGE_ERROR_READING, r.indexOf(x) + 2)
                        + e.getMessage());
                }
                persons.add(personToAdd);
            }

            AddressBook importedData = new AddressBook();
            for (Person singlePerson : persons) {
                importedData.addPerson(singlePerson);
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
}
