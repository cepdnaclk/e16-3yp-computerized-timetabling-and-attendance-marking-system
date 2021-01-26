package Group10.example.API.Exception;

import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Arrays;

public class ApiError {

    //HttpStatus and Error messages
    private HttpStatus status;
    private List<String> errors;

    public ApiError(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String error) {
        super();
        this.status = status;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }




}


