package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;
/**
 * @author anoopboyal
 */
public class DeadEndLink extends AbstractLink {

	@Override
	public Point getConnectedPoint(Point point) {
		return null;
	}



	@Override
	public int getNumPaths() {
		return 1;
	}
	/**
	 * because the connected point is null
	 * we cannot shift any points
	 */
	public void shiftPoints(PositionVector positionVector) {
	
	}

}
