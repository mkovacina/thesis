all:
	cd src && find . -name '*.java' > files
	cd src && javac -classpath ../jars/dom4j-full.jar -d ../bin @files

run:
	java -cp jars/dom4j-full.jar:bin sweep.viewer.Main simulation.xml
