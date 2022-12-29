JC = javac
JVM= java

RM = rm
MAIN_FILE = Main
JAR = lz4-java-1.8.0.jar

all: 
	$(JC) -cp $(JAR) $(MAIN_FILE).java && \
	$(JVM) $(MAIN_FILE)

build: 
	$(JC) -cp $(JAR) $(MAIN_FILE).java

run: 
	$(JVM) $(MAIN_FILE)

clean:
	$(RM) *.class
