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
public class PathFactory {
    public static PathDescriptor getPathDescriptor(int iteration) {
        if (iteration == 0) {
            return new PathDescriptor();
        }
        PathDescriptor prev = getPathDescriptor(iteration - 1);
        return new PathDescriptor(prev);
    }
}
