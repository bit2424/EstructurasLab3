
package view;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.BVC;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;


public class Main extends Application {
	private static BVC reception;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			SplitPane root = (SplitPane) FXMLLoader.load(getClass().getResource("BVC.fxml"));
			Scene scene = new Scene(root,741,504);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BVC getReception() {
		return reception;
	}
	
	
	
	public static void main(String[] args)  {
		
			reception = new BVC();
		launch(args);
	}
}