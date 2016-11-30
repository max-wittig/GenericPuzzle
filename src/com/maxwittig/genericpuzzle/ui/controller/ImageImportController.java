package com.maxwittig.genericpuzzle.ui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;

import java.io.File;

public class ImageImportController extends Controller
{
    @FXML private Label imagePickLabel;
    @FXML private Slider puzzlePiecesSlider;
    @FXML private Label puzzlePiecesLabel;
    private int numberOfPuzzlePieces = 2;

    @Override
    protected void initController()
    {
        puzzlePiecesSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                onPuzzlePiecesSliderMoved(newValue.intValue());
            }
        });
    }

    @FXML
    private void onChoseFileButtonClicked()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose Image...");
        File selectedFile = fileChooser.showOpenDialog(stage.getOwner());
        if(selectedFile != null && selectedFile.exists())
        {
            imagePickLabel.setText(selectedFile.getName());
        }
    }

    private void onPuzzlePiecesSliderMoved(int newValue)
    {
        numberOfPuzzlePieces = newValue;
        puzzlePiecesLabel.setText(String.valueOf(newValue));
    }
}
