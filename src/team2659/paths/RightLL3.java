package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class RightLL3 implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(292,298,0,0));
        sWaypoints.add(new Waypoint(270,240,10,50));
        sWaypoints.add(new Waypoint(224,235,0,40));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(277, 242), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
