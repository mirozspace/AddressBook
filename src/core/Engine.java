package core;

public class Engine {

    public void takeDataPersonFromFX(String personInformation){
        String [] personInformationArr = personInformation.split(":");
        String firstName = personInformationArr[0];
        String lastName = personInformationArr[1];
        String mobilePhone = personInformationArr[2];
        String homePhone = personInformationArr[3];
        String workPhone = personInformationArr[4];
        int age = Integer.parseInt(personInformationArr[5]);
        String city = personInformationArr[6];
        String country = personInformationArr[7];

        for (String s : personInformationArr) {
            System.out.println(s);
        }
    }

}
