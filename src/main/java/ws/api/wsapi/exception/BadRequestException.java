package ws.api.wsapi.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String msg){
        super(msg);
    }
}
