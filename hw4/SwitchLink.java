package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;
/**
 * @author anoopboyal
 */
public class SwitchLink extends AbstractLink {

	private boolean turn;
	private Point endpointA;
	private Point endpointB;
	private Point endpointC;

	public SwitchLink(Point endpointA, Point endpointB, Point endpointC) {
		this.endpointA = endpointA;
		this.endpointB = endpointB;
		this.endpointC = endpointC;
	}

	public Point getConnectedPoint(Point point) {
		Point connectedpoint = null;
		if (turn && point == endpointA ) {
			connectedpoint = endpointC;
		} else if (!turn && point == endpointA) {
			connectedpoint = endpointB;
		} else if (point == endpointB) {
			connectedpoint = endpointA;
		} else if (point == endpointC) {
			connectedpoint = endpointC;
		}
		return connectedpoint;
	}

	public void setTurn(boolean b) {
		turn = b;
		
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
		return 0;
	}

}
