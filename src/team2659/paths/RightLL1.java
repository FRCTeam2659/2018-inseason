package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class RightLL1 implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,55,0,0));
        sWaypoints.add(new Waypoint(130,55,8,150));
        sWaypoints.add(new Waypoint(235,45,30,100));
        sWaypoints.add(new Waypoint(247,240,7,90));
        sWaypoints.add(new Waypoint(277,242,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 55), Rotation2d.fromDegrees(180.0)); 
    }

    public boolean isReversed() {
        return true; 
    }
}
