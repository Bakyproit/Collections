package exception;

public class MyValidateException extends Exception {

	String errorMessage ; 
	
	public MyValidateException(String message) {
		super();
		this.errorMessage = message ;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
