# Java GWT Blockchain

This is a simple example Blockchain in Java and GWT. 
This example is taken from: https://www.baeldung.com/java-blockchain

# Start the Webapp

Start the GWT devmode:
```
mvn gwt:generate-module gwt:devmode
```

# Notes

One change needs to be done:
- GWT doesn't emulate String.format so this part needs to be changed. This StackOverflow article 
is a good place to see ByteToHex alternatives in Java: 
https://stackoverflow.com/questions/2817752/java-code-to-convert-byte-to-hexadecimal

# Browser Chrome

![Browser Chrome](src/doc/chrome.png?raw=true "Browser Chrome")

# Eclipse IDE

![Eclipse IDE](src/doc/eclipse.png?raw=true "Eclipse IDE")
