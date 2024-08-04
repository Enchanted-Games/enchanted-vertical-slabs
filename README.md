# Enchanted Vertical Slabs
A simple mod that adds vertical slabs to Minecraft in a vanilla-like way.

The `1.20.1-backport` branch is based on v2.2 of the `1.21` branch, backported to fabric 1.20.1

## Set up in IntelliJ IDEA

1. Open the template's root folder as a new project in IDEA. This is the folder that contains this README file and the gradlew executable.
2. If your default JVM/JDK is not Java 21 you will encounter an error when opening the project. This error is fixed by going to `File > Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JVM` and changing the value to a valid Java 21 JVM. You will also need to set the Project SDK to Java 21. This can be done by going to `File > Project Structure > Project SDK`. Once both have been set open the Gradle tab in IDEA and click the refresh button to reload the project.
3. Open the Gradle tab in IDEA if it has not already been opened. Navigate to `Your Project > Common > Tasks > vanilla gradle > decompile`. Run this task to decompile Minecraft.
4. Open your Run/Debug Configurations. Under the Application category there should now be options to run NeoForge and Fabric projects. Select one of the client options and try to run it.
5. Assuming you were able to run the game in step 4 your workspace should now be set up.


## License
See Main branch
