package com.maxwittig.genericpuzzle.main;

import com.maxwittig.genericpuzzle.logic.Board;
import com.maxwittig.genericpuzzle.ui.controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
    private final boolean debug = false;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("com/maxwittig/genericpuzzle/ui/fxml/main_controller.fxml"));
            Parent root = (Parent)loader.load();

            Scene scene = new Scene(root, 800, 600);

            ((MainController)loader.getController()).init(primaryStage, null);

            primaryStage.setTitle("GenericPuzzle");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.setMinWidth(600);
            primaryStage.setMinHeight(400);

            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
            {
                @Override
                public void handle(WindowEvent event)
                {
                    System.exit(0);
                }
            });

            primaryStage.show();

            //debug
            if(debug)
            {
                Board board = new Board(new Image("https://pictureis24-a.akamaihd.net/pic/orig03/N/533/423/262/533423262-0.jpg/ORIG/legacy_thumbnail/519x380/format/jpg/quality/50"), 9);
                ((MainController) loader.getController()).setBoard(board);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
