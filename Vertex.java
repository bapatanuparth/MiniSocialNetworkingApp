import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * //On my honor:
 * //
 * // - I have not used source code obtained from another student,
 * // or any other unauthorized source, either modified or
 * // unmodified.
 * //
 * // - All source code and documentation used in my program is
 * // either my original work, or was derived by me from the
 * // source code published in the textbook for this course.
 * //
 * // - I have not discussed coding details about this project with
 * // anyone other than my partner (in the case of a joint
 * // submission), instructor, ACM/UPE tutors or the TAs assigned
 * // to this course. I understand that I may discuss the concepts
 * // of this program with other students, and that another student
 * // may help me debug my program so long as neither of us writes
 * // anything during the discussion or modifies any computer file
 * // during the discussion. I have violated neither the spirit nor
 * // letter of this restriction.
 * //-- 906548673
 * 
 */

/**
 * @author Parth
 * @version 11/17/2022
 *
 */
public class Vertex<T> implements VertexInterface<T> {

    class Edge {
        /**
         * end vertex of the edge
         */
        private VertexInterface<T> vertex; // destination vertex
        /**
         * weight of the edge
         */
        private double weight;

        /**
         * 
         * @param endVertex
         * @param edgeWeight
         *            constructor to initialize edge of the vertex
         */
        protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
            vertex = endVertex;
            weight = edgeWeight;
        }


        /**
         * 
         * @return destination vertex of the edge
         */
        protected VertexInterface<T> getEndVertex() {
            return vertex;
        }


        /**
         * 
         * @return weight of the edge
         */
        protected double getWeight() {
            return weight;
        }

    }

    /**
     * name of the vertex
     */
    private T label; // name of vertex
    /**
     * mark if vertex is visited
     */
    private boolean visited; // true if visited
    /**
     * mark previous vertex
     */
    private VertexInterface<T> previousVertex; // on path to this vertex
    /**
     * cost of path to the vertex
     */
    private double cost; // of path to this vertex
    /**
     * edge list of the vertex
     */
    private List<Edge> edgeList; // edges to neighbors

    /**
     * 
     * @param vertexLabel
     *            initialize vertex
     */
    Vertex(T vertexLabel) {
        label = vertexLabel;
        visited = false;
        previousVertex = null;
        cost = 0;
        edgeList = new ArrayList<>();
    }


    /**
     * get label
     */
    @Override
    public T getLabel() {
        return this.label;
    }


    /**
     * get number of friends of label
     */
    @Override
    public int getNumberOfNeighbors() {
        return this.edgeList.size();
    }


    /**
     * mark the vertex as visited
     */
    @Override
    public void visit() {
        this.visited = true;

    }


    /**
     * mark as unvisited
     */
    @Override
    public void unvisit() {
        this.visited = false;

    }


    /**
     * check if visited
     */
    @Override
    public boolean isVisited() {
        return this.visited;
    }


    /**
     * form an edge with the destination vertex
     * 
     * @param endvertex
     *            destination
     * @param edgeWeight
     *            weight of edge
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        if (!this.equals(endVertex)) {

            // fetch iterator
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            // while iterator has next element
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (nextNeighbor.equals(endVertex)) {// check if duplicate
                    duplicateEdge = true;
                    break;
                }
            }
            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, edgeWeight));
                endVertex.connect(this, edgeWeight);
                result = true;
            }
        }
        return result;
    }


    /**
     * form an edge with weight Zero
     * 
     * @param endVertex
     *            desstination
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        boolean result = false;
        if (!this.equals(endVertex)) {

            // fetch iterator
            Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            // while iterator has next element
            while (!duplicateEdge && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (nextNeighbor.equals(endVertex)) {// check if duplicate
                    duplicateEdge = true;
                    break;
                }
            }
            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, 0));
                result = true;
            }
        }
        return result;
    }


    /**
     * disconnect the edge between vertices
     * 
     * @param endVertex
     *            destination
     * @param edgeWeight
     *            weight of edge to be removed
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean result = false;
        if (!this.equals(endVertex)) {

            boolean found = false;
            for (Edge e : edgeList) {
                if (e.getEndVertex().equals(endVertex)) {
                    found = true;
                    edgeList.remove(e);
                    break;
                }
            }

            if (found) {
                result = true;
            }
        }
        return result;
    }


    /**
     * disconnect the two vertices
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        return this.disconnect(endVertex, 0);
    }


    /**
     * get neighbor iterator
     */
    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }


    /**
     * check if vertex has neighbor
     */
    @Override
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }


    /**
     * get unvisited neighbor of the vertex
     */
    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {

        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = this.getNeighborIterator();

        // while iterator has next element and result not found yet
        while (neighbors.hasNext() && result == null) {

            VertexInterface<T> nextNeighbor = neighbors.next();

            if (!nextNeighbor.isVisited()) {// check if visited
                result = nextNeighbor;
            }
        }

        return result;
    }


    /**
     * set predecessor
     */
    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;

    }


    /**
     * get predecessor
     */
    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }


    /**
     * has predecessor
     */
    @Override
    public boolean hasPredecessor() {
        return previousVertex != null;
    }


    /**
     * set cost to reach the vertex
     */
    @Override
    public void setCost(double newCost) {
        this.cost = newCost;

    }


    /**
     * get the path cost of the vertex
     */
    @Override
    public double getCost() {
        return this.cost;
    }


    /**
     * check equality of Vertices
     */
    public boolean equals(Object other) {
        boolean result;
        if ((other == null) || (getClass() != other.getClass()))
            return false;
        else {
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        }
        return result;
    }

    /**
     * 
     * @author Parth
     *         get neighbor iterator
     *
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>> {

        /**
         * edged of vertex
         */
        private Iterator<Edge> edges;

        /**
         * neighbor iterator constructor
         */
        private NeighborIterator() {
            edges = edgeList.listIterator();
        }


        /**
         * check if iterator has next element
         */
        @Override
        public boolean hasNext() {

            return edges.hasNext();
        }


        /**
         * go to the next element of iterator
         */
        @Override
        public VertexInterface<T> next() {

            VertexInterface<T> nextNeighbor = null;
            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        }

    }

}
