# java-gwt-blockchain
Java GWT Blockchain Example

This is a simple example Blockchain in Java and GWT. 
This example is taken from: https://www.baeldung.com/java-blockchain

Start the GWT devmode:
mvn gwt:generate-module gwt:devmode

One change needs to be done:
- GWT doesn't emulate String.format so need to be changed. This StackOverflow article 
is a good place to see ByteToHex in Java: 
https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal
