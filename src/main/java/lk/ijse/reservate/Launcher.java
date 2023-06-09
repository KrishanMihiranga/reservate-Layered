package lk.ijse.reservate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Launcher extends Application {

    @Override

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/views/login_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.setFullScreen(true);
        stage.show();
    }
}
