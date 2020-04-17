import java.util.TimerTask;
import java.util.Timer;
import java.util.Date;
import java.io.File;

import processing.core.PApplet;
import processing.core.PVector;

class LiveBrushManager
{
  PApplet applet;

  public String absoluteSourceFolderPath;

  public ArrayList<LiveBrush> liveBrushes;
  public ArrayList<LiveBrushInstance> liveBrushInstances;

  public int activeLiveBrushIndex = 0;

  public LiveBrushManager(String absoluteSourceFolderPath)
  {
    this.absoluteSourceFolderPath = absoluteSourceFolderPath;
    liveBrushes = getLiveBrushesFromExternalSource();
    liveBrushInstances = new ArrayList<LiveBrushInstance>();
    setFileChangeListener(absoluteSourceFolderPath, 1000);
  }

  public LiveBrushManager(String absoluteSourceFolderPath, int refreshDelay)
  {
    this.absoluteSourceFolderPath = absoluteSourceFolderPath;
    liveBrushes = getLiveBrushesFromExternalSource();
    liveBrushInstances = new ArrayList<LiveBrushInstance>();
    setFileChangeListener(absoluteSourceFolderPath, refreshDelay);
  }

  public LiveBrushManager()
  {
    this.absoluteSourceFolderPath = sketchPath() + "/livebrushes/";
    liveBrushes = getLiveBrushesFromExternalSource();
    liveBrushInstances = new ArrayList<LiveBrushInstance>();
    setFileChangeListener(absoluteSourceFolderPath, 1000);
  }

  public void draw(PApplet applet)
  {
    drawLiveBrushInstances(applet);
  }
  
  public void create()
  {
    createLiveBrushDocumentWithRandomName();
  }
  
  public void create(String name)
  {
    createLiveBrushDocument(name);
  }
  
  public void preview(PApplet applet, PVector origin)
  {
    drawActiveLiveBrush(applet, origin);
  }
  
  public void add(PVector origin)
  {
    addLiveBrushInstance(origin);
  }

  public void edit() {
    editActiveLiveBrush();
  }
  
  public void clear()
  {
    clearLiveBrushInstances();
  }
  
  public void openFolder(){
    openLiveBrushFolder();
  }
  
  public void selectNext()
  {
    selectNextLiveBrush();
  }
  
  public void selectPrevious()
  {
    selectPreviousLiveBrush();
  }

  private void openLiveBrushFolder()
  {
    try
    {
      java.awt.Desktop.getDesktop().open(new java.io.File(this.absoluteSourceFolderPath));
    }
    catch(IOException error) {
      System.out.println(error.getMessage());
    }
  }  
  
  private void createLiveBrushDocumentWithRandomName()
  {
    String randomName = "LiveBrush_"+year()+month()+hour()+minute()+second()+millis(); //TODO generate from current time
    createLiveBrushDocument(randomName);
  }
  
  private void createLiveBrushDocument(String name)
  {
    try{ 
      String[] templateContent = new String[]{
      "class " + name,
      "{",  
      "\t" + "public processing.core.PApplet applet;",   
      "\t",
      "\t"+ "public " + name + "()",
      "\t" + "{",
      "\t",
      "\t" + "}",    
      "\t",
      "\t" + "public void draw(Object[] args)",
      "\t" + "{",
      "\t" + "\t",
      "\t" + "}",
      "}"
      };
      
      String filePath = absoluteSourceFolderPath + "/" + name + ".java";
      saveStrings(filePath, templateContent);
      java.awt.Desktop.getDesktop().open(new java.io.File(filePath));
    }catch(Exception error){System.out.println(error.getMessage());}
  }

  private void editActiveLiveBrush()
  {
    try
    {
      LiveBrush activeLiveBrush = getActiveLiveBrush(activeLiveBrushIndex);
      if (activeLiveBrush == null) return;
      java.awt.Desktop.getDesktop().open(new java.io.File(activeLiveBrush.absoluteSourceFilePath));
    }
    catch(IOException error) {
      System.out.println(error.getMessage());
    }
  }  
  
