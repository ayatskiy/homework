package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.RandomString;

import java.util.Locale;
import java.util.Random;

public class GenerationTools {

private static final Logger logger = LogManager.getLogger(GenerationTools.class);

    public static final String SET_OF_VALID_NUMBERS = "3489";

    private GenerationTools() {
        throw new IllegalStateException("Utility class");
    }

    public static String getRandomPhone() {
        var createdPhone = getRandomStringFromSet(1, RandomString.DIGITS) +
                getRandomStringFromSet(1, SET_OF_VALID_NUMBERS) +
                getRandomStringFromSet(9, RandomString.DIGITS);
        logger.info("Сгенерирован номер телефона: {}", createdPhone);
        return createdPhone;
    }

    public static String getRandomEmail() {
        var createdEmail = (getRandomString(8) + "@" + getRandomString(5) + ".com")
                .toLowerCase(Locale.ROOT);
        logger.info("Сгенерирован email: {}", createdEmail);
        return createdEmail;
    }

    public static String getRandomName() {
        var createdName = getRandomStringFromSet(10, RandomString.UPPER_CASE);
        String nameCapitalized = createdName.substring(0, 1).toUpperCase()
                + createdName.substring(1).toLowerCase();
        logger.info("Сгенерировано имя/фамилия на английском: {}", nameCapitalized);
        return nameCapitalized;
    }

    private static String getRandomStringFromSet(int length, String random) {
        return new RandomString(length, new Random(), random).nextString();
    }

    private static String getRandomString(int length) {
        return new RandomString(length, new Random(), RandomString.ALPHANUM).nextString();
    }
}
