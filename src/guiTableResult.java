import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class guiTableResult extends Application{

    public static void main(String[] args){
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collection<TrainSchedulerData> list = 
        		Files.readAllLines(this.mostRecent(new File("./results/txt/").listFiles())).stream()
                        .map(line -> {
                            String[] details = line.split(",");
                            TrainSchedulerData cd = new TrainSchedulerData();
                            cd.setTrainNum(details[0]);
                            cd.setStart(details[1]);
                            cd.setEnd(details[2]);
                            cd.setRoute(details[3]);
                            cd.setExpected(details[4]);
                            cd.setActual(details[5]);
                            cd.setDelay(details[6]);
                            return cd;
                        })
                        .collect(Collectors.toList());

        ObservableList<TrainSchedulerData> details = FXCollections.observableArrayList(list);

        TableView<TrainSchedulerData> tableView = new TableView<>();
        TableColumn<TrainSchedulerData, String> col1 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col2 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col3 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col4 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col5 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col6 = new TableColumn<>();
        TableColumn<TrainSchedulerData, String> col7 = new TableColumn<>();

        tableView.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);

        col1.setCellValueFactory(data -> data.getValue().trainNumProperty());
        col2.setCellValueFactory(data -> data.getValue().startProperty());
        col3.setCellValueFactory(data -> data.getValue().endProperty());
        col4.setCellValueFactory(data -> data.getValue().routeProperty());
        col5.setCellValueFactory(data -> data.getValue().expectedProperty());
        col6.setCellValueFactory(data -> data.getValue().actualProperty());
        col7.setCellValueFactory(data -> data.getValue().delayProperty());
        
        tableView.setItems(details);

        StackPane sp = new StackPane(tableView);
        Scene scene = new Scene(sp);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     private Path mostRecent(File[] listFiles) {
		int mostRecent = 0;
		
		for (int i = 1; i < listFiles.length; i++) {
			if (listFiles[mostRecent].lastModified() < listFiles[i].lastModified()) {
				mostRecent = i;
			}
		}
		
		return listFiles[mostRecent].toPath();
	}

	private class TrainSchedulerData {
        StringProperty trainNum = new SimpleStringProperty();
        StringProperty start = new SimpleStringProperty();
        StringProperty end = new SimpleStringProperty();
        StringProperty route = new SimpleStringProperty();
        StringProperty expected = new SimpleStringProperty();
        StringProperty actual = new SimpleStringProperty();
        StringProperty delay = new SimpleStringProperty();
        
        public final StringProperty trainNumProperty() {
            return this.trainNum;
        }

        public final java.lang.String getTrainNum() {
            return this.trainNumProperty().get();
        }

        public final void setTrainNum(final java.lang.String trainNum) {
            this.trainNumProperty().set(trainNum);
        }

        public final StringProperty startProperty() {
            return this.start;
        }

        public final java.lang.String getStart() {
            return this.startProperty().get();
        }

        public final void setStart(final java.lang.String start) {
            this.startProperty().set(start);
        }

        public final StringProperty endProperty() {
            return this.end;
        }

        public final java.lang.String getEnd() {
            return this.endProperty().get();
        }

        public final void setEnd(final java.lang.String end) {
            this.endProperty().set(end);
        }

        public final StringProperty routeProperty() {
            return this.route;
        }

        public final java.lang.String getRoute() {
            return this.routeProperty().get();
        }

        public final void setRoute(final java.lang.String route) {
            this.routeProperty().set(route);
        }

        public final StringProperty expectedProperty() {
            return this.expected;
        }

        public final java.lang.String getExpected() {
            return this.expectedProperty().get();
        }

        public final void setExpected(final java.lang.String expected) {
            this.expectedProperty().set(expected);
        }
        
        public final StringProperty actualProperty() {
            return this.actual;
        }

        public final java.lang.String getActual() {
            return this.actualProperty().get();
        }

        public final void setActual(final java.lang.String actual) {
            this.actualProperty().set(actual);
        }
        
        public final StringProperty delayProperty() {
            return this.delay;
        }

        public final java.lang.String getDelay() {
            return this.delayProperty().get();
        }

        public final void setDelay(final java.lang.String delay) {
            this.delayProperty().set(delay);
        }
    }
}