  private void clearLiveBrushInstances()
  {
    this.liveBrushInstances = new ArrayList<LiveBrushInstance>();
  }

  private void drawActiveLiveBrush(PApplet applet, PVector origin) //TODO Arguments does not work like this, every brush has different arguments, so for now, it only supports PApplet
  {
    if (origin == null) return;
    LiveBrush activeLiveBrush = getActiveLiveBrush(activeLiveBrushIndex);
    if (activeLiveBrush == null) return;
    LiveBrushInstance temporaryLiveBrushInstance = new LiveBrushInstance(activeLiveBrush, origin);
    temporaryLiveBrushInstance.draw(applet);
  }

  private void drawLiveBrushInstances(PApplet applet)
  {
    for (LiveBrushInstance liveBrushInstance : liveBrushInstances) liveBrushInstance.draw(applet);
  }

  private void addLiveBrushInstance(PVector origin)
  {
    LiveBrush activeLiveBrush = getActiveLiveBrush(activeLiveBrushIndex);
    if (activeLiveBrush == null) return;
    LiveBrushInstance liveBrushInstance = new LiveBrushInstance(activeLiveBrush, origin);
    liveBrushInstances.add(liveBrushInstance);
  }

  private void selectNextLiveBrush()
  {
    if (liveBrushes == null) return;
    if (liveBrushes.size() <= 0) return;
    activeLiveBrushIndex++;
    if (activeLiveBrushIndex >= liveBrushes.size()) activeLiveBrushIndex = 0;
  }

  public void selectPreviousLiveBrush()
  {
    if (liveBrushes == null) return;
    if (liveBrushes.size() <= 0) return;
    activeLiveBrushIndex--;
    if (activeLiveBrushIndex < 0) activeLiveBrushIndex = liveBrushes.size()-1;
  }

  private void setFileChangeListener(String absoluteSourceFolderPath, int refreshDelay)
  {
    TimerTask task = new FileChangeListener(absoluteSourceFolderPath, "java" ) {
      protected void onChange( File file, String action ) {
        ArrayList<LiveBrush> updatedLiveBrushes = getLiveBrushesFromExternalSource();
        if (updatedLiveBrushes != null) liveBrushes = updatedLiveBrushes;
        System.out.println("Updated Live Brushes");
      }
    };

    Timer timer = new Timer();
    timer.schedule( task, new Date(), refreshDelay);
  }

  private ArrayList<LiveBrush> getLiveBrushesFromExternalSource() //TODO check this
  {
    String absoluteSourceFolderPath = this.absoluteSourceFolderPath;
    if (absoluteSourceFolderPath == null) return null;
    File folder = new File(absoluteSourceFolderPath);
    ArrayList liveBrushes = new ArrayList<LiveBrush>();
    for (File file : folder.listFiles()) liveBrushes.add(new LiveBrush(file.getAbsolutePath()));
    updateLiveBrushInstances(liveBrushes);
    return liveBrushes;
  }

  private LiveBrush getActiveLiveBrush(int activeLiveBrushIndex)
  {
    if (liveBrushes == null) return null;
    if (liveBrushes.size() == 0) return null;
    if (activeLiveBrushIndex > liveBrushes.size()-1 || activeLiveBrushIndex < 0) return null;
    return liveBrushes.get(activeLiveBrushIndex);
  }

  //TODO update familyinstances
  private void updateLiveBrushInstances(ArrayList<LiveBrush> liveBrushes)
  {
    if (liveBrushInstances == null) return;
    for (LiveBrush liveBrush : liveBrushes)
    {
      if (liveBrush == null) continue;
      for (int i = 0; i < liveBrushInstances.size(); i++)
      {
        LiveBrushInstance liveBrushInstance = liveBrushInstances.get(i);
        if (liveBrushInstance == null) continue;
        if (liveBrushInstance.liveBrush.absoluteSourceFilePath.equals(liveBrush.absoluteSourceFilePath) == false) continue;
        liveBrushInstances.set(i, new LiveBrushInstance(liveBrush, liveBrushInstance.origin));
      }
    }
  }

  //TODO create source file from template

  //TODO delete/clear canvas
}
