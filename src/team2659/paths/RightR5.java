package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class RightR5 implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(215,95,0,0));
        sWaypoints.add(new Waypoint(230,96,0,50));
        sWaypoints.add(new Waypoint(240,123,0,80));
        sWaypoints.add(new Waypoint(280,123,0,150));
        //sWaypoints.add(new Waypoint(258,93,0,60));
        //sWaypoints.add(new Waypoint(258,70,0,60));
        
        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 55), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return true; 
    }
}
