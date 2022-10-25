package cn.vorbote.curriculum.generators;

import cn.vorbote.web.utils.BizAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * RandomCodeGenerator<br>
 * Created at 21/10/2022 10:53
 *
 * @author vorbote
 */
@Component
public final class RandomCodeGenerator {

    private final Random randomizer;

    @Autowired
    public RandomCodeGenerator(Random randomizer) {
        this.randomizer = randomizer;
    }

    public String randomDigitCode(int length) {
        BizAssert.isTrue(length > 0, "长度不可以小于0！");

        var builder = new StringBuilder();
        for (var i = 0; i < length; ++i) {
            builder.append(randomizer.nextInt(10));
        }
        return builder.toString();
    }
}
