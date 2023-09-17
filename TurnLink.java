package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;
/**
 * @author anoopboyal
 */
public class TurnLink extends AbstractLink  {

	private Point endpointA;
	private Point endpointB;
	private Point endpointC;

	public TurnLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}

	public Point getConnectedPoint(Point highPoint) {
		Point connectedPoint = null;

	    if (highPoint == endpointA) {
	        connectedPoint = endpointC;
	    } else if (highPoint == endpointB) {
	        connectedPoint = endpointA;
	    } else if (highPoint == endpointC) {
	        connectedPoint = endpointA;
	    }
	    
		return connectedPoint;
	}



	@Override
	public void trainEnteredCrossing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void trainExitedCrossing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumPaths() {
		// TODO Auto-generated method stub
		return 3;
	}

}
