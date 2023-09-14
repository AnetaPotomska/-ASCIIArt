# ASCII Art

[![pipeline status](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart/badges/master/pipeline.svg)](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart)

The idea of this project is to load images, translate them into ASCII ART images, optionally apply filters, and save them. (https://courses.fit.cvut.cz/BI-OOP/projects/ASCII-art.html)

## Example
### Image before
![image before](./resources/emoji.jpg)
### Image after (no filters)
[image after](./resources/emojiConverted.txt)

## How to run
In sbt shell type **"run _[args]_"**

## How to test
In sbt shell type **"test"**

## Possible arguments for run
### Image
Exactly one argument from this category is needed.
- Randomly generated: **--image-random**
- Loaded from path: **--image _'path'_**, where 'path' is path to image file 
#### Supported image extensions
- png
- jpg

### Ascii conversion table
Maximally one argument from this category is needed. If no table argument is detected, program will use default conversion table.
- Table based on name: **--table _'name'_**
- Linear table with defined characters: **--custom-table _'characters'_**
#### Ascii conversion table names
- **"bourke"** for Paul Bourke's conversion table
- **"fun"** for non-linear conversion table

## Image filters
There can be unlimited arguments from this category.
- **--invert**
- **--flip _'arg'_**, where 'arg' is axis:
  - x
  - y
- **--brightness _'value'_**, where 'value' is number
  - example of positive nums: +50, 50
  - example of negative nums: -50

## Save an image
There can be unlimited arguments from this category.
- Output to console: **--output-console** 
- Output to file: **--output-file _'path'_**, where 'path' is path to new file 