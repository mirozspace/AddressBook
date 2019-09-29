package visual;

import core.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import models.Person;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class BaseLayoutController implements Initializable {

    @FXML
    private TextField searchedFirstName;
    @FXML
    private TextArea textArea;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField mobilePhone;
    @FXML
    private TextField homePhone;
    @FXML
    private TextField workPhone;
    @FXML
    private TextField age;
    @FXML
    private TextField city;
    @FXML
    private TextField country;

    public BaseLayoutController() throws IOException {
    }


    public void removeAllContacts() throws IOException {
        Engine engine = new Engine();
        engine.removeAllPeopleInFile();
    }

    public void buttonSavePersonClicked() throws IOException {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName.getText()).append(":");
        sb.append(this.lastName.getText()).append(":");
        sb.append(this.mobilePhone.getText()).append(":");
        sb.append(this.homePhone.getText()).append(":");
        sb.append(this.workPhone.getText()).append(":");
        sb.append(this.age.getText()).append(":");
        sb.append(this.city.getText()).append(":");
        sb.append(this.country.getText());
        Engine engine = new Engine();
        String dataLinePerson = sb.toString();
        sb.delete(0, sb.length());
        engine.takeDataPersonFromFX(dataLinePerson);
    }

    public void buttonResetPersonClicked() {
        this.firstName.setText("");
        this.lastName.setText("");
        this.mobilePhone.setText("");
        this.homePhone.setText("");
        this.workPhone.setText("");
        this.age.setText("");
        this.city.setText("");
        this.country.setText("");
    }

    public void tabExitProgramClicked() throws IOException {
        Engine engine = new Engine();
        //engine.removeAllPeopleInFile();
        System.exit(0);
    }

    public void buttonSearchByFirstName(){
        this.textArea.setText("");
        try {
            List<Person> people = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader("files\\people_information.txt"));
            StringBuilder sb = new StringBuilder();
            Person person = null;

            String line = bf.readLine();
            while (line != null) {
                String[] lineArr = line.split(" , ");
                person = new Person(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4],
                        Integer.parseInt(lineArr[5]), lineArr[6], lineArr[7]);
                people.add(person);
                line = bf.readLine();
            }

            people = people.stream().filter(e-> e.getFirstName().equals(this.searchedFirstName.getText())).collect(Collectors.toList());

            for (Person person1 : people) {
                sb.append(person1.toString());
            }
            this.textArea.setText(sb.toString().trim());
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonShowContactsClicked() {
        String choiceForSort = this.choiceBox.getValue().toString();
        sortingByParam(choiceForSort);
    }

    private void sortingByParam(String choiceForSort) {
        try {
            this.textArea.setText("");
            List<Person> people = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader("files\\people_information.txt"));
            StringBuilder sb = new StringBuilder();
            Person person = null;

            String line = bf.readLine();
            while (line != null) {
                String[] lineArr = line.split(" , ");
                person = new Person(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4],
                        Integer.parseInt(lineArr[5]), lineArr[6], lineArr[7]);
                people.add(person);
                line = bf.readLine();
            }
            if (choiceForSort.equalsIgnoreCase("First Name")) {
                people = people.stream().sorted((p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName())).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase("Last Name")) {
                people = people.stream().sorted(Comparator.comparing(Person::getLastName)).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase("City")) {
                people = people.stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase("Country")) {
                people = people.stream().sorted(Comparator.comparing(Person::getCountry)).collect(Collectors.toList());
            }

            for (Person person1 : people) {
                sb.append(person1.toString());
            }
            this.textArea.setText(sb.toString().trim());
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.choiceBox.getItems().add("First Name");
        this.choiceBox.getItems().add("Last Name");
        this.choiceBox.getItems().add("City");
        this.choiceBox.getItems().add("Country");
        this.choiceBox.setValue("First Name");

    }
}
