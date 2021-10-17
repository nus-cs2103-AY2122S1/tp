package seedu.academydirectory.versioncontrol;

public class OptionalVersion<T extends Version> implements Version {
    private final T versionManager;

    private OptionalVersion(T versionManager) {
        this.versionManager = versionManager;
    }

    public static <T extends Version> OptionalVersion<Version> ofNullable(T versionManager) {
        return new OptionalVersion<Version>(versionManager);
    }

    @Override
    public boolean commit(String message) {
        if (versionManager == null) {
            return true;
        } else {
            return versionManager.commit(message);
        }
    }
}
