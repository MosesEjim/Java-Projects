
package soundsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SoundSystem extends Application {
    
    public static Stage window;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        
        Scene scene = new Scene(root);
        
        window.setScene(scene);
        window.show();
    }

    
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
