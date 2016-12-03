package com.maxwittig.genericpuzzle.ui.controller;


import com.maxwittig.genericpuzzle.logic.Board;
import com.maxwittig.genericpuzzle.logic.PuzzlePiece;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController extends Controller
{

    private Board board;
    @FXML private GridPane gridPane;
    private PuzzlePiece currentlySelectedPiece = null;

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
            imageImportStage.setResizable(true);
            imageImportStage.setScene(scene);
            imageImportStage.show();
            ((ImageImportController)loader.getController()).init(stage, this);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Board getBoard()
    {
        return board;
    }

    public void setBoard(Board board)
    {
        //debug
        this.board = board;
        refreshBoard();
    }

    private void adjustGridPane()
    {
        gridPane.getColumnConstraints().clear();
        double xPercentage = 1.0 / board.getMaxWidth();
        for (int i = 0; i < board.getMaxWidth(); i++)
        {
            ColumnConstraints c = new ColumnConstraints();
            c.setPercentWidth(xPercentage * 100);
            gridPane.getColumnConstraints().add(c);
        }

        gridPane.getRowConstraints().clear();
        double yPercentage = 1.0 / board.getMaxHeight();
        for (int i = 0; i < board.getMaxHeight(); i++)
        {
            RowConstraints c = new RowConstraints();
            c.setPercentHeight(yPercentage * 100);
            gridPane.getRowConstraints().add(c);
        }
        //gridPane.setGridLinesVisible(true);
    }

    private Background getBackGroundFromImage(Image image, double width, double height)
    {
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,new BackgroundSize(width, height, true, true, false, false));
        Background background = new Background(backgroundImage);
        return background;
    }

    private void refreshBoard()
    {
        gridPane.getChildren().clear();
        for(ArrayList<PuzzlePiece> row : board.getMixedPuzzle())
        {
            for(PuzzlePiece puzzlePiece : row)
            {
                StackPane stackPane = new StackPane();
                stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event)
                    {
                        onImageViewClicked(puzzlePiece);
                        stackPane.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    }
                });
                stackPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
                gridPane.add(stackPane, (int)puzzlePiece.getPosition().getX(), (int)puzzlePiece.getPosition().getY());
                stackPane.setBackground(getBackGroundFromImage(puzzlePiece.getImage(), 0.99, 0.99));

            }
        }
        adjustGridPane();
    }

    private void showWinAlert()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You won!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulations!");
        alert.show();
    }

    private void onImageViewClicked(PuzzlePiece puzzlePiece)
    {
        if(currentlySelectedPiece == null)
        {
            currentlySelectedPiece = puzzlePiece;
        }
        else
        {
            if(board.swapPieces(currentlySelectedPiece, puzzlePiece))
            {
                showWinAlert();
            }
            currentlySelectedPiece = null;
            refreshBoard();
        }
    }

    @FXML
    private void onReshuffleClicked()
    {
        if(board != null)
        {
            board.shuffle();
            refreshBoard();
        }
    }

    @FXML
    private void onAboutItemClicked()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Made by Max Wittig in 2016 with the help of deadlocker.");
        alert.show();
    }
}