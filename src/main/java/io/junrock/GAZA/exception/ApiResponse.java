package io.junrock.GAZA.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiResponse<T> {
    private T data;
    private int status;
    private String message;

    public ApiResponse(HttpStatusCode status, T data,String message){
        this.status=status.getStatus();
        this.data=data;
        this.message=message;
    }

    public static <T>ApiResponse<T> success(HttpStatusCode statusCode,T data){
       return new ApiResponse<>(statusCode,data,null);
    }

}
