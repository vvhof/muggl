package de.wwu.muggl.vm.classfile;

import de.wwu.muggl.configuration.MugglException;

/**
 * Exception that is thrown if write access to a ClassFile is attempted but write access
 * is denied.
 *
 * @author Tim Majchrzak
 * @version 1.0.0, 2010-02-15
 */
public class ClassFileWriteAccessViolationException extends MugglException {

	/**
	 * Constructs a new exception with null as its detail message.
	 *
	 * @see Exception#Exception()
	 */
	public ClassFileWriteAccessViolationException() {
		super();
	}

	/**
	 * Constructs a new exception with the specified detail message.
	 *
	 * @param arg0 the detail message.
	 * @see Exception#Exception(String)
	 */
	public ClassFileWriteAccessViolationException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 *
	 * @param arg0 the detail message.
	 * @param arg1 the cause.
	 * @see Exception#Exception(String, Throwable)
	 */
	public ClassFileWriteAccessViolationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of (cause==null ?
	 * null : cause.toString()).
	 *
	 * @param arg0 the cause.
	 * @see Exception#Exception(Throwable)
	 */
	public ClassFileWriteAccessViolationException(Throwable arg0) {
		super(arg0);
	}
}
