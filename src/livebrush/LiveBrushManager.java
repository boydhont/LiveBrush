package livebrush;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.util.Date;
import java.io.File;

import processing.core.PApplet;
import processing.core.PVector;

public class LiveBrushManager
{
	PApplet applet;

	public String absoluteSourceFolderPath;

	public ArrayList<LiveBrush> liveBrushes;
	public ArrayList<LiveBrushInstance> liveBrushInstances;

	public int activeLiveBrushIndex = 0;

	public LiveBrushManager(PApplet applet, String absoluteSourceFolderPath)
	{
		this.applet = applet;
		this.absoluteSourceFolderPath = absoluteSourceFolderPath;
		liveBrushes = getLiveBrushesFromExternalSource();
		liveBrushInstances = new ArrayList<LiveBrushInstance>();
		setFileChangeListener(absoluteSourceFolderPath, 1000);
	}

	public LiveBrushManager(PApplet applet, String absoluteSourceFolderPath, int refreshDelay)
	{
		this.applet = applet;
		this.absoluteSourceFolderPath = absoluteSourceFolderPath;
		liveBrushes = getLiveBrushesFromExternalSource();
		liveBrushInstances = new ArrayList<LiveBrushInstance>();
		setFileChangeListener(absoluteSourceFolderPath, refreshDelay);
	}

	public LiveBrushManager(PApplet applet)
	{
		this.applet = applet;
		this.absoluteSourceFolderPath = applet.sketchPath() + "/livebrushes/";
		liveBrushes = getLiveBrushesFromExternalSource();
		liveBrushInstances = new ArrayList<LiveBrushInstance>();
		setFileChangeListener(absoluteSourceFolderPath, 1000);
	}

	/**
	 * Draws all the LiveBrush instances in the manager
	 * @param applet The sketch as a PApplet
	 */
	
	public void draw()
	{
		drawLiveBrushInstances();
	}

	/**
	 * Creates a new Java source document in the LiveBrush folder with a random name
	 */
	
	/*public void create()
	{
		createLiveBrushDocumentWithRandomName();
	}*/
	
	/**
	 * Creates a new Java source document in the LiveBrush folder
	 * @param name The name of the new document
	 */
	
	public void create(String name)
	{
		createLiveBrushDocument(name);
	}

	/**
	 * Draws the active LiveBrush without creating a LiveBrush instance
	 * @param applet The sketch as a PApplet
	 * @param origin The origin point of the preview
	 */
	
	public void preview(PVector origin)
	{
		drawActiveLiveBrush(origin);
	}

	/**
	 * Adds a LiveBrush instance at the given position
	 * @param origin The origin point of the instance
	 */
	
	public void add(PVector origin)
	{
		addLiveBrushInstance(origin);
	}

	/**
	 * Opens a system editor to edit the current livebrush
	 */
	
	public void edit() {
		editActiveLiveBrush();
	}

	/**
	 * Clears the LiveBrushManager of all the LiveBrush instances
	 */
	
	public void clear()
	{
		clearLiveBrushInstances();
	}

	/**
	 * Opens the system folder containing the LiveBrush source files
	 */
	
	public void openFolder(){
		openLiveBrushFolder();
	}

	/**
	 * Selects the next LiveBrush class
	 */
	
	public void selectNext()
	{
		selectNextLiveBrush();
	}

	/**
	 * Selects the previous LiveBrush class
	 */
	
	public void selectPrevious()
	{
		selectPreviousLiveBrush();
	}

	/**
	 * Opens the system folder containing the LiveBrush source files
	 */
	
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

	/**
	 * Creates a new Java source document in the LiveBrush folder with a random name
	 */
	
	private void createLiveBrushDocumentWithRandomName()
	{
		String randomName = "LiveBrush_"+applet.year()+applet.month()+applet.hour()+applet.minute()+applet.second()+applet.millis(); //TODO make this independent of the PApplet
		createLiveBrushDocument(randomName);
	}

