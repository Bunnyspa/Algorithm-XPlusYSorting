# X+Y Sorting Algorithm With DAG
This is my attempt at solving [X+Y sorting](https://en.wikipedia.org/wiki/X_%2B_Y_sorting) problem. I am not sure if this algorithm truly achieves <img src="https://latex.codecogs.com/gif.latex?O(n^2)" title="O(n^2)" /> time complexity.

## Idea
### Points
Because the Cartesian product is sorted by the sum, an equation <img src="https://latex.codecogs.com/gif.latex?x&plus;y=c" title="x+y=c" /> can be used as a property. The problem can be solved by adding all pairs that satisfies the equation while increasing the constant <img src="https://latex.codecogs.com/gif.latex?c" title="c" />.

![output0](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/a/a.gif?raw=true)

### Directed Acyclic Graph

![output0](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/b/b.png?raw=true)

The problem is converted into a [DAG](https://en.wikipedia.org/wiki/Directed_acyclic_graph) so that the algorithm can look up the pairs by following the edges. The cost represents the distance from one point to another. Edges going up (except for the first column) are redundant because moving from one vertex to another costs the same for all paths. By removing redundant edges, the efficiency is increased by visiting all vertices only once. In fact, the algorithm assumes that each vertex will be visited only once.
