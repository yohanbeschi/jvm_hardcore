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
	|  |  |  +- assembler (Assembler)
	|  |  |  +- java
	|  |  |  +- pjb (Plume Java Bytecode)
	|  |  |  +- resources
	|  |  +- test
	|  +- 02_build (generated)
	|  |  |  +- assembler
	|  |  |  |  +- classes (Compiler Assembler compilé)
	|  |  |  |  +- reports (Assembling reports)
	|  |  |  +- classes
	|  |  |  +- junit-data
	|  |  |  +- junit-reports
	|  |  |  +- pjb-classes (.pjb assembled into .class)
	|  |  |  +- test-classes
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

	<jvm_hardcore_path>$ ant clean

To delete the folder *04\_archives*:

	<jvm_hardcore_path>$ ant clean-achives

The others **targets** are the followings:

- **clean-all**: delete the folders *02\_build* & *03\_dist* of each projects, and the forlder *04\_archives*
- **compile** : compile the Java source code of each projects
- **assemble** : assemble *.pjb* files or class file representations as Java objects to *.class* files. And compile unit tests of these generated files.
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

Assembling and dumping without Ant. mystery's jar is available in the folders *04\_archives* and *03\_projects/mystery/03\_dist*:

	<jvm_hardcore_path>$ java -jar mystery.<date>.jar assemble="../03_projects/mystery/Mystery.hex"

where *<date>* can have the following value: *20130726.123057*.

