/*
 * This is RootLayout Controller (Menu Bar)
 */
package com.weblook.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 * @since 04.03.2018
 * @author Jakub Mazur
 */
public class RootLayoutController {

    @FXML
    private MenuItem menuBtnClose;

    @FXML
    private void initialize() {

        menuBtnClose.setOnAction((event) -> {
            Platform.exit();
        });

    }

}
