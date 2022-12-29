#include <jni.h>
#include <stdlib.h>
#include "HashTable.h"
#include "hash_table.h"
#include <stdio.h>

ht_hash_table* loadHashTable(JNIEnv *env, jobject obj) { 
    jclass cls = (*env)->GetObjectClass(env, obj);
    jfieldID sizeID = (*env)->GetFieldID(env, cls, "size", "I");
    jfieldID countID = (*env)->GetFieldID(env, cls, "count", "I");
    jfieldID baseSizeID = (*env)->GetFieldID(env, cls, "base_size", "I");
    jfieldID itemsID = (*env)->GetFieldID(env, cls, "items", "J");

    ht_hash_table* ht = (ht_hash_table*)malloc(sizeof(ht_hash_table)); 

    ht->size = (int)((*env)->GetIntField(env, obj, sizeID));
    ht->count = (int)((*env)->GetIntField(env, obj, countID));
    ht->base_size = (int)((*env)->GetIntField(env, obj, baseSizeID));
    ht->items = (ht_item**)((*env)->GetLongField(env, obj, itemsID));

    return ht; 
}

void saveHashTable(JNIEnv *env, jobject obj, ht_hash_table *ht) { 
    jclass cls = (*env)->GetObjectClass(env, obj);
    jfieldID sizeID = (*env)->GetFieldID(env, cls, "size", "I");
    jfieldID countID = (*env)->GetFieldID(env, cls, "count", "I");
    jfieldID baseSizeID = (*env)->GetFieldID(env, cls, "base_size", "I");
    jfieldID itemsID = (*env)->GetFieldID(env, cls, "items", "J");

    (*env)->SetIntField(env, obj, sizeID, (jint)ht->size); 
    (*env)->SetIntField(env, obj, countID, (jint)ht->count); 
    (*env)->SetIntField(env, obj, baseSizeID, (jint)ht->base_size); 
    (*env)->SetLongField(env, obj, itemsID, (jlong)ht->items);
}

JNIEXPORT void JNICALL Java_HashTable_newHashTable(JNIEnv* env, jobject obj) {
    ht_hash_table* ht = ht_new(); 
    saveHashTable(env, obj, ht); 
}

JNIEXPORT void JNICALL Java_HashTable_deleteHashTable
  (JNIEnv *env, jobject obj) 
{ 
    ht_hash_table* ht = loadHashTable(env, obj); 
    ht_del_hash_table(ht); 
}

JNIEXPORT void JNICALL Java_HashTable_insert
  (JNIEnv *env, jobject obj, jstring jkey, jstring jvalue) 
{
    ht_hash_table* ht = loadHashTable(env, obj); 
    const char *key = (*env)->GetStringUTFChars(env, jkey, 0);
    const char *value = (*env)->GetStringUTFChars(env, jvalue, 0);

    ht_insert(ht, key, value); 

    saveHashTable(env, obj, ht);     
}

JNIEXPORT jstring JNICALL Java_HashTable_search
  (JNIEnv *env, jobject obj, jstring jkey)
{
    ht_hash_table* ht = loadHashTable(env, obj); 
    const char *key = (*env)->GetStringUTFChars(env, jkey, 0);

    char *value = ht_search(ht, key);
    return (*env)->NewStringUTF(env, value); 
}

JNIEXPORT void JNICALL Java_HashTable_delete
  (JNIEnv *env, jobject obj, jstring jkey) 
{
    ht_hash_table* ht = loadHashTable(env, obj); 
    const char *key = (*env)->GetStringUTFChars(env, jkey, 0);

    ht_delete(ht, key); 
    saveHashTable(env, obj, ht);
}

