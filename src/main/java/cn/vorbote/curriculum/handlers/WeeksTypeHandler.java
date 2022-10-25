package cn.vorbote.curriculum.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * WeeksTypeHandler<br>
 * Created at 2022/9/23 09:33
 *
 * @author vorbote
 */
public final class WeeksTypeHandler implements TypeHandler<List<Integer>> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
        var builder = new StringBuilder();
        for (var n : integers) {
            builder.append(n).append(",");
        }
        preparedStatement.setString(i, builder.substring(0, builder.length() - 1));
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, String s) throws SQLException {
        var arr = resultSet.getString(s).split(",");

        var list = new ArrayList<Integer>();
        for (var item : arr) {
            list.add(Integer.valueOf(item));
        }

        return list;
    }

    @Override
    public List<Integer> getResult(ResultSet resultSet, int i) throws SQLException {
        var arr = resultSet.getString(i).split(",");

        var list = new ArrayList<Integer>();
        for (var item : arr) {
            list.add(Integer.valueOf(item));
        }

        return list;
    }

    @Override
    public List<Integer> getResult(CallableStatement callableStatement, int i) throws SQLException {
        var arr = callableStatement.getString(i).split(",");

        var list = new ArrayList<Integer>();
        for (var item : arr) {
            list.add(Integer.valueOf(item));
        }

        return list;
    }
}
