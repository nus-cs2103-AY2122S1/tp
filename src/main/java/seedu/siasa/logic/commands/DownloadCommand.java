package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.siasa.commons.util.CurrencyUtil.centsToDollars;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import seedu.siasa.commons.util.FileUtil;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;

public class DownloadCommand extends Command {

    public static final String COMMAND_WORD = "download";

    public static final String CURRENT_DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    public static final String TXT_FILEPATH = "data\\stats.txt";
    public static final String MESSAGE_DOWNLOAD_SUCCESS = "File has been downloaded, you can view it at "
            + TXT_FILEPATH;
    public static final String MESSAGE_ERROR_WRITING_FILE = "There was an error saving the file.";
    public static final String TITLE_UNDERLINE = "-----------------------";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        double totalCommission = model.getTotalCommission();

        Map<Contact, Integer> numberPoliciesPerContact = model.getNumberPoliciesPerContact();
        Map<Contact, Double> commissionPerContact = model.getCommissionPerContact();

        List<String> listStringForTxt = stringListBuilderForTxt(
                totalCommission, commissionPerContact, numberPoliciesPerContact);

        try {
            writeToTxt(listStringForTxt);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_ERROR_WRITING_FILE);
        }

        return new CommandResult(MESSAGE_DOWNLOAD_SUCCESS);
    }

    private float getAvgPoliciesPerContact(Map<Contact, Integer> numberPoliciesPerContact) {
        int countContacts = numberPoliciesPerContact.size();
        int countPolicies = numberPoliciesPerContact.values()
                .stream().mapToInt(Integer::intValue).sum();
        if (countContacts == 0) {
            return 0;
        }
        assert countContacts > 0 : "number of contacts should be greater than 0";
        return (float) countPolicies / countContacts;
    }

    private List<String> stringListBuilderForTxt(
        double totalCommission, Map<Contact,
            Double> commissionPerContact, Map<Contact, Integer> numberPoliciesPerContact) {
        List<String> stringList = new ArrayList<>();

        stringList.add("Statistics for " + CURRENT_DATE + "\n");
        stringList.add("Most premium contacts:\n" + TITLE_UNDERLINE);
        commissionPerContact.forEach((contact, commission) -> {
            stringList.add(contact + "; Commission: " + centsToDollars(commission));
        });
        stringList.add("\n");

        stringList.add("Number of policies per contact:\n" + TITLE_UNDERLINE);
        numberPoliciesPerContact.forEach((contact, policyCount) -> {
            stringList.add(contact + "; Number of Policies: " + policyCount);
        });
        stringList.add("\n");

        stringList.add("Average number of policies per contact: "
                + String.format("%.2f", getAvgPoliciesPerContact(numberPoliciesPerContact)));

        stringList.add("Total Commission: " + centsToDollars(totalCommission));
        return stringList;
    }

    private void writeToTxt(List<String> stringList) throws IOException {
        Path pathToFile = Paths.get(".", TXT_FILEPATH);
        FileUtil.createIfMissing(pathToFile);
        StringBuilder stats = new StringBuilder();
        for (String string : stringList) {
            stats.append(string + "\n");
        }
        FileUtil.writeToFile(pathToFile, stats.toString());
    }
}
