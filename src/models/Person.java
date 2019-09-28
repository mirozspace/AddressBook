package models;

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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" -> ").append(this.getFirstName()).append(" <-> ");
        sb.append(this.getLastName()).append(" <-> ");
        sb.append(this.getMobilePhone()).append(" <-> ");
        sb.append(this.getHomePhone()).append(" <-> ");
        sb.append(this.getWorkPhone()).append(" <-> ");
        sb.append(this.getAge()).append(" <-> ");
        sb.append(this.getCity()).append(" <-> ");
        sb.append(this.getCountry());
        String result = sb.toString();
        sb.delete(0, sb.length());
        return result;
    }
}
