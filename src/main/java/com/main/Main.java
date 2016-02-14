package com.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Main extends Application {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	private Stage mainStage;

	public static void main(String[] args) {
		launch(args);
		logger.info("ExitExoplanetApplication");
	}

	public void start(Stage primaryStage) throws Exception {
		this.mainStage = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("exoplanet.fxml"));
		loader.setControllerFactory(new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> clazz) {
				return ctx.getBean(clazz);
			}
		});
		Parent root = loader.load();
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.setTitle("EXOPLANETMANAGER");
		primaryStage.show();

		ExoplanetController exoplanetController = loader.getController();
		exoplanetController.setMainStage(mainStage);
		exoplanetController.setMain(this);
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}
}
