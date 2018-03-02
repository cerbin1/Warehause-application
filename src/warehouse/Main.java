package warehouse;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene loginScene;
    private Scene mainScene;
    private Stage primaryStage;
    private UserDao userDao;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        userDao = new UserDao();

        GridPane gridPane = createDefaultGridPane();

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

        final Text text = new Text();
        text.setFill(Color.FIREBRICK);
        gridPane.add(text, 1, 6);

        Button signInButton = new Button("Sign in");
        signInButton.setOnAction(e -> {
            User user = new User(loginTextField.getText(), passwordField.getText());
            if (userDao.isUserInDatabase(user)) {
                login();
                text.setText("");
            } else {
                text.setText("Login failed");
            }
        });
        HBox hBoxButton = new HBox(10);
        hBoxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hBoxButton.getChildren().add(signInButton);
        gridPane.add(hBoxButton, 1, 4);


        mainScene = createMainScene();

        loginScene = new Scene(gridPane, 300, 275);
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private Scene createMainScene() {
        GridPane gridPane = createDefaultGridPane();

        TableView tableView = new TableView();
        TableColumn loginColumn = new TableColumn("Login");
        TableColumn passwordColumn = new TableColumn("Password");

        tableView.getColumns().addAll(loginColumn, passwordColumn);

        Button logoutButton = new Button("Logout");
        logoutButton.setOnMouseClicked(event -> primaryStage.setScene(loginScene));
        gridPane.add(tableView, 0, 0);
        gridPane.add(logoutButton, 0, 1);

        return new Scene(gridPane, 300, 300);
    }

    private GridPane createDefaultGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void login() {
        primaryStage.setScene(mainScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
