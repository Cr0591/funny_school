package edu.gpnu.util;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UUIDGenerator {
    /**
     * 使用ThreadLocalRandom获取UUID获取更优的效果 去掉"-"
     */
    public static String get32UUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString().replace("-", "");
    }
}
