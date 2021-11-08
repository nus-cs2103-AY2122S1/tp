package manageme.model.link.exceptions;

public class DesktopNotSupportException extends RuntimeException {
    public DesktopNotSupportException() {
        super("JavaDeskTop is not supported. Please make sure java Desktop runnable on your desktop to open the file.");
    }
}
