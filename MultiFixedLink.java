package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PointPair;
import api.PositionVector;
/**
 * @author anoopboyal
 */
public class MultiFixedLink extends AbstractLink {
	/**
	 * globalizes the parameter
	 */
	private PointPair[] pairs;

	public MultiFixedLink(PointPair[] pairs) {
		this.pairs = pairs;
	}
	/**
	 * makes a loop that goes into the pair and finds what point is point
	 */
	public Point getConnectedPoint(Point point) {

	    for (PointPair pair : pairs) {
	        if (pair.getPointA() == point) {
	            return pair.getPointB();
	        } else if (pair.getPointB() == point) {
	            return pair.getPointA();
	        }
	    }
	    return null;
	}





	@Override
	public int getNumPaths() {

		return 6;
	}

}
