/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.homepc.mibe.dragoncurve.drawer;

import it.homepc.mibe.dragoncurve.path.PathDescriptor;
import it.homepc.mibe.dragoncurve.path.PathUtils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author mircobe87
 */
public class DCDrawer {
    private int imageWidth;
    private int imageHeight;
    private String fileName;
    private BufferedImage img;
    private Graphics2D g2;
    
    public DCDrawer(int width, int height, String fileName) {
        this.imageHeight = height;
        this.imageWidth = width;
        this.fileName = fileName;
        this.img = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        this.g2 = img.createGraphics();
        this.g2.setColor(Color.BLACK);
    }
    
    public void draw(PathDescriptor curvePath) {
        double startSegmentLength;
        double startX, startY;
        double segmentLength;
        int startDirection;
        
        if (this.imageWidth/(double)this.imageHeight >= PathUtils.APECT_RATIO) {
            startSegmentLength = getStartSegmentFromHeight(this.imageHeight);
            double curveWidth = PathUtils.APECT_RATIO * startSegmentLength;
            
            startX = (this.imageWidth - curveWidth)/2f + PathUtils.LEFT_FACTOR * startSegmentLength;
            startY = PathUtils.TOP_FACTOR * startSegmentLength;
        } else {
            startSegmentLength = getStartSegmentFromWidth(this.imageWidth);
            double curveHeight = startSegmentLength;
            
            startX = PathUtils.LEFT_FACTOR * startSegmentLength;
            startY = (this.imageHeight - curveHeight)/2f + PathUtils.TOP_FACTOR * startSegmentLength;
        }
        
        startDirection = PathUtils.getInitialRotation(curvePath.getIteration());
        segmentLength = Math.pow(Math.sqrt(2)/2, curvePath.getIteration()+1) * startSegmentLength;
        
        double x0, y0, rotation;
        x0 = startX;
        y0 = startY;
        
        
        
        GeneralPath dragonCurve = new GeneralPath(GeneralPath.WIND_EVEN_ODD, PathUtils.getPathLength(curvePath.getIteration()) + 2);
        dragonCurve.moveTo(startX, startY);
        
        rotation = startDirection * Math.PI/180.0;
              x0 = x0 + segmentLength * Math.cos(rotation);
              y0 = y0 - segmentLength * Math.sin(rotation);
            dragonCurve.lineTo(x0, y0);
        
        
        System.out.println("startSegmentLength: " + startSegmentLength);
        System.out.println("startX: " + startX + "    startY: " + startY);
        System.out.println("segmentLength: " + segmentLength);
        System.out.println("startDirection: " + startDirection);
        System.out.println("rotation: " + rotation);
            
        boolean[] turns = curvePath.toBooleanArray();
        for(int i=0; i<turns.length; i++){
            rotation = turns[i] ? rotation - Math.PI/2 : rotation + Math.PI/2 ;
                  x0 = x0 + segmentLength * Math.cos(rotation);
                  y0 = y0 - segmentLength * Math.sin(rotation);
            dragonCurve.lineTo(x0, y0);
        }
        
        this.g2.draw(dragonCurve);
        
        try {
            File outputfile = new File(this.fileName + "_" + this.imageWidth + "x" + this.imageHeight + "_" + curvePath.getIteration() + ".png");
            ImageIO.write(this.img, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static double getStartSegmentFromHeight(double height) {
        return height;
    }
    
    private static double getStartSegmentFromWidth(double width) {
        return width/PathUtils.APECT_RATIO;
    }
    
}
