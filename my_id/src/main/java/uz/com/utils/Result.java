package uz.com.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: Result
 * @date: 23.01.2024
 * @author: Uralbaev Diyorbek
 */

@Getter
@Setter
public class Result<T> {
    public static int SUCCESS = 0;
    public static int NOT_FOUND = 404;

    @Getter
    public T data;
    public int code;
    public String description;

    private Result(int code, String description, T data) {
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, "success", data);
    }

    public static <T> Result<T> error(int code, String description) {
        return new Result<>(code, description, null);
    }


    public static <T> Result<T> error(int code, String description, T data) {
        return new Result<>(code, description, data);
    }


    public boolean hasData() {
        return this.data != null;
    }

    @Override
    public String toString() {
        if (code == 0) {
            if (data != null) {
                return String.format("data: %s", data.toString());
            } else {
                return "data: NULL";
            }
        } else {
            return String.format("error %d: %s", code, description);
        }
    }

    public boolean isSucceed() {
        return code == SUCCESS;
    }
}
