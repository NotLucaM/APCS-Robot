APCS Robot Project
==================

Running
-------

####Running in repl.it

To run in repl.it you need to first type "./gradlew build" in the terminal.
After that runs, you will need to use "./gradlew run" to run the program.
The graphics on repl are very slow so there might be clipping or weird graphics.

####Running in terminal

Follow the same instructions as repl.it. Will not work on Mac OS as a specific argument must be set.
I do not know how to set this with gradle.
On Linux run "./gradlew build" and "./gradlew run".
On Windows the commands should be similar (still uses gradle) however the syntax might be different.
I am not familiar with the windows command line, so I can't confirm if this works.

####Running in IntelliJ

When importing the project, click the build.gradle file and click yes on import as project.
Wait for IntelliJ to index everything and then run the Main.java class normally.
If on Mac OS, add "-XstartOnFirstThread" to the VMOptions part of edit configuration.

Using the program
-----------------

#### Using the program

To use the program you only need to click on the screen where you want the robots to go.
If you want to add a new robot or change the parameters, all the robots are currently in the OpenGLWindow class.
The parameters to the robot are x, y, width, height, p, i, and d. They also have the option to add a graph to them.
The robots all move using a pid loop and have a hardcoded weight.

#### Adding more to it
The program is not very well commented however, if you want to edit it you can add objects in the OpenGL window in the init function.
Make sure to add them as GameObjects, so my code knows to run it.
If you want to add a custom object add it in the objects package and make sure the class implements the GameObject interface. 