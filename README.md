# csv-parser

csv-parser is a system which is be able to read a folder with CSV files and order all included records by configured options.

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

To get a generic approach, it is possible to configure most of sort parameters. You can find all configuration variables in ![csvParserConfig.properties](https://github.com/aurelido/csv-parser/src/main/resources/csvParserConfig.properties):


```
#
# Config properties
#

# Records sorted by field in this position
sort.field=2
# Records sorted by field desc order
sort.desc=true
# Type of sorter field [date|number|string]
sort.type=date

# Path to csv folder
files.csv.path=/path_to_csv_folder
```

## Running Tests

Then run the tests:

```bash
$ mvn test
```