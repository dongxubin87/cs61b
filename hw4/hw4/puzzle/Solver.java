package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashSet;
import java.util.LinkedList;


public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode prev;
        private int priority;

        public SearchNode(WorldState state, int moves, SearchNode prev) {
            this.state = state;
            this.moves = moves;
            this.prev = prev;
            this.priority = moves + this.state.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    private SearchNode goalNode;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        HashSet<WorldState> visited = new HashSet<>();

        pq.insert(new SearchNode(initial, 0, null));

        while (!pq.isEmpty()) {
            SearchNode curr = pq.delMin();

            if (curr.state.isGoal()) {
                goalNode = curr;
                return;
            }

            if (visited.contains(curr.state)) {
                continue;
            }

            visited.add(curr.state);

            for (WorldState neighbor : curr.state.neighbors()) {
                if (curr.prev != null && neighbor.equals(curr.prev.state)) {
                    continue;
                }

                if (!visited.contains(neighbor)) {
                    pq.insert(new SearchNode(neighbor, curr.moves + 1, curr));
                }
            }
        }
    }

    public int moves() {
        if (goalNode == null) {
            throw new IllegalStateException("Solver did not find a solution.");
        }
        return goalNode.moves;
    }

    public Iterable<WorldState> solution() {
        LinkedList<WorldState> path = new LinkedList();
        SearchNode current = goalNode;
        while (current != null) {
            path.addFirst(current.state);
            current = current.prev;
        }
        return path;
    }

}
