package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RoleReqStorage {

    public static final String FILEPATH = "data/RoleReq.txt";
    private static File file = new File(FILEPATH);
    private static int[] requirements = new int[]{0, 0, 0}; // bartender, floor, kitchen

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
     * @throws FileNotFoundException If the file is not found.
     */
    private static void update() throws FileNotFoundException {
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
    public static void update(String role, int numMinStaff) throws FileNotFoundException {
        int roleNum = getNumFromRole(role.toLowerCase());
        requirements[roleNum] = numMinStaff;
        update();
    }

    private static int getNumFromRole(String role) {
        if (role.equals("bartender")) {
            return 0;
        } else if (role.equals("floor")) {
            return 1;
        } else { // checks are done in setRoleReqCommand
            return 2;
        }
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
        return "Bartenders: " + requirements[0] + "\n"
                + "Floor: " + requirements[1] + "\n"
                + "Kitchen: " + requirements[2] + "\n";
    }
}

