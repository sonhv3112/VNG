JC = javac
JVM= java

RM = rm
TARGET_DIR = Target

PACKAGE_NAME = PredictiveText
MAIN_FILE = Main
DICT=a.csv

FILES = \
	$(MAIN_FILE).java \
	Trie/*.java \
	Utils/*.java \
	Dictionary/*.java 

all: 
	@$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES) && \
	$(JVM) -cp $(TARGET_DIR) $(PACKAGE_NAME).$(MAIN_FILE) $(DICT)

build: 
	@$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES)

run: 
	@$(JVM) -cp $(TARGET_DIR) $(PACKAGE_NAME).$(MAIN_FILE) $(DICT)

clean:
	$(RM) -rf $(TARGET_DIR)/ 
