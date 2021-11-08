package seedu.mycrm.logic.commands.mails;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;
import static seedu.mycrm.testutil.TypicalMails.COMPLETED_JOB;
import static seedu.mycrm.testutil.TypicalMails.NO_EMAIL_CONTACT;
import static seedu.mycrm.testutil.TypicalMails.NO_EMAIL_JOB;
import static seedu.mycrm.testutil.TypicalMails.getTypicalMyCrm;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.ReadOnlyMyCrm;
import seedu.mycrm.model.ReadOnlyUserPrefs;
import seedu.mycrm.model.UserPrefs;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.history.History;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.mail.Mail;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.MailBuilder;


class MailCommandTest {

    @Test
    public void constructor_nullMail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MailCommand(null, null));
    }

    @Test
    public void execute_mailAcceptedByModelManager_addSuccessful() {
        Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

        Job jobToMail = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        Template templateToMail = model.getFilteredTemplateList().get(INDEX_FIRST_TEMPLATE.getZeroBased());
        Mail validMail = new Mail(jobToMail, templateToMail);

        MailCommand expectedCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);

        String expectedMessage = String.format(MailCommand.MESSAGE_MAIL_SUCCESS, COMPLETED.getSubject().toString());

        ModelManager expectedModel = new ModelManager(model.getMyCrm(), new UserPrefs());
        expectedModel.addMail(validMail);

        assertCommandSuccess(expectedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_mailAcceptedByModelStub_addSuccessful() throws CommandException {
        MailCommandTest.ModelStubAcceptingMailAdded modelStub =
                new MailCommandTest.ModelStubAcceptingMailAdded();
        modelStub.addJob(COMPLETED_JOB);
        modelStub.addTemplate(COMPLETED);

        Mail validMail = new MailBuilder().withJob(COMPLETED_JOB).withTemplate(COMPLETED).build();

        CommandResult commandResult = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE).execute(modelStub,
            new StateManager(modelStub));

        assertEquals(String.format(MailCommand.MESSAGE_MAIL_SUCCESS, COMPLETED.getSubject().toString()),
                commandResult.getFeedbackToUser());
        assertEquals(List.of(validMail), modelStub.mailsAdded);
    }

    @Test
    public void execute_noEmailClient_throwsCommandException() {
        MailCommandTest.ModelStubAcceptingMailAdded modelStub =
                new MailCommandTest.ModelStubAcceptingMailAdded();
        modelStub.addJob(NO_EMAIL_JOB);
        modelStub.addContact(NO_EMAIL_CONTACT);
        modelStub.addTemplate(COMPLETED);

        MailCommand mailNoEmailCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);
        assertCommandFailure(mailNoEmailCommand, modelStub, Messages.MESSAGE_INVALID_JOB_NO_EMAIL);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalMyCrm(), new UserPrefs());

        Index outOfBoundJobIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        Index outOfBoundTemplateIndex = Index.fromOneBased(model.getFilteredTemplateList().size() + 1);

        MailCommand mailJobCommand = new MailCommand(outOfBoundJobIndex, INDEX_FIRST_TEMPLATE);
        MailCommand mailTemplateCommand = new MailCommand(INDEX_FIRST_JOB, outOfBoundTemplateIndex);

        assertCommandFailure(mailJobCommand, model, Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        assertCommandFailure(mailTemplateCommand, model, Messages.MESSAGE_INVALID_TEMPLATE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MailCommand completedCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);
        MailCommand doneCommand = new MailCommand(INDEX_FIRST_JOB, INDEX_SECOND_TEMPLATE);

        // same object -> returns true
        assertEquals(completedCommand, completedCommand);

        // same values -> returns true
        MailCommand completedCommandCopy = new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE);
        assertEquals(completedCommand, completedCommandCopy);

        // different types -> returns false
        assertNotEquals(1, completedCommand);

        // null -> returns false
        assertNotEquals(null, completedCommand);

        // different mail -> returns false
        assertNotEquals(completedCommand, doneCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private static class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMyCrmFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyCrmFilePath(Path myCrmFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTemplate(Template template) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMail(Mail mail) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTemplate(Template target, Template editedTemplate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addJob(Job job) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHistory(History history) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearHistory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMyCrm(ReadOnlyMyCrm newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMyCrm getMyCrm() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasContact(Contact contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTemplate(Template contact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasJob(Job toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTemplate(Template target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteJob(Job target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContact(Contact target, Contact editedContact) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setJob(Job job, Job editedJob) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void hideContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoHideContact(Contact target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProduct(Product product) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProduct(Product target, Product edited) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getRevenue(LocalDate date) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredTopThreeProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredIncompleteJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredAllJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredMonthlyCompletedJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Job> getLatestJobPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Mail> getFilteredMailList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Product> getFilteredProductList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<History> getFilteredHistoryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredContactList(Predicate<Contact> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTemplateList(Predicate<Template> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMailList(Predicate<Mail> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredProductList(Predicate<Product> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredJobList(Predicate<Job> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHistoryList(Predicate<History> history) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the template, job and only 1 mail being added.
     */
    private static class ModelStubAcceptingMailAdded extends MailCommandTest.ModelStub {
        final ArrayList<Mail> mailsAdded = new ArrayList<>();
        final ArrayList<Template> templatesAdded = new ArrayList<>();
        final ArrayList<Job> jobsAdded = new ArrayList<>();
        final ArrayList<Contact> contactsAdded = new ArrayList<>();

        @Override
        public void addMail(Mail mail) {
            requireNonNull(mail);
            mailsAdded.add(mail);
        }

        @Override
        public void addTemplate(Template template) {
            requireNonNull(template);
            templatesAdded.add(template);
        }

        @Override
        public void addJob(Job job) {
            requireNonNull(job);
            jobsAdded.add(job);
        }

        @Override
        public void addContact(Contact contact) {
            requireNonNull(contact);
            contactsAdded.add(contact);
        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            return FXCollections.observableList(templatesAdded);
        }

        @Override
        public ObservableList<Job> getFilteredJobList() {
            return FXCollections.observableList(jobsAdded);
        }

        @Override
        public ObservableList<Contact> getFilteredContactList() {
            return FXCollections.observableList(contactsAdded);
        }

        @Override
        public ReadOnlyMyCrm getMyCrm() {
            return new MyCrm();
        }
    }
}
