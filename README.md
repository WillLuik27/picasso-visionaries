# Team Visionaries

Authors - Cailen, Joe, Micah, Tim, Will

# Picasso

An application that allows the user to create expressions that
evaluate to colors and then eventually to images.

The given code base is a good start, but it is sparsely documented
(document each method/part after you understand what it's doing), and,
as your application grows, you may need to refactor.

See the specification for Picasso on the course web site.

## Extensions

1.) Button that evaluates a random expression. To test, hit the 
'random' button in the top left corner of the viewer.

2.) View previously executed expressions. To test, execute a few expressions, then use the 'up' and 'down' arrows on the keyboard to scroll through the history of expressions (works like the command-line).

3.) Binary expression nodes are pruned to simply redundant operations like multiplying by 1. This was in an effort to increase the efficiency of evaluations. Operations with pruned logic include addition, subtraction, multiplication, division, exponents, and modulus.  

4.) Fractal button that recursively evaluates the expression to create a fractal pattern. To use the extension type in an expression and click the fractal button in the GUI. 

5.) Allow users to view multiple images at once using separate windows. To test, hit the "new window" button at the bottom right of the viewer.
## Running Picasso

To run Picasso, run `picasso.Main`

## Project Organization

`src` - the source code for the project

`conf` - the configuration files for the project

`images` - contains some sample images generated from Picasso. Some of the expressions for these images can be found in the `expressions` directory.

## Code Base History

This code base originated as a project in a course at Duke University.  The professors realized that the code could be designed better and refactored.  This code base has some code leftover from the original version.

# picasso-visionaries
