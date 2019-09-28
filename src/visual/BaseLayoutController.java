package visual;

import core.Engine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseLayoutController implements Initializable {

    @FXML
    private Label choiceBoxLabel;
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

    public BaseLayoutController() {
        /*this.cursors = FXCollections.observableArrayList("First Name", "Last Name", "Mobile Phone",
                "Home Phone", "Work Phone", "Age", "City", "Country");
        this.choiceBox = new ChoiceBox<String>();
        choiceBox.setItems(cursors);
        choiceBox.setValue("Name");
        //choiceBox.*/
    }


    public void removeAllContacts() {

    }

    public void buttonSavePersonClicked() {
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

    public void tabExitProgramClicked() {
        System.exit(0);
    }

    public void buttonShowContactsClicked() {
        System.out.println(this.choiceBox.getValue().toString());
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
