package warehouse;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = createGridPane();

        Scene scene = new Scene(gridPane, 300, 257);
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Text title = new Text("Login to application");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(title, 0, 0, 2, 1);

        Label login = new Label("Login");
        gridPane.add(login, 0, 1);

        TextField loginTextField = new TextField();
        gridPane.add(loginTextField, 1, 1);

        Label password = new Label("Password:");
        gridPane.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button signInButton = new Button("Sign in");
        HBox hBoxButton = new HBox(10);
        hBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxButton.getChildren().add(signInButton);
        gridPane.add(hBoxButton, 1, 4);

        final Text text = new Text();
        gridPane.add(text, 1, 6);

        signInButton.setOnAction(e -> {
            boolean userInDatabase = Database.isUserInDatabase(loginTextField.getText(), passwordField.getText());
            if (userInDatabase) {
                login();
            } else {
                text.setText("Login failed");
                text.setFill(Color.FIREBRICK);
            }
        });
        return gridPane;
    }

    private void login() {

    }


    public static void main(String[] args) {
        launch(args);
    }
}
