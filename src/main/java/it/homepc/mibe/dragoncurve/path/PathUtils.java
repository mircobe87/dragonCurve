/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.homepc.mibe.dragoncurve.path;

/**
 *
 * @author mircobe87
 */
public class PathUtils {
    public static int getPathLength(int iteration) {
        if (iteration == 0) {
            return 1;
        }
        return 1 + 2 * getPathLength(iteration - 1);
    }
    
    public static int initialRotation(int iteration) {
        int rotation = ((iteration % 8) * 45 + 45) % 360;
        return rotation > 180 ? rotation - 360 : rotation;
    }
}
