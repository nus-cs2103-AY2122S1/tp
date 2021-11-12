package seedu.teachbook.model.classobject.exceptions;

/**
 * Signals that the operation is unable to find a class having the specified {@code ClassName}.
 * More specifically, signals that the operation is unable to find a class
 * whose name is described by the specified {@link seedu.teachbook.model.classobject.ClassNameDescriptor}.
 */
public class NoClassWithNameException extends RuntimeException {}
