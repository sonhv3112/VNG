#include <jni.h> 
#include "Main.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_Main_sayHello
  (JNIEnv* env, jobject thisObject) {
    printf("Hello world!"); 
}