package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserInterface {
    private Label label;
    protected GridPane Stage;
    private GridPane gridPane;
    private Board fourByFourBoard = new Board();


    UserInterface() throws IOException {
        makeStage();
    }

    public void resetUI() throws FileNotFoundException {
         makeGrid();
    }

    private void makeStage() throws IOException {
        Stage = new GridPane();

        initializeGridPane();
        Stage.add(gridPane, 0, 0);

        Button newGameButton = new Button("New Game !");
        newGameButton.setAlignment(Pos.BASELINE_CENTER);
        Stage.add(newGameButton, 0, 1);
        GridPane.setHalignment(newGameButton, HPos.CENTER);
        Stage.setAlignment(Pos.CENTER);

        label = new Label("Welcome to Tic Tac Toe !");
        label.autosize();
        label.setPadding(new Insets(40));

        GridPane.setHalignment(label,HPos.CENTER);

        Stage.add(label,0,2);

        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fourByFourBoard.resetBoard();
                try {
                    label.setText("Welcome to Tic Tac Toe !");
                    resetUI();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initializeGridPane() throws IOException {
        gridPane = new GridPane();
        makeGrid();

    }
    public void makeGrid() throws FileNotFoundException {
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 4; i++) {
                FileInputStream input = new FileInputStream("back.jpg");
                Image image = new Image(input, 40, 40, false, false);
                ImageView imageView = new ImageView(image);


                String Id = "B" + j + i;
                imageView.setId(Id);


                imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new ImageClickHandler());

                gridPane.add(imageView, j, i);
                gridPane.setHgap(10);
                gridPane.setVgap(10);
                gridPane.setPadding(new Insets(20));
            }
    }

    class ImageClickHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            int oldturn = fourByFourBoard.getCount_Turns();

            try {
                String id = ((ImageView) event.getSource()).getId().toString();

                int j = id.charAt(1) - 48;
                int i = id.charAt(2) - 48;
                //System.out.println(j + " " + i);


                if (fourByFourBoard.playerXTurn && (fourByFourBoard.getBoard()[j][i] != "X") && (fourByFourBoard.getBoard()[j][i] !="O")) {
                    FileInputStream input = new FileInputStream("x.jpg");
                    Image image = new Image(input, 40, 40, false, false);
                    ((ImageView) event.getSource()).setImage(image);

                    fourByFourBoard.getBoard()[j][i] = "X";
                    fourByFourBoard.setCount_Turns(fourByFourBoard.getCount_Turns()+1);
                }
                else
                {
                    if((fourByFourBoard.getBoard()[j][i] != "X") && (fourByFourBoard.getBoard()[j][i] !="O"))
                    {
                        FileInputStream input = new FileInputStream("o.png");
                        Image image = new Image(input, 40, 40, false, false);
                        ((ImageView) event.getSource()).setImage(image);


                        fourByFourBoard.setCount_Turns(fourByFourBoard.getCount_Turns()+1);
                        fourByFourBoard.getBoard()[j][i] = "O";
                    }
                }


                if (fourByFourBoard.checkIfWon())
                {
                    if(fourByFourBoard.playerXTurn)
                    {
                        label.setText("X Wins!!");
                    }
                    else
                    {
                        label.setText( "O Wins !!");
                    }
                }
                else if (fourByFourBoard.getCount_Turns() == 16)
                {
                    label.setText(" It's a Draw");
                }
                else
                {
                    if(fourByFourBoard.getCount_Turns() != oldturn) {
                        fourByFourBoard.playerXTurn = !fourByFourBoard.playerXTurn;
                        oldturn = fourByFourBoard.getCount_Turns();
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
