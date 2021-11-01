package seedu.notor.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import seedu.notor.commons.core.LogsCenter;
import seedu.notor.model.Model;
import seedu.notor.model.person.Person;

/**
 * Clears Notor of all entries
 */
public class ExportCommand implements Command {

    public static final String COMMAND_WORD = "export";
    public static final List<String> COMMAND_WORDS = Arrays.asList("export", "exp");

    public static final String MESSAGE_SUCCESS = "Data has been exported." + System.lineSeparator()
        + "You can find the CSV file in the directory where Notor belongs.";
    public static final String UNEXPECTED_ERROR = "An unexpected error has occur. Please try again!";

    private static final Logger logger = LogsCenter.getLogger(ExportCommand.class);

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Exporting to CSV...");
        try {
            String fileName = "Exported Data " + new SimpleDateFormat("d.MM.yyyy-HHmmss").format(new Date())
                + ".csv";
            Writer writer = new FileWriter(fileName);
            List<Person> people = model.getFilteredPersonList();
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer).build();
            beanToCsv.write(people);
            writer.close();
            assert new File(fileName).exists();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            return new CommandResult(UNEXPECTED_ERROR);
        }
    }
}
