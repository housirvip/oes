package vip.housir.support.service;

/**
 * @author housirvip
 */
public interface QiniuService {

    /**
     * 生成七牛云存储上传凭证
     *
     * @param name String
     * @return String
     */
    String getToken(String name);
}
