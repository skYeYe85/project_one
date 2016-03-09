package com.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.dialog.ProgressDialog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;
import com.service.IExoplanetPage;
import com.service.IExoplanetService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExoplanetController extends DialogManager implements Initializable {

	private static final Logger logger = LogManager.getLogger(ExoplanetController.class);
	private WebDriver driver;
	private List<Exoplanet> exoplanetList;
	private ObservableList<Exoplanet> oExoplanetList;
	private Exoplanet exoplanet;
	private Stage mainStage;
	private Main main;
	private double defaultHeight;
	private double defaultWidth;
	@Autowired
	private IExoplanetService exoplanetService;
	@Autowired
	private IExoplanetPage exoplanetPage;

	@FXML
	private TableView<Exoplanet> exoplanetTable;
	@FXML
	private TableColumn<Exoplanet, Integer> exoplanetId;
	@FXML
	private TableColumn<Exoplanet, String> exoplanetName;
	@FXML
	private Label labelMasse;
	@FXML
	private Label labelRadius;
	@FXML
	private Label labelUmlaufzeit;
	@FXML
	private Label labelHalbachse;
	@FXML
	private Label labelExzentrizitaet;
	@FXML
	private Label labelBahnneigung;
	@FXML
	private Label labelWinkelabstand;
	@FXML
	private Label labelEntdeckung;
	@FXML
	private Label labelAktualisierung;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("ExoplanetController - initialize");
		exoplanetList = new ArrayList<Exoplanet>();
		try {
			exoplanetList = exoplanetService.listAllExoplanets();
		} catch (ExoplanetServiceException ese) {
			logger.error(ese.getMessage());
		}
		showExoplanetDetails(exoplanet);
		exoplanetId.setCellValueFactory(new PropertyValueFactory<Exoplanet, Integer>("id"));
		exoplanetName.setCellValueFactory(new PropertyValueFactory<Exoplanet, String>("planet"));
		oExoplanetList = FXCollections.observableList(exoplanetList);
		exoplanetTable.setItems(oExoplanetList);
		exoplanetTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showExoplanetDetails(newValue));
	}

	private void refreshTable() throws ExoplanetServiceException {
		exoplanetList = exoplanetService.listAllExoplanets();
		this.oExoplanetList.removeAll(oExoplanetList);
		for (int i = 0; i < exoplanetList.size(); i++) {
			oExoplanetList.add(exoplanetList.get(i));
		}
	}

	private void showExoplanetDetails(Exoplanet exoplanet) {
		if (exoplanet != null) {
			labelMasse.setText(exoplanet.getMasse().toString());
			labelRadius.setText(exoplanet.getRadius().toString());
			labelUmlaufzeit.setText(exoplanet.getPeriode().toString());
			labelHalbachse.setText(exoplanet.getAstroEinheit().toString());
			labelExzentrizitaet.setText(exoplanet.getExzentrizitaet().toString());
			labelBahnneigung.setText(exoplanet.getBahnneigung().toString());
			labelWinkelabstand.setText(exoplanet.getWinkelabstand().toString());
			labelEntdeckung.setText(exoplanet.getEntdeckung().toString());
			labelAktualisierung.setText(exoplanet.getAktualisierung());
		} else {
			labelMasse.setText("");
			labelRadius.setText("");
			labelUmlaufzeit.setText("");
			labelHalbachse.setText("");
			labelExzentrizitaet.setText("");
			labelBahnneigung.setText("");
			labelWinkelabstand.setText("");
			labelEntdeckung.setText("");
			labelAktualisierung.setText("");
		}
	}

	@FXML
	public void onReloadButton() {
		logger.info("ExoplanetController - onReloadButton");
		Service<Void> service = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {
					@Override
					protected Void call() throws InterruptedException {
						reloadExoplanets();
						return null;
					}
				};
			}
		};
		ProgressDialog progDiag = new ProgressDialog(service);
		progDiag.setTitle("Retrieving Exoplanetdata in Progress");
		progDiag.initOwner(getMainStage());
		progDiag.setHeaderText(
				"Waiting for import of exoplanet data. For more information visit http://exoplanet.eu/catalog");
		progDiag.initModality(Modality.WINDOW_MODAL);
		service.start();
	}

	private void reloadExoplanets() {
		try {
			List<Exoplanet> list = exoplanetService.listAllExoplanets();
			for (int i = 0; i < list.size(); i++) {
				exoplanetService.delete(list.get(i));
			}
			driver = new HtmlUnitDriver();
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
			list = exoplanetPage.exoplanetListAll(driver);
			for (int i = 0; i < list.size(); i++) {
				exoplanetService.create(list.get(i));
			}
			list = exoplanetService.listAllExoplanets();
			this.oExoplanetList.removeAll(oExoplanetList);
			for (int i = 0; i < list.size(); i++) {
				oExoplanetList.add(list.get(i));
			}
		} catch (ExoplanetServiceException ese) {
			logger.error(ese.getMessage());
		}
	}

	@FXML
	public void onUpdateButton() {
		logger.info("ExoplanetController - onUpdateButton");
		exoplanet = exoplanetTable.getSelectionModel().getSelectedItem();
		try {
			int dbid = exoplanet.getId();
			driver = new HtmlUnitDriver();
			((HtmlUnitDriver) driver).setJavascriptEnabled(true);
			exoplanet = exoplanetPage.exoplanetByName(driver, exoplanet.getPlanet()).get(0);
			driver.close();
			exoplanet.setId(dbid);
			exoplanetService.update(exoplanet);
			refreshTable();
		} catch (ExoplanetServiceException ese) {
			logger.error(ese.getMessage());
		}
		// createInformationDialog("UpdateNotImplemented", null, "Update of
		// Exoplanet still not Implemented.");
	}

	@FXML
	public void onDeleteButton() {
		logger.info("ExoplanetController - onDeleteButton");
		Optional<ButtonType> option = createConfirmationDialog("DeleteNotImplemented", null,
				"Deletion of Exoplanet still not Implemented.");
		if (option.get() == ButtonType.OK) {
			createInformationDialog("PUSHED OK", null, "you just confirmed the last message.");
		} else {
			createInformationDialog("PUSHED CANCEL", null, "you just canceld the last message.");
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

	// FXMLLoader loader = new FXMLLoader(getClass().getResource("Test.fxml"));
	// loader.setControllerFactory(new Callback<Class<?>, Object>() {
	// @Override
	// public Object call(Class<?> clazz) {
	// return main.getCtx().getBean(clazz);
	// }
	// });
	// AnchorPane page = null;
	// try {
	//
	// page = loader.load();
	// Stage dialogStage = new Stage();
	// dialogStage.setTitle("See Details");
	// dialogStage.initOwner(mainStage);
	// Scene scene = new Scene(page);
	// dialogStage.setResizable(false);
	// dialogStage.setScene(scene);
	//
	// // Show the dialog and wait until the user closes it
	// dialogStage.showAndWait();
	//
	// } catch (IOException e) {
	// logger.error("Reading the necessary file failed: " + e);
	// e.printStackTrace();
	// }
}
