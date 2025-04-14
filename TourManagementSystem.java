package tourManagementSystem2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
public class TourManagementSystem extends Application{
	private List<TourPackage> tourPackages = new ArrayList<>();
    private User currentUser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tour Management System");

        // Initialize some tour packages
        initializeTourPackages();

        // Login Screen
        VBox loginScreen = createLoginScreen(primaryStage);
        Scene loginScene = new Scene(loginScreen, 400, 300);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Method to initialize tour packages
    private void initializeTourPackages() {
        List<String> desertSafariAccommodations = List.of("Hotel Stay (1 Night)", "Desert Camp Experience", "Meals Included");
        tourPackages.add(new TourPackage("Desert Safari Package", 300, "2 Days / 1 Night", 20, desertSafariAccommodations));

        List<String> futureMuseumAccommodations = List.of("Access to the Future Museum", "Visit to Burj Khalifa and Dubai Fountain", "Lunch at a popular restaurant");
        tourPackages.add(new TourPackage("Future Museum and City Highlights Package", 500, "1 Day", 15, futureMuseumAccommodations));

        List<String> culturalBeachAccommodations = List.of("Hotel Stay (1 Night)", "Visit to Jumeirah Beach and Palm Jumeirah", "Traditional Dhow Cruise Dinner");
        tourPackages.add(new TourPackage("Cultural and Beach Adventure Package", 400, "2 Days / 1 Night", 10, culturalBeachAccommodations));
    }

    // Method to create the login screen
    private VBox createLoginScreen(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Tour Management System");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            // Simulate a user login (for demo purposes)
            currentUser = new User("1", "Admin", email, password);
            if (currentUser.login(email, password)) {
                showPackageDisplayScreen(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid email or password.");
                alert.show();
            }
        });

        vbox.getChildren().addAll(titleLabel, emailField, passwordField, loginButton);
        return vbox;
    }

    // Method to display the package selection screen
    private void showPackageDisplayScreen(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Available Tour Packages");
        vbox.getChildren().add(titleLabel);

        for (TourPackage tourPackage : tourPackages) {
            Button packageButton = new Button(tourPackage.toString());
            packageButton.setOnAction(e -> showUserInformationForm(primaryStage, tourPackage));
            vbox.getChildren().add(packageButton);
        }

        Scene packageScene = new Scene(vbox, 400, 300);
        primaryStage.setScene(packageScene);
    }

    // Method to show the user information form
    private void showUserInformationForm(Stage primaryStage, TourPackage tourPackage) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Enter Your Details for " + tourPackage.getPackageName());
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField contactField = new TextField();
        contactField.setPromptText("Contact Information");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");
        TextField pickupField = new TextField();
        pickupField.setPromptText("Pickup Location");
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String contact = contactField.getText();
            String date = datePicker.getValue().toString();
            String pickupLocation = pickupField.getText();

            // Generate ticket
            showTicketGenerationScreen(primaryStage, name, tourPackage, date, pickupLocation);
        });

        vbox.getChildren().addAll(titleLabel, nameField, contactField, datePicker, pickupField, submitButton);
        Scene formScene = new Scene(vbox, 400, 300);
        primaryStage.setScene(formScene);
    }

    // Method to show the ticket generation screen
    private void showTicketGenerationScreen(Stage primaryStage, String name, TourPackage tourPackage, String date, String pickupLocation) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Your Tour Ticket");
        Label nameLabel = new Label("Name: " + name);
        Label packageLabel = new Label("Package: " + tourPackage.getPackageName());
        Label priceLabel = new Label("Price: " + tourPackage.getPrice() + " AED");
        Label dateLabel = new Label("Date: " + date);
        Label pickupLabel = new Label("Pickup Location: " + pickupLocation);

        vbox.getChildren().addAll(titleLabel, nameLabel, packageLabel, priceLabel, dateLabel, pickupLabel);
        Scene ticketScene = new Scene(vbox, 400, 300);
        primaryStage.setScene(ticketScene);
    }
}