package vip.housir.support.service;

/**
 * @author housirvip
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param phone String
     * @return Boolean
     */
    Boolean send(String phone);

    /**
     * 验证短信
     *
     * @param code  String
     * @param phone String
     * @return Boolean
     */
    Boolean verify(String code, String phone);
}
