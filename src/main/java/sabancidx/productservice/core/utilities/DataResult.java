package sabancidx.productservice.core.utilities;

public class DataResult<T> extends Result {

    public T data;

    public DataResult(boolean success, T data) {
        super(success);
        this.data = data;
    }
    public DataResult(boolean success, String message,T data) {
        super(success,message);
        this.data = data;
    }
    public DataResult(T data) {

        this.data = data;
    }

}