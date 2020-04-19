class BouncingSphere
{
	public processing.core.PApplet applet;
	
	float height = 0;
	int seed = 0;

	public void draw(Object[] args)
	{
		height += (seed*0.025)*applet.sin((float)((applet.frameCount+seed)*applet.map(seed, 0, 200, 0.01, 0.05)));
		
		int size = 80;
		if(seed != 0) size = applet.map(seed, 0, 200, 50,100);
		if(seed != 0) applet.translate(0,0,height);
		if(seed == 0) seed = applet.random(200);

		applet.fill(20);
		applet.stroke(230);
		applet.strokeWeight(1);

		applet.sphere(size);
	}
}
