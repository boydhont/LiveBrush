package livebrush;

import groovy.lang.GroovyObject;

import processing.core.PApplet;
import processing.core.PVector;

public class LiveBrushInstance
{
    public GroovyObject groovyObject;
    public LiveBrush liveBrush;
    public PVector origin;

    //TODO add frameCountstartvalue

    public LiveBrushInstance(LiveBrush liveBrush, PVector origin)
    {
        this.liveBrush = liveBrush;
        this.origin = origin;
        try
        {
            this.groovyObject = (GroovyObject) this.liveBrush.liveBrushClass.newInstance();
        }
        catch(Exception error) {
            System.out.println(error);
        } //TODO specifiy exception
    }

    public LiveBrushInstance(LiveBrush liveBrush)
    {
        this.liveBrush = liveBrush;
        this.origin = new PVector();
        try
        {
            this.groovyObject = (GroovyObject) this.liveBrush.liveBrushClass.newInstance();
        }
        catch(Exception error) {
            System.out.println(error);
        } //TODO specifiy exception
    }

    public void setup(PApplet applet)
    {
        invokeGroovyObjectMethod(applet, "setup");
    }

    public void draw(PApplet applet)
    {
        invokeGroovyObjectMethod(applet, "draw");
    }

    private void invokeGroovyObjectMethod(PApplet applet, String methodName)
    {
        if (applet == null) return;
        if (liveBrush == null) return;
        if (origin == null) return;

        applet.push();
        applet.translate(origin.x, origin.y);
        try {
            groovyObject.setProperty("applet", applet);
            groovyObject.invokeMethod(methodName, null);
        }
        catch (Exception error) {
            System.out.println(error);
        } //TODO specify Exception
        applet.pop();
    }

}

