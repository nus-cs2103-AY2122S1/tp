package seedu.address.commons.util;
import com.opencsv.CSVWriter;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {

    public static String[] personToStringArr(Person person, List<Prefix> exportPrefixes) {

        List<String> csvLine = new ArrayList<>();
        csvLine.add(person.getName().toString());

        if (exportPrefixes.contains(PREFIX_PHONE)) {
            csvLine.add(person.getPhone().toString());
        }
        if (exportPrefixes.contains(PREFIX_EMAIL)) {
            csvLine.add(person.getEmail().toString());
        }
        if (exportPrefixes.contains(PREFIX_ADDRESS)) {
            csvLine.add(person.getAddress().toString());
        }
        if (exportPrefixes.contains(PREFIX_BIRTHDAY)) {
            csvLine.add(person.getBirthday().map(x->x.toString()).orElse(""));
        }
        if (exportPrefixes.contains(PREFIX_TAG)) {
            csvLine.add(person.getTags().toString());
        }
        return csvLine.toArray(String[]::new);
    }

    public static String[] personHeaders (List<Prefix> prefixes) {
        List<String> csvLine = new ArrayList<>();
        csvLine.add("Name");
        if (prefixes.contains(PREFIX_PHONE)) {
            csvLine.add("Phone");
        }
        if (prefixes.contains(PREFIX_EMAIL)) {
            csvLine.add("Email");
        }
        if (prefixes.contains(PREFIX_ADDRESS)) {
            csvLine.add("Address");
        }
        if (prefixes.contains(PREFIX_BIRTHDAY)) {
            csvLine.add("Birthday");
        }
        if (prefixes.contains(PREFIX_TAG)) {
            csvLine.add("Tags");
        }
        return csvLine.toArray(String[]::new);
    }


    public static void modelToCsv(List<Person> personList, Path path, List<Prefix> exportPrefixes) throws IOException {

        File file = new File(path.toString());
        FileWriter fileWriter = new FileWriter(file);

        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(personHeaders(exportPrefixes));


        for (Person person: personList) {
            csvWriter.writeNext(personToStringArr(person,exportPrefixes));
        }
        csvWriter.close();
    }
}
