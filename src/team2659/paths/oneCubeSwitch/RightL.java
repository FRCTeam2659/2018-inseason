package team2659.paths.oneCubeSwitch;

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
        sWaypoints.add(new Waypoint(55,55,8,130));
        sWaypoints.add(new Waypoint(80,200,10,80));
        sWaypoints.add(new Waypoint(135,220,0,80));
        /*sWaypoints.add(new Waypoint(25,55,0,0));
        sWaypoints.add(new Waypoint(240,55,25,160));
        sWaypoints.add(new Waypoint(245,195,8,50));
        sWaypoints.add(new Waypoint(225,205,0,70));*/

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 55), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
