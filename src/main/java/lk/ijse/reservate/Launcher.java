package lk.ijse.reservate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.security.cert.PolicyNode;

public class Launcher extends Application {

    @Override

    public void start(Stage stage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
//        stage.centerOnScreen();
//        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setScene(new Scene(root));
//        stage.show();


       // FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/login_form.fxml"));
       FXMLLoader fxmlLoader= new FXMLLoader(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.setFullScreen(true);
        stage.show();
    }
}
