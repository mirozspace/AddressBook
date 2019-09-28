package sample;

import core.Engine;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BaseLayoutController {

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


}
