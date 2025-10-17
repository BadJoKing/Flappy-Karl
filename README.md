# Flappy-Karl
☭
## Keybinds
```
UP_ARROW = flap
RIGHT_ARROW = shoot
```
## Dependencies
A newer version of java. Probably like java 17 at least. I have confirmed that it works on java 25.

## Build Instructions
### For Linux
Open a terminal in the program directory. \
Check whether build.sh has the correct permissions and if it doesn't, execute
```
chmod +x build.sh
```
then do
```
./build.sh
```
The result can be found in build/Flappy-Karl.jar

### For Windows
I'm not an expert in window stuff, but I believe it's pretty similar, except you have to open a **powershell window** in the directory (SHIFT + RIGHT_CLICK shows that option in a context menu, I believe)
then do:
```
javac *.java
jar cmf Flappy-Karl.mf build/Flappy-Karl.jar .
```
The result should be in build/Flappy-Karl.jar, but again: I haven't tried this method at all. It may very well be possible that windows just works differently now than I remember.
