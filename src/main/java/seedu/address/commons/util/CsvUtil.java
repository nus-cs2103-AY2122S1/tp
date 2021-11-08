package seedu.address.commons.util;

import static java.util.Map.entry;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

public class CsvUtil {

    /**
     * Sequence that exported fields should take
     */
    static final List<Prefix> OPTIONAL_PREFIXES = List.of(
            PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_BIRTHDAY, PREFIX_TAG);

    /**
     * Mapping between the prefix and the function that returns that field
     */
    static final Map<Prefix, Function<Person, String>> PREFIX_TO_DATA_FUNC = Map.ofEntries(
            entry(PREFIX_NAME, (p)->p.getName().toString()),
            entry(PREFIX_PHONE, (p)->p.getPhone().toString()),
            entry(PREFIX_EMAIL, (p)->p.getEmail().toString()),
            entry(PREFIX_ADDRESS, (p)->p.getAddress().toString()),
            entry(PREFIX_BIRTHDAY, (p)->p.getBirthday().map(x->x.toString()).orElse("")),
            entry(PREFIX_TAG, (p)-> p.getTags().isEmpty()
                    ? ""
                    : p.getTags().stream().map(x->x.toString()).collect(Collectors.joining(","))
            )
    );

    /**
     * Mapping between the prefix and the name of that field
     */
    static final Map<Prefix, String> PREFIX_TO_NAME = Map.ofEntries(
            entry(PREFIX_NAME, "Name"),
            entry(PREFIX_PHONE, "Phone"),
            entry(PREFIX_EMAIL, "Email"),
            entry(PREFIX_ADDRESS, "Address"),
            entry(PREFIX_BIRTHDAY, "Birthday"),
            entry(PREFIX_TAG, "Tags")
    );

    /**
     * Writes a List of Person objects as a csv file.
     *
     * @param personList List of Person objects to write
     * @param path file path for csv
     * @param exportPrefixes Prefixes for the fields to be included in the csv
     * @throws IOException
     */
    public static void modelToCsv(List<Person> personList, Path path,
                                  Collection<Prefix> exportPrefixes) throws IOException {

        File file = new File(path.toString());
        FileWriter fileWriter = new FileWriter(file);

        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(getPersonHeadersAsStringArray(exportPrefixes));

        for (Person person: personList) {
            csvWriter.writeNext(getPersonAsStringArray(person, exportPrefixes));
        }
        csvWriter.close();
    }

    /**
     * Gets the field for a person, prefix combination.
     *
     * @param prefix
     * @param person
     * @return String data for the person,prefix combination
     */
    private static String getField(Prefix prefix, Person person) {
        return PREFIX_TO_DATA_FUNC.get(prefix).apply(person);
    }

    /**
     * Gets the field name for a prefix.
     *
     * @param prefix
     * @return String data for the person,prefix combination
     */
    private static String getName(Prefix prefix) {
        return PREFIX_TO_NAME.get(prefix);
    }

    /**
     * Serializes a {@code Person} into an array of Strings.
     * First member is always Name, rest of array is filled based on exportPrefixes.
     *
     * @param person Person object to serialize
     * @param prefixes List of prefixes that the array should contain the information of
     * @return String Array containing Name, followed by other data
     */
    private static String[] getPersonAsStringArray(Person person, Collection<Prefix> prefixes) {

        List<String> csvLine = new ArrayList<>();
        csvLine.add(getField(PREFIX_NAME, person));

        List<String> csvFields = OPTIONAL_PREFIXES.stream()
                .filter(prefix->prefixes.contains(prefix))
                .map(prefix->getField(prefix, person))
                .collect(Collectors.toList());

        csvLine.addAll(csvFields);
        return csvLine.toArray(String[]::new);
    }

    /**
     * Creates a header row when provided a series of Prefixes.
     *
     * @param prefixes List of prefixes to add
     * @return String Array of the headers
     */
    private static String[] getPersonHeadersAsStringArray(Collection<Prefix> prefixes) {
        List<String> csvHeaders = new ArrayList<>();
        csvHeaders.add("Name");

        List<String> csvFields = OPTIONAL_PREFIXES.stream()
                .filter(prefix-> prefixes.contains(prefix))
                .map(prefix->getName(prefix))
                .collect(Collectors.toList());

        csvHeaders.addAll(csvFields);
        return csvHeaders.toArray(String[]::new);
    }
}
