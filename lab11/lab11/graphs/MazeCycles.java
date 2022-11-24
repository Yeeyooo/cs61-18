package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean hasCycle = false;

    private final Maze maze;
    private int[] nodeTo;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.V()];
    }

    @Override
    public void solve() {

        for (int v = 0; v < maze.V(); v++) {
            if (!marked[v]) {
                distTo[v] = 0;
                dfs(-1, v);
            }
        }

    }


    private void dfs(int u, int v) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                nodeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                dfs(v, w);
            }
            else if(w != u) {
                edgeTo[w] = v;
                announce();
                for (int x = v; x != w; x = nodeTo[x]) {
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
                hasCycle = true;
            }
            if (hasCycle) return;
        }
    }
}

