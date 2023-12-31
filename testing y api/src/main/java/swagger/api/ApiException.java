package swagger.api;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")
public class ApiException extends Exception {
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
