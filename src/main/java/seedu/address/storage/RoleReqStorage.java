package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class RoleReqStorage {

    public static final String FILEPATH = "data/RoleReq.txt";
    private static File file = new File(FILEPATH);

    // The role name and the requirements have corresponding indexes.
    // For example, "bartender" is in roleNames[0], and its role requirements corresponds to requirements[0]
    private static final String[] roleNames = new String[]{"bartender", "floor", "kitchen"};
    private static final int[] DEFAULT_REQUIREMENTS = new int[]{0, 0, 0};
    private static int[] requirements = new int[]{0, 0, 0};

    /**
     * Loads the existing file for the list of requirements if it exists.
     *
     * @return an int[] of requirements.
     * @throws IOException If there are errors processing the file.
     */
    public static int[] load() throws IOException {

        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the RoleReq.txt file.
            update();
            return requirements; // default should be 0, 0, 0
        }

        if (file.length() == 0) {
            update();
            return requirements; // default should be 0, 0, 0
        }

        return readFileModifyRequirements();
    }

    /**
     * Reads the saved file and adds tasks to the tasklist.
     *
     * @return An int[] of tasks read from the save file.
     * @throws FileNotFoundException If saved file cannot be found.
     */
    private static int[] readFileModifyRequirements() throws FileNotFoundException {
        Scanner fileSc = new Scanner(file);
        int i = 0;

        while (fileSc.hasNext() && i < 3) {
            String nextLine = fileSc.nextLine();
            requirements[i] = Integer.parseInt(nextLine);
            i++;
        }

        fileSc.close();
        return requirements;
    }

    /**
     * Updates the file.
     *
     * @throws IOException If the file cannot be created or opened.
     */
    private static void update() throws IOException {
        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the RoleReq.txt file.
        }

        StringBuilder txt = new StringBuilder();
        for (int n : requirements) {
            txt.append(n).append("\n");
        }

        PrintWriter pw = new PrintWriter(file);
        pw.append(txt.toString());
        pw.flush();
    }

    /**
     * Updates the save file with the new minimum number of staff required for a specific role.
     *
     * @param role The role which minimum requirement will be updated.
     * @param numMinStaff The minimum number of staff required for that role.
     */
    public static void update(String role, int numMinStaff) throws IOException {
        if (!file.exists()) {
            // Create the data folder if it does not exist.
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            file.createNewFile(); // Create the RoleReq.txt file.
        }

        int roleNum = getNumFromRole(role.toLowerCase());
        requirements[roleNum] = numMinStaff;
        update();
    }

    private static int getNumFromRole(String role) {
        return Arrays.asList(roleNames).indexOf(role);
    }

    /**
     * Returns the minimum of bartenders required for each shift.
     *
     * @return the minimum of bartenders required for each shift.
     */
    public static int getMinNumBartender() {
        return requirements[0];
    }

    /**
     * Returns the minimum of floor staff required for each shift.
     *
     * @return the minimum of floor staff required for each shift.
     */
    public static int getMinNumFloor() {
        return requirements[1];
    }

    /**
     * Returns the minimum of kitchen staff required for each shift.
     *
     * @return the minimum of kitchen staff required for each shift.
     */
    public static int getMinNumKitchen() {
        return requirements[2];
    }

    /**
     * Returns a String representing the current Role Requirements.
     *
     * @return String representation of the current Role Requirements.
     */
    public static String getRoleReqs() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < roleNames.length; i++) {
            result.append(roleNames[i] + ": " + requirements[i] + "\n");
        }
        return result.toString();
    }

    /**
     * Resets the timings to the default timings.
     *
     * @throws IOException If the file cannot be created or opened.
     */
    public static void reset() throws IOException {
        requirements = DEFAULT_REQUIREMENTS;
        update();
    }
}

