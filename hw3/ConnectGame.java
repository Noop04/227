package hw3;
/**
 * @author anoopboyal
 */
import java.util.ArrayList;
import java.util.Random;
import api.ScoreUpdateListener;
import api.ShowDialogListener;
import api.Tile;

/**
 * Class that models a game.
 */
public class ConnectGame {
	/**
	 * Shows dialog
	 */
    private ShowDialogListener dialogListener;
    /**
     * updates score
     */
    private ScoreUpdateListener scoreListener;
    /**
     * the grids Height
     */
    private int gridHeight;
    /**
     * the grids width
     */
    private int gridWidth;
    /**
     * the minimum tile level
     */
    private int tileMin;
    /**
     * the maximum tile level
     */
    private int tileMax;
    /**
     * a random number
     */
    private Random rnum;
    /**
     * the score
     */
    private long score;
    /**
     * the grid
     */
    private Grid grid;
    /**
     * an arraylist of selected Tiles
     */
    private ArrayList<Tile> selectedTiles;
    /**
     * the first selected Tile
     */
    private Tile firstSelectedTile;
    /**
     * the last selected tile
     */
	private Tile lastSelectedTile;
	
	
	/**
	 * Constructs a new ConnectGame object with given grid dimensions and minimum
	 * and maximum tile levels.
	 * 
	 * @param width  grid width
	 * @param height grid height
	 * @param min    minimum tile level
	 * @param max    maximum tile level
	 * @param rand   random number generator
	 */
	public ConnectGame(int width, int height, int min, int max, Random rand) {
        gridWidth = width;
        gridHeight = height;
        tileMin = min;
        tileMax = max;
        rnum = rand;
        selectedTiles = new ArrayList<>();
        grid = new Grid(width,height);
        

	}

	/**
	 * Gets a random tile with level between minimum tile level inclusive and
	 * maximum tile level exclusive. For example, if minimum is 1 and maximum is 4,
	 * the random tile can be either 1, 2, or 3.
	 * <p>
	 * DO NOT RETURN TILES WITH MAXIMUM LEVEL
	 * 
	 * @return a tile with random level between minimum inclusive and maximum
	 *         exclusive
	 */
	public Tile getRandomTile() {
        int level = rnum.nextInt(tileMax - tileMin) + tileMin;
        return new Tile(level);
	}
	

	/**
	 * Regenerates the grid with all random tiles produced by getRandomTile().
	 */
	public void radomizeTiles() {
		for (int row = 0; row < gridWidth; row++) {
			for (int col = 0; col < gridHeight; col++) {
				Tile tile = getRandomTile();
				grid.setTile(tile,row,col);
			}
		}
	}


	/**
	 * Determines if two tiles are adjacent to each other. The may be next to each
	 * other horizontally, vertically, or diagonally.
	 * 
	 * @param t1 one of the two tiles
	 * @param t2 one of the two tiles
	 * @return true if they are next to each other horizontally, vertically, or
	 *         diagonally on the grid, false otherwise
	 */
	public boolean isAdjacent(Tile t1, Tile t2) {
		 int xDiff = Math.abs(t1.getX() - t2.getX()); 
		 int yDiff = Math.abs(t1.getY() - t2.getY()); 
		return (xDiff <= 1 && yDiff <= 1 && xDiff + yDiff != 0);
	}

	/**
	 * Indicates the user is trying to select (clicked on) a tile to start a new
	 * selection of tiles.
	 * <p>
	 * If a selection of tiles is already in progress, the method should do nothing
	 * and return false.
	 * <p>
	 * If a selection is not already in progress (this is the first tile selected),
	 * then start a new selection of tiles and return true.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return true if this is the first tile selected, otherwise false
	 */
	public boolean tryFirstSelect(int x, int y) {
		if (selectedTiles.size() == 0) {
			selectedTiles.add(grid.getTile(x, y));
			grid.getTile(x, y).setSelect(true);
			return true;
		} else {
			return false;
		}
	}
	
	

