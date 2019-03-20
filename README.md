# VersatileEngine
The VersatileEngine, or VerseEngine for short, is my own 2D game development engine/library. I made this engine from scratch in hopes I can help other game developers to help build games. The engine is fully extensible, allowing for others to make components for it.

# Usage
Currently, the project needs to be converted to a .jar and added as an external library and the dependencies it requires need to be added as well. I recommend using Maven to use the project as all the dependencies can be grabbed from the pom.xml of the project. I know this is a unconvient way of using the library/engine, but I am working on a better way in my free time.

The examine the tests try running the following commands (assuming you have maven install for the command line):

`mvn clean install`

`mvn compile`

To test Animation run the below command:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.animation.AnimationTest -Dexec.classpathScope="test"`

To test Collision run the below command:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.collision.CollisionTest -Dexec.classpathScope="test"`

To test Graphics (like just solely rendering, the above two do more with graphics) run the below command:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.graphics.GraphicsTest -Dexec.classpathScope="test"`

To test Input Processing (displaying what the ActionManager does, uses arrow keys) run the command below:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.inputProcessing.InputTest -Dexec.classpathScope="test"`

To test Sound (the song played is Thrill by Band Maid) run the below command:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.sound.SoundTest -Dexec.classpathScope="test"`

to test Tile loading/display run the below command:

`mvn exec:java -Dexec.mainClass=verse.engine.manualTesting.tiles.TilesTest -Dexec.classpathScope="test"`

This will run the animation testing class I made for showcase. 

# Additional Information
Currently this is just the first release (or working version) of the VersatileEngine. The engine will be updated and refined more over time as I get time to update it. For now, enjoy this library I made to help you develop games in java.
