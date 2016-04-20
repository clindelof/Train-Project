import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class collector {
    public static void display(String title, String message) {
    	Stage window = new Stage ();
    	window.setTitle("collector");
    	
//    	window.setOnCloseRequest(e -> {
//    		e.consume();
//    		closeProgram();
//    	});
    	
    	Label label = new Label();
    	label.setText("form");
    	
    	Button closeButton = new Button();
    	closeButton.setText("close");
    	
    	Pane layout = new Pane();
        closeButton.setLayoutX(100);
        closeButton.setLayoutY(50);
    	layout.getChildren().addAll(label, closeButton);
    	
    	Scene scene = new Scene(layout, 240, 200);
    	window.setScene(scene);
    	window.show();
    }
    
//    private void closeProgram() {
//    	Boolean answer = ConfirmBox.display("Title", "exit?");
//    	if (answer)
//    		window.close();
//    }
}
