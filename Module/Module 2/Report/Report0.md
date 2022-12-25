# Module 2.0

Họ và tên: Hồ Văn Sơn \
Domain: sonhv2 

---------------------------------------

## 1. Processing texts

### Count the number of lines satisfying a specific pattern in a log file

```sh
awk -F <separator> '<pattern> {++count}END{print "Number of line satisfy:" count}' <file>
```
- Ví dụ đếm số dòng có chứa "root" trong /etc/password ta sử dụng lệnh như sau:

```sh
awk -F ':' '/root/{++count}END{print "Number of line satisfy: " count}' /etc/passwd
```

### Calculate KLOC of code C/C++ files in a directory

```sh
find <directory> -name '*.c' -o -name '*.cpp' | xargs wc -l |
awk '{total_size += $1 / 1000}END{print "Total KLOC of code C/C++ files in current directory: " total_size}'
```

## 2. System

### Kill multiple processes following a patterns (using `awk`, `grep`, `xargs`)

```sh
ps -eo user,pid,comm,time | <pattern> | xargs -r kill -9
```

- Trong đó ta sử dụng lệnh **grep** hoặc **awk** để liệt kê các port tương ứng với process mà ta muốn kill. Ví dụ để kill các process được user bật và có lệnh chứa chữ "fire" thì ta sử dụng lệnh tương ứng sau: 

```sh
ps -eo user,pid,comm,time | awk -v USER="$USER" '$1==USER && $3~"fire" {print $2}' | xargs -r kill -9 
```

### Kill processes opening a specific port (using `netstat`, `grep`...)

```sh
kill -9 <portID>
```

### List opennned ports, handles

```sh
netstat -a | grep LISTENING
```

### Find files via regular expressions, and remove them

```sh
find <path> -type f -regex <regular_expression>
```

### List, one at a time, all files larger than 100K in the /home/username directory tree. Give the user the option to delete or compress the file, then proceed to show the next one. Write to a logfile the names of all deleted files and the deletion times.

```sh
findFile() {    
	find $1 -type f -exec du -a --block-size=1024 {} + | 
	awk -v LOWER_BOUND_SIZE=$2 '$1 > LOWER_BOUND_SIZE {print $2}'
}
	
doTask() {    
	echo -n "0. Delete\n1. Compress all file\nYour option [01]: ";    
	read option;    
	if [[ $option = 0 ]]; then        
		{ echo -n "Datetime: " && date "+%H:%M:%S %d/%m/%y" && findFile $1 $2 } > ./delete.log;  
		findFile $1 $2 | xargs rm -f;
		echo "Name of all deleted files are log into ./detele.log"    
	else      
		echo "Enter filename of zip file:";         
		read filename;         
		findFile $1 $2 | xargs zip $filename    
	fi
}

doTask ~/ 100
```

## 3. Others

- Write a script named `commit.sh` to push updates to a git repo

```sh
git add --all .
git commit -m $1
git push -u origin master
```

- Parse `/etc/passwd`, and output its contents in nice, easy-to-read tabular form.

```sh
{ echo "username:password:UID:GID:GECOS:Home_directory:Command" && cat /etc/passwd } | column -tn -s":"
```

- Print (to stdout) all prime numbers between 60000 and 63000. The output should be nicely formatted in columns (hint: use printf).

```sh
isPrime() {
	if [[ $1 -le 1 ]]; then 
		echo 0;
		return 0
	fi
	if [[ $1 -le 3 ]]; then 
		echo 1;
		return 0
	fi
	
    i=2;
    
    while [[ $((i * i)) -le $1 ]];  do
        if [[ $(($1 % i)) -eq 0 ]]; then
            echo 0
            return 0	
        fi
        i=$((i + 1));
    done
    
    echo 1
}

listPrime() {  
	for i in $(seq $1 $2); do
		check=$(isPrime $i);
		if [[ $check -eq 1 ]]; then 
			echo $i; 
		fi
	done 
}	

listPrime 60000 63000
```

- `[Hard]` Implement a script to accept integral input, sorting them via `sleep sort` algorithm

```sh
sortElement() { 
	sleep "$1"
	echo "$1"
}

sortArray() { 
	arr=("$@")
	for i in "${arr[@]}";
	do	
		sortElement "$i" & 
	done
	wait
}

# Example: sortArray 5 2 4 1 3
```