	/**
	 * Indicates the user is trying to select (mouse over) a tile to add to the
	 * selected sequence of tiles. The rules of a sequence of tiles are:
	 * 
	 * <pre>
	 * 1. The first two tiles must have the same level.
	 * 2. After the first two, each tile must have the same level or one greater than the level of the previous tile.
	 * </pre>
	 * 
	 * For example, given the sequence: 1, 1, 2, 2, 2, 3. The next selected tile
	 * could be a 3 or a 4. If the use tries to select an invalid tile, the method
	 * should do nothing. If the user selects a valid tile, the tile should be added
	 * to the list of selected tiles.
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 */
	public void tryContinueSelect(int x, int y) {
		
		Tile continueTile = grid.getTile(x,y);
		
		if (selectedTiles.size() > 0) {
			lastSelectedTile = selectedTiles.get(selectedTiles.size() - 1);
		}
		if (selectedTiles.size() == 1) {	 	
			if (lastSelectedTile.getLevel() == continueTile.getLevel()) {
				if (isAdjacent(continueTile,lastSelectedTile)) {
						selectedTiles.add(continueTile);
						continueTile.setSelect(true);
				}
			} 
		}

		if (selectedTiles.size() > 1) {
			if (continueTile.getLevel() == lastSelectedTile.getLevel() + 1 
					|| continueTile.getLevel() == lastSelectedTile.getLevel()) {
				if (isAdjacent(continueTile,lastSelectedTile)) {
					selectedTiles.add(continueTile);
					continueTile.setSelect(true);
			}		
		}
	}	
		
		if (selectedTiles.size() > 1) {
			Tile secondLastSelect = selectedTiles.get(selectedTiles.size( )- 2);
			if (continueTile == secondLastSelect) {
				unselect(lastSelectedTile.getX(),lastSelectedTile.getY());	
			}
		}
	}
	/**
	 * Indicates the user is trying to finish selecting (click on) a sequence of
	 * tiles. If the method is not called for the last selected tile, it should do
	 * nothing and return false. Otherwise it should do the following:
	 * 
	 * <pre>
	 * 1. When the selection contains only 1 tile reset the selection and make sure all tiles selected is set to false.
	 * 2. When the selection contains more than one block:
	 *     a. Upgrade the last selected tiles with upgradeLastSelectedTile().
	 *     b. Drop all other selected tiles with dropSelected().
	 *     c. Reset the selection and make sure all tiles selected is set to false.
	 * </pre>
	 * 
	 * @param x the column of the tile selected
	 * @param y the row of the tile selected
	 * @return return false if the tile was not selected, otherwise return true
	 */
	public boolean tryFinishSelection(int x, int y) {
		if (selectedTiles.size()>=1) {
			Tile lastSelect = selectedTiles.get(selectedTiles.size()-1);
			if (selectedTiles.size() == 1) {
				unselect(x,y);
			}
			else {
				for (int i = 0; i < selectedTiles.size(); i++) {
					score += (long) selectedTiles.get(i).getValue();
				}
				scoreListener.updateScore(score);
				upgradeLastSelectedTile();
				dropSelected();
			}
			return true;
		}
		return false;
	}

