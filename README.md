# csv-parser

csv-parser is a system which is be able to read a folder with CSV files and order all included records by configured options. It is importand that you have no creata a specific Class to cast data, to be more configurable and flexible, it only manages String arrays. 
More fields could be added without coding. 
### Version
1.0

### Tech
Java 1.7, Junit and Mockito to test it and Maven to automation. 

## Installation

mvn:

```bash
$ mvn clean package
```

## Usage

To get a generic approach, it is possible to configure most of sort parameters. You can modify all configuration variables in [/src/main/resources/csvParserConfig.properties](https://github.com/aurelido/csv-parser/blob/master/src/main/resources/csvParserConfig.properties):


```bash
#
# Config properties
#

# Records sorted by field in this position. 0 default value
sort.field=2
# Records sorted by field desc order. true default value
sort.desc=true
# Type of sorter field [date|number|string]. 'string' default value
sort.type=date

# Path to csv folder. Empty values use classpath to search csv files
files.csv.path=
```

to run it in stand-alone mode, first you must register third party aabanegas-utils.jar in your local repository:

```bash
$ mvn install:install-file -Dfile=lib/aabanegas-utils.jar -DpomFile=/pom.xml -DgroupId=es.aabanegas -DartifactId=aabanegas-utils -Dversion=1.0 -Dpackaging=jar
```

and then, run it!

```bash
$ mvn exec:java -Dexec.mainClass="es.aabanegas.csv.loader.CSVParserLoader"
```

## Running Tests

Then run the tests:

```bash
$ mvn test
```