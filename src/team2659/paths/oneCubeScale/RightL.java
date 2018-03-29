package team2659.paths.oneCubeScale;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class RightL implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,55,0,0));
        sWaypoints.add(new Waypoint(130,55,8,150));
        sWaypoints.add(new Waypoint(248,50,20,70));
        sWaypoints.add(new Waypoint(250,270,15,100));
        sWaypoints.add(new Waypoint(275,295,8,60));
        sWaypoints.add(new Waypoint(300,274,0,50));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 55), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
