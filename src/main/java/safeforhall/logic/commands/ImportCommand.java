package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.model.AddressBook;
import safeforhall.model.Model;
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

    public static final String MESSAGE_SUCCESS = "Imported resident information from csv";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        AddressBook importedData = readCsv();
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
        Path what = Paths.get("data" , "safeforhall.csv");
        try (CSVReader reader = new CSVReader(new FileReader(what.toString()))) {
            List<String[]> r = reader.readAll();
            // Remove column headings row
            r.remove(0);
            r.forEach(x -> {
                persons.add(
                        new Person(
                                new Name(x[0]), new Room(x[1]), new Phone(x[2]),
                                new Email(x[3]), new VaccStatus(x[4]),
                                new Faculty(x[5]), new LastDate(x[6]), new LastDate(x[7])
                        )
                );
            });
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        AddressBook importedData = new AddressBook();
        for (Person singlePerson : persons) {
            importedData.addPerson(singlePerson);
        }
        return importedData;
    }
}
