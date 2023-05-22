package samoilik.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samoilik.model.Person;
import samoilik.util.DateUtil;


public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        phoneNumberField.setText(person.getPhoneNumber());
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPhoneNumber(phoneNumberField.getText());
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "Invalid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "Invalid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "Invalid street!\n";
        }

        if (phoneNumberField.getText() == null || phoneNumberField.getText().length() == 0) {
            errorMessage += "Invalid phone number!\n";
        }


        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "Invalid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "Invalid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "Invalid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() != 0) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
        return true;

    }
}