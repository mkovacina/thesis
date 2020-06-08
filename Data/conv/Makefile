CLASSPATH = .:dom4j-full.jar:miko.jar

OD_SRC = ../runs/d01/solution-best.xml
OD_DST = ../../Thesis/FSA/destruction.fsa

OC_SRC = ../runs/c02/solution-best.xml
OC_DST = ../../Thesis/FSA/collection.fsa

OM_SRC = ../runs/m02/solution-best.xml
OM_DST = ../../Thesis/FSA/manipulation.fsa

DD_SRC = dispersion.xml
DD_DST = ../../Thesis/FSA/dispersion.fsa

all: Converter.java
	javac -classpath $(CLASSPATH) Converter.java

conv:
	java -cp $(CLASSPATH) Converter $(DD_SRC) > $(DD_DST)
	#java -cp $(CLASSPATH) Converter $(OD_SRC) > $(OD_DST)
	#java -cp $(CLASSPATH) Converter $(OC_SRC) > $(OC_DST)
	#java -cp $(CLASSPATH) Converter $(OM_SRC) > $(OM_DST)

