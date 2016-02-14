package com.main;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public abstract class DialogManager {
	
	private Alert alert;
	
    public void createInformationDialog(String title, String header, String content) {
    	alert = new Alert(AlertType.INFORMATION);
    	setDialogParameters(title, header, content);
    	alert.showAndWait();
    }

    public Optional<ButtonType> createConfirmationDialog(String title, String header, String content){
    	alert = new Alert(AlertType.CONFIRMATION);
    	setDialogParameters(title, header, content);
    	return alert.showAndWait();
    }
    
    private void setDialogParameters(String title, String header, String content){
    	this.alert.setTitle(title);
    	this.alert.setHeaderText(header);
    	this.alert.setContentText(content);
    }
}
