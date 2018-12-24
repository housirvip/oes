package vip.housir.base.request;

import lombok.Data;
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

    public Map<String, Object> paramToMap() {

        return JsonUtils.convertToMap(param, String.class, Object.class);
    }
}
