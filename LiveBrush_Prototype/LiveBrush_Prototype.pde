//TODO create a system, so it can catch arguments/ variables

LiveBrushManager lbm;

void setup()
{
  size(800, 800);
  lbm = new LiveBrushManager();
}

void draw()
{
  background(230);
  lbm.draw(this);
  lbm.preview(this, new PVector(mouseX, mouseY));
}

void mouseDragged()
{
  lbm.add(new PVector(mouseX, mouseY));
}

void keyPressed()
{
  if (key == 'e') lbm.edit();
  if (key == 'k') lbm.openFolder();
  if (key == 'q') lbm.selectPrevious();
  if (key == 'w') lbm.selectNext();
  if (key == 'r') lbm.clear();
  if (key == 'n') lbm.create();
}