	/**
	 * Creates a new Java source document in the LiveBrush folder
	 * @param name The name of the new document
	 */
	
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
			applet.saveStrings(filePath, templateContent); //TODO handle this over a native java library instead of the PApplet
			java.awt.Desktop.getDesktop().open(new java.io.File(filePath));
		}catch(Exception error){System.out.println(error.getMessage());}
	}

	/**
	 * Opens a system editor to edit the current livebrush
	 */
	
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

	/**
	 * Clears the LiveBrushManager of all the LiveBrush instances
	 */
	
	private void clearLiveBrushInstances()
	{
		this.liveBrushInstances = new ArrayList<LiveBrushInstance>();
	}

	/**
	 * Draws the active LiveBrush without creating a LiveBrush instance
	 * @param applet The sketch as a PApplet
	 * @param origin The origin of the instance
	 */
	
	private void drawActiveLiveBrush(PVector origin) //TODO Arguments does not work like this, every brush has different arguments, so for now, it only supports PApplet
	{
		if (origin == null) return;
		LiveBrush activeLiveBrush = getActiveLiveBrush(activeLiveBrushIndex);
		if (activeLiveBrush == null) return;
		LiveBrushInstance temporaryLiveBrushInstance = new LiveBrushInstance(activeLiveBrush, origin);
		temporaryLiveBrushInstance.draw(applet);
	}

	/**
	 * Draws all the LiveBrush instances in the manager
	 * @param applet The sketch as a PApplet
	 */
	
	private void drawLiveBrushInstances()
	{
		for (LiveBrushInstance liveBrushInstance : liveBrushInstances) liveBrushInstance.draw(applet);
	}

	/**
	 * Adds a LiveBrush instance at the given position
	 * @param origin The origin of the instance
	 */
	
	private void addLiveBrushInstance(PVector origin)
	{
		LiveBrush activeLiveBrush = getActiveLiveBrush(activeLiveBrushIndex);
		if (activeLiveBrush == null) return;
		LiveBrushInstance liveBrushInstance = new LiveBrushInstance(activeLiveBrush, origin);
		liveBrushInstances.add(liveBrushInstance);
	}

	/**
	 * Selects the next LiveBrush class
	 */
	
	private void selectNextLiveBrush()
	{
		if (liveBrushes == null) return;
		if (liveBrushes.size() <= 0) return;
		activeLiveBrushIndex++;
		if (activeLiveBrushIndex >= liveBrushes.size()) activeLiveBrushIndex = 0;
	}

	/**
	 * Selects the previous LiveBrush class
	 */
	
	public void selectPreviousLiveBrush()
	{
		if (liveBrushes == null) return;
		if (liveBrushes.size() <= 0) return;
		activeLiveBrushIndex--;
		if (activeLiveBrushIndex < 0) activeLiveBrushIndex = liveBrushes.size()-1;
	}

	/**
	 * Sets the listener for LiveBrush source file changes
	 * @param absoluteSourceFolderPath The filepath to the LiveBrush source folder
	 * @param refreshDelay The frequency time to check for file changes
	 */
	
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

	/**
	 * Parses LiveBrush objects from external source files
	 * @return A list of LiveBrush objects
	 */
	
	private ArrayList<LiveBrush> getLiveBrushesFromExternalSource()
	{
		String absoluteSourceFolderPath = this.absoluteSourceFolderPath;
		if (absoluteSourceFolderPath == null) return null;
		File folder = new File(absoluteSourceFolderPath);
		ArrayList liveBrushes = new ArrayList<LiveBrush>();
		for (File file : folder.listFiles()) liveBrushes.add(new LiveBrush(file.getAbsolutePath()));
		updateLiveBrushInstances(liveBrushes);
		return liveBrushes;
	}

	/**
	 * Retrieves the active LiveBrush
	 * @param activeLiveBrushIndex The index of the active LiveBrush
	 * @return The active LiveBrush
	 */
	
	private LiveBrush getActiveLiveBrush(int activeLiveBrushIndex)
	{
		if (liveBrushes == null) return null;
		if (liveBrushes.size() == 0) return null;
		if (activeLiveBrushIndex > liveBrushes.size()-1 || activeLiveBrushIndex < 0) return null;
		return liveBrushes.get(activeLiveBrushIndex);
	}

	/**
	 * Updates the LiveBrush instances
	 * @param liveBrushes A list of LiveBrushes
	 */
	
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
}
