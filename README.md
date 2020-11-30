Dice Arithmetic
===
This project is about computing the result of arithmetic operations including dice rolls based on a formula string.

Examples
---
These are some example formulas and all their possible results:

| Formula | Possible results |
| :---: | --- | 
| d6 | 1, 2, 3, 4, 5, 6 |
| d8 | 1, 2, 3, 4, 5, 6, 7, 8 |
| d6 + 3 | 4, 5, 6, 7, 8, 9 |
| d6 * 2 | 2, 4, 6, 8, 10, 12 |
| 2d6 | 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 |
| d6 + d6 | 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 |
| d6 + d4 | 2, 3, 4, 5, 6, 7, 8, 9, 10 |

Project structure
---
The overall project consists of two subprojects.

### Core
Here all the core functionality can be found.
The main entry point for calling the whole function is the ***rollDice*** function in [DiceArithmetic.kt](core/src/main/kotlin/com/github/sourcefranke/dicearithmetic/core/DiceArithmetic.kt).

### Rest
This subproject offers the possibility to deploy and run the software as a microservice, offering some basic REST interface.

The REST API currently contains:

| URL | HTTP method | Parameters |
| --- | :---: | --- |
| /api/roll/{formula} | GET | formula - string defining the arithmetic operations |
| /api/roll/{times}/{formula} | GET | times - number of rolls to be executed <br> formula - string defining the arithmetic operations |

#### Example
If you start the REST module by executing the main method, you can call e.g. the following link with your preferred web browser:
http://localhost:8080/api/roll/5/d6

One possible response you could see in your browser tab could look like:
```json
{
  "formula" : "d6",
  "min" : 1,
  "max" : 6,
  "results" : [ 6, 1, 3, 4, 3 ]
}
```

Roadmap
---
I already thought of some possible advancements I could implement in the near future!

### Subtraction
Yes, I know, this one basic arithmetic operation is still missing ... coming soon! 

### Explicit listing of dice values
By writing e.g. ...
> d[ 6, 13, 25, 42 ]

... it could be possible to create a custom dice with all the values you want it to have.

#### Prime dice?
Based on explicit listings, one possible enhancement could be predefined set, e.g. primes.
Writing ...
> prime[ 100 ]

... you then get all prime numbers till the upper (including) border of 100.

By writing two number, like seen here:
> prime[ 50, 100 ]

... you then define both a minimum, and a maximum number for the defined range.

#### Range
Speaking of range ... analog to primes I could introduce a term for just using every number within borders:
> range[ 50, 100 ]

#### Weighted probabilities
Another opportunity explicit listings could offer are weighted probabilities.

By writing e.g. ...
> d[ 1(3), 2(4), 3(2), 4(1) ]

... you then could define different probabilities.
In the given example you would roll:
* a 1 in three of 10 cases
* a 2 in four of 10 cases
* a 3 in two of 10 cases
* a 4 in one of 10 cases

### Brackets
As you might know:
> (2 + d6) * 4

... will return different results than ...
> 2 + (d6 * 4)

### Custom Extensions
I still need to invest some brain power into a smooth possibility of providing a way of extending the core functionalities to other developers ... without overriding existing methods.
