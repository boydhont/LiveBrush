class RotatingRectangle
{
	public processing.core.PApplet applet;
	
	float cubeRotation = 0;

	public void draw(Object[] args)
	{
		applet.noFill();
		applet.stroke(255);

		applet.strokeWeight(4);
		applet.circle(0,0,100);

		applet.rotate((float)(cubeRotation+=0.02));
		applet.noStroke();
		applet.fill(255);
		applet.rectMode(applet.CENTER);
		applet.rect(0,0,35,35,5);
	}
}
