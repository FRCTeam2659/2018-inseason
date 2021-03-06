package team2659.paths.oneCubeScale;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class MidL implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,150,0,0));
        sWaypoints.add(new Waypoint(60,150,15,120));
        sWaypoints.add(new Waypoint(125,262,25,140));
        sWaypoints.add(new Waypoint(240,262,8,110));
        sWaypoints.add(new Waypoint(272,252,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 150), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
