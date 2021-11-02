package seedu.fast.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.fast.commons.exceptions.IllegalValueException;
import seedu.fast.model.person.Address;
import seedu.fast.model.person.Appointment;
import seedu.fast.model.person.AppointmentCount;
import seedu.fast.model.person.Email;
import seedu.fast.model.person.Name;
import seedu.fast.model.person.Person;
import seedu.fast.model.person.Phone;
import seedu.fast.model.person.Remark;
import seedu.fast.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String remark;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String appointmentDate;
    private final String appointmentTime;
    private final String appointmentVenue;
    private final String appointmentCount;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("remark") String remark, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("appointment date") String date, @JsonProperty("appointment time") String time,
            @JsonProperty("appointment venue") String venue, @JsonProperty("appointment count") String count) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.appointmentVenue = venue;
        this.appointmentCount = count;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        // shows the appointment date if there is one, otherwise shows "No Appointment Scheduled"
        appointmentDate = source.getAppointment().getDate();
        appointmentTime = source.getAppointment().getTime();
        appointmentVenue = source.getAppointment().getVenue();
        appointmentCount = source.getCount().toString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final Name modelName = getName();
        final Phone modelPhone = getPhone();
        final Email modelEmail = getEmail();
        final Address modelAddress = getAddress();
        final Remark modelRemark = getRemark();
        final Appointment modelAppointment = getAppointment();
        final AppointmentCount apptCount = getAppointmentCount();
        final Set<Tag> modelTags = new HashSet<>(personTags);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelTags,
                modelAppointment, apptCount);
    }

    private Name getName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        return modelName;
    }

    private Phone getPhone() throws IllegalValueException {
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);
        return modelPhone;
    }

    private Email getEmail() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);
        return modelEmail;
    }

    private Address getAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        return modelAddress;
    }

    private Remark getRemark() throws IllegalValueException {
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Remark.class.getSimpleName()));
        }
        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemark = new Remark(remark);
        return modelRemark;
    }

    private Appointment getAppointment() throws IllegalValueException {
        if (appointmentDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }

        if (!Appointment.isValidDateFormat(appointmentDate)) {
            throw new IllegalValueException(Appointment.INVALID_DATE_INPUT);
        }

        if (appointmentTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }

        if (!Appointment.isValidTimeFormat(appointmentTime)) {
            throw new IllegalValueException(Appointment.INVALID_TIME_INPUT);
        }

        if (appointmentVenue == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Appointment.class.getSimpleName()));
        }

        if (!Appointment.isValidVenueFormat(appointmentVenue)) {
            throw new IllegalValueException(Appointment.INVALID_VENUE_INPUT);
        }

        final Appointment modelAppointment = new Appointment(appointmentDate, appointmentTime,
                appointmentVenue);
        return modelAppointment;
    }

    private AppointmentCount getAppointmentCount() throws IllegalValueException {
        if (appointmentCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentCount.class.getSimpleName()));
        }

        if (!AppointmentCount.isValidCount(appointmentCount)) {
            throw new IllegalValueException(AppointmentCount.INVALID_COUNT_INPUT);
        }

        final AppointmentCount apptCount = new AppointmentCount(appointmentCount);
        return apptCount;
    }
}
