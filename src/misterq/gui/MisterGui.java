package misterq.gui;/**
 * Created by stefa on 31.03.2017.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import misterq.logic.CookingLogic;

import java.util.Timer;
import java.util.TimerTask;

public class MisterGui extends Application {

    CookingLogic cookingLogic;

    @FXML
    public Button btnReset;
    @FXML
    private Button btnBurger;
    @FXML
    private Button btnSteak;
    @FXML
    private Button btnChicken;
    @FXML
    private Button btnSausage;

    @FXML
    Button btn100g;
    @FXML
    Button btn200g;
    @FXML
    Button btn300g;
    @FXML
    Button btn500g;
    @FXML
    Button btn700g;
    @FXML
    Label textWeight;

    @FXML
    Button btnRare;
    @FXML
    Button btnMedium;
    @FXML
    Button btnDone;
    @FXML
    Label textHowDone;

    @FXML
    Label timer;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        cookingLogic = new CookingLogic();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("misterq/gui/maingui.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("Mister Q - Your BBQ buddy");
        stage.setScene(scene);
        stage.show();

        Label toptText = (Label) root.lookup("#toptext");

        initialize(root, toptText);

        btnReset = (Button) root.lookup("#btnReset");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle(ActionEvent event) {
                                     initialize(root, toptText);
                                 }
                             }
        );

    }

    private void initialize(Parent root, Label toptText) {
        timer = (Label) root.lookup("#timer");
        timer.setVisible(false);

        toptText.setText("Hello buddy! What do you want to eat today?");
        btnBurger = (Button) root.lookup("#btnBurger");
        btnBurger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("Mmhhh... Burger .. What a nice choice!");
                hideInitialButtons(false);
                hideGramsButtons(true);
            }
        });

        btnSteak = (Button) root.lookup("#btnSteak");
        btnSteak.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("Steak. Wise choice, my friend!");
                hideInitialButtons(false);
                hideGramsButtons(true);
            }
        });

        btnChicken = (Button) root.lookup("#btnChicken");
        btnChicken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("Let's make some Chicken!");
                hideInitialButtons(false);
                hideGramsButtons(true);
            }
        });

        btnSausage = (Button) root.lookup("#btnSausage");
        btnSausage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("You want Sausage? Let's do it!");
                hideInitialButtons(false);
                hideGramsButtons(true);
            }
        });
        hideInitialButtons(true);

        btn100g = (Button) root.lookup("#btn100g");
        btn100g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 100 Grams");
            }
        });
        btn200g = (Button) root.lookup("#btn200g");
        btn200g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 200 Grams");
            }
        });
        btn300g = (Button) root.lookup("#btn300g");
        btn300g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 300 Grams");
            }
        });
        btn500g = (Button) root.lookup("#btn500g");
        btn500g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 500 Grams");
            }
        });
        btn700g = (Button) root.lookup("#btn700g");
        btn700g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 700 Grams");
            }
        });
        textWeight = (Label) root.lookup("#textWeight");

        btnRare = (Button) root.lookup("#btnRare");
        btnRare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Rare");
                startGrilling();
            }
        });

        btnMedium = (Button) root.lookup("#btnMedium");
        btnMedium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Medium");
                startGrilling();
            }
        });

        btnDone = (Button) root.lookup("#btnDone");
        btnDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Done");
                startGrilling();
            }
        });

        textHowDone = (Label) root.lookup("#textHowDone");

        hideGramsButtons(false);
        hideHowDoneButtons(false);
    }

    private void startGrilling() {

        cookingLogic.startCooking("Steak", 100, "Done");

        timer.setVisible(true);
        timer.setText("LETS ROCK!");

        new Timer().schedule(new TimerTask() {
            int second = 60;

            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timer.setText("Food is done in: " + second + "sec");

                        if(second == 60){
                            cookingLogic.servoZero();
                        }

                        if(second == 50){
                            cookingLogic.servo180();
                        }
                        second--;
                    }
                });
            }
        }, 0, 1000);
    }

    private void hideInitialButtons(boolean visible) {
        btnSteak.setVisible(visible);
        btnChicken.setVisible(visible);
        btnSausage.setVisible(visible);
        btnBurger.setVisible(visible);
    }

    private void hideGramsButtons(boolean visible) {
        btn100g.setVisible(visible);
        btn200g.setVisible(visible);
        btn300g.setVisible(visible);
        btn500g.setVisible(visible);
        btn700g.setVisible(visible);
        textWeight.setVisible(visible);
    }

    private void hideHowDoneButtons(boolean visible) {
        btnRare.setVisible(visible);
        btnMedium.setVisible(visible);
        btnDone.setVisible(visible);
        textHowDone.setVisible(visible);
    }
}
