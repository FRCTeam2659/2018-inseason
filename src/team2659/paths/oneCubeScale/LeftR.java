package team2659.paths.oneCubeScale;

import java.util.ArrayList;

import team2659.util.control.Path;
import team2659.util.control.PathBuilder;
import team2659.util.control.PathBuilder.Waypoint;
import team2659.util.math.RigidTransform2d;
import team2659.util.math.Rotation2d;
import team2659.util.math.Translation2d;

import team2659.util.control.PathContainer;

public class LeftR implements PathContainer {
	public Path buildPath() {
        ArrayList<Waypoint> sWaypoints = new ArrayList<Waypoint>();
        sWaypoints.add(new Waypoint(25,270,0,0));
        sWaypoints.add(new Waypoint(130,270,8,150));
        sWaypoints.add(new Waypoint(238,275,25,80));
        sWaypoints.add(new Waypoint(245,55,15,80));
        sWaypoints.add(new Waypoint(277,30,8,50));
        sWaypoints.add(new Waypoint(294,50,0,50));
        
       /* sWaypoints.add(new Waypoint(230,270,30,150));
        sWaypoints.add(new Waypoint(245,90,15,120));
        sWaypoints.add(new Waypoint(278,85,0,60));*/

        return PathBuilder.buildPathFromWaypoints(sWaypoints);
    }
    
    public RigidTransform2d getStartPose() {
    	return new RigidTransform2d(new Translation2d(25, 270), Rotation2d.fromDegrees(0.0)); 
    }

    public boolean isReversed() {
        return false; 
    }
}
