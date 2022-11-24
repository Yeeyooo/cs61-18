package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private final int s;

    private final int t;

    private boolean targetFound = false;

    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);

        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        announce();
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int q = queue.dequeue();
            for (int neighbor : maze.adj(q)) {
                if (!marked[neighbor]) {
                    edgeTo[neighbor] = q;
                    announce();
                    distTo[neighbor] = distTo[q] + 1;
                    marked[neighbor] = true;
                    announce();
                    if (neighbor == t) {
                        targetFound = true;
                    }
                    if (targetFound) {
                        return;
                    }
                    queue.enqueue(neighbor);
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}

