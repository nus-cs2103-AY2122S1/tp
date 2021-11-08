package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.CurrencyUtil.centsToDollars;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import seedu.siasa.commons.util.FileUtil;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;

public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";
    public static final String COMMISSION_FILEPATH = "data\\commission_per_contact.csv";
    public static final String NO_POLICIES_FILEPATH = "data\\no_policies_per_contact.csv";
    public static final String COMMISSION_HEADER = "Name,Phone,Email,Address,Commission";
    public static final String NO_POLICIES_HEADER = "Name,Phone,Email,Address,NoPolicies";
    public static final String MESSAGE_DOWNLOAD_SUCCESS = "Files have been downloaded, "
            + "you can view it in /data folder";
    public static final String MESSAGE_ERROR_WRITING_FILE = "There was an error saving the file.";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Map<Contact, Integer> numberPoliciesPerContact = model.getNumberPoliciesPerContact();
        Map<Contact, Double> commissionPerContact = model.getCommissionPerContact();

        List<String> listStringCommission = commissionStringListBuilder(commissionPerContact, COMMISSION_HEADER);
        List<String> listStringNoPolicies = noPoliciesStringListBuilder(numberPoliciesPerContact, NO_POLICIES_HEADER);

        try {
            writeToTxt(listStringCommission, COMMISSION_FILEPATH);
            writeToTxt(listStringNoPolicies, NO_POLICIES_FILEPATH);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_ERROR_WRITING_FILE);
        }

        return new CommandResult(MESSAGE_DOWNLOAD_SUCCESS);
    }

    private List<String> commissionStringListBuilder(Map<Contact, Double> contactStats, String headerText) {
        List<String> stringList = new ArrayList<>();

        stringList.add(headerText);

        contactStats.forEach((contact, stat) -> {
            stringList.add(contact.toCsvFormat() + ",\"" + centsToDollars(stat) + "\"");
        });
        stringList.add("\n");

        return stringList;
    }

    private List<String> noPoliciesStringListBuilder(Map<Contact, Integer> contactStats, String headerText) {
        List<String> stringList = new ArrayList<>();

        stringList.add(headerText);

        contactStats.forEach((contact, stat) -> {
            stringList.add(contact.toCsvFormat() + ",\"" + stat + "\"");
        });
        stringList.add("\n");

        return stringList;
    }

    private void writeToTxt(List<String> stringList, String filePath) throws IOException {
        Path pathToFile = Paths.get(".", filePath);
        FileUtil.createIfMissing(pathToFile);
        StringBuilder stats = new StringBuilder();
        for (String string : stringList) {
            stats.append(string + "\n");
        }
        FileUtil.writeToFile(pathToFile, stats.toString());
    }
}