	/**
	 * Increases the level of the last selected tile by 1 and removes that tile from
	 * the list of selected tiles. The tile itself should be set to unselected.
	 * <p>
	 * If the upgrade results in a tile that is greater than the current maximum
	 * tile level, both the minimum and maximum tile level are increased by 1. A
	 * message dialog should also be displayed with the message "New block 32,
	 * removing blocks 2". Not that the message shows tile values and not levels.
	 * Display a message is performed with dialogListener.showDialog("Hello,
	 * World!");
	 */
	public void upgradeLastSelectedTile() {
		
		Tile lastSelectedTile = selectedTiles.get(selectedTiles.size() - 1);
		
		// increases the level of the last selected tile by 1 and removes that tile from the list of selected tiles
		lastSelectedTile.setLevel(lastSelectedTile.getLevel() + 1);
		
//		If the upgrade results in a tile that is greater than the current maximum
//		tile level, both the minimum and maximum tile level are increased by 1
		if (lastSelectedTile.getLevel() > tileMax) {
			setMinTileLevel(tileMin + 1);
			setMaxTileLevel(lastSelectedTile.getLevel());
		}
		selectedTiles.clear();
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j <gridHeight; j++) {
				Tile changeUnSelect = grid.getTile(i, j);
				changeUnSelect.setSelect(false);
			}
		}
		dialogListener.showDialog("New block " + lastSelectedTile.getValue() + ", removing Blocks " + Math.pow(2, tileMin));
				
		
	}

	/**
	 * Gets the selected tiles in the form of an array. This does not mean selected
	 * tiles must be stored in this class as a array.
	 * 
	 * @return the selected tiles in the form of an array
	 */
	public Tile[] getSelectedAsArray() {
		
		   Tile[] selected = new Tile[selectedTiles.size()];
		    for (int i = 0; i < selectedTiles.size(); i++) {
		        selected[i] = selectedTiles.get(i);
		    }
		    return selected;
	}

	/**
	 * Removes all tiles of a particular level from the grid. When a tile is
	 * removed, the tiles above it drop down one spot and a new random tile is
	 * placed at the top of the grid.
	 * 
	 * @param level the level of tile to remove
	 */
	public void dropLevel(int level) {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				Tile tileDrop = grid.getTile(i,j);
				if (tileDrop.getLevel() == level) {
					for (int x = j; x < gridHeight; x++) {
						try {
							Tile upgradeTile = grid.getTile(tileDrop.getX(), x - 1);
							grid.setTile(upgradeTile, tileDrop.getX(), x);
						}
						
						catch (ArrayIndexOutOfBoundsException e) {
							grid.setTile(getRandomTile(), tileDrop.getX(), x);
							break;
								
						}
					}
				}
			}
		}
	}

	/**
	 * Removes all selected tiles from the grid. When a tile is removed, the tiles
	 * above it drop down one spot and a new random tile is placed at the top of the
	 * grid.
	 */
	public void dropSelected() {
		for (int i = 0; i < selectedTiles.size(); i++) {
			int x = selectedTiles.get(i).getX();
			int y = selectedTiles.get(i).getY();
			Tile tileDrop = grid.getTile(x, y);
			for (int j = y; j < gridHeight; j--) {
				try {
					Tile tileRemove = grid.getTile(tileDrop.getX(), j - 1);
					grid.setTile(tileRemove, tileDrop.getX(), j);
				}
				catch (ArrayIndexOutOfBoundsException e) {
					grid.setTile(getRandomTile(), tileDrop.getX(), j);
					break;
				}
			}
		}
	}

			

	/**
	 * Remove the tile from the selected tiles.
	 * 
	 * @param x column of the tile
	 * @param y row of the tile
	 */
	public void unselect(int x, int y) {
		Tile removeTile = grid.getTile(x,y);
		selectedTiles.remove(removeTile);
		removeTile.setSelect(false);
		
	}

	/**
	 * Gets the player's score.
	 * 
	 * @return the score
	 */
	public long getScore() {
		return score;
	}

	/**
	 * Gets the game grid.
	 * 
	 * @return the grid
	 */
	public Grid getGrid() {
		return grid; 
	}

	/**
	 * Gets the minimum tile level.
	 * 
	 * @return the minimum tile level
	 */
	public int getMinTileLevel() {
		return tileMin;
	}

	/**
	 * Gets the maximum tile level.
	 * 
	 * @return the maximum tile level
	 */
	public int getMaxTileLevel() {
		return tileMax;
	}

	/**
	 * Sets the player's score.
	 * 
	 * @param score number of points
	 */
	public void setScore(long score) {
		this.score = score;
	}

	/**
	 * Sets the game's grid.
	 * 
	 * @param grid game's grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
		
	}

	/**
	 * Sets the minimum tile level.
	 * 
	 * @param minTileLevel the lowest level tile
	 */
	public void setMinTileLevel(int minTileLevel) {
		tileMin = minTileLevel;		
	}

	/**
	 * Sets the maximum tile level.
	 * 
	 * @param maxTileLevel the highest level tile
	 */
	public void setMaxTileLevel(int maxTileLevel) {
		tileMax = maxTileLevel;
	}

	/**
	 * Sets callback listeners for game events.
	 * 
	 * @param dialogListener listener for creating a user dialog
	 * @param scoreListener  listener for updating the player's score
	 */
	public void setListeners(ShowDialogListener dialogListener, ScoreUpdateListener scoreListener) {
		this.dialogListener = dialogListener;
		this.scoreListener = scoreListener;
	}

	/**
	 * Save the game to the given file path.
	 * 
	 * @param filePath location of file to save
	 */
	public void save(String filePath) {
		GameFileUtil.save(filePath, this);
	}

	/**
	 * Load the game from the given file path
	 * 
	 * @param filePath location of file to load
	 */
	public void load(String filePath) {
		GameFileUtil.load(filePath, this);
	}
}
