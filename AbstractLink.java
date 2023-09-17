package hw4;
/**
 * @author anoopboyal
 */
import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;
/**
 * @author anoopboyal
 */

	/**
	 * 
	 * This class is like a parent class for the rest of the classes
	 * because they all extend from this class this class implements crossable 
	 * this way I dont need to duplicate the same code because I dont implement crossable
	 *
	 */
public abstract class AbstractLink implements Crossable {
/**
 * this is a boolean that shows if the train has entered the crossing
 */
	private boolean enteredCrossing;

	/**
	 * this method is to shift the points to connect to the path
	 */
	@Override
	public void shiftPoints(PositionVector positionVector) {
		
		Point pointB = positionVector.getPointB();
		Point pointA = getConnectedPoint(pointB);
		
		
		Path path = pointA.getPath();
		positionVector.setPointA(pointA);
		
		if (pointA.getPointIndex() == 0) {
			positionVector.setPointB(path.getPointByIndex(1));
		} else {
			positionVector.setPointB(path.getPointByIndex(pointA.getPointIndex() - 1));
		}
		
		
		
	}
	/**
	 * this method gets the point and is different in each class
	 */
	@Override
	public abstract Point getConnectedPoint(Point point);
	/**
	 * the train entered crossing
	 */
	@Override
	public void trainEnteredCrossing() {
		enteredCrossing = true;
		
	}
	/**
	 * the train EXITED crossing
	 */
	@Override
	public void trainExitedCrossing() {
		enteredCrossing = false;
		
	}
	/**
	 * this method returns the boolean if train is crossing
	 * @return
	 */
	protected boolean trainCrossing() {
		return enteredCrossing;
	}
	/**
	 * the number of paths
	 */
	@Override
	public abstract int getNumPaths();

}
