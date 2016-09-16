package bzh.abr.user.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String m) {
        super(m);
    }

}
