package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class LeftLL1 implements PathContainer {
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,270,0,0));
        sWaypoints.add(new Waypoint(200,270,30,130));
        sWaypoints.add(new Waypoint(277,241,0,70));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 270), Rotation2d.fromDegrees(180.0)); 
    }

    public boolean isReversed() {
        return true; 
    }
}
