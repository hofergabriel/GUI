/*
1. Tier: Model	26 total
        _____ All base apartment model classes there
        _____ All listed apartment member variables for model there
        _____ All apartment complex model classes there
        _____ All listed apartment complex member variables or getters (this includes things like rent!) for model there
        _____ Model classes properly connected


        2. Tier: View	15 total
        _____ All required items there
        _____ All visuals present and not overlapping


        3. Tier: functionality	29 total
        _____ All 5 info items display (equally weighted)
        _____ All 5 info items display correctly on start (equally weighted)
        _____ Rent increase does something
        _____ Rent increase works on Floors
        _____ Rent increase works on info bar
        _____ New Month works on Floors
        _____ New Month works on all current Month and funds (50% each)


        The grade you compute is the starting point for course staff, who reserve the right to change the grade if they disagree with your assessment and to deduct points for other issues they may encounter, such as errors in the submission process, naming issues, etc.
*/
package hofer_gabriel;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.lang.model.util.Elements;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Layout layout = new Layout(primaryStage);
    }
}
