# space-visualizations
Visualizing functions and spaces in multiple dimensions through coloring techniques, vector fields, user manipulation, and perspective changes. Relies on Java StdDraw drawing for rendering shapes.

## To run the program:
Set current working directory to spacevisuals
% cd spacevisuals

Ensure you have access permissions
% chmod +x run.sh

Start the program with arguments[i=0], --functionhandle optional
% ./run.sh <animation[i]> <functionhandle> <animation[i+1]> <functionhandle>

### Help:
#### animation options:
animation 'f(x)' 'f(y)' 'f(z)'

animation options:
basic2d

graph2d

vectorfield2d

domaincolor

pointmap2d

juliaset

mandelbrot

gradient

parametriccurve

arcdeformation

basic3d

vectorfield3d

graph3d

polygons3d

spheremagnet

lorenzattractor

basic4d

polygons4d

sphere4d

##### animation input parameters should be based on the same space, meaning either all 2D spaces or all 3D spaces.