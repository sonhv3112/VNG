#include <stdio.h>
#include "hash_table.h"

int main() {
    ht_hash_table* ht = ht_new();
    ht_insert(ht, "121231a2c1211", "hello vn"); 
    char *str; 
    // str = ht_search(ht, "1212312");
    // printf("%s", str);
    ht_del_hash_table(ht);
}