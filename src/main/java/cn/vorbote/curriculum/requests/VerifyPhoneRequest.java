package cn.vorbote.curriculum.requests;

/**
 * VerifyPhoneRequest<br>
 * Created at 22/10/2022 12:12
 *
 * @author vorbote
 */
public record VerifyPhoneRequest(
        Integer region,
        String phone,
        String requestCode) {
}
