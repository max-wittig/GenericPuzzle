package com.maxwittig.genericpuzzle.ui.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController extends Controller
{
    @Override
    protected void initController()
    {

    }

    @FXML
    private void onImageOpenClicked()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/maxwittig/genericpuzzle/ui/fxml/open_image_controller.fxml"));
            Parent root = (Parent)loader.load();

            Scene scene = new Scene(root);
            Stage imageImportStage = new Stage();
            imageImportStage.setResizable(false);
            imageImportStage.setScene(scene);
            imageImportStage.show();
            ((ImageImportController)loader.getController()).init(stage);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
