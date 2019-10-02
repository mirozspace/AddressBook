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
    private TextField fieldDeleteByMobilePhone;
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

    //SETTERS ===

    //GETTERS ===

    /**
     * get info from Text Area
     * @return
     */
    public TextArea getTextArea() {
        return textArea;
    }

    //BUTTONS ===

    /**
     * Save button for a one Person to our Data Base (txt file)
     * @throws IOException
     */
    public void buttonSavePersonClicked() throws IOException {

        try {
            Validator.checkName(this.firstName.getText());
            Validator.checkMobilePhoneNumber(this.mobilePhone.getText());

            this.textArea.setText("Successful record!");
        } catch (Exception e) {
            //this.textArea.setStyle("-fx-text-fill: red");
            this.textArea.setText(e.getMessage());
            return;
            //this.textArea.setStyle("-fx-text-fill: black");
        }

        String lastName1;
        if (this.lastName.getText().isEmpty()) {
            lastName1 = this.lastName.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            lastName1 = this.lastName.getText();
        }

        String homePhone1;
        if (this.homePhone.getText().isEmpty()) {
            homePhone1 = this.homePhone.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            homePhone1 = this.homePhone.getText();
        }

        String workPhone1;
        if (this.workPhone.getText().isEmpty()) {
            workPhone1 = this.workPhone.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            workPhone1 = this.workPhone.getText();
        }

        String age1;
        if (this.age.getText().isEmpty()) {
            age1 = this.age.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            age1 = this.age.getText();
        }

        String city1;
        if (this.city.getText().isEmpty()) {
            city1 = this.city.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            city1 = this.city.getText();
        }

        String country1;
        if (this.country.getText().isEmpty()) {
            country1 = this.country.getText() + CommonConstants.NEW_SYMBOL;
        } else {
            country1 = this.country.getText();
        }

        //========

        StringBuilder sb = new StringBuilder();
        sb.append(this.firstName.getText()).append(APPEND_SYMBOL_SB);
        sb.append(lastName1).append(APPEND_SYMBOL_SB);
        sb.append(this.mobilePhone.getText()).append(APPEND_SYMBOL_SB);
        sb.append(homePhone1).append(APPEND_SYMBOL_SB);
        sb.append(workPhone1).append(APPEND_SYMBOL_SB);
        sb.append(age1).append(APPEND_SYMBOL_SB);
        sb.append(city1).append(APPEND_SYMBOL_SB);
        sb.append(country1);

        Engine engine = new Engine();
        String dataLinePerson = sb.toString();
        sb.delete(0, sb.length());
        engine.takeDataPersonFromFX(dataLinePerson);
    }

    /**
     * Reset all fields for Person in our FX form
     */
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

    /**
     * Sear Result by First Name
     */
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
                person = getPerson(lineArr);
                people.add(person);
                line = bf.readLine();
            }
            people = people.stream().filter(e -> e.getFirstName().equalsIgnoreCase(this.searchedFirstName.getText())).collect(Collectors.toList());
            for (Person person1 : people) {
                sb.append(person1.toString());
            }
            String result = sb.toString().trim();
            showAnyResultOrSetNothing(result);
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crate one person from 1d array
     * @param lineArr
     * @return
     */
    private Person getPerson(String[] lineArr) {
        Person person;
        person = new Person(lineArr[0], lineArr[2]);
        person.setLastName(lineArr[1]);
        person.setHomePhone(lineArr[3]);
        person.setWorkPhone(lineArr[4]);
        person.setAge(Integer.parseInt(lineArr[5]));
        person.setCity(lineArr[6]);
        person.setCountry(lineArr[7]);
        return person;
    }

    /**
     * Show Any Result Or Set Nothing (empty)
     * @param result
     */
    private void showAnyResultOrSetNothing(String result) {
        if (result.equals("")) {
            this.textArea.setText(CommonConstants.NO_RESULTS);
        } else {
            this.textArea.setText(result);
        }
    }

    /**
     * Displays contacts sorted by criteria from FX form.
     */
    public void buttonShowContactsClicked() {
        String choiceForSort = this.choiceBox.getValue();
        try {
            this.textArea.setText(null);
            List<Person> people = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader(CommonConstants.FILE_PATH));
            StringBuilder sb = new StringBuilder();
            Person person;

            String line = bf.readLine();
            while (line != null) {
                String[] lineArr = line.split(CommonConstants.ARRAY_DELIMITER1);
                person = getPerson(lineArr);
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
            showAnyResultOrSetNothing(result);
            sb.delete(0, sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clear Text Area
     */
    public void buttonClearTextAreaClicked() {
        this.textArea.setText("");
    }

    /**
     * Remove all contacts from txt file
     * @throws IOException
     */
    public void buttonRemoveAllContacts() throws IOException {
        Engine engine = new Engine();
        engine.removeAllPeopleInFile();
    }

    /**
     *
     * @throws IOException
     */
    public void buttonRemoveByMobilePhone() throws IOException {
        Engine engine = new Engine();
        int a = 1;
        engine.removeByMobilePhone(fieldDeleteByMobilePhone.getText());

    }
    /**
     * Tab from top menu. Exit from program
     */
    public void tabExitProgramClicked() {
        System.exit(0);
    }

    /**
     * When we click on lin for facebook site
     * @throws IOException
     * @throws URISyntaxException
     */
    public void facebookLinkClicked() throws IOException, URISyntaxException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI(CommonConstants.URL_FACEBOOK));
    }

    //FROM INTERFACE
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.choiceBox.getItems().add(CommonConstants.FIRST_NAME);
        this.choiceBox.getItems().add(CommonConstants.LAST_NAME);
        this.choiceBox.getItems().add(CommonConstants.CITY);
        this.choiceBox.getItems().add(CommonConstants.COUNTRY);
        this.choiceBox.setValue(CommonConstants.FIRST_NAME);

    }
}
