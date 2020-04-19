package livebrush;

import groovy.lang.GroovyClassLoader;

import java.io.File;

public class LiveBrush
{
    public String absoluteSourceFilePath;
    @SuppressWarnings("rawtypes")
	public Class liveBrushClass;

    public LiveBrush(String absoluteSourceFilePath)
    {
        this.absoluteSourceFilePath = absoluteSourceFilePath;
        liveBrushClass = getLiveBrushClass(absoluteSourceFilePath);
    }

    /**
     * Parses a class from an external Java source document
     * @param absoluteSourceFilePath The file path to the source file of the class
     * @return The class
     */
    
    @SuppressWarnings("rawtypes")
	private Class getLiveBrushClass(String absoluteSourceFilePath)
    {
        try
        {
            GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
            Class parsedClass = groovyClassLoader.parseClass(new File(absoluteSourceFilePath));
            groovyClassLoader.close();
            return parsedClass;
        }
        catch(Exception error) //TODO specify the exceptions
        {
            System.out.println(error.getMessage());
            return null;
        }
    }
}