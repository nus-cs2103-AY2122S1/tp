package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RoleReqStorage {

    private File file;
    private ArrayList<String> requirements = new ArrayList<>();

    /**
     * Constructs a RoleReqStorage object.
     *
     * @param filePath The filePath where the file is found or will be created.
     */
    public RoleReqStorage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads the existing file for the list of requirements if it exists.
     *
     * @return an ArrayList of requirements.
     * @throws IOException If there are errors processing the file.
     */
    public ArrayList<String> load() throws IOException {

        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the duke.txt file.
            return new ArrayList<String>();
        }

        if (file.length() == 0) {
            return new ArrayList<String>();
        }

        return readFileModifyReq();
    }

    /**
     * Reads the saved file and adds tasks to the tasklist.
     *
     * @return An ArrayList of tasks read from the save file.
     * @throws FileNotFoundException If saved file cannot be found.
     */
    private ArrayList<String> readFileModifyReq() throws FileNotFoundException {
        Scanner fileSc = new Scanner(file);

        while (fileSc.hasNext()) {
            String nextLine = fileSc.nextLine();
            requirements.add(nextLine);
        }

        fileSc.close();
        return requirements;
    }

    /**
     * Updates the file.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private void update() throws FileNotFoundException {
        StringBuilder txt = new StringBuilder();
        for (String s : requirements) {
            txt.append(s).append("\n");
        }

        PrintWriter pw = new PrintWriter(file);
        pw.append(txt.toString());
        pw.flush();
    }

}

