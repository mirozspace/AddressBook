package models;

import messages.CommonConstants;

@SuppressWarnings("CanBeFinal")
public class Person {
    private String firstName;
    private String lastName;
    private String mobilePhone;
    private String homePhone;
    private String workPhone;
    //private String email;
    private int age;
    private String city;
    private String country;

    public Person(String firstName, String lastName, String mobilePhone, String homePhone,
                  String workPhone, int age, String city, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
        this.age = age;
        this.city = city;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private String getMobilePhone() {
        return mobilePhone;
    }

    private String getHomePhone() {
        return homePhone;
    }

    private String getWorkPhone() {
        return workPhone;
    }

    private int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String makePersonLineForPerson() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.firstName).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.lastName).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.mobilePhone).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.homePhone).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.workPhone).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.age).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.city).append(CommonConstants.ARRAY_DELIMITER1);
        sb.append(this.country).append(System.lineSeparator());

        String result = sb.toString();
        sb.delete(0, sb.length());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(CommonConstants.FIRST_NAME).append(CommonConstants.ARRAY_DELIMITER3).append(this.getFirstName()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.LAST_NAME).append(CommonConstants.ARRAY_DELIMITER3).append(this.getLastName()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.MOBILE_PHONE).append(CommonConstants.ARRAY_DELIMITER3).append(this.getMobilePhone()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.HOME_PHONE).append(CommonConstants.ARRAY_DELIMITER3).append(this.getHomePhone()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.WORK_PHONE).append(CommonConstants.ARRAY_DELIMITER3).append(this.getWorkPhone()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.AGES).append(CommonConstants.ARRAY_DELIMITER3).append(this.getAge()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.CITY).append(CommonConstants.ARRAY_DELIMITER3).append(this.getCity()).append(CommonConstants.ARRAY_DELIMITER4);
        sb.append(CommonConstants.COUNTRY).append(CommonConstants.ARRAY_DELIMITER3).append(this.getCountry()).append(System.lineSeparator());
        //sb.append("---").append(System.lineSeparator());
        String result = sb.toString();
        sb.delete(0, sb.length());

        return result;
    }
}
