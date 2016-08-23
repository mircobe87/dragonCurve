/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.homepc.mibe.dragoncurve;

import it.homepc.mibe.dragoncurve.path.PathDescriptor;
import it.homepc.mibe.dragoncurve.path.PathFactory;
import it.homepc.mibe.dragoncurve.path.PathUtils;

/**
 *
 * @author mircobe87
 */
public class PrintPath {
    
    public static void main(String[] args) {
        PathDescriptor pd;
        int maxIter = 6;

        System.out.println("+-----------+------------------------------------------------------------------+---------+----------+");
        System.out.printf("| %-9s | %-64s | %-7s | %-8s |\n", "iteration", "path", "lentgth", "rotation");
        System.out.println("+-----------+------------------------------------------------------------------+---------+----------+");
        for (int i = 0; i < maxIter; i++) {
            pd = PathFactory.getPathDescriptor(i);
            System.out.printf("| %9d | %-64s | %7d | %7d° |\n", i, pd.toString(), PathUtils.getPathLength(i), PathUtils.initialRotation(i));
        }
        System.out.println("+-----------+------------------------------------------------------------------+---------+----------+");
    }
}
