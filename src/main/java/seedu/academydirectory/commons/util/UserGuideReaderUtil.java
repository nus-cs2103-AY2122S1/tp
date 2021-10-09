package seedu.academydirectory.commons.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserGuideReaderUtil {
    private static final File USER_GUIDE= new File("docs/UserGuide.md");
    private static final String SUMMARY_HEADER = "## Command summary";
    private static final String COMMAND_HEADER = "### ";

    private static boolean isTerminatedCondition(String currentLine, String keyword) {
        return currentLine.startsWith(COMMAND_HEADER) && !currentLine.contains(keyword);
    }

    private static boolean isStartingCondition(String currentLine, String keyword) {
        return currentLine.startsWith(COMMAND_HEADER) && currentLine.contains(keyword);
    }

    public static String getGeneralHelp() {
        StringBuilder generalHelp = new StringBuilder();
        boolean isSummary = false;
        try {
            FileReader fileReader = new FileReader(USER_GUIDE);
            Scanner fileScanner = new Scanner(fileReader);
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine().trim();
                if (nextLine.contains(SUMMARY_HEADER)) {
                    isSummary = true;
                }
                if (isSummary) {
                    generalHelp.append(nextLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generalHelp.toString();
    }

    public static String getSpecificHelp(String keyword) {
        StringBuilder specificHelp = new StringBuilder();
        boolean hasKeyword = false;
        try {
            FileReader fileReader = new FileReader(USER_GUIDE);
            Scanner fileScanner = new Scanner(fileReader);
            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine().trim();
                if (isStartingCondition(nextLine, keyword)) {
                    hasKeyword = true;
                }
                if (hasKeyword && isTerminatedCondition(nextLine, keyword)) {
                    break;
                } else if (hasKeyword) {
                    specificHelp.append(nextLine).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return specificHelp.toString();
    }
}
