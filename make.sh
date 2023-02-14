#$/bin/bash
#Compile and load the programm
javac */*/*/*.java
javac */*/*.java
javac */*.java
javac *.java
java Main

#Then, when the program is loaded in memory, delete all .class extension
rm */*/*/*.class
rm */*/*.class
rm */*.class
rm *.class
exit 0;