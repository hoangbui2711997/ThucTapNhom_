package view;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PleaseProvideControllerClassName {

    @FXML
    private JFXButton button;

    @FXML
    void action(MouseEvent event) {
        System.out.println("Helo world");
    }
}