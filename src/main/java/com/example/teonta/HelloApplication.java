package com.example.teonta;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Healthy Eatz");

        // BorderPane as the root
        BorderPane root = new BorderPane();

        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save Order");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(saveItem, exitItem);
        menuBar.getMenus().add(fileMenu);

        // Create a VBox for the order form
        VBox orderBox = new VBox(10);
        orderBox.setPadding(new Insets(20, 20, 20, 20));

        // Add a RadioButton for delivery or pickup
        ToggleGroup deliveryGroup = new ToggleGroup();
        RadioButton deliveryRadioButton = new RadioButton("Delivery");
        deliveryRadioButton.setToggleGroup(deliveryGroup);
        deliveryRadioButton.setSelected(true); // Default selection
        RadioButton pickupRadioButton = new RadioButton("Pickup");
        pickupRadioButton.setToggleGroup(deliveryGroup);
        RadioButton cateringRadioButton = new RadioButton("Catering");
        pickupRadioButton.setToggleGroup(deliveryGroup);

        // Labels
        Label titleLabel = new Label("Healthy Eatz");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Label burgerLabel = new Label("Select Vegan Burger Type:");
        Label sideLabel = new Label("Select a Side:");
        Label drinkLabel = new Label("Select a Drink:");
        Label totalLabel = new Label("Total:");

        // ComboBox for burger selection
        ComboBox<String> burgerComboBox = new ComboBox<>();
        burgerComboBox.getItems().addAll("Black Bean ($1.00)", "Chickpea ($1.50)", "Impossible Burger ($2.00)");

        // CheckBoxes for side selection
        CheckBox friesCheckBox = new CheckBox("Fries ($1.00)");
        CheckBox sweetPotatoFriesCheckBox = new CheckBox("Sweet Potato Fries ($1.50)");
        CheckBox kaleSlawCheckBox = new CheckBox("Kale Slaw ($2.50)");

        // ComboBox for drink selection
        ComboBox<String> drinkComboBox = new ComboBox<>();
        drinkComboBox.getItems().addAll("Green Juice ($4.50)", "Fruit Smoothie ($4.00)", "Alkaline Water ($1.20)");

        // TextField for displaying the total
        TextField totalTextField = new TextField();
        totalTextField.setEditable(false);

        // Button for placing the order
        Button placeOrderButton = new Button("Place Order");
        placeOrderButton.setOnAction(e -> calculateTotal(burgerComboBox, friesCheckBox, sweetPotatoFriesCheckBox,
                kaleSlawCheckBox, drinkComboBox, totalTextField));

        // VBox for the order summary
        VBox summaryBox = new VBox(10);
        summaryBox.setPadding(new Insets(20, 20, 20, 20));
        summaryBox.setBorder(new TitledPane().getBorder());

        // Add components to the orderBox
        orderBox.getChildren().addAll(
                titleLabel, deliveryRadioButton, pickupRadioButton, burgerLabel, burgerComboBox, sideLabel,
                friesCheckBox, sweetPotatoFriesCheckBox, kaleSlawCheckBox,
                drinkLabel, drinkComboBox, totalLabel, totalTextField, placeOrderButton);

        root.setTop(menuBar);
        root.setCenter(orderBox);
        root.setRight(summaryBox);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateTotal(ComboBox<String> burgerComboBox, CheckBox friesCheckBox, CheckBox sweetPotatoFriesCheckBox,
                                CheckBox kaleSlawCheckBox, ComboBox<String> drinkComboBox, TextField totalTextField) {
        double total = 0;

        // Calculate burger cost
        String burgerSelection = burgerComboBox.getValue();
        if (burgerSelection != null) {
            if (burgerSelection.contains("Black Bean")) {
                total += 1.0;
            } else if (burgerSelection.contains("Chickpea")) {
                total += 1.5;
            } else if (burgerSelection.contains("Impossible Burger")) {
                total += 2.0;
            }
        }

        // Calculate side cost
        if (friesCheckBox.isSelected()) {
            total += 1.0;
        }
        if (sweetPotatoFriesCheckBox.isSelected()) {
            total += 1.5;
        }
        if (kaleSlawCheckBox.isSelected()) {
            total += 2.5;
        }

        // Calculate drink cost
        String drinkSelection = drinkComboBox.getValue();
        if (drinkSelection != null) {
            if (drinkSelection.contains("Green Juice")) {
                total += 4.5;
            } else if (drinkSelection.contains("Fruit Smoothie")) {
                total += 4.0;
            } else if (drinkSelection.contains("Alkaline Water")) {
                total += 1.2;
            }
        }

        // Display the total
        totalTextField.setText("$" + String.format("%.2f", total));
    }
}