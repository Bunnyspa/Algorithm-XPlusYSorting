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

`r` is the root vertex of a graph.
<pre><code>
 1 Sort(r):
 2   O = output list
 3   E = edge list
 4   add r to O
 5   add r.edges to E
 6   while E is not empty:
 7     m = the minimum distance of edges in Q
 8     for e in each E:
 9       e.distance -= m
10       if e.distance == 0:
11         remove e from E
12         v = e.vertex
13         add v to O
14         add v.edges to E
15   return O
</code></pre>
This is a simple algorithm that works but lacks efficiency for some cases. If a problem contains sets that have repeated values, the graph will contain edges with 0 distances. Line 9-14 processes only one vertex even though some verticies may be 0 distance away from the vertex. This problem can be solved by checking for those vertices recursively.
<pre><code>
 1 Sort(r):
 2   O = output list
 3   E = edge list
 4   add r to O
 5   add r.edges to E
 6   while E is not empty:
 7     m = the minimum distance of edges in Q
 8     for e in each E:
 9       e.distance -= m
10       if e.distance == 0:
11         remove e from E
12         v = e.vertex
13         Add(v, O, E)
14   return O

15 Add(V, O, E):
16   add V to O
17   for e in each V.edges:
18     if e.distance == 0:
19       Add(e.vertex, O, E)
20     else:
21       add e to E
</code></pre>
Line 13-14 from the previous pseudocode is modified. `Add` is a recursive function that adds the vertex V and all vertices 0 distance away from the vertex V to the output list. It also adds all edges that have not been explored yet to the edge list.
