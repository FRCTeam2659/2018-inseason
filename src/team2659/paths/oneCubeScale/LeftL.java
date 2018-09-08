package team2659.paths.oneCubeScale;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class LeftL implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,270,0,0));
        sWaypoints.add(new Waypoint(120,270,25,150));
        sWaypoints.add(new Waypoint(210,295,15,100));
        sWaypoints.add(new Waypoint(320,295,8,75));
        sWaypoints.add(new Waypoint(325,275,0,60));
        //sWaypoints.add(new Waypoint(200,270,30,150));
        //sWaypoints.add(new Waypoint(277,240,0,80));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 270), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
