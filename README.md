JVM Hardcore
============

	-- WORK IN PROGRESS --

This project is the illustration of a series of blog posts (in french) :
[JVM Hardcore - Part 0 - Sneak Peek](http://blog.soat.fr/2013/09/01-jvm-hardcore-part-0-sneak-peek) (Introduction of the series and Table of Content)

Each article has its own branch and tag.

## Topics

- Java Bytecode
- Implementation of languages for the JVM
- Inner workings of a Virtual Machine

## Project Structure
Ant allowing a lot of freedom it is necessary to set some conventions. The typical structure of each project will be the following:

	project
 	|  +- 01_src (source code)
	|  |  +- main
	|  |  |  +- pjb (Plume Java Bytecode)
	|  |  |  +- java
	|  |  |  +- resources
	|  |  +- test
	|  |  |  +- pjb
	|  |  |  +- java
	|  |  |  +- resources
	|  +- 02_build (generated)
	|  +- 03_dist (generated)

The series of blog posts covering a lot of different topics there will be several projects, therefore a root level in the directories' hierarchy is needed:
	
	jvm_hardcore
	|  +- 01_conf (Ant configuration files)
	|  +- 02_libs (*.jar)
	|  +- 03_projects
 	|  |  +- mystery
	|  |  +- [...]
	|  +- 04_archives (all generated JARs)

## Compiling the source code
It's really simple to compile everything at once:

	<jvm_hardcore_path>$ ant

To delete the folders *02\_build* and *03\_dist* of each projects:

	<jvm_hardcore_path>$ ant clean-all

To delete the folder *04\_archives*:

	<jvm_hardcore_path>$ ant clean-achives

The others **targets** are the followings:

- **clean**: delete the folder *02\_build* of each projects
- **clean-dist**: delete the folder *03\_dist* of each projects
- **clean-test**: delete the folder *02\_build/[test-classes &amp; junit-data &amp; junit-reports]* of each projects
- **compile** : compile the Java source code of each projects
- **test-compile** : compile the Unit Tests of each projects
- **test** : run Unit Tests of each projects
- **archive** : generated a JAR for each projects.

All of these targets are available for each projects, for example:

	<mystery_project_path>$ ant compile

will only compile the source code of the mystery project.

To execute the Unit Tests of a single class or method, you need to be in the root folder of the project from which the class or method is originating from. For example to execute the test method *byteToHex0Test()* of the class *org.isk.jvmhardcore.mystery.ByteToHexTest* :

	<mystery_project_path>$ ant test -DtestClass=org.isk.jvmhardcore.mystery.ByteToHexTest -DtestMethod=byteToHex0Test

## part00
### Summary
Introduction of the series. The project mystery allows:

- dumping a class file to a text file containing ASCII hexadecimal characters ;
- assembling the previous text file to a class file.

### Compile and execute
Compiling: see "Compiling the source code". 

Assembling using Ant and executing the generated class file:

	<mystery_project>$ ant execute -Dargs="assemble=01_src/test/resources/Mystery.hex"
	<mystery_project>$ java Mystery
	[Mystery sentence]
	
Dumping using Ant:

	<mystery_project>$ ant execute -Dargs="dump=Mystery.class"

Notes :

- In the previous example, the file *Mystery.class* MUST be at the root of the project.
- *-Dargs* is an argument defined in the Ant target **execute**, whose value is passed to the *main()* method of the class *AssembleAndDump*.
- **assemble** and **dump**, are parameters of the application. Only one of them can be used at a time. They take relative paths from the current directory or absolute paths.
- Resulting files (*.class* or *.hex*) are created in the current directory. They have the same name as the orginal file, only the extension is different.
- The parameter **help** prints the help.

Assembling and dumping without Ant. mystery's jar is available in the folders *04\_archives* and *03\_projects/mystery/03\_dist* :

	<jvm_hardcore_path>$ java -jar mystery.<date>.jar assemble="../03_projects/mystery/Mystery.hex"

where *<date>* can have the following value: *20130726.123057*.

Blog post: [JVM Hardcore - Part 0 - Sneak Peek](http://blog.soat.fr/2013/09/01-jvm-hardcore-part-0-sneak-peek)


