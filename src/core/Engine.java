package core;

import models.Person;
import repositories.PersonRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Engine {

    private PersonRepository personRepository;

    public Engine() throws IOException {
        this.personRepository = new PersonRepository();
    }

    public void takeDataPersonFromFX(String personInformation) throws IOException {
        String[] personInformationArr = personInformation.split(":");
        String firstName = personInformationArr[0];
        String lastName = personInformationArr[1];
        String mobilePhone = personInformationArr[2];
        String homePhone = personInformationArr[3];
        String workPhone = personInformationArr[4];
        int age = Integer.parseInt(personInformationArr[5]);
        String city = personInformationArr[6];
        String country = personInformationArr[7];

        Person person = new Person(firstName, lastName, mobilePhone,
                homePhone, workPhone, age, city, country);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("files\\people_information.txt", true));
        //this.personRepository.getPersons().add(person);
        bufferedWriter.write(person.toString());
        bufferedWriter.close();

        /*for (String s : personInformationArr) {
            System.out.println(s);
        }*/

    }

    public void removeAllPeopleInFile() throws IOException {
        FileWriter fw = new FileWriter("files\\people_information.txt");
        PrintWriter pw = new PrintWriter(fw);
        pw.write("");
        pw.flush();
        pw.close();
    }

}
