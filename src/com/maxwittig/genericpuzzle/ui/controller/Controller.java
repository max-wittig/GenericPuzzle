package com.maxwittig.genericpuzzle.ui.controller;


import javafx.stage.Stage;

public abstract class Controller
{
    protected Stage stage;

    public void init(Stage stage)
    {
        this.stage = stage;
        initController();
    }

    protected abstract void initController();
}
