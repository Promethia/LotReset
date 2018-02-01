# Lot Reset

A Minecraft lot resetting tool

#### License

This code is open-sourced software licensed under the [MIT license](http://opensource.org/licenses/MIT).

## Dependencies

+ **[json-simple-1.1.1.jar](https://github.com/fangyidong/json-simple)** - Adds JSON functionality
+ **[nbt-2.0.0.jar](https://github.com/seanboyy/NBT)** - Adds NBT functionality

## Documentation

[Javadoc](https://seanboyy.github.io/doc/LotReset/index.html)

## How to use

#### PRE-REQUISITES:

+ You have a cuboid defined lot region to reset/change in one location ("TO LOT")
+ You have a cuboid defined lot region to grab data from in another location ("FROM LOT")
+ FROM LOT and TO LOT are defined/exist in a valid Minecraft world/server
+ You have a JSON file located somewhere that is formatted as depicted in [temp.json](https://github.com/Promethia/LotReset/blob/master/temp.json)

#### STEPS
+ Download the [latest release](https://github.com/Promethia/LotReset/releases)
+ In your preferred command line executer of choice run
```
java -jar lot-reset-X.Y.Z.jar
```
+ If this is the first time you run the jar, you will also need to specify four arguments after the name of the jar:
+ The list of the alphabet
+ The list of lot types
+ The list of worlds
+ The location of the JSON file for the resetting
+ **ALL VALUES IN STRING LISTS MUST BE SEPARATED BY A COMMA AND THEN A SPACE**
+ EX:
```
java -jar lot-reset-X.Y.Z.jar "A, B, C, D, E, F, G" "LOT, XROAD, ZROAD, CORNER" "MAIN, WORLD2" http://yourdomain.com/reset
```
+ If you need to change anything **do not** run the jar with new arguments. This will not change anything. Go into the config.properties file and edit the values there **still keeping string lists comma + space separated**

**If any errors arise please let me know what the stacktrace says or what happened. I cannot fix what I do not know about**
