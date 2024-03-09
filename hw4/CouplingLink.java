package hw4;
/**
 * @author anoopboyal
 */
import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;


public class CouplingLink extends AbstractLink {

	
	/**
	 * parameter of coupling link contructer 
	 */
	private Point endpoint1;
	
	/**
	 * parameter of coupling link contructer 
	 */
	private Point endpoint2;
	/**
	 * Models a link between exactly two paths. 
	 * When the train reaches the endpoint of one of the paths 
	 * it passes to the endpoint of the other path next.
	 */
	public CouplingLink(Point endpoint1, Point endpoint2) {
		this.endpoint1 = endpoint1;
		this.endpoint2 = endpoint2;
	}

	@Override
	public int getNumPaths() {
		return 2;
	}
	
	/**
	 * the connected point
	 */
	@Override
	public Point getConnectedPoint(Point highPoint) {
		Point connectedPoint = null;
		if (endpoint1 == highPoint) {
			connectedPoint = endpoint2;	
		} else if (endpoint2 == highPoint) {
			connectedPoint = endpoint1;
		}
		else {
			connectedPoint = null;
		}
		
		return connectedPoint;
	}


}
