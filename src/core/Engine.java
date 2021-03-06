package core;

import messages.CommonConstants;
import messages.ErrorMessagesAB;
import models.Person;
import validators.Validator;
import visual.BaseLayoutController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    //private PersonRepository personRepository;

    public Engine() {
        //this.personRepository = new PersonRepository();
    }

    public void takeDataPersonFromFX(String personInformation) throws IOException {
        String[] personInformationArr = personInformation.split(CommonConstants.ARRAY_DELIMITER2);
        String firstName = personInformationArr[0];
        String lastName = personInformationArr[1];
        String mobilePhone = personInformationArr[2];
        String email = personInformationArr[3];
        String workPhone = personInformationArr[4];
        String age = personInformationArr[5];
        String city = personInformationArr[6];
        String country = personInformationArr[7];

        /*Person person = new Person(firstName, lastName, mobilePhone,
                email, workPhone, age, city, country);*/
        BaseLayoutController controller = new BaseLayoutController();
        Person person = new Person(firstName, mobilePhone);

        if (!lastName.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkName(lastName);
                person.setLastName(lastName);
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_NAME);
            }
        }

        if (!email.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkName(email);
                person.setEmail(email);
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_EMAIL);
            }
        }

        if (!workPhone.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkName(workPhone);
                person.setWorkPhone(workPhone);
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_WORK_NUMBER);
            }
        }

        if (!age.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkAge(Integer.parseInt(age));
                person.setAge(Integer.parseInt(age));
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_AGES);
            }
        }

        if (!city.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkCityName(city);
                person.setCity(city);
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_CITY_NAME);
            }
        }

        if (!country.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkCountryName(country);
                person.setCountry(country);
            } catch (Exception e) {
                controller.getTextArea().setText(ErrorMessagesAB.INVALID_COUNTRY_NAME);
            }
        }

        /*checkingValuesBeforeSetting(lastName, controller, person);
        checkingValuesBeforeSetting(email, controller, person);
        checkingValuesBeforeSetting(workPhone, controller, person);
        checkingValuesBeforeSetting(age + "", controller, person);
        checkingValuesBeforeSetting(city, controller, person);
        checkingValuesBeforeSetting(country, controller, person);*/

        //------------------------------------------

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(CommonConstants.FILE_PATH, true));
        bufferedWriter.write(person.makePersonLineForPerson());
        bufferedWriter.close();

    }


    public void removeAllPeopleInFile() throws IOException {
        FileWriter fw = new FileWriter(CommonConstants.FILE_PATH);
        PrintWriter pw = new PrintWriter(fw);
        pw.write("");
        pw.flush();
        pw.close();
    }

    public void removeByMobilePhone(String mobileNumber) throws IOException {
        List<Person> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(CommonConstants.FILE_PATH));
        BufferedWriter writer = new BufferedWriter(new FileWriter(CommonConstants.FILE_PATH, true));
        String line = reader.readLine();
        while (line != null) {
            String[] lineArr = line.split(" , ");
            list.add(new Person(lineArr[0],lineArr[1], lineArr[2], lineArr[3], lineArr[4],
                    Integer.parseInt(lineArr[5]), lineArr[6], lineArr[7]));
            line = reader.readLine();
        }
        Person person = list.stream().filter(e->e.getMobilePhone().equals(mobileNumber)).findFirst().get();
        list.remove(person);
        removeAllPeopleInFile();
        for (Person person1 : list) {
            writer.write(person1.makePersonLineForPerson());
        }
        reader.close();
        writer.close();
    }

    //PRIVATE METHODS
    /*private void checkingValuesBeforeSetting(String value, BaseLayoutController controller, Person person) {
        if (!value.equals(CommonConstants.EMPTY_STRING)) {
            try {
                Validator.checkName(value);
                // invoke method...
            } catch (Exception e) {
                controller.getTextArea().setText(e.getMessage());
            }
        }
    }

    private void invokeCurrentSetterPerson(String value, Person person) {
        //if (value.equals())
    }*/

}
