# Programming 4 - Java chat client 

A socket based chat client built with java and maven.

## How to build
Ensure Maven in propperly instaled and navigate to the source code of the part of the application that you want to build e.g "/ChatClient/chatclient/" and run ``` mvn package ```

Prebuilt jar files are included in the releases section

## How to run
Ensure java is installed and run the jar files with ```java -jar ```  followed by the file name, for example ```java -jar chatclient-1.0-SNAPSHOT-jar-with-dependencies.jar```

> Note: The chat client requires a valid display server.

## External libraries used
An external library is used to implement a dark theme into the GUI. This library's source is included in my pom.xml and here for reference:
``` 
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.4.1</version>
</dependency>
```