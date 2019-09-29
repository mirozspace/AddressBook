package visual;

import core.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import messages.CommonConstants;
import models.Person;
import validators.Validator;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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
    private ChoiceBox<String> choiceBox;
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

    private final static String APPEND_SYMBOL_SB = ":";

    public BaseLayoutController() {
    }


    public void removeAllContacts() throws IOException {
        Engine engine = new Engine();
        engine.removeAllPeopleInFile();
    }

    public void buttonSavePersonClicked() throws IOException {

        System.out.println();

        try {
            Validator.checkName(this.firstName.getText());
            Validator.checkName(this.lastName.getText());
            Validator.checkMobilePhoneNumber(this.mobilePhone.getText());
            Validator.checkHomePhoneNumber(this.homePhone.getText());
            Validator.checkWorkPhoneNumber(this.workPhone.getText());
            Validator.checkAge(Integer.parseInt(this.age.getText()));
            Validator.checkCityName(this.city.getText());
            Validator.checkCountryName(this.country.getText());

            this.textArea.setText("Successful record!");
        } catch (Exception e) {
            //this.textArea.setStyle("-fx-text-fill: red");
            this.textArea.setText(e.getMessage());
            return;
            //this.textArea.setStyle("-fx-text-fill: black");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.lastName.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.mobilePhone.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.homePhone.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.workPhone.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.age.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.city.getText()).append(APPEND_SYMBOL_SB);
        sb.append(this.country.getText());
        Engine engine = new Engine();
        String dataLinePerson = sb.toString();
        sb.delete(0, sb.length());
        engine.takeDataPersonFromFX(dataLinePerson);
    }

    public void buttonResetPersonClicked() {
        this.firstName.setText(null);
        this.lastName.setText(null);
        this.mobilePhone.setText(null);
        this.homePhone.setText(null);
        this.workPhone.setText(null);
        this.age.setText(null);
        this.city.setText(null);
        this.country.setText(null);
    }

    public void tabExitProgramClicked() {
        System.exit(0);
    }

    public void buttonSearchByFirstName() {
        this.textArea.setText(null);
        try {
            List<Person> people = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader(CommonConstants.FILE_PATH));
            StringBuilder sb = new StringBuilder();
            Person person;

            String line = bf.readLine();
            while (line != null) {
                String[] lineArr = line.split(CommonConstants.ARRAY_DELIMITER1);
                person = new Person(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4],
                        Integer.parseInt(lineArr[5]), lineArr[6], lineArr[7]);
                people.add(person);
                line = bf.readLine();
            }
            people = people.stream().filter(e -> e.getFirstName().equals(this.searchedFirstName.getText())).collect(Collectors.toList());
            for (Person person1 : people) {
                sb.append(person1.toString());
            }
            String result = sb.toString().trim();
            showAnyResultOtSetNothing(result);
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAnyResultOtSetNothing(String result) {
        if (result.equals("")) {
            this.textArea.setText(CommonConstants.NO_RESULTS);
        } else {
            this.textArea.setText(result);
        }
    }

    public void facebookLinkClicked() throws IOException, URISyntaxException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(CommonConstants.URL_FACEBOOK));
    }

    public void buttonShowContactsClicked() {
        String choiceForSort = this.choiceBox.getValue();
        sortingByParam(choiceForSort);
    }

    public void buttonClearTextAreaClicked() {
        this.textArea.setText("");
    }

    private void sortingByParam(String choiceForSort) {
        try {
            this.textArea.setText(null);
            List<Person> people = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader(CommonConstants.FILE_PATH));
            StringBuilder sb = new StringBuilder();
            Person person;

            String line = bf.readLine();
            while (line != null) {
                String[] lineArr = line.split(CommonConstants.ARRAY_DELIMITER1);
                person = new Person(lineArr[0], lineArr[1], lineArr[2], lineArr[3], lineArr[4],
                        Integer.parseInt(lineArr[5]), lineArr[6], lineArr[7]);
                people.add(person);
                line = bf.readLine();
            }
            if (choiceForSort.equalsIgnoreCase(CommonConstants.FIRST_NAME)) {
                people = people.stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase(CommonConstants.LAST_NAME)) {
                people = people.stream().sorted(Comparator.comparing(Person::getLastName)).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase(CommonConstants.CITY)) {
                people = people.stream().sorted(Comparator.comparing(Person::getCity)).collect(Collectors.toList());
            } else if (choiceForSort.equalsIgnoreCase(CommonConstants.COUNTRY)) {
                people = people.stream().sorted(Comparator.comparing(Person::getCountry)).collect(Collectors.toList());
            }

            for (Person person1 : people) {
                sb.append(person1.toString());
            }
            String result = sb.toString().trim();
            showAnyResultOtSetNothing(result);
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.choiceBox.getItems().add(CommonConstants.FIRST_NAME);
        this.choiceBox.getItems().add(CommonConstants.LAST_NAME);
        this.choiceBox.getItems().add(CommonConstants.CITY);
        this.choiceBox.getItems().add(CommonConstants.COUNTRY);
        this.choiceBox.setValue(CommonConstants.FIRST_NAME);

    }
}
