import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Gui extends Application implements EventHandler<ActionEvent>{
	static int numOfTrains;
	static int answer;
	
	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Train Dispatch Map");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        final Text action = new Text();
        GridPane.setConstraints(action, 2, 10);
        
        Button baseButton = new Button("Base");
        GridPane.setConstraints(baseButton, 1, 2);
        baseButton.setOnAction(new EventHandler<ActionEvent>(){
	    	@Override
	    	public void handle(ActionEvent event) {
	    		answer = 0;
	    	}
	    });
        
        Button optButton = new Button("Optimized");
        GridPane.setConstraints(optButton, 2, 2);
        optButton.setOnAction(new EventHandler<ActionEvent>(){
	    	@Override
	    	public void handle(ActionEvent event) {
	    		answer = 1;
	    	}
	    });
        
        Label numLabel = new Label("number of train");
        GridPane.setConstraints(numLabel, 0, 8);
        final TextField numInput = new TextField();
        numInput.setPromptText("Please Enter Integer");
        GridPane.setConstraints(numInput, 1, 8);
        
        Button runButton = new Button("Run");
        GridPane.setConstraints(runButton, 1, 10);
        runButton.setOnAction(new EventHandler<ActionEvent>(){
	    	@Override
	    	public void handle(ActionEvent event) {
	    		if (isInt(numInput, numInput.getText())){
	    			//call the program
	    			action.setFill(Color.FIREBRICK);
	    			action.setText("Running");
	    			
	    			String[] arguments = {Integer.toString(numOfTrains), Integer.toString(answer)};
	    			
					Driver.main(arguments);
					
					primaryStage.close();
	    		} else {
	    			alertBox ();
	    		}
	    	}
	    });
        
        grid.getChildren().addAll(baseButton, optButton, numLabel, numInput, runButton, action);
        
        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
    
    private boolean isInt (TextField input, String message) {
    	try {
    		numOfTrains = Integer.parseInt(input.getText());
    		return numOfTrains > 0;
    	} catch (NumberFormatException e) {
    		System.out.println("Error: " + message + " is not an integer");
    		return false;
    	}
    }
    
    public void alertBox () {
    	Stage window = new Stage();
    	
    	window.initModality(Modality.APPLICATION_MODAL);
    	window.setTitle("Alert");
    	window.setMinWidth(250);
    	window.setMinHeight(150);
    	
    	Label label = new Label();
    	label.setText("the input does not fulfill the requirement");
    	
    	VBox layout = new VBox (10);
    	layout.getChildren().addAll(label);
    	layout.setAlignment(Pos.CENTER);
    	
    	Scene scene = new Scene (layout);
    	window.setScene(scene);
    	window.showAndWait();
    }


	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
}
