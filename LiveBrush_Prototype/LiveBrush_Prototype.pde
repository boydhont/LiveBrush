//TODO create a system, so it can catch arguments
//TODO create a new file over a template
//TODO update brush instances

LiveBrushManager liveBrushManager;

void setup()
{
  size(800, 800, P2D);
  liveBrushManager = new LiveBrushManager();
}

void draw()
{
  background(230);
  liveBrushManager.drawActiveLiveBrush(this, new PVector(mouseX, mouseY));
  liveBrushManager.drawLiveBrushInstances(this);
}

void mouseDragged()
{
  liveBrushManager.addLiveBrushInstance(new PVector(mouseX, mouseY));
}

void keyPressed()
{
  if (key == 'e') liveBrushManager.editActiveLiveBrush();  
  if (key == 'q') liveBrushManager.selectPreviousLiveBrush();
  if (key == 'w') liveBrushManager.selectNextLiveBrush();
}
