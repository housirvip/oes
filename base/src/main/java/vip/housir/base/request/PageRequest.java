package vip.housir.base.request;

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

    private String orderBy;

    private String orderType;

    private String param;

    public Map<String, Object> toMap() {

        Map<String, Object> map = Maps.newHashMap();

        if (orderBy != null) {
            map.put(Constant.ORDER_BY, orderBy);
        }
        if (orderType != null) {
            map.put(Constant.ORDER_TYPE, orderType);
        }

        if (param != null) {
            Map<String, Object> objectMap = JsonUtils.convertToMap(param, String.class, Object.class);
            if (objectMap != null) {
                map.putAll(objectMap);
            }
        }

        return map;
    }
}
