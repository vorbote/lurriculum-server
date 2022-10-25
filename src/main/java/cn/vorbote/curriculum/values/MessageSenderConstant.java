package cn.vorbote.curriculum.values;

/**
 * MessageSenderConstant<br>
 * Created at 2022/9/26 11:21
 *
 * @author vorbote
 */
public final class MessageSenderConstant {

    private MessageSenderConstant() {
    }

    /**
     * 短信签名
     */
    public static final String SIGN = "心若向阳无畏悲伤";

    /**
     * 注册账号模版
     * <pre>您正在注册账号并绑定手机号，您的验证码是{1}，识别码是{2}。请核对识别码后再输入验证码，有效期30分钟。</pre>
     */
    public static final String TEMPLATE_REGISTER_ZH_HANS = "1581925";

    /**
     * 注册账号模版
     * <pre>您正在修改账号密码，您的验证码是{1}，识别码是{2}。请核对识别码后再输入验证码，有效期30分钟。</pre>
     */
    public static final String TEMPLATE_CHANGE_PASSWORD_ZH_HANS = "1581926";

    /**
     * 注册账号模版
     * <pre>您正在修改绑定的手机号，您的验证码是{1}，识别码是{2}。请核对识别码后再输入验证码，有效期30分钟。</pre>
     */
    public static final String TEMPLATE_CHANGE_PHONE_ZH_HANS = "1581927";

}
