package hw3;

import api.Tile;
/**
 * @author anoopboyal
 */
/**
 * Represents the game's grid.
 */
public class Grid {
	
	/**
	 * the Grid
	 */
	private Tile[][] grid;
	/**
	 * the grids width
	 */
	private int gridWidth;
	/**
	 * the grids Height
	 */
	private int gridHeight;


	/**
	 * Creates a new grid.
	 * 
	 * @param width  number of columns
	 * @param height number of rows
	 */
	public Grid(int width, int height) {
		grid = new Tile[width][height];

	        gridWidth = width;
	        gridHeight = height;
	       
	}

	/**
	 * Get the grid's width.
	 * 
	 * @return width
	 */
	public int getWidth() {
		return gridWidth;
	}

	/**
	 * Get the grid's height.
	 * 
	 * @return height
	 */
	public int getHeight() {
		return gridHeight;
	}

	/**
	 * Gets the tile for the given column and row.
	 * 
	 * @param x the column
	 * @param y the row
	 * @return
	 */
	public Tile getTile(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Sets the tile for the given column and row. Calls tile.setLocation().
	 * 
	 * @param tile the tile to set
	 * @param x    the column
	 * @param y    the row
	 */
	public void setTile(Tile tile, int x, int y) {
        grid[x][y] = tile;
        tile.setLocation(x, y);
	}
	
	
	@Override
	public String toString() {
		String str = "";
		for (int y=0; y<getHeight(); y++) {
			if (y > 0) {
				str += "\n";
			}
			str += "[";
			for (int x=0; x<getWidth(); x++) {
				if (x > 0) {
					str += ",";
				}
				str += getTile(x, y);
			}
			str += "]";
		}
		return str;
	}
}
