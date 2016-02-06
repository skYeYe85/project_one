package com.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;
import com.service.IExoplanetPage;
import com.service.IExoplanetService;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ExoplanetController {

	private static final Logger logger = LogManager.getLogger(ExoplanetController.class);
	private Exoplanet e;
	private Stage mainStage;
	private Main main;
	private double defaultHeight;
	private double defaultWidth;
	@Autowired
	private IExoplanetService exoplanetService;
	@Autowired
	private IExoplanetPage exoplanetPage;

	@FXML
	public void onTestButton() throws ParseException {
		logger.info("ExoplanetController");
		
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Test.fxml"));
//        loader.setControllerFactory(new Callback<Class<?>, Object>() {
//        	@Override
//            public Object call(Class<?> clazz) {
//                return main.getCtx().getBean(clazz);
//            }
//        });
//        AnchorPane page = null;
//        try {
//
//            page = loader.load();
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("See Details");
//            dialogStage.initOwner(mainStage);
//            Scene scene = new Scene(page);
//            dialogStage.setResizable(false);
//            dialogStage.setScene(scene);
//            
//            // Show the dialog and wait until the user closes it
//            dialogStage.showAndWait();
//
//        } catch (IOException e) {
//            logger.error("Reading the necessary file failed: " + e);
//            e.printStackTrace();
//        }

		List<Exoplanet> list;
		list = exoplanetPage.exoplanetByName("Gliese");
		for (int i = 0; i < list.size(); i++) {
			try {
				e = exoplanetService.create(list.get(i));
			} catch (ExoplanetServiceException ese) {
				// TODO Auto-generated catch block
				ese.printStackTrace();
			}
		}
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
		this.defaultHeight = this.mainStage.getHeight();
		this.defaultWidth = this.mainStage.getWidth();
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
