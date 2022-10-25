package cn.vorbote.curriculum.interceptors;

import cn.vorbote.core.time.DateTime;
import cn.vorbote.core.time.TimeSpan;
import cn.vorbote.curriculum.plains.User;
import cn.vorbote.curriculum.values.AppConstant;
import cn.vorbote.curriculum.values.HttpConstant;
import cn.vorbote.simplejwt.AccessKeyUtil;
import cn.vorbote.web.constants.WebStatus;
import cn.vorbote.web.model.ResponseResult;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.Assert;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UserInterceptor<br>
 * Created at 21/09/2022 13:35
 *
 * @author theod
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private final AccessKeyUtil accessKeyUtil;

    private final ObjectMapper objectMapper;

    public UserInterceptor(AccessKeyUtil accessKeyUtil,
                           ObjectMapper objectMapper) {
        this.accessKeyUtil = accessKeyUtil;
        this.objectMapper = objectMapper;
    }

    /**
     * 负责拦截未登录用户
     *
     * @param request  请求，由 Spring 负责传递
     * @param response 响应，由 Spring 负责传递
     * @param handler  处理器，由 Spring 负责传递
     */
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler)
            throws Exception {
        var authorization = request.getHeader("Authorization");
        try {
            var expiresAt = new DateTime(accessKeyUtil.info(authorization).getExpiresAt());
            var currentUser = accessKeyUtil.getBean(authorization, User.class);

            log.info("用户 {} [ {} ] 访问了路径 [ {} ]",
                    currentUser.getUsername(), currentUser.getId(), request.getRequestURI());

            var expireAfter = expiresAt.minus(DateTime.now());
            if (expireAfter.getTotalSeconds() < 5 * 60) {
                log.info("用户 {} [ {} ] 令牌过期还有 {} 秒，已自动更新。",
                        currentUser.getUsername(), currentUser.getId(), expireAfter.getTotalSeconds());
                var newToken = accessKeyUtil.renewWithBean(authorization, TimeSpan.builder().minutes(30).build(), User.class);
                response.setHeader(AppConstant.TOKEN_KEY, newToken);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            var resp = ResponseResult.error("您还没有登录，请登录后再试！")
                    .code(WebStatus.UNAUTHORIZED.getCode());
            response.setHeader(HttpConstant.HEADER_CONTENT_TYPE, HttpConstant.DEFAULT_CONTENT_TYPE);
            response.getWriter().println(objectMapper.writeValueAsString(resp));
            return false;
        }
        return true;
    }
}
