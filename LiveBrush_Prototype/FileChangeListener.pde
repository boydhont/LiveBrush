import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

private abstract class FileChangeListener extends TimerTask {
  private String absoluteBrushFolderPath;
  private File filesArray [];
  private HashMap dir = new HashMap();
  private FileFilterListener fileFilterListener;

  public FileChangeListener(String absoluteBrushFolderPath, String filter) {
    this.absoluteBrushFolderPath = absoluteBrushFolderPath;
    fileFilterListener = new FileFilterListener(filter);
    filesArray = new File(absoluteBrushFolderPath).listFiles(fileFilterListener);
    for (int i = 0; i < filesArray.length; i++) {
      dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
    }
  }

  public final void run() {
    HashSet checkedFiles = new HashSet();
    filesArray = new File(absoluteBrushFolderPath).listFiles(fileFilterListener);
    for (int i = 0; i < filesArray.length; i++) {
      Long current = (Long)dir.get(filesArray[i]);
      checkedFiles.add(filesArray[i]);
      if (current == null) {
        dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
        onChange(filesArray[i], "add");
      } else if (current.longValue() != filesArray[i].lastModified()) {
        dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
        onChange(filesArray[i], "modify");
      }
    }
    Set ref = ((HashMap)dir.clone()).keySet();
    ref.removeAll((Set)checkedFiles);
    Iterator it = ref.iterator();
    while (it.hasNext()) {
      File deletedFile = (File)it.next();
      dir.remove(deletedFile);
      onChange(deletedFile, "delete");
    }
  }

  protected abstract void onChange( File file, String action );
}
