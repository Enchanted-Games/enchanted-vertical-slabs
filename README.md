# Enchanted Vertical Slabs
A simple mod that adds vertical slabs to Minecraft in a vanilla-like way. They can be crafted the same way a normal slab can in a crafting table just vertically instead of horizontally.

This is a mostly rewritten version of the original [Enchanted Vertical Slabs](https://github.com/Enchanted-Games/vertical-slabs) mod that now supports NeoForge and Fabric

Originally forked from [jaredlll08/MultiLoader-Template](https://github.com/jaredlll08/MultiLoader-Template)

## W.I.P
This rewrite is still a work in progress and doesn't have all the features of the original mod.

## License
<p xmlns:cc="http://creativecommons.org/ns#" >Enchanted Vertical Slabs by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://enchanted.games">ioblackshaw (a.k.a. Enchanted_Games)</a> is licensed under <a href="http://creativecommons.org/licenses/by-nc/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC 4.0<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1"><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/nc.svg?ref=chooser-v1"></a></p> 
Video content creators may monetise videos including this work provided the license is followed.


## Set up in IntelliJ IDEA

1. Open the template's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
2. If your default JVM/JDK is not Java 21 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM` and changing the value to a valid Java 21 JVM. You will also need to set the Project SDK to Java 21. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
3. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
4. Open your Run/Debug Configurations. Under the Application category there should now be options to run NeoForge and Fabric projects. Select one of the client options and try to run it.
5. Assuming you were able to run the game in step 4 your workspace should now be set up.
