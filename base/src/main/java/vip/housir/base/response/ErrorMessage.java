package vip.housir.base.response;

/**
 * @author housirvip
 */
public class ErrorMessage {

    public static final String UNAUTHORIZED = "未认证";
    public static final String NULL_POINTER_EXCEPTION = "NullPointerException";

    public static final String ACCOUNT_NOT_FOUND = "账户未找到";
    public static final String ACCOUNT_DISABLED = "账户已停用";
    public static final String ACCOUNT_PASSWORD_ERROR = "账户密码错误";

    public static final String USERNAME_EXIST = "用户名已存在";
    public static final String EMAIL_EXIST = "邮箱已存在";
    public static final String PHONE_EXIST = "手机号码已存在";

    public static final String USER_NOT_FOUND = "用户未找到";
    public static final String USER_LEVEL_DOWN_DENY = "不支持降级";

    public static final String PAPER_NOT_FOUND = "试卷未找到";
    public static final String PAPER_LEVEL_LIMIT = "试卷没有权限";

    public static final String EXAM_NOT_FOUND = "考试记录未找到";
}
