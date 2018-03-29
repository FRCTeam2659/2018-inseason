package team2659.paths;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class LeftRL4 implements PathContainer {
    public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(270,240,0,0));
        sWaypoints.add(new Waypoint(245,220,5,80));
        sWaypoints.add(new Waypoint(245,127,5,100));
        sWaypoints.add(new Waypoint(230,122,0,60));

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    		return new RigidTransform2d(new Translation2d(270, 240), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
