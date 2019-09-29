package visual;

import core.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BaseLayoutController implements Initializable {

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

    public void buttonShowContactsClicked() {
        String choiceForSort = this.choiceBox.getValue().toString();
        showContacts();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.choiceBox.getItems().add("First Name");
        this.choiceBox.getItems().add("Last Name");
        this.choiceBox.getItems().add("City");
        this.choiceBox.getItems().add("Country");
        this.choiceBox.setValue("First Name");


        //showContacts();
    }

    private void showContacts() {
        try {
            List<String> strings = new ArrayList<>();
            BufferedReader bf = new BufferedReader(new FileReader("files\\people_information.txt"));
            StringBuilder sb = new StringBuilder();
            String line = bf.readLine();
            while (line!= null) {
                this.textArea.setText(line);
                line = bf.readLine();
                strings.add(line);
            }
            for (String string : strings) {
                sb.append(string).append(System.lineSeparator());
            }
            this.textArea.setText(sb.toString());
            sb.delete(0,sb.length());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
