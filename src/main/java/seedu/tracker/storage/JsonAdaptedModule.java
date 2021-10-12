package seedu.tracker.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Module's %s field is missing!";

    private final String code;
    private final String title;
    private final String description;
    private final int mc;
    private final int academicYear;
    private final int semester;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("code") String code, @JsonProperty("title") String title,
                             @JsonProperty("description") String description, @JsonProperty("mc") int mc,
                             @JsonProperty("academicYear") int academicYear, @JsonProperty("semester") int semester,
                             @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.mc = mc;
        this.academicYear = academicYear;
        this.semester = semester;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Module} into this class for Jackson use.
     */
    public JsonAdaptedModule(Module source) {
        code = source.getCode().value;
        title = source.getTitle().value;
        description = source.getDescription().value;
        mc = source.getMc().value;

        if (source.hasAcademicCalendar()) {
            academicYear = source.getAcademicCalendar().getAcademicYear().value;
            semester = source.getAcademicCalendar().getSemester().value;
        } else {
            academicYear = 0;
            semester = 0;
        }

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {
        final List<Tag> moduleTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            moduleTags.add(tag.toModelType());
        }

        if (code == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName()));
        }
        if (!Code.isValidCode(code)) {
            throw new IllegalValueException(Code.MESSAGE_CONSTRAINTS);
        }
        final Code modelCode = new Code(code);

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        // if (mc == null) {
        // throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
        // Address.class.getSimpleName()));
        // }

        if (!Mc.isValidMc(mc)) {
            throw new IllegalValueException(Mc.MESSAGE_CONSTRAINTS);
        }
        final Mc modelMc = new Mc(mc);

        final Set<Tag> modelTags = new HashSet<>(moduleTags);

        if (academicYear == 0 || semester == 0) {
            return new Module(modelCode, modelTitle, modelDescription, modelMc, modelTags);
        } else {

            if (!AcademicYear.isValidAcademicYear(academicYear)) {
                throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
            }
            final AcademicYear modelAcademicYear = new AcademicYear(academicYear);

            if (!Semester.isValidSemester(semester)) {
                throw new IllegalValueException(Semester.MESSAGE_CONSTRAINTS);
            }
            final Semester modelSemester = new Semester(semester);

            final AcademicCalendar modelAcademicCalendar = new AcademicCalendar(modelAcademicYear, modelSemester);

            return new Module(modelCode, modelTitle, modelDescription, modelMc, modelTags, modelAcademicCalendar);
        }
    }

}
