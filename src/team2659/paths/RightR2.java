package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class RightR2 implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(166,80,0,0));
        sWaypoints.add(new Waypoint(175,48,15,80));
        sWaypoints.add(new Waypoint(228,78,5,60));
        sWaypoints.add(new Waypoint(260,93,0,60));
 
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 55), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return true; 
    }
}
