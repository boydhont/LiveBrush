//LiveBrush - Basic example
//
//Note: First set up the default program on your system for editing .java files
//This library has been optimized for Visual Studio Code, though any other text editor would suffice

import liveBrush.*;

LiveBrushManager lbm; //The LiveBrush manager holds all brushes and brush controls

void setup()
{
  size(800, 800);
  lbm = new LiveBrushManager(this); //Intialize the LiveBrushManager with the default folder path for the LiveBrushes files
}

void draw()
{
  background(20);

  lbm.draw(); //Draw all LiveBrush instances in the LiveBrush Manager
  lbm.preview(new PVector(mouseX, mouseY)); //Preview the active LiveBrush at the mouse position
}

void mousePressed()
{
  lbm.add(new PVector(mouseX, mouseY)); //Place the active LiveBrush to the LiveBrushManager at the current mouse position
}

/*void mouseDragged()
{
  lbm.add(new PVector(mouseX, mouseY)); //Paint with the active LiveBrush
}*/

void keyPressed()
{
  if (key == 'n') lbm.create(); //Create a new LiveBrush file with a random name
  if (key == 'o') lbm.openFolder(); //Open the folder containing the LiveBrush source files
  if (key == 'e') lbm.edit(); //Edit the active LiveBrush in the default editor for .java files
  if (key == 'c') lbm.clear(); //Clear the LiveBrush Manager of all LiveBrush instances

  if (key == 'q') lbm.selectPrevious(); //Select the previous LiveBrush
  if (key == 'w') lbm.selectNext(); //Select the next liveBrush
}
