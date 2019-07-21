package pl.jbaranska.alpha.exeptions;

public class EmailAlreadyExistsException extends Exception {
    public EmailAlreadyExistsException(String messageException)
    {
        super(messageException);
    }
}
