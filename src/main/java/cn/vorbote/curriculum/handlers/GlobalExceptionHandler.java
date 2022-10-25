package cn.vorbote.curriculum.handlers;

import cn.vorbote.core.exceptions.NotImplementedException;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.exceptions.BizException;
import cn.vorbote.web.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 * 全局统一异常处理中心
 * Created at 2022/6/6 17:46
 *
 * @author vorbote
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public final class GlobalExceptionHandler {

    @ExceptionHandler(NotImplementedException.class)
    public ResponseResult<?> handleNotImplementedException(NotImplementedException exception) {
        return ResponseResult.error(exception.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public ResponseResult<?> handleBizException(BizException exception) {
        return exception.respond();
    }

    /**
     * 全局异常处理
     *
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("捕捉到异常，异常信息：{}", e.getMessage());
        var resp = ResponseResult.error(e.getMessage());
        resp.code(WebStatus.BAD_REQUEST.getCode());
        return resp;
    }

    /**
     * 全局异常处理
     *
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseResult<Object> handleNullPointerException(NullPointerException e) {
        log.error("捕捉到异常，异常信息：{}", e.getMessage());
        var resp = ResponseResult.error("缺少必要数据：" + e.getMessage());
        resp.code(WebStatus.BAD_REQUEST.getCode());
        return resp;
    }

    /**
     * 全局处理SQL语法错误异常
     *
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseResult<Object> handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        log.error("SQL语法错误，异常信息：{}", e.getMessage());
        var resp = ResponseResult.error("SQL语句错误！");
        resp.code(WebStatus.REQUEST_TIMEOUT.getCode());
        return resp;
    }

    /**
     * 全局处理SQL异常
     *
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(SQLException.class)
    public ResponseResult<Object> handleSQLException(SQLException e) {
        log.error("SQL语法错误，异常信息：{}", e.getMessage());
        var resp = ResponseResult.error("SQL语句错误，提示信息：" + e.getMessage());
        resp.code(WebStatus.REQUEST_TIMEOUT.getCode());
        return resp;
    }

}

