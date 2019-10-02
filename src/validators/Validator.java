package validators;

import messages.ErrorMessagesAB;

public class Validator {

    public static void checkName(String name) {
        if (name == null || name.length() < 2 || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessagesAB.INVALID_NAME);
        }
    }

    public static void checkMobilePhoneNumber(String mobileNumber) {
        if (mobileNumber.length() > 20 || mobileNumber.length() < 10) {
            throw new IllegalArgumentException(ErrorMessagesAB.INVALID_MOBILE_NUMBER);
        }
    }

    public static void checkEmailNumber(String mobileNumber) {

    }

    public static void checkWorkPhoneNumber(String mobileNumber) {

    }

    public static void checkAddress(String address) {

    }

    public static void checkAge(int age) {
        if (age < 7 || age > 120) {
            throw new IllegalArgumentException(ErrorMessagesAB.INVALID_AGES);
        }
    }

    /*public static void checkEmail(String homeNumber) {

    }*/

    public static void checkCityName(String city) {

    }

    public static void checkCountryName(String country) {

    }

    public static void checkCommonString(String commonString) {

    }

}
