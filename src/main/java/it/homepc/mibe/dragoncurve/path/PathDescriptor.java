/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.homepc.mibe.dragoncurve.path;

import java.util.Vector;

/**
 *
 * @author mircobe87
 */
public class PathDescriptor {
    private Vector<Boolean> path;
    private int iteration;
    
    protected PathDescriptor() {
        path = new Vector<>();
        path.add(Boolean.TRUE);
        iteration = 0;
    }
    
    protected PathDescriptor(PathDescriptor prev) {
        PathDescriptor next = prev.reverse().not();
        this.path = new Vector<>(prev.path);
        this.path.add(Boolean.TRUE);
        this.path.addAll(next.path);
        this.iteration = prev.iteration + 1;
    }
    
    private PathDescriptor not() {
        PathDescriptor retval = new PathDescriptor();
        retval.path.clear();

        for(Boolean b : this.path) {
            retval.path.add(!b);
        }
        return retval;
    }
    
    private PathDescriptor reverse() {
        PathDescriptor retval = new PathDescriptor();
        retval.path.clear();
        
        for(int i=this.path.size()-1; i>-1; i--) {
            retval.path.add(this.path.get(i));
        }
        
        return retval;
    }
    
    public String toString() {
        String s = "";
        for(Boolean b : this.path) {
            s += (b) ? "1" : "0";
        }
        return s;
    }
    
    public int getIteration() {
        return this.iteration;
    }
    
    public boolean[] toBooleanArray() {
        int size = this.path.size();
        boolean[] ba = new boolean[size];
        for(int i=0; i<size; i++) {
            ba[i] = this.path.get(i);
        }
        return ba;
    }
    
    public String[] toStringArray() {
        int size = this.path.size();
        String[] ba = new String[size];
        for(int i=0; i<size; i++) {
            ba[i] = this.path.get(i) ? "1" : "0";
        }
        return ba;
    }
    
    public int[] toIntArray() {
        int size = this.path.size();
        int[] ba = new int[size];
        for(int i=0; i<size; i++) {
            ba[i] = this.path.get(i) ? 1 : 0;
        }
        return ba;
    }
}
