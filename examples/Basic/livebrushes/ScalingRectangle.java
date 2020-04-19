class ScalingRectangle
{
	public processing.core.PApplet applet;
	
	float size = 30;

	public void draw(Object[] args)
	{
		size += applet.sin(applet.frameCount*0.1);

		applet.fill(255);
		applet.noStroke();
		applet.rectMode(applet.CENTER);
		applet.rect(0,0,size,size,5);
	}
}
