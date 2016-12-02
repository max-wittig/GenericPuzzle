package com.maxwittig.genericpuzzle.logic;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class PuzzlePiece
{
    private int number;
    private Image image;
    private Point2D position;

    public PuzzlePiece(int number, Image image, Point2D position)
    {
        this.number = number;
        this.position = position;
        this.image = image;
    }

    public int getNumber()
    {
        return number;
    }

    public Image getImage()
    {
        return image;
    }

    public Point2D getPosition()
    {
        return position;
    }

    public void setPosition(Point2D position)
    {
        this.position = position;
    }
}
