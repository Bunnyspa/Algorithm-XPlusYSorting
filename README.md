# X+Y Sorting Algorithm With DAG
This is my attempt at solving [X+Y sorting](https://en.wikipedia.org/wiki/X_%2B_Y_sorting) problem. It seems that this algorithm achieves O(n<sup>2</sup>) time complexity, but it is not confirmed.

## Idea
### Cartesian coordinate system
Because the [Cartesian product](https://en.wikipedia.org/wiki/Cartesian_product) is sorted by the sum, an equation **x+y=c** can be used as a property. The problem can be solved by adding all pairs that satisfies the equation while increasing the constant **c**.

![output0](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/a/a.gif?raw=true)

### Directed Acyclic Graph

![output1](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/b/b.png?raw=true)

The problem is converted into a [directed acyclic graph](https://en.wikipedia.org/wiki/Directed_acyclic_graph) so that the algorithm can look up the pairs by following the edges. The cost represents the distance from one point to another. Edges going up (except for the first column) are redundant because moving from one vertex to another costs the same for all paths. By removing redundant edges, the efficiency is increased by visiting all vertices only once. (In fact, the algorithm assumes that each vertex will be visited only once.)

### Algorithm

![output2](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/c/c.gif?raw=true)

R is the root vertex of a graph.
```
Sort(r):
  O = output list
  E = edge list
  add r to O
  add r.edges to E
  while E is not empty:
    m = the minimum distance of edges in Q
    for e in each E:
      e.distance -= m
      if e.distance == 0:
        remove e from E
        Add(e.vertex, O, E)
  return O

Add(V, O, E):
  add V to O
  for e in each V.edges:
    if e.distance == 0:
      Add(e.vertex, O, E)
    else:
      add e to E
```
Add() adds the vertex and all vertices 0 distance away from the vertex to the output list. It also adds all edges that have not been explored yet to the edge list.
