package org.geektimes.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -408207283294903013L;

	private final String errorCode;
	private boolean logged;
	private boolean fatal;

	/**
	 * @param errorCode
	 *            Well defined error code for the error type. Used in logs and
	 *            for translation to user messages.
	 * @param message
	 *            Technical message. Used for debugging purpose, not intended
	 *            for end users.
	 */
	public SystemException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * @param errorCode
	 *            Well defined error code for the error type. Used in logs and
	 *            for translation to user messages.
	 * @param message
	 *            Technical message. Used for debugging purpose, not intended
	 *            for end users.
	 * @param cause
	 *            Original cause of the exception, use with caution since
	 *            clients must include the class of the cause also (e.g. a
	 *            vendor specific database exception should not be exposed to
	 *            clients).
	 */
	public SystemException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * This flag indicates that the error is of fatal charcter and needs special
	 * attention, such as an alert in surveilance montoring.
	 */
	public boolean isFatal() {
		return fatal;
	}

	/**
	 * @see #isFatal()
	 */
	public void setFatal(boolean fatal) {
		this.fatal = fatal;
	}

	/**
	 * This flag indicates that the exception has been logged. Used to avoid
	 * duplicate logging of the same error.
	 */
	public boolean isLogged() {
		return logged;
	}

	/**
	 * @see #isLogged()
	 */
	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	/**
	 * Well defined error code for the error type. Used in logs and for
	 * translation to user messages.
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Returns a string representation of this exception instance, on the form:
	 * <code>ClassName[errorCode]:Message</code>
	 *
	 * @return the string representation
	 */
	public String toString() {
		return getClass().getName() + "[" + getErrorCode() + "]:" + getMessage();
	}

	/**
	 * Looks for SystemException in the cause chain.
	 * @return Found SystemException in the cause chain, or null if none found
	 */
	public static SystemException unwrapSystemException(Throwable throwable) {
		if (throwable == null) {
			return null;
		} else if (throwable instanceof SystemException) {
			return (SystemException) throwable;
		} else if (throwable.getCause() != null) {
			// recursive call to unwrap the cause
			return unwrapSystemException(throwable.getCause());
		} else {
			// didn't find any SystemException in the cause stack
			return null;
		}
	}

}
