import java.lang.Class;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

class LiveBrush
{
  public String absoluteSourceFilePath;
  public Class liveBrushClass;

  public LiveBrush(String absoluteSourceFilePath)
  {
    this.absoluteSourceFilePath = absoluteSourceFilePath;
    liveBrushClass = getLiveBrushClass(absoluteSourceFilePath);
  }

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
