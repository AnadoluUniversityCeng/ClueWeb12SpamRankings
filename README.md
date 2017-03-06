# Waterloo Spam Rankings for the [ClueWeb12](http://lemurproject.org/clueweb12/) Dataset

The file [waterloo-spam-cw12-encoded.tar](http://www.mansci.uwaterloo.ca/~msmucker/cw12spam/waterloo-spam-cw12-encoded.tar) contains a gzip file for each of the cw12 directories. Each file was encoded using compress-spam12.c before being gzip'd. After gunzipping, each file must be decompressed using [decompress-spam12.c](http://www.mansci.uwaterloo.ca/~msmucker/cw12spam/decompress-spam12.c) 
To fetch and uncompress all of the files do (assuming a linux-like setup and bash shell):
* `wget http://www.mansci.uwaterloo.ca/~msmucker/cw12spam/waterloo-spam-cw12-encoded.tar`
* `wget http://www.mansci.uwaterloo.ca/~msmucker/cw12spam/decompress-spam12.c`
* `gcc -o decompress-spam12 decompress-spam12.c`
* `tar -xvf waterloo-spam-cw12-encoded.tar`
* `mkdir -p waterloo-spam-cw12-decoded`
* `cd waterloo-spam-cw12-encoded`
* `for f in *.spamPct.gz ; do cat $f | gunzip -c | ../decompress-spam12 | gzip -c | gunzip > ../waterloo-spam-cw12-decoded/"${f%%.*}".txt ; done`

At the end, `waterloo-spam-cw12-decoded` folder will contain *358* files : `cw12-0000tw.txt  cw12-0601wb.txt  cw12-1501tw.txt ... cw12-0600wb.txt  cw12-1012wb.txt  cw12-1500wb.txt` and so on.
The path of this folder will be the first argument of your program.

The tar is 654 MB. Decoded text files are 20 GB in size and contain *733,019,372* lines total. The format of each decoded text file is:
```
    percentile-score clueweb-docid
    ------------------------------
    29 clueweb12-1500wb-39-00001
    19 clueweb12-1500wb-39-00002
    20 clueweb12-1500wb-39-00003
```
where the percentile score indicates the percentage of the documents in the corpus that are "spammier."
The spammiest documents have a score of 0, and the least spammy have a score of 99. 
The docids are not listed in any particular order in each file.

In this homework, you will retrieve spam scores for a given list of docids. 
Your program will accept document identifiers that are separated by commas.

* `java -jar target/HW2-1.0.jar /home/iorixxx/waterloo-spam-cw12-decoded clueweb12-0003wb-22-11553,clueweb12-0109wb-20-20925,clueweb12-0109wb-78-15059,clueweb12-0112wb-44-22651,clueweb12-0302wb-50-22339,clueweb12-0308wb-28-03934`

will print out:

```
84,56,19,26,79,88
```

* `java -jar target/HW2-1.0.jar /home/iorixxx/waterloo-spam-cw12-decoded clueweb12-0000tw-14-03004`

will print out:

```
61
```

If a non-existing docID is encountered, -1 should be returned as a spam score.

* `java -jar target/HW2-1.0.jar /home/iorixxx/waterloo-spam-cw12-decoded clueweb12-0003wb-22-11553,foo,clueweb12-0109wb-78-15059,bar,clueweb12-0302wb-50-22339`

will print out:

```
84,-1,19,-1,79
```

* Your project must a valid [maven](https://maven.apache.org) project and `mvn clean package` command should produce an executable jar file named `target/HW2-1.0.jar`.