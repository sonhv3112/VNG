JAR = ./target/dictionary-1.0-SNAPSHOT-jar-with-dependencies.jar
MAIN = module2.dictionary.Main

DICT = a.csv
DICT_TYPE = triedictionary

all: 
	@mvn clean compile assembly:single && java -cp $(JAR) $(MAIN) $(DICT) $(DICT_TYPE)

build: 
	@mvn clean compile assembly:single

run:
	@java -cp $(JAR) $(MAIN) $(DICT) $(DICT_TYPE)

clean:
	mvn clean
    
