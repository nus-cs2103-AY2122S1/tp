package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * A class to access HrManager data stored as a json file on the hard disk.
 */
public class JsonHrManagerStorage implements HrManagerStorage {


    private static final Logger logger = LogsCenter.getLogger(JsonHrManagerStorage.class);

    private Path candidatesFilePath;
    private Path positionsFilePath;
    private Path interviewsFilePath;

    /**
     * Constructs a {@code JsonHrManagerStorage} with the given paths.
     */
    public JsonHrManagerStorage(Path candidatesFilePath, Path positionsFilePath, Path interviewsFilePath) {
        this.candidatesFilePath = candidatesFilePath;
        this.positionsFilePath = positionsFilePath;
        this.interviewsFilePath = interviewsFilePath;
    }

    public Path getHrManagerCandidatesFilePath() {
        return candidatesFilePath;
    }

    public Path getHrManagerPositionsFilePath() {
        return positionsFilePath;
    }

    public Path getHrManagerInterviewsFilePath() {
        return interviewsFilePath;
    }

    @Override
    public Optional<ReadOnlyHrManager> readHrManager() throws DataConversionException {
        return readHrManager(candidatesFilePath, positionsFilePath, interviewsFilePath);
    }

    /**
     * Similar to {@link #readHrManager()}.
     *
     * @param candidatesFilePath location of the data. Cannot be null.
     * @param positionsFilePath location of the data. Cannot be null.
     * @param interviewsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHrManager> readHrManager(Path candidatesFilePath,
                                                     Path positionsFilePath,
                                                     Path interviewsFilePath) throws DataConversionException {
        requireNonNull(candidatesFilePath);
        requireNonNull(positionsFilePath);

        Optional<JsonSerializableHrManagerCandidates> candidates = JsonUtil.readJsonFile(
                candidatesFilePath, JsonSerializableHrManagerCandidates.class);

        Optional<JsonSerializableHrManagerPositions> positions = JsonUtil.readJsonFile(
                positionsFilePath, JsonSerializableHrManagerPositions.class);

        Optional<JsonSerializableHrManagerInterviews> interviews = JsonUtil.readJsonFile(
                interviewsFilePath, JsonSerializableHrManagerInterviews.class);

        //merge data from all files
        try {
            HrManager merge = new HrManager();
            if (candidates.isPresent()) {
                for (Person person : candidates.get().toModelType().getPersonList()) {
                    merge.addPerson(person);
                }
            }
            if (positions.isPresent()) {
                for (Position position : positions.get().toModelType().getPositionList()) {
                    merge.addPosition(position);
                }
            }

            //add candidate to interviews as well as add interview data
            if (interviews.isPresent()) {
                for (Interview interview : interviews.get().toModelType().getInterviewList()) {
                    Set<Integer> uniqueIds = interview.getCandidateIDs();
                    Set<Person> candidateSet = new HashSet<>();
                    for (Person person : merge.getPersonList()) {
                        for (Integer integer : uniqueIds) {
                            if (integer.equals(person.hashCode())) {
                                candidateSet.add(person);
                                person.addInterview(interview);
                            }
                        }
                    }
                    interview.setCandidates(candidateSet);
                    merge.addInterview(interview);
                }
            }

            if (candidates.isEmpty() && positions.isEmpty() && interviews.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(merge);
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found when merging data from " + candidatesFilePath + ", "
                    + positionsFilePath + " and " + interviewsFilePath + ":" + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHrManager(ReadOnlyHrManager hrManager) throws IOException {
        saveHrManager(hrManager, candidatesFilePath, positionsFilePath, interviewsFilePath);
    }

    /**
     * Similar to {@link #saveHrManager(ReadOnlyHrManager)}.
     *
     * @param hrManager cannot be null.
     * @param candidatesFilePath location of the data. Cannot be null.
     * @param positionsFilePath location of the data. Cannot be null.
     * @param interviewsFilePath location of the data. Cannot be null.
     */
    public void saveHrManager(ReadOnlyHrManager hrManager, Path candidatesFilePath,
                              Path positionsFilePath, Path interviewsFilePath) throws IOException {
        requireNonNull(hrManager);
        requireNonNull(candidatesFilePath);
        requireNonNull(positionsFilePath);
        requireNonNull(interviewsFilePath);

        //save candidates
        FileUtil.createIfMissing(candidatesFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManagerCandidates(hrManager), candidatesFilePath);

        //save positions
        FileUtil.createIfMissing(positionsFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManagerPositions(hrManager), positionsFilePath);

        //save interviews
        FileUtil.createIfMissing(interviewsFilePath);
        JsonUtil.saveJsonFile(new JsonSerializableHrManagerInterviews(hrManager), interviewsFilePath);
    }
}
