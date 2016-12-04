package com.maxwittig.genericpuzzle.ui.controller;

import com.maxwittig.genericpuzzle.logic.Board;
import com.maxwittig.genericpuzzle.main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class ImageImportController extends Controller
{
    @FXML
    private Label imagePickLabel;
    @FXML
    private Slider puzzlePiecesSlider;
    @FXML
    private Label puzzlePiecesLabel;
    @FXML private ImageView previewImageView;
    private int numberOfPuzzlePieces;
    private Image selectedImage = null;

    @Override
    protected void initController()
    {
        numberOfPuzzlePieces = (int)puzzlePiecesSlider.getValue();
        puzzlePiecesSlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                int sqrt = (int) Math.sqrt(newValue.intValue());
                if (sqrt * sqrt != newValue.intValue())
                {
                    puzzlePiecesSlider.setValue(oldValue.intValue());
                }
            }
        });

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
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Images", "png", "jpeg"));
        fileChooser.setTitle("Chose Image...");
        File selectedFile = fileChooser.showOpenDialog(stage.getOwner());
        if (selectedFile != null && selectedFile.exists())
        {
            imagePickLabel.setText(selectedFile.getName());
            System.out.println(selectedFile.getPath());
            try
            {
                selectedImage = new Image(selectedFile.toURI().toURL().toString());
                previewImageView.setImage(selectedImage);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    private void onPuzzlePiecesSliderMoved(int newValue)
    {
        numberOfPuzzlePieces = newValue;
        puzzlePiecesLabel.setText(String.valueOf(newValue));
    }

    @FXML
    private void importImage()
    {
        if (selectedImage != null)
        {
            Board board = new Board(selectedImage, numberOfPuzzlePieces);
            ((MainController) parentController).setBoard(board);
            stage.close();
        }
    }
}
