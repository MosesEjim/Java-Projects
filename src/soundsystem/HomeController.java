/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soundsystem;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author CS LAB 4
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton hostButton;
    @FXML
    private JFXButton clientButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void createHost(ActionEvent e) throws IOException {
        Server server = new Server();
        server.start();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("host.fxml"));
        Scene scene = new Scene(pane);
        SoundSystem.window.setScene(scene);
    }

    public void joinHost(ActionEvent e) throws IOException {
        Client client = new Client();
        client.start();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("host.fxml"));
        Scene scene = new Scene(pane);
        SoundSystem.window.setScene(scene);
    }
}
