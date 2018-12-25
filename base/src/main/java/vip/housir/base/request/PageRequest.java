package vip.housir.base.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import lombok.Data;
import vip.housir.base.constant.Constant;
import vip.housir.base.utils.JsonUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author housirvip
 */
@Data
public class PageRequest implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

    private String param;

    @JsonIgnore
    private Map<String, Object> map = Maps.newHashMap();

    public PageRequest addUid(Integer uid) {

        map.put(Constant.UID, uid);

        return this;
    }

    public PageRequest addParam() {

        Map<String, Object> paramMap = JsonUtils.convertToMap(param, String.class, Object.class);

        if (paramMap != null) {
            map.putAll(paramMap);
        }

        return this;
    }
}
