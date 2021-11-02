package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.facility.Facility;

/**
 * Exports the data of all facilities that have members allocated, including member names to a CSV file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_IO_ERROR = "Please close exportedData.csv and try again.";
    public static final String MESSAGE_SUCCESS = "Successfully exported to [JAR file location]/data/exportedData.csv";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            File csvFile = new File("data/exportedData.csv");
            if (!csvFile.createNewFile()) {
                csvFile.delete();
                csvFile.createNewFile();
            }

            FileWriter fw = new FileWriter(csvFile);
            List<List<String>> data = new ArrayList<>();
            List<String> headers = Arrays.asList("Facility Name", "Location", "Time", "Capacity", "Allocated Members");
            data.add(headers);
            List<Facility> facilities = model.getFilteredFacilityList();

            for (Facility facility: facilities) {
                List<String> rowData = convertToList(facility);
                data.add(rowData);
            }
            for (List<String> rowData : data) {
                fw.append(String.join(",", rowData));
                fw.append("\n");
            }
            fw.flush();
            fw.close();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_IO_ERROR);
        }
    }

    /**
     * Helper function that converts a given facility's data into a {@code List<String>}.
     *
     * @param facility the given facility.
     * @return the converted {@code List<String>} of the facility's data.
     */
    private List<String> convertToList(Facility facility) {
        List<String> rowData = new ArrayList<>();
        String facilityName = facility.getName().facilityName;
        rowData.add(facilityName);

        String location = facility.getLocation().location;
        rowData.add(location);

        String time = facility.getTime().time;
        rowData.add(time);

        String capacity = facility.getCapacity().capacity;
        rowData.add(capacity);

        String allocatedMembers = facility.getAllocationMap().toString();
        allocatedMembers = allocatedMembers.replaceAll(",", " |").replaceAll("\n", " ");
        rowData.add(allocatedMembers);

        return rowData;
    }
}
