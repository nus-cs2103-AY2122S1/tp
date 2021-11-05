package seedu.mycrm.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;
import seedu.mycrm.model.products.Product;

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";
    public static final String MESSAGE_INVALID_CLIENT = "JSON Job list contains an illegal job contact";
    public static final String MESSAGE_INVALID_PRODUCT = "JSON Job list contains an illegal job product";
    public static final String MESSAGE_INVALID_STATUS = "JSON Job list contains an illegal job status";
    public static final String MESSAGE_INVALID_COMPLETION_DATE = "Pending job should not have a completion date";

    private String jobDescription;
    private String client;
    private String product;
    private String expectedCompletionDate;
    private String jobStatus;
    private String receivedDate;
    private String completionDate;
    private String fee;

    /**
     * Constructs a {@code JsonAdaptedJob} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("jobDescription") String jobDescription,
                          @JsonProperty("client") String client,
                          @JsonProperty("product") String product,
                          @JsonProperty("expectedCompletionDate") String expectedCompletionDate,
                          @JsonProperty("jobStatus") String jobStatus,
                          @JsonProperty("receivedDate") String receivedDate,
                          @JsonProperty("completionDate") String completionDate,
                          @JsonProperty("fee") String fee) {
        this.jobDescription = jobDescription;
        this.client = client;
        this.product = product;
        this.expectedCompletionDate = expectedCompletionDate;
        this.jobStatus = jobStatus;
        this.receivedDate = receivedDate;
        this.completionDate = completionDate;
        this.fee = fee;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        jobDescription = source.getJobDescription().toString();
        client = source.getClient().getName().toString();
        product = source.getProduct().getName().toString();
        expectedCompletionDate = source.getExpectedCompletionDate().raw();
        jobStatus = source.getJobStatus().toString();
        receivedDate = source.getReceivedDate().raw();
        fee = source.getFee().toString();
        if (source.isCompleted()) {
            completionDate = source.getCompletionDate().raw();
        }
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Job} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job.
     */
    public Job toModelType(MyCrm model) throws IllegalValueException {

        if (jobDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidJobDescription(jobDescription)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelJobDescription = new JobDescription(jobDescription);


        if (expectedCompletionDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Expected Completion Date"));
        }

        if (!JobDate.isValidJobDate(expectedCompletionDate)) {
            throw new IllegalValueException("Expected Completion Date: " + JobDate.MESSAGE_CONSTRAINTS);
        }
        final JobDate modelJobExpectedCompletionDate = new JobDate(expectedCompletionDate);


        if (receivedDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                "Received Date"));
        }

        if (!JobDate.isValidJobDate(receivedDate)) {
            throw new IllegalValueException("Received Date: " + JobDate.MESSAGE_CONSTRAINTS);
        }
        final JobDate modelJobReceivedDate = new JobDate(receivedDate);


        if (fee == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JobFee.class.getSimpleName()));
        }

        if (!JobFee.isValidJobFee(fee)) {
            throw new IllegalValueException(JobFee.MESSAGE_CONSTRAINTS);
        }
        final JobFee modelJobFee = new JobFee(fee);

        if (jobStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobStatus.class.getSimpleName()));
        }

        final JobStatus modelJobStatus;
        if (jobStatus.equals("Completed")) {
            modelJobStatus = new JobStatus(true);
        } else if (jobStatus.equals("In Progress")) {
            modelJobStatus = new JobStatus(false);
        } else {
            throw new IllegalValueException(MESSAGE_INVALID_STATUS);
        }

        JobDate modelJobCompletionDate = null;
        if (completionDate != null) {
            if (jobStatus.equals("In Progress")) {
                throw new IllegalValueException(MESSAGE_INVALID_COMPLETION_DATE);
            }

            if (JobDate.isValidJobDate(completionDate)) {
                modelJobCompletionDate = new JobDate(completionDate);
            } else {
                throw new IllegalValueException("Completion Date: " + JobDate.MESSAGE_CONSTRAINTS);
            }
        }

        final Job modelJob = new Job(modelJobDescription, modelJobExpectedCompletionDate,
                modelJobReceivedDate, modelJobFee);
        modelJob.setJobStatus(modelJobStatus);
        modelJob.setCompletionDate(modelJobCompletionDate);

        linkContactProduct(modelJob, model);

        return modelJob;
    }

    /**
     * Updates specified job with {@code Contact} and {@code Product} object
     *
     * @param job target job
     * @param model MyCrm model
     * @throws IllegalValueException if there were any data constraints violated in the adapted job.
     */
    public void linkContactProduct(Job job, MyCrm model) throws IllegalValueException {
        if (this.client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Contact.class.getSimpleName()));
        }
        if (this.product == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Product.class.getSimpleName()));
        }

        Optional<Contact> matchClient = model.getContactList()
                .stream()
                .filter(contact -> this.client.equals(contact.getName().toString()))
                .findFirst();

        if (matchClient.isPresent()) {
            job.setClient(matchClient.get());
        } else {
            throw new IllegalValueException(MESSAGE_INVALID_CLIENT);
        }

        Optional<Product> matchProduct = model.getProductList()
                .stream()
                .filter(product -> this.product.equals(product.getName().toString()))
                .findFirst();

        if (matchProduct.isPresent()) {
            job.setProduct(matchProduct.get());
        } else {
            throw new IllegalValueException(MESSAGE_INVALID_PRODUCT);
        }
    }
}
