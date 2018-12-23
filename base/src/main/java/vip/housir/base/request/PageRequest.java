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

    private String filter;

    private String search;

    public Map<String, Object> toMap() {

        Map<String, Object> param = Maps.newHashMap();

        if (orderBy != null) {
            param.put(Constant.ORDER_BY, orderBy);
        }
        if (orderType != null) {
            param.put(Constant.ORDER_TYPE, orderType);
        }

        if (filter != null) {
            Map<String, Object> map = JsonUtils.convertToMap(filter, String.class, Object.class);
            if (map != null) {
                param.putAll(map);
            }
        }
        if (search != null) {
            Map<String, Object> map = JsonUtils.convertToMap(search, String.class, Object.class);
            if (map != null) {
                param.putAll(map);
            }
        }

        return param;
    }
}
