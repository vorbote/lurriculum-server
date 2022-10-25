package cn.vorbote.curriculum.controllers;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.web.constants.WebStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义错误信息处理器<br>
 * Created at 2022/9/21 08:57
 *
 * @author vorbote
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomErrorController extends BasicErrorController {

    @Value("${server.error.path:${error.path:/error}}")
    private String path;

    public CustomErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        var status = this.getStatus(request);

        return new ResponseEntity<>(new HashMap<>() {{
            put("code", status.value());
            put("message", status.getReasonPhrase());
            put("data", "");
            put("timestamp", DateTime.now().unix());
        }}, HttpStatus.OK);
    }
}
