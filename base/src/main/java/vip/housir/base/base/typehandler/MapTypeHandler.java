package vip.housir.base.base.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.TypeHandler;
import org.jetbrains.annotations.Contract;
import vip.housir.base.utils.JsonUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author housirvip
 */
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class MapTypeHandler implements TypeHandler<Object> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {

        ps.setString(i, JsonUtils.convertToString(parameter));
    }

    @Override
    public Map getResult(ResultSet rs, String columnName) throws SQLException {

        return jsonToMap(rs.getString(columnName));
    }

    @Override
    public Map getResult(ResultSet rs, int columnIndex) throws SQLException {

        return jsonToMap(rs.getString(columnIndex));
    }

    @Override
    public Map getResult(CallableStatement cs, int columnIndex) throws SQLException {

        return jsonToMap(cs.getString(columnIndex));
    }

    @Contract(value = "null -> null", pure = true)
    private Map jsonToMap(String json) throws RuntimeException {

        return JsonUtils.convertToMap(json, Object.class, Object.class);
    }
}


