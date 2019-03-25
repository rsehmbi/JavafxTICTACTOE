package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Board board = new Board();
        primaryStage.setTitle("Tic Tac Toe");

        //Making a UI of Board with 16 Image Views
        UserInterface UI = new UserInterface();


        //Setting the Stage
        primaryStage.setScene(new Scene(UI.Stage, 400, 400));


        //Showing the Stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
