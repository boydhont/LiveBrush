class wobblyLines
{
    processing.core.PApplet applet;

    public void draw(Object[] args)
    {
        applet.stroke(50);
        applet.line(0,(float)(100*applet.sin(applet.frameCount*0.02)),(float)(100*applet.sin(applet.frameCount*0.03)),0);
        applet.line(-100,(float)(200*applet.sin(applet.frameCount*0.01)),100,100);
    }
}
