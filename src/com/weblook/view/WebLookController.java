/*
 * This is DataOverview Controller
 */
package com.weblook.view;

import com.weblook.WebLook;
import com.webscrap4j.WebScrap;
import com.webscrap4j.WebScrapException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @since 04.03.2018
 * @author Jakub Mazur
 */
public class WebLookController {
    
    private WebLook WebLook;
    
    @FXML
    private Button btnScan;
    
    @FXML
    private TextField txtUrl;
    
    @FXML
    private TextArea txtResults;
    
    @FXML
    private ChoiceBox<String> choiceBox;
    
    @FXML
    private Button btnClear;
    
    @FXML
    private void initialize() {
        
        txtUrl.setPromptText("http://example.com");
        txtResults.setPromptText("Created by Jakub Mazur 2018. \n Have fun! :) \n Contact: mazurkuba0@gmail.com");
        
        choiceBox.getItems().addAll("URL", "IMG", "HTML", "HEAD");
        
        btnClear.setOnAction((event) -> {
            txtResults.clear();
        });
        
        btnScan.setOnAction((event) -> {
            try {
                startScrap(txtUrl.getText());
            } catch (WebScrapException ex) {
                Logger.getLogger(WebLookController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    private void startScrap(String URL) throws WebScrapException {
        WebScrap wScrp = new WebScrap();
        wScrp.setUrl(URL);
        wScrp.startWebScrap();
        
        try {
            if (choiceBox.getSelectionModel().getSelectedItem().equals("URL")) {
                txtResults.appendText("----------URL----------" + "\n");
                txtResults.setWrapText(false);
                for (String adata : wScrp.getImageTagData("a", "href")) {
                    txtResults.appendText(adata + "\n");
                }
            }
            if (choiceBox.getSelectionModel().getSelectedItem().equals("IMG")) {
                txtResults.appendText("----------IMG----------" + "\n");
                txtResults.setWrapText(false);
                for (String adata : wScrp.getImageTagData("img", "src")) {
                    txtResults.appendText(adata + "\n");
                }
            }
            if (choiceBox.getSelectionModel().getSelectedItem().equals("HTML")) {
                txtResults.appendText("----------HTML----------" + "\n");
                txtResults.setWrapText(true);
                for (String adata : wScrp.getSingleHTMLScriptData("<html>", "</html>")) {
                    txtResults.appendText(adata + "\n");
                }
            }
            if (choiceBox.getSelectionModel().getSelectedItem().equals("HEAD")) {
                txtResults.appendText("----------HEAD----------" + "\n");
                txtResults.setWrapText(true);
                for (String adata : wScrp.getSingleHTMLScriptData("<head>", "</head>")) {
                    txtResults.appendText(adata + "\n");
                }
            }
            if (choiceBox.getSelectionModel().getSelectedItem().isEmpty()) {
                txtResults.appendText("Choose your option first/ Set your website" + "\n");
            }
            
        } catch (NullPointerException ex) {
            txtResults.appendText("Choose your option first/ Set your website" + "\n");
        }
        
    }
    
    /**
     *
     * @param webLook
     */
    public void setMainApp(WebLook webLook) {
        this.WebLook = webLook;
    }
    
}
