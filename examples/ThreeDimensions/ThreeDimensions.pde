//LiveBrush - 3D example
//
//Note: First set up the default program on your system for editing .java files
//This library has been optimized for Visual Studio Code, though any other text editor would suffice

import liveBrush.*;

LiveBrushManager lbm;

void setup()
{
  size(800,800, P3D);
  smooth(8);
    
  lbm = new LiveBrushManager(this);
}

void draw()
{
  background(230);
  
  lbm.draw(); //Draw all LiveBrush instances in the LiveBrush Manager
  lbm.preview(new PVector(mouseX, mouseY)); //Preview the active LiveBrush at the mouse position
}

void mousePressed()
{
  
  lbm.add(new PVector(mouseX, mouseY)); //Place the active LiveBrush to the LiveBrushManager at the current mouse position
}

void keyPressed()
{
  if (key == 'n') lbm.create(); //Create a new LiveBrush file with a random name
  if (key == 'o') lbm.openFolder(); //Open the folder containing the LiveBrush source files
  if (key == 'e') lbm.edit(); //Edit the active LiveBrush in the default editor for .java files
  if (key == 'c') lbm.clear(); //Clear the LiveBrush manager of all LiveBrush instances

  if (key == 'q') lbm.selectPrevious(); //Select the previous LiveBrush
  if (key == 'w') lbm.selectNext(); //Select the next liveBrush
}
