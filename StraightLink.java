package hw4;
/**
 * @author anoopboyal
 */
import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;

public class StraightLink extends AbstractLink {

	private Point endpointA;
	private Point endpointB;
	private Point endpointC;
	

	public StraightLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}

	public Point getConnectedPoint(Point highPoint) {
		Point connectedpoint = null;
		connectedpoint = endpointB;
		return connectedpoint;
	}
	

	@Override
	public int getNumPaths() {
		
		return 3;
	}

}
