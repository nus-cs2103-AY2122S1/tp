package seedu.siasa.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import seedu.siasa.logic.commands.exceptions.CommandException;
import seedu.siasa.model.Model;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.PolicyIsOwnedByPredicate;

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

        int totalCommission = model.getTotalCommission();

        Map<Contact, Integer> numberPoliciesPerContact = model.getNumberPoliciesPerContact();

        List<Contact> modifiableContactList = new ArrayList<>(model.getFilteredContactList());

        // TODO: Abstract out when comparators for sorting policies are finished.
        modifiableContactList.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contactA, Contact contactB) {
                model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(contactA));
                ObservableList<Policy> personAPolicies = model.getFilteredPolicyList();
                int commissionFromPersonA = getCommissionFromPolicyList(personAPolicies);

                model.updateFilteredPolicyList(new PolicyIsOwnedByPredicate(contactB));
                ObservableList<Policy> personBPolicies = model.getFilteredPolicyList();
                int commissionFromPersonB = getCommissionFromPolicyList(personBPolicies);

                model.updateFilteredPolicyList(x -> true);

                return (commissionFromPersonB - commissionFromPersonA);
            }
        });

        List<String> listStringForTxt = stringListBuilderForTxt(
                totalCommission, modifiableContactList, numberPoliciesPerContact);

        try {
            writeToTxt(listStringForTxt);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_ERROR_WRITING_FILE);
        }

        return new CommandResult(MESSAGE_DOWNLOAD_SUCCESS);
    }

    private int getCommissionFromPolicyList(List<Policy> policyList) {
        float total = 0;
        for (Policy policy : policyList) {
            int commissionPercentage = policy.getCommission().commissionPercentage;
            int numberPayments = policy.getCommission().numberOfPayments;
            int paymentAmt = policy.getPaymentStructure().paymentAmount;
            total = total + ((float) commissionPercentage / 100)
                    * numberPayments * paymentAmt;
        }
        return (int) total;
    }

    private float getAvgPoliciesPerContact(Map<Contact, Integer> numberPoliciesPerContact) {
        int countContacts = numberPoliciesPerContact.size();
        int countPolicies = numberPoliciesPerContact.values()
                .stream().mapToInt(Integer::intValue).sum();
        return (float) countPolicies / countContacts;
    }

    private List<String> stringListBuilderForTxt(
        int totalCommission, List<Contact> sortedContacts, Map<Contact, Integer> numberPoliciesPerContact) {
        List<String> stringList = new ArrayList<>();

        stringList.add("Statistics for " + CURRENT_DATE + "\n");
        stringList.add("Most premium contacts:\n" + TITLE_UNDERLINE);
        for (Contact contact : sortedContacts) {
            stringList.add(contact.toString());
        }
        stringList.add("\n");

        stringList.add("Number of policies per contact:\n" + TITLE_UNDERLINE);
        numberPoliciesPerContact.forEach((contact, count) -> {
            stringList.add(String.format("%s: %d policies", contact.getName().fullName, count));
        });
        stringList.add("\n");

        stringList.add("Average number of policies per contact: "
                + String.format("%.2f", getAvgPoliciesPerContact(numberPoliciesPerContact)));

        stringList.add("Total Commission: " + centsToDollars(totalCommission));
        return stringList;
    }

    private void writeToTxt(List<String> stringList) throws IOException {
        FileWriter fileWriter = new FileWriter(TXT_FILEPATH);
        for (String string : stringList) {
            fileWriter.append(string);
            fileWriter.append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    private String centsToDollars(int priceInCents) {
        int cents = priceInCents % 100;
        int dollars = (priceInCents - cents) / 100;

        String centsStr;
        if (cents <= 9) {
            centsStr = 0 + "" + cents;
        } else {
            centsStr = Integer.toString(cents);
        }

        return "$" + dollars + "." + centsStr;
    }

}
