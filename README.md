##**Island Life Simulator**
___

###Description
The program creates an island size of 10 by 150 cells (locations). Randomly locations are filled with different numbers 
of animals and grass. In total there are 15 species of animals. One game day - one cycle of the program, during which 
the animals pass the following phases:
- animals saturation decreases (if saturation falls below zero, animal dies of hunger).
- animals move in a random direction.
- herbivores eat grass. Carnivores can eat another animal at a certain chance.
- if there are pairs/pairs of animals of the same species, they give offspring.

At the end of each day, the entire island is displayed on the screen - in each cell the icon indicates the animals 
that are there now and their number. And textual statistics - how many new animals were born in the world in a day. 
How many died. How many animals are left in the world.

On the 30th day of the simulation, a cataclysm occurs - new grass ceases to grow. The animals stop reproducing, 
but they keep moving around on the map, eating the rest of the grass and other animals. 
The game ends when the last animal in the world dies.

Project launch:
___
###Description of classes
The root package of the `ua.com.javarush.lifesimulator` project contains the `Main` class containing an application
start point.

The package `annotations` contains the annotation:
- `NumberOfItemsOnField` - contains information about the minimum and maximum number of animals in the game.

The package `configuration` contains the classes:
- `AnimalCharacteristics` - animal characteristic class.
- `Characteristics` - class characteristic.
- `ItemsConfigurations` - animal configurations from json files.
- `ItemSettings` - animal storage settings class.

The package `constants` contains the classes:
- `GameConstants` - game constants and settings.
- `GameErrors` - Messages with errors.
- `PrintableFieldElements` - elements for visualization of information on the screen.

The package `controllers` contains the classes:
- `GameController` - class responsible for starting the game day tasks.
- `GameEventsController` - class for tracking game day events.
- `LifeController` - class for program life cycle start.

The package `factories` contains the classes:
- `AnimalFactory` - class for animal cloning.

The package `interfaces` contains the classes:
- `BasicItemCloneable` - animal cloning interface.
- `Carnivores`, `Herbivores`  - marker interfaces for matching animal groups.

The package `items` contains the packages and class:
- `animals` - 15 classes of different animals and one abstract class `Animal`.
- `board` - class `Cell` - locations on the game board; class `GameBoard` - two-dimensional game array; 
class `ItemPosition` - class for creating and obtaining the coordinates of objects on the playing field.
- `Plants` - plant class.
- `BasicItem` - abstract class for all game subjects.

The package `services` contains the class:
- `ConditionsChecker` - checks the status of game items.
- `ItemCreator` - class for creating all game items.
- `ItemMover` - class responsible for moving items through the game field.
- `ItemPlacer` - places items on the game field according to the terms and conditions.
- `ItemPrinter` - prints all game items and statistical information on the screen.
- `LifeHandler` - responsible for the main functional activities of animals.
- `Utility` - auxiliary class for different tasks.
- `WorldUpdater` - class that updates the status of the game field and items.
___
###Examples of a game screen.

![](images/Screenshot%201.jpg)

End of the game example:

![](images/Screenshot%202.jpg)

Work program in dynamics:

![](images/animation.gif)
