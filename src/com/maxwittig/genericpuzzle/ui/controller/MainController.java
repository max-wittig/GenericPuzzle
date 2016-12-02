package com.maxwittig.genericpuzzle.ui.controller;


import com.maxwittig.genericpuzzle.logic.Board;
import com.maxwittig.genericpuzzle.logic.PuzzlePiece;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController extends Controller
{

    private Board board;
    @FXML private GridPane gridPane;

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
        this.board = board;
        showBoard();
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
        gridPane.setGridLinesVisible(true);
    }

    private void showBoard()
    {
        gridPane.getChildren().clear();
        adjustGridPane();
        for(ArrayList<PuzzlePiece> row : board.getCompletePuzzle())
        {
            for(PuzzlePiece puzzlePiece : row)
            {
                StackPane stackPane = new StackPane();
                ImageView imageView = new ImageView(puzzlePiece.getImage());
                stackPane.setStyle("-fx-border-color: black;");
                stackPane.getChildren().add(imageView);
                System.out.println((int)puzzlePiece.getPosition().getX());
                gridPane.add(stackPane, (int)puzzlePiece.getPosition().getX(), (int)puzzlePiece.getPosition().getY());
            }
        }
        adjustGridPane();
    }
}