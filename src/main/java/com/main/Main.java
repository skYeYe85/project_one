package com.main;
	
import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;
import com.service.ExoplanetPage;
import com.service.ExoplanetService;
import com.service.IExoplanetPage;
import com.service.IExoplanetService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("exoplanet.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ParseException {
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date parsed = sdf.parse("2014-10-01");
//		java.sql.Date date = new java.sql.Date(parsed.getTime());
//		
		Exoplanet e = new Exoplanet();
		IExoplanetService ies = new ExoplanetService();

		
		IExoplanetPage page = new ExoplanetPage();
		List<Exoplanet> list;
		list = page.exoplanetWholeList();
		for(int i = 0; i < list.size(); i++){
			try {
				e = ies.create(list.get(i));
			} catch (ExoplanetServiceException ese) {
				// TODO Auto-generated catch block
				ese.printStackTrace();
			}
		}
		launch(args);
		logger.info("HelloWorld");
	}
}
