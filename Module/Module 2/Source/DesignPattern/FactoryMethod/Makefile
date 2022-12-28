JC = javac
JVM= java

RM = rm
TARGET_DIR = Target

PACKAGE_NAME = FactoryMethod
MAIN_FILE = Main

FILES = \
	$(MAIN_FILE).java \
	./*.java 

all: 
	$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES) && \
	$(JVM) -cp $(TARGET_DIR) $(PACKAGE_NAME).$(MAIN_FILE)

build: 
	$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES)

run: 
	$(JVM) -cp $(TARGET_DIR) $(PACKAGE_NAME).$(MAIN_FILE)

clean:
	$(RM) -rf $(TARGET_DIR)/ 
