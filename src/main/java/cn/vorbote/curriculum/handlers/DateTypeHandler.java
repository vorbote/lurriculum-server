package cn.vorbote.curriculum.handlers;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.curriculum.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DateTimeTypeHandler<br>
 * Created at 2022/10/12 09:11
 *
 * @author vorbote
 */
@Slf4j
public final class DateTypeHandler implements TypeHandler<DateTime> {
    @Override
    public void setParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter.unix());
    }

    @Override
    public DateTime getResult(ResultSet rs, String columnName) throws SQLException {
        return new DateTime(rs.getLong(columnName));
    }

    @Override
    public DateTime getResult(ResultSet rs, int columnIndex) throws SQLException {
        return new DateTime(rs.getLong(columnIndex));
    }

    @Override
    public DateTime getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new DateTime(cs.getLong(columnIndex));
    }
}
