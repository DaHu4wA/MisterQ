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
import misterq.logic.DoneGrade;
import misterq.logic.Food;

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
    public Button btnStart;

    @FXML
    Button btn100g;
    @FXML
    Button btn200g;
    @FXML
    Button btn300g;
    @FXML
    Button btn500g;
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
    Label tempLabel;

    @FXML
    Label timer;

    private Food chosenFood;
    private int chosenGrams;
    private DoneGrade doneGrade;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        cookingLogic = new CookingLogic(text -> Platform.runLater(() -> {
            if (tempLabel != null) {
                tempLabel.setText(text);
            }
        }));

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("misterq/gui/maingui.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("Mister Q - Your BBQ buddy");
        stage.setScene(scene);
        stage.show();

        Label toptText = (Label) root.lookup("#toptext");


        tempLabel = (Label) root.lookup("#tempLabel");

        initialize(root, toptText);

        btnReset = (Button) root.lookup("#btnReset");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
                                 @Override
                                 public void handle(ActionEvent event) {
                                     initialize(root, toptText);
                                 }
                             }
        );

        Button btnOpen = (Button) root.lookup("#open");
        btnOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.openIt();
            }
        });

        Button btnClose = (Button) root.lookup("#close");
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.closeIt();
            }
        });

        Button btnUp = (Button) root.lookup("#btnUp");
        btnUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.manualUp();
            }
        });

        Button btnDown = (Button) root.lookup("#btnDown");
        btnDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.manualDown();
            }
        });

        Button btnZero = (Button) root.lookup("#btnZero");
        btnZero.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.manualZero();
            }
        });

        Button btn180 = (Button) root.lookup("#btn180");
        btn180.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cookingLogic.manual180();
            }
        });

    }

    private void initialize(Parent root, Label toptText) {
        btnStart = (Button) root.lookup("#btnStart");
        btnStart.setVisible(false);
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                btnStart.setVisible(false);
                startGrilling();
            }
        });


        tempLabel.setText("");

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
                chosenFood = Food.BURGER;
            }
        });

        btnSteak = (Button) root.lookup("#btnSteak");
        btnSteak.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("Steak. Wise choice, my friend!");
                hideInitialButtons(false);
                hideGramsButtons(true);
                chosenFood = Food.STEAK;
            }
        });

        btnChicken = (Button) root.lookup("#btnChicken");
        btnChicken.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("Let's make some Chicken!");
                hideInitialButtons(false);
                hideGramsButtons(true);
                chosenFood = Food.CHICKEN;
            }
        });

        btnSausage = (Button) root.lookup("#btnSausage");
        btnSausage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toptText.setText("You want Sausage? Let's do it!");
                hideInitialButtons(false);
                hideGramsButtons(true);
                chosenFood = Food.SAUSAGE;
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
                chosenGrams = 100;
            }
        });
        btn200g = (Button) root.lookup("#btn200g");
        btn200g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 200 Grams");
                chosenGrams = 200;
            }
        });
        btn300g = (Button) root.lookup("#btn300g");
        btn300g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 300 Grams");
                chosenGrams = 300;
            }
        });
        btn500g = (Button) root.lookup("#btn500g");
        btn500g.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideGramsButtons(false);
                hideHowDoneButtons(true);
                toptText.setText(toptText.getText() + " - 500 Grams");
                chosenGrams = 500;
            }
        });

        textWeight = (Label) root.lookup("#textWeight");

        btnRare = (Button) root.lookup("#btnRare");
        btnRare.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Rare");
                doneGrade = DoneGrade.RARE;
                btnStart.setVisible(true);
                cookingLogic.openIt();
            }
        });

        btnMedium = (Button) root.lookup("#btnMedium");
        btnMedium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Medium");
                doneGrade = DoneGrade.MEDIUM;
                btnStart.setVisible(true);
                cookingLogic.openIt();
            }
        });

        btnDone = (Button) root.lookup("#btnDone");
        btnDone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hideHowDoneButtons(false);
                toptText.setText(toptText.getText() + " - Done");
                doneGrade = DoneGrade.DONE;
                btnStart.setVisible(true);
                cookingLogic.openIt();
            }
        });

        textHowDone = (Label) root.lookup("#textHowDone");

        hideGramsButtons(false);
        hideHowDoneButtons(false);
    }

    private void startGrilling() {
        timer.setVisible(true);
        timer.setText("LETS ROCK!");
        cookingLogic.startCooking(chosenFood, doneGrade, chosenGrams, text -> Platform.runLater(() -> timer.setText(text)));
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
        textWeight.setVisible(visible);
    }

    private void hideHowDoneButtons(boolean visible) {
        btnRare.setVisible(visible);
        btnMedium.setVisible(visible);
        btnDone.setVisible(visible);
        textHowDone.setVisible(visible);
    }
}
