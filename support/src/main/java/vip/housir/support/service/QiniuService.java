package vip.housir.support.service;

/**
 * @author housirvip
 */
public interface QiniuService {

    /**
     * 生成七牛云存储简单上传凭证
     *
     * @return String
     */
    String simpleToken();
}
