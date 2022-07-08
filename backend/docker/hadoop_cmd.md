## 

```
# echo "this is test file 01" > testfile01.txt

# hadoop fs -mkdir -p /hadoop-dir/mydir01
# hadoop fs -copyFromLocal testfile01.txt /hadoop-dir/mydir01

# hadoop fs -ls -R / 

# hadoop jar /home/hadoop_home/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /hadoop-dir/mydir01/testfile01.txt wordcount_output
# hadoop fs -text /wordcount/part-r-00000
```