Blog post: [JVM Hardcore - Part 0 - Sneak Peek](http://blog.soat.fr/2013/09/01-jvm-hardcore-part-0-sneak-peek)

## part01
No source code available for this part.

### Summary
Introduction to the JVM.

Blog post: [JVM Hardcore - Part 1 - Introduction à la JVM](http://blog.soat.fr/2013/09/02-jvm-hardcore-part-1-introduction-a-la-jvm/)

## part02
### Summary
Introduction to PJBA (Plume Java Bytecode Assembler).

### Compile and execute
The folder *01\_src/main/assembler/* contains classes allowing us to generate *.class* files from *.pjb* files or Java code. For simplicity's sake, the choice has been made to use JUnit instead of a *main()* method to run the assembler.

The folder *02\_build/assembler/* contains all the compiled components of the assembler and tests reports from tests ran to generate *.class* files.

The method [assemblePjb()](http://github.com/yohanbeschi/jvm_hardcore/blob/part02/03_projects/bytecode/01_src/main/assembler/org/isk/jvmhardcore/bytecode/parttwo/Assembler#L42) takes all the files in the folder *pjb/* and its children regardless of their extension and invokes PJBA parser (available in the folder   *02\_libs* under the name *pjba.jar*).<br />
Generated files are created in the folder *02\_build\pjb-classes*. Therefore, it 
MUST be added to the classpath to allow unit tests testing its classes and methods to access it.

### Ant build scripts
A new target named **assemble** has been added:

	ant assemble

Moreover, in order that build scripts remains simple and to be able to compile/assemble every projects from the master build script several choices have been made:

- From the root directory or any project all targets are available. Therefore, it's possible to use the target **compile** with the project bytecode whereas the folder *01\_src/main/java* doesn't exist. It's execution will be successful, but won't do anything because before running the target, we test of the folder *01\_src/main/java* exists.
- To the compilation classpath the library *pjba.jar* has been added while it is not need for the project mystery.
- Likewise, to the tests compilation classpath the folder *02\_build/pjb-classes*
 has been added while, again, it is not need for the project mystery.

Therefore, all of this isn't a real problem.

Blog post: [JVM Hardcore - Part 2 - Bytecode - Plume Java Bytecode Assembler](http://blog.soat.fr/2013/10/03-jvm-hardcore-part-2-bytecode-plume-java-bytecode-assembler)

## part03
### Summary
Bytecode instructions : constants (`xconst_y`, `bipush`, `sipush` and `ldc`) and returns (`xreturn`)

### Compile and execute
See part02.

Blog post: [JVM Hardcore - Part 3 - Bytecode - Constantes](http://blog.soat.fr/2013/10/04-jvm-hardcore-part-3-bytecode-constantes)

## part04
### Summary
Bytecode instructions : handling local variables (`xload`, `xload_y`, `xstore` and `xstore_y`).

### Compile and execute
See part02.

Blog post: [JVM Hardcore - Part 4 - Bytecode - Manipulation des variables locales](http://blog.soat.fr/2013/11/05-jvm-hardcore-part-4-bytecode-variables-locales)

## part05
### Summary
Bytecode instructions : operations on numbers.

### Compile and execute
See part02.

Blog post: [JVM Hardcore - Part 5 - Bytecode - Mathématiques et Conversions](http://blog.soat.fr/2013/11/06-jvm-hardcore-part-5-bytecode-mathematiques-et-conversions)

## part06
### Summary
Bytecode instructions : handling the Stack.

### Compile and execute
See part02.

Blog post: [JVM Hardcore - Part 6 - Bytecode - Manipulation de la pile](http://blog.soat.fr/2013/11/07-jvm-hardcore-part-6-bytecode-manipulation-de-la-pile)

## part07
### Summary
Introduction to a simple LL event-based parser (1/2).
 
### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 7 - Mon premier analyseur syntaxique - 1/2](http://blog.soat.fr/2013/12/08-jvm-hardcore-part-7-mon-premier-analyseur-syntaxique-12)

## part08
### Summary
Introduction to a simple LL event-based parser (2/2).

To see the evolution of the grammar look at branches `part08_01` to `part08_07`, where `part08_07` is equals to `part08`. 
 
### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 8 - Mon premier analyseur syntaxique - 2/2](http://blog.soat.fr/2013/12/09-jvm-hardcore-part-8-mon-premier-analyseur-syntaxique-22)

## part09
### Summary
Introduction to a simple interpreter.

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 9 - Résoudre une expression arithmétique](http://blog.soat.fr/2014/01/10-jvm-hardcore-part-9-resoudre-une-expression-arithmetique/)

## part10
### Summary
Unicode and Java.

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 10 - L'unicode et Java](http://blog.soat.fr/2014/01/11-jvm-hardcore-part-10-unicode-et-java)

## part11
### Summary
Introduction to the `class` File Format.

### Compile and execute
Nothing to test. The project **pjba** contains only beans representing the structure of a class file.

Blog post: [JVM Hardcore - Part 11 - Bytecode - Format d'un fichier .class](http://blog.soat.fr/2014/01/12-jvm-hardcore-part-11-bytecode-format-fichier-class)

## part12
### Summary
Building a bytecode assembler (1/2).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 12 – Bytecode - Assembleur de bytecode - 1/2](http://blog.soat.fr/2014/01/13-jvm-hardcore-part-12-bytecode-assembleur-1)

## part13
### Summary
The Visitor Design Pattern and new features for PJBA (Plume Java Bytecode Assembler).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 13 – Bytecode - Ajouter des fonctionnalités à PJBA](http://blog.soat.fr/2014/02/14-jvm-hardcore-part-13-bytecode-ajouter-des-fonctionnalites-a-pjba)

## part14
### Summary
Building a bytecode assembler (2/2).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 14 – Bytecode – Assembleur de bytecode - 2/2](http://blog.soat.fr/2014/02/15-jvm-hardcore-part-14-bytecode-assembleur-2)

## part15
### Summary
Local variables and math instructions (iinc and wide).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 15 – Bytecode – Variables locales et Maths, le retour](http://blog.soat.fr/2014/02/16-jvm-hardcore-part-15-bytecode-variables-locales-et-maths-le-retour)

## part16
### Summary
Comparisons and control (1/3).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 16 – Bytecode – Comparaisons et contrôle - 1/3](http://blog.soat.fr/2014/02/17-jvm-hardcore-part-16-bytecode-comparaisons-et-controle-13)

## part17
### Summary
Comparisons and control (2/3).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 17 – Bytecode – Comparaisons et contrôle - 2/3](http://blog.soat.fr/2014/03/18-jvm-hardcore-part-17-bytecode-comparaisons-et-controle-23)

## part18
### Summary
Converting a logical expression into bytecode.

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 18 – Bytecode - Convertir une expression logique en bytecode](http://blog.soat.fr/2014/03/19-jvm-hardcore-part-18-convertir-une-expression-logique-en-bytecode)

## part19
(No change in code)
### Summary
Comparisons and control (3/3).

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 19 – Bytecode – Comparaisons et contrôle - 3/3](http://blog.soat.fr/2014/03/20-jvm-hardcore-part-19-bytecode-comparaisons-et-controle-33)

## part20
### Summary
Class Members: Fields and Methods

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore - Part 20 – Bytecode – Champs et Méthodes de classes](http://blog.soat.fr/2014/03/21-jvm-hardcore-part-20-bytecode-champs-et-methodes-de-classes)

## part21
### Summary
Objects

### Compile and execute
The project can only be executed through unit tests. (See introduction)

Blog post: [JVM Hardcore – Part 21 – Bytecode – Manipuler des Objets](http://blog.soat.fr/2014/04/22-jvm-hardcore-part-21-bytecode-manipuler-des-objets)
