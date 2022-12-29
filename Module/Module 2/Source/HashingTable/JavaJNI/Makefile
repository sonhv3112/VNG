JC = javac
JVM= java
GCC = gcc

RM = rm
TARGET_DIR = Target

PACKAGE_NAME = JavaJNI
MAIN_FILE = Main

NATIVE_FILE = 

FILES = \
	$(MAIN_FILE).java \
	./*.java 

all: 
	$(GCC) -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -lm -c prime.c hash_table.c HashTable.c && \
	$(GCC) -shared -o libHashTable.so prime.o hash_table.o HashTable.o && \
	javac *.java && \
	$(JVM) -cp . -Djava.library.path=. Main

build: 
	$(GCC) -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -lm -c prime.c hash_table.c HashTable.c && \
	$(GCC) -shared -o libHashTable.so prime.o hash_table.o HashTable.o && \
	javac *.java

run: 
	$(JVM) -cp . -Djava.library.path=. Main

clean:
	$(RM) *.o *.class *.so

# JC = javac
# JVM= java
# GCC = gcc

# RM = rm
# TARGET_DIR = Target

# PACKAGE_NAME = JavaJNI
# MAIN_FILE = Main

# FILES = \
# 	$(MAIN_FILE).java \
# 	./*.java 

# all: 
# 	$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES) && \
# 	$(JVM) -cp $(TARGET_DIR) $(PACKAGE_NAME).$(MAIN_FILE)

# build: 
# 	@$(RM) -rf $(TARGET_DIR)/ && \
# 	mkdir ./$(TARGET_DIR) && mkdir ./$(TARGET_DIR)/obj && mkdir ./$(TARGET_DIR)/lib && \
# 	$(GCC) -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -lm -c ./native/prime.c -o ./Target/obj/prime.o && \
# 	$(GCC) -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -lm -c ./native/hash_table.c -o ./Target/obj/hash_table.o && \
# 	$(GCC) -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -lm -c ./native/HashTable.c -o ./Target/obj/HashTable.o && \
# 	$(GCC) -shared -o ./Target/lib/libHashTable.so ./Target/obj/*.o && \
# 	$(JC) -d $(TARGET_DIR) -classpath -sourcepath $(FILES)

# run: 
# 	$(JVM) -cp $(TARGET_DIR) -Djava.library.path=./$(TARGET_DIR)/lib $(PACKAGE_NAME).$(MAIN_FILE)

# clean:
# 	$(RM) -rf $(TARGET_DIR)/ 
