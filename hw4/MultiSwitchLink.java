package hw4;
/**
 * @author anoopboyal
 */
import api.Crossable;
import api.Path;
import api.Point;
import api.PointPair;
import api.PositionVector;
/**
 * @author anoopboyal
 */
public class MultiSwitchLink extends AbstractLink {

	private PointPair[] pairs;


	public MultiSwitchLink(PointPair[] pairs) {
		this.pairs = pairs;
	}

	public void switchConnection(PointPair pointPair, int index) {
		if (trainCrossing()) {
	        // train is currently in the crossing, cannot modify connection
	        return;
	    }
	    if (index >= 0 && index < pairs.length) {
	        for (int i = 0; i < pairs.length; i++) {
	            if (i == index) {
	                pairs[i] = pointPair;
	                break;
	            }
	        }
	    }
	}
		
	

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
