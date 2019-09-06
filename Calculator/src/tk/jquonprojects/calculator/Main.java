package tk.jquonprojects.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Jaiquon Smith <jaydeshaun@live.ca>
 */
/*
GUI opens up
Press numbers (goes into text field)
press operator (store numbers)
press numbers
press equal
 */
public class Main extends Application {

    private TextField txtViewer;
    private Button btnReset, btnEq;
    private final double[] values = new double[3]; //User values
    private String lastOp;
    private Label lblStatus;
    //private Calculator calc = new Calculator();

    @Override
    public void start(Stage primaryStage) {
        double max = Double.MAX_VALUE;

        //Calculation Viewer
        txtViewer = new TextField("");
        txtViewer.setPromptText("Start calculations...");
        txtViewer.setDisable(true);
        txtViewer.setMaxSize(max, max);

        //Calculator Resetter
        btnReset = new Button("C");
        btnReset.setDisable(true);
        btnReset.setMaxSize(max, max);
        btnReset.setOnAction((ActionEvent) -> {
            //calc.reset();
            values[0] = 0;
            values[1] = 0;
            values[2] = 0;
            txtViewer.setText("");
            (this.btnReset).setDisable(true);
            (this.btnEq).setDisable(true);
        });

        //Operators Button
        calc cal = new calc();
        Button[] btnOps = new Button[4];
        btnOps[0] = new Button("/");
        btnOps[1] = new Button("*");
        btnOps[2] = new Button("-");
        btnOps[3] = new Button("+");
        for (Button btnOp : btnOps) {
            btnOp.setMaxSize(max, max);
            btnOp.setOnAction(cal);
        }

        //Number Buttons
        Button[] btnInts = new Button[10];
        for (int i = 0; i < 10; i++) {
            btnInts[i] = new Button(i + "");
            btnInts[i].setMaxSize(max, max);
            btnInts[i].setOnAction((ActionEvent) -> {
                Button source = (Button) ActionEvent.getSource();
                txtViewer.appendText(source.getText());
            });
            /*btnInts[i].setOnKeyPressed((KeyEvent) -> {
                char digit = KeyEvent.getCharacter().charAt(0);
                
                if (digit >= '0' && digit <= '9') {
                    txtViewer.appendText(digit + "");
                }
            });
            */
        }

        //Equal Button
        btnEq = new Button("=");
        btnEq.setDisable(true);
        btnEq.setMaxSize(max, max);
        btnEq.setOnAction((ActionEvent) -> {
            values[1] = Double.parseDouble(txtViewer.getText());
            switch (lastOp) {
                case "-":
                    values[2] = values[0] - values[1];
                    break;
                case "+":
                    values[2] = values[0] + values[1];
                    break;
                case "/":
                    values[2] = values[0] / values[1];
                    break;
                case "*":
                    values[2] = values[0] * values[1];
                    break;
            }
            txtViewer.setText(values[2] + "");
        });
        
        //lblStatus = new Label("Begin");

        //UI Design/Organizing
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(25);

        GridPane btns = new GridPane();
        btns.getColumnConstraints().addAll(col, col, col, col); //create cols
        
        //Place Operand Buttons (btn, col, row) + Equal
        btns.add(btnReset, 0, 0);
        btns.add(btnOps[0], 1, 0);
        btns.add(btnOps[1], 2, 0);
        btns.add(btnOps[2], 3, 0);
        btns.add(btnOps[3], 3, 1);
        btns.add(btnEq, 3, 2, 1, 2);
        

        //Place Number Buttons
        int i = 1;
        for (int r = 1; r < 4; r++) {
            for (int c = 1; c < 4; c++) {
                btns.add(btnInts[i], c - 1, r);
                i++;
            }
        }
        //btns.add(btnInts[0],0,5,4,1);
        //btns.add(lblStatus,0,5,4,1);

        BorderPane root = new BorderPane();
        root.setTop(txtViewer);
        root.setCenter(btns);
        //root.setBottom(lblStatus);
        root.setBottom(btnInts[0]);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Simple Java Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class calc implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            double val = Double.parseDouble(txtViewer.getText());
            btnReset.setDisable(false);
            btnEq.setDisable(false);
            
            //Get Operand Selected
            Button source = (Button) event.getSource();
            lastOp = source.getText();
            if (values[0] == 0) {
                values[0] = val;
            } else {
                values[1] = val;
                switch (lastOp) {
                    case "-":
                        values[0] -= values[1];
                        break;
                    case "+":
                        values[0] += values[1];
                        break;
                    case "/":
                        values[0] /= values[1];
                        break;
                    case "*":
                        values[0] *= values[1];
                        break;
                }
                values[1] = 0;
            }
            txtViewer.setText("");
        }

    }

}
