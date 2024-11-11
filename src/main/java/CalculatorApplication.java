import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class CalculatorApplication extends Application {

    private ResourceBundle bundle;
    private Label distanceLabel;
    private TextField distanceField;
    private Label fuelLabel;
    private TextField fuelField;
    private Button calculateButton;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Joel Tikkanen");

        distanceLabel = new Label();
        distanceField = new TextField();
        fuelLabel = new Label();
        fuelField = new TextField();
        calculateButton = new Button();
        resultLabel = new Label();


        setLanguage(Locale.ENGLISH);

        calculateButton.setOnAction(e -> calculateFuelConsumption());

        Button enButton = new Button("EN");
        Button frButton = new Button("FR");
        Button jpButton = new Button("JP");
        Button irButton = new Button("IR");

        enButton.setOnAction(e -> setLanguage(Locale.ENGLISH));
        frButton.setOnAction(e -> setLanguage(Locale.FRENCH));
        jpButton.setOnAction(e -> setLanguage(Locale.JAPANESE));
        irButton.setOnAction(e -> setLanguage(new Locale("fa", "IR")));


        VBox langBox = new VBox(5, enButton, frButton, jpButton, irButton);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(distanceLabel, distanceField, fuelLabel, fuelField, calculateButton, resultLabel, langBox);

        Scene scene = new Scene(root, 300, 300);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private void setLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("messages", locale);
        distanceLabel.setText(bundle.getString("distance.label"));
        fuelLabel.setText(bundle.getString("fuel.label"));
        calculateButton.setText(bundle.getString("calculate.button"));
        resultLabel.setText("");
    }

    private void calculateFuelConsumption() {
        try {
            double distance = Double.parseDouble(distanceField.getText());
            double fuel = Double.parseDouble(fuelField.getText());
            double consumption = (fuel / distance) * 100;
            double price = consumption * 1.67;
            resultLabel.setText(String.format(bundle.getString("result.label"), String.format("%.2f", consumption)) +
                    String.format("\n" + bundle.getString("price.label"), price));
        } catch (NumberFormatException ex) {
            resultLabel.setText(bundle.getString("invalid.input"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
