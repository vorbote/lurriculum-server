package cn.vorbote.curriculum.requests;

import cn.vorbote.curriculum.transfers.UserDto;

/**
 * RegisterRequest<br>
 * Created at 22/10/2022 23:26
 *
 * @author vorbote
 */
public record RegisterRequest(
        UserDto user,
        String requestCode,
        String validationCode
) {
}
