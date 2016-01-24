package com.main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;
import com.service.ExoplanetService;
import com.service.IExoplanetService;

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = sdf.parse("2014-10-01");
		java.sql.Date date = new java.sql.Date(parsed.getTime());
		
		Exoplanet e = new Exoplanet(null, "Gliese 876 d", 0.017, 0.0, 1.94, 0.02080665,
				0.081,50.0, 0.004427, 2005, date);
		IExoplanetService ies = new ExoplanetService();
		try {
			e = ies.create(e);
		} catch (ExoplanetServiceException ese) {
			// TODO Auto-generated catch block
			ese.printStackTrace();
		}
		launch(args);
		logger.info("HelloWorld");
	}
}