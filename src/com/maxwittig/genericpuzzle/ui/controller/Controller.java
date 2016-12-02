package com.maxwittig.genericpuzzle.ui.controller;


import javafx.stage.Stage;

public abstract class Controller
{
    protected Stage stage;
    protected Controller parentController;

    public void init(Stage stage, Controller parentController)
    {
        this.stage = stage;
        this.parentController = parentController;
        initController();
    }

    protected abstract void initController();
}
