# Data Compression — Assignment 2: LZW

Java GUI application implementing the **Lempel-Ziv-Welch (LZW) compression algorithm** with a Swing dialog interface.

## How it works

LZW builds a dynamic dictionary of substrings encountered during encoding:
- Starts with a base dictionary of all 256 ASCII characters
- Reads the input character by character, extending matched strings
- When a new string is found, it is added to the dictionary and the code for the previous match is output
- Results in variable-length codes that grow shorter as the dictionary adapts to input patterns

The Java GUI allows the user to select an input file, compress it to `myoutputfile.txt`, and decompress back.

## Run
```bash
javac src/DataCompressionDialog.java
java DataCompressionDialog
```
