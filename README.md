# X+Y Sorting Algorithm With DAG
This is my attempt at solving [X+Y sorting](https://en.wikipedia.org/wiki/X_%2B_Y_sorting) problem. It seems that this algorithm achieves O(n<sup>2</sup>) time complexity. It is not yet confirmed by others. If you find any issue, please let me know in [Issues](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/issues) tab.

## Idea
### Cartesian coordinate system
Because the [Cartesian product](https://en.wikipedia.org/wiki/Cartesian_product) is sorted by the sum, an equation **x+y=c** can be used as a property. The problem can be solved by adding all pairs that satisfies the equation while increasing the constant **c**.

![output0](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/a/a.gif?raw=true)

### Directed Acyclic Graph

![output1](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/b/b.png?raw=true)

The problem is converted into a [directed acyclic graph](https://en.wikipedia.org/wiki/Directed_acyclic_graph) so that the algorithm can look up the pairs by following the edges. The cost represents the distance from one point to another. Edges going up (except for the first column) are redundant because moving from one vertex to another costs the same for all paths. By removing redundant edges, the efficiency is increased by visiting all vertices only once. (In fact, the algorithm assumes that each vertex will be visited only once.)

### Algorithm

`r` is the root vertex of a graph.
```
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
15       end
16     end
17   end
18   return O
```

![output2](https://github.com/Bunnyspa/Algorithm-XPlusYSorting/blob/main/images/c/c.gif?raw=true)

This is a working algorithm but lacks efficiency for some cases. If a problem contains a set that has repeated values, the graph will contain edges with 0 distances. Line 9-15 processes only one vertex even though some verticies may be 0 distance away from the vertex. This problem can be solved by checking for those vertices recursively.
```
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
14       end
15     end
16   end
17   return O
18
19 Add(v, O, E):
20   add v to O
21   for e in each v.edges:
22     if e.distance == 0:
23       Add(e.vertex, O, E)
24     else:
25       add e to E
26     end
27   end
```
Line 13-14 from the previous pseudocode is modified. `Add` is a recursive function that adds the vertex V and all vertices 0 distance away from the vertex V to the output list. It also adds all edges that have not been explored yet to the edge list.

## Test
I ran 3 tests with different settings. Each run consists of fixed set sizes: 100, 200, and 400. "Iteration Steps" counts how many times the loop (Line 9-14) is performed. "Addition Steps" counts how many times the element is added to the output list (Line 20). I have verified that all the tests are sorted properly. The test code is `test()` function inside `App` class.

### Repeat
Each set consists of repeated 1's. i.e. X = {1, 1, ...}; Y = {1, 1, ...}

| Size            | 100    | 200    | 400     |
|-----------------|--------|--------|---------|
| Iteration Steps | 2      | 2      | 2       |
| Addition Steps  | 10,000 | 40,000 | 160,000 |

### Step 1
Each set consists of incrementing elements by 1. i.e. X = {0, 1, 2, ...}; Y = {0, 1, 2, ...}

| Size            | 100    | 200    | 400     |
|-----------------|--------|--------|---------|
| Iteration Steps | 9,999  | 39,999 | 159,999 |
| Addition Steps  | 10,000 | 40,000 | 160,000 |

### Step 7/11
Each set consists of incrementing elements by 7 or 11. i.e. X = {0, 7, 14, ...}; Y = {0, 11, 22, ...}


| Size            | 100    | 200     | 400       |
|-----------------|--------|---------|-----------|
| Iteration Steps | 70,203 | 280,603 | 1,121,403 |
| Addition Steps  | 10,000 | 40,000  | 160,000   |
