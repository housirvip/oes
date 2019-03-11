package vip.housir.base.constant;

/**
 * @author housirvip
 */
public class ErrorMessage {

    public static final String UNAUTHORIZED = "未认证";
    public static final String CAPTCHA_ERROR = "验证码错误";
    public static final String SMS_ERROR = "短信验证码错误";
    public static final String NULL_POINTER_EXCEPTION = "系统空指针异常";
    public static final String SERVICE_EXCEPTION = "系统内部服务异常";

    public static final String ACCOUNT_NOT_FOUND = "账户未找到";
    public static final String ACCOUNT_DISABLED = "账户已停用";
    public static final String ACCOUNT_PASSWORD_ERROR = "账户密码错误";

    public static final String USERNAME_EXIST = "用户名已存在";
    public static final String EMAIL_EXIST = "邮箱已存在";
    public static final String PHONE_EXIST = "手机号码已存在";

    public static final String USER_NOT_FOUND = "用户未找到";
    public static final String USER_LEVEL_DOWN_DENY = "用户不支持降级";
    public static final String USER_LEVEL_LIMIT = "用户等级限制";
    public static final String USER_GROUP_LIMIT = "用户组限制";
    public static final String USER_WALLET_LIMIT = "用户余额不足";

    public static final String PAPER_NOT_FOUND = "试卷未找到";
    public static final String PAPER_LEVEL_LIMIT = "试卷没有权限";
    public static final String PAPER_TIMES_LIMIT = "试卷次数超出";

    public static final String EXAM_NOT_FOUND = "考试记录未找到";
    public static final String EXAM_PERMISSION_DENY = "此考试记录无权查看";

    public static final String PRODUCT_NOT_FOUND = "商品未找到";

    public static final String ORDER_NOT_FOUND = "订单未找到";
    public static final String ORDER_PERMISSION_DENY = "此订单无权查看";
    public static final String ORDER_NOT_PENDING = "此订单非待支付状态";

    public static final String RECHARGE_NOT_FOUND = "充值未找到";
    public static final String RECHARGE_PERMISSION_DENY = "此充值无权查看";
    public static final String RECHARGE_PENDING = "此充值待支付";

    public static final String TICKET_NOT_FOUND = "服务单未找到";
    public static final String TICKET_CONTENT_NULL = "服务单内容为空";
    public static final String TICKET_PERMISSION_DENY = "此服务单无权查看";
}
