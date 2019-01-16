package vip.housir.user.service.impl;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.user.service.QiniuService;

/**
 * @author housirvip
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    @Value("${qiniu.access-key}")
    private String accessKey;

    @Value("${qiniu.secret-key}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

    @Override
    public String getToken(String name) {

        return Auth.create(accessKey, secretKey).uploadToken(bucket, name);
    }
}
