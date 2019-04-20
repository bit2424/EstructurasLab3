
package view;
	

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

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
	
	public static void serealization() throws  IOException {
		File file = new File("./LAB/Serializacion/serializacion");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(reception);
		oos.close();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File("./LAB/Serializacion/serializacion");
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			reception = (BVC) ois.readObject();
		}
		else
			reception = new BVC();
		launch(args);
	}
}