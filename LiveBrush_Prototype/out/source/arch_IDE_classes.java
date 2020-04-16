import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import groovy.beans.*; 
import groovy.cli.*; 
import groovy.cli.internal.*; 
import groovy.grape.*; 
import groovy.inspect.*; 
import groovy.io.*; 
import groovy.lang.*; 
import groovy.lang.groovydoc.*; 
import groovy.namespace.*; 
import groovy.security.*; 
import groovy.time.*; 
import groovy.transform.*; 
import groovy.transform.builder.*; 
import groovy.transform.options.*; 
import groovy.transform.stc.*; 
import groovy.ui.*; 
import groovy.util.*; 
import groovy.util.logging.*; 
import groovy.xml.*; 
import groovyjarjarantlr.*; 
import groovyjarjarantlr.ASdebug.*; 
import groovyjarjarantlr.actions.cpp.*; 
import groovyjarjarantlr.actions.csharp.*; 
import groovyjarjarantlr.actions.java.*; 
import groovyjarjarantlr.actions.python.*; 
import groovyjarjarantlr.build.*; 
import groovyjarjarantlr.collections.*; 
import groovyjarjarantlr.collections.impl.*; 
import groovyjarjarantlr.debug.*; 
import groovyjarjarantlr.debug.misc.*; 
import groovyjarjarantlr.preprocessor.*; 
import groovyjarjarantlr4.runtime.*; 
import groovyjarjarantlr4.runtime.debug.*; 
import groovyjarjarantlr4.runtime.misc.*; 
import groovyjarjarantlr4.runtime.tree.*; 
import groovyjarjarantlr4.v4.*; 
import groovyjarjarantlr4.v4.analysis.*; 
import groovyjarjarantlr4.v4.automata.*; 
import groovyjarjarantlr4.v4.codegen.*; 
import groovyjarjarantlr4.v4.codegen.model.*; 
import groovyjarjarantlr4.v4.codegen.model.chunk.*; 
import groovyjarjarantlr4.v4.codegen.model.decl.*; 
import groovyjarjarantlr4.v4.codegen.target.*; 
import groovyjarjarantlr4.v4.gui.*; 
import groovyjarjarantlr4.v4.misc.*; 
import groovyjarjarantlr4.v4.parse.*; 
import groovyjarjarantlr4.v4.runtime.*; 
import groovyjarjarantlr4.v4.runtime.atn.*; 
import groovyjarjarantlr4.v4.runtime.dfa.*; 
import groovyjarjarantlr4.v4.runtime.misc.*; 
import groovyjarjarantlr4.v4.runtime.tree.*; 
import groovyjarjarantlr4.v4.runtime.tree.pattern.*; 
import groovyjarjarantlr4.v4.runtime.tree.xpath.*; 
import groovyjarjarantlr4.v4.semantics.*; 
import groovyjarjarantlr4.v4.tool.*; 
import groovyjarjarantlr4.v4.tool.ast.*; 
import groovyjarjarantlr4.v4.unicode.*; 
import groovyjarjarasm.asm.*; 
import groovyjarjarasm.asm.commons.*; 
import groovyjarjarasm.asm.signature.*; 
import groovyjarjarasm.asm.tree.*; 
import groovyjarjarasm.asm.util.*; 
import groovyjarjarpicocli.*; 
import org.apache.groovy.antlr.*; 
import org.apache.groovy.ast.tools.*; 
import org.apache.groovy.internal.metaclass.*; 
import org.apache.groovy.internal.util.*; 
import org.apache.groovy.io.*; 
import org.apache.groovy.lang.annotation.*; 
import org.apache.groovy.metaclass.*; 
import org.apache.groovy.parser.antlr4.*; 
import org.apache.groovy.parser.antlr4.internal.*; 
import org.apache.groovy.parser.antlr4.internal.atnmanager.*; 
import org.apache.groovy.parser.antlr4.util.*; 
import org.apache.groovy.plugin.*; 
import org.apache.groovy.util.*; 
import org.apache.groovy.util.concurrent.*; 
import org.apache.groovy.util.concurrent.concurrentlinkedhashmap.*; 
import org.codehaus.groovy.*; 
import org.codehaus.groovy.antlr.*; 
import org.codehaus.groovy.antlr.java.*; 
import org.codehaus.groovy.antlr.parser.*; 
import org.codehaus.groovy.antlr.treewalker.*; 
import org.codehaus.groovy.ast.*; 
import org.codehaus.groovy.ast.builder.*; 
import org.codehaus.groovy.ast.decompiled.*; 
import org.codehaus.groovy.ast.expr.*; 
import org.codehaus.groovy.ast.stmt.*; 
import org.codehaus.groovy.ast.tools.*; 
import org.codehaus.groovy.classgen.*; 
import org.codehaus.groovy.classgen.asm.*; 
import org.codehaus.groovy.classgen.asm.indy.*; 
import org.codehaus.groovy.classgen.asm.indy.sc.*; 
import org.codehaus.groovy.classgen.asm.sc.*; 
import org.codehaus.groovy.classgen.asm.util.*; 
import org.codehaus.groovy.control.*; 
import org.codehaus.groovy.control.customizers.*; 
import org.codehaus.groovy.control.customizers.builder.*; 
import org.codehaus.groovy.control.io.*; 
import org.codehaus.groovy.control.messages.*; 
import org.codehaus.groovy.plugin.*; 
import org.codehaus.groovy.reflection.*; 
import org.codehaus.groovy.reflection.android.*; 
import org.codehaus.groovy.reflection.stdclasses.*; 
import org.codehaus.groovy.reflection.v7.*; 
import org.codehaus.groovy.runtime.*; 
import org.codehaus.groovy.runtime.callsite.*; 
import org.codehaus.groovy.runtime.dgmimpl.*; 
import org.codehaus.groovy.runtime.dgmimpl.arrays.*; 
import org.codehaus.groovy.runtime.m12n.*; 
import org.codehaus.groovy.runtime.memoize.*; 
import org.codehaus.groovy.runtime.metaclass.*; 
import org.codehaus.groovy.runtime.powerassert.*; 
import org.codehaus.groovy.runtime.typehandling.*; 
import org.codehaus.groovy.runtime.wrappers.*; 
import org.codehaus.groovy.syntax.*; 
import org.codehaus.groovy.tools.*; 
import org.codehaus.groovy.tools.ast.*; 
import org.codehaus.groovy.tools.gse.*; 
import org.codehaus.groovy.tools.javac.*; 
import org.codehaus.groovy.tools.shell.*; 
import org.codehaus.groovy.tools.shell.util.*; 
import org.codehaus.groovy.transform.*; 
import org.codehaus.groovy.transform.sc.*; 
import org.codehaus.groovy.transform.sc.transformers.*; 
import org.codehaus.groovy.transform.stc.*; 
import org.codehaus.groovy.transform.tailrec.*; 
import org.codehaus.groovy.transform.trait.*; 
import org.codehaus.groovy.util.*; 
import org.codehaus.groovy.vmplugin.*; 
import org.codehaus.groovy.vmplugin.v5.*; 
import org.codehaus.groovy.vmplugin.v6.*; 
import org.codehaus.groovy.vmplugin.v7.*; 
import org.codehaus.groovy.vmplugin.v8.*; 
import org.codehaus.groovy.vmplugin.v9.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class arch_IDE_classes extends PApplet {

public void setup()
{
    
    background(0);
    LiveBrushManager lbm = new LiveBrushManager(sketchPath());
}
class LiveBrush
{
    
}
class LiveBrushInstance
{
    
}
class LiveBrushManager
{
    String absoluteBrushFolderPath;

    ArrayList<LiveBrush> liveBrushes;
    ArrayList<LiveBrushInstance> liveBrushInstances;

    //FamilyInstances

    LiveBrushManager(String absoluteBrushFolderPath)
    {
        this.absoluteBrushFolderPath = absoluteBrushFolderPath;
        liveBrushes = new ArrayList<LiveBrush>();
        liveBrushInstances = new ArrayList<LiveBrushInstance>();
        setFileChangeListener(absoluteBrushFolderPath, 1000);
    }

    LiveBrushManager(String absoluteBrushFolderPath, int refreshDelay)
    {
        this.absoluteBrushFolderPath = absoluteBrushFolderPath;
        liveBrushes = new ArrayList<LiveBrush>();
        liveBrushInstances = new ArrayList<LiveBrushInstance>();
        setFileChangeListener(absoluteBrushFolderPath, refreshDelay);
    }

    //TODO constructor with default path
    /*LiveBrushManager()
    {

    }*/

    private void setFileChangeListener(String absoluteBrushFolderPath, int refreshDelay)
    {
        TimerTask task = new DirWatcher(absoluteBrushFolderPath, "java" ) {
            protected void onChange( File file, String action ) {
                //loadFamily(); //TODO update this
            }
        };

        Timer timer = new Timer();
        timer.schedule( task, new Date(), refreshDelay);
    }

    //draw families

    //draw cursor (active family)

    //update family

    //load family

    //add family instance

    //edit family
}
  public void settings() {  size(800,800,P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "arch_IDE_classes" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
