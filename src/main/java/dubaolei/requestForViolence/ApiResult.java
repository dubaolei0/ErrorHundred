package dubaolei.requestForViolence;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName ApiResult.java
 * @Description TODO
 * @createTime 2022年06月22日 16:22:00
 */
public class ApiResult {
    private String code;

    public ApiResult(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
