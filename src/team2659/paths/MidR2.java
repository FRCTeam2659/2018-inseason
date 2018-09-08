package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class MidR2 implements PathContainer {
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(130,100,0,0));
        sWaypoints.add(new Waypoint(100,110,5,80));
        sWaypoints.add(new Waypoint(75,156,5,80));
        sWaypoints.add(new Waypoint(60,156,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(127, 103), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return true; 
    }
}
