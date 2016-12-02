package com.maxwittig.genericpuzzle.logic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Board
{
	private Image image;
	private int pieces;
	private ArrayList<ArrayList<PuzzlePiece>> completePuzzle;
	private ArrayList<ArrayList<PuzzlePiece>> mixedPuzzle;

	public Board(Image image, int pieces)
	{
		this.image = image;
		this.pieces = pieces;
		completePuzzle = new ArrayList<>();
		mixedPuzzle = new ArrayList<>();
		createBoard();
	}

	private PuzzleSize getCalculatedPieceSize()
	{
		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		return new PuzzleSize(imageWidth / (Math.floor(Math.sqrt(pieces))), imageHeight / (Math.floor(Math.sqrt(pieces))));
	}

	private Image getSubImage(int width, int height, PuzzleSize puzzleSize)
	{
		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
		BufferedImage subImage = bufferedImage.getSubimage(width, height, (int)Math.floor(puzzleSize.getWidth()), (int)Math.floor(puzzleSize.getHeight()));
		return SwingFXUtils.toFXImage(subImage, null);
	}

	/**
	 * creates puzzlePieces
	 */
	private void createBoard()
	{
		PuzzleSize puzzleSize = getCalculatedPieceSize();

		int number = -1;
		int x = 0;
		int y = 0;

		for(int h = 0; h < Math.floor(Math.sqrt(pieces)); h++)
		{
			y = (int)(h * Math.floor(puzzleSize.getHeight()));
            ArrayList<PuzzlePiece> row = new ArrayList<>();
			for(int w = 0; w < Math.floor(Math.sqrt(pieces)); w++)
			{
				x = (int)(w * Math.floor(puzzleSize.getWidth()));
				number++;
                row.add(new PuzzlePiece(number, getSubImage(x, y, puzzleSize),new Point2D(w, h)));
			}
            completePuzzle.add(row);
		}
	}

	public int getMaxHeight()
    {
        return completePuzzle.size();
    }

    public int getMaxWidth()
    {
        return completePuzzle.get(0).size();
    }

    public ArrayList<ArrayList<PuzzlePiece>> getCompletePuzzle()
    {
        return completePuzzle;
    }
}