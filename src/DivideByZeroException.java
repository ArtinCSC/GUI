
public class DivideByZeroException extends Exception {

	public DivideByZeroException() {
		super("Dividing by Zero!"); // Divide by zero catch class
	}

	public DivideByZeroException(String message) {
		super(message);
	}

}
