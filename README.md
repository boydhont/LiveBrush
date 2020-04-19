# Live Brush

## What is Live Brush?
Live Brush islibrary for live coding with dynamic brushes, developed for Processing 3. Code brushes are dynamicly loaded from .java source files on the harddisk. Brushes can be edited while running the Processing sketch, using a code editor of choice. Processing regognizes changes in the livebrush folder and updates the sketch accordingly.

## Why us LiveBrush?
Live Brush is meant for live performing and rapid prototyping. Old Processing sketches can be easily recycled to create new interesting visuals in a few minutes, new concepts allow to be propotyped in an instance.

## How to work with Live Brush?
1. Set up the default program on your system for editing .java files
2. Create a new Processing sketch and save it
3. Initialize the LiveBrushManager as a global variable in the setup function
  ```
  import liveBrush.*;
  
  LiveBrushManager lbm;
  
  void setup()
  {
    fullScreen();
    lbm = new LiveBrushManager(this);
  }
  ```
4. Draw the LiveBrush instances in the draw function. Optionally, add a cursor
  ```
  void draw()
  {
    lbm.draw();
    lbm.preview(new PVector(mouseX, mouseY)); //Cursor preview is optional
  }
  ```
5. Add controls
  ```
  void mousePressed()
  {
    lbm.add(new PVector(mouseX, mouseY));
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
  ```
6. Run sketch
7. Trigger the create function. In this case, press "n"
8. A Java file is ready to be edited. Processing native functions can be used by calling the "applet" object 
  ```
  class CircleBrush
  {
    public processing.core.PApplet applet;

    public void draw(Object[] args)
    {
      applet.fill(255);
      applet.circle(0,0,100);
    }
  }
  ```
 9. Save the file to update the previously drawn brushes
 10. Experimentation: It is possible to add constructors and import external libraries in the Java files
  ```
  import lunar.*;
   
  class CircleBrush
  {
    public processing.core.PApplet applet;

    String message;

    public CircleBrush()
    {
      message = "Good luck with the experimentation"
      System.out.println(message);
    }

    public void draw(Object[] args)
    {
      applet.fill(255);
      applet.circle(0,0,100);
    }
  }
  ```
  
---
Boy d'Hont | www.bdhont.net  
