import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
// -- 906548673
/**
 * @author Parth
 *
 */
public class Graph<T> implements GraphInterface<T> {

    /**
     * graph represented as a hashmap
     */
    private Map<T, VertexInterface<T>> vertices;
    /**
     * number of edges in the graph
     */
    private int edgeCount;

    /**
     * create graph object
     */
    public Graph() {
        vertices = new HashMap<>();
        edgeCount = 0;
    }


    /**
     * add vertex with new label
     */
    @Override
    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> addOutcome = vertices.put(vertexLabel, new Vertex<T>(
            vertexLabel));
        return addOutcome == null;
    }


    /**
     * remove vertex from graph
     */
    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {
        // remove all the edges of the vertices as well
        if (vertices.containsKey(vertexLabel)) {
            VertexInterface<T> vertex = vertices.get(vertexLabel);

            Iterator<VertexInterface<T>> neighbors = vertex
                .getNeighborIterator();

            List<T> friends = new ArrayList<>();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                friends.add(nextNeighbor.getLabel());
            }

            for (T friend : friends) {
                this.removeEdge(vertexLabel, friend);
            }
            vertices.remove(vertexLabel);
            return vertex;
        }
        else
            return null;
    }


    /**
     * add edge between vertices
     */
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {

        boolean result = false;
        boolean result2 = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ((beginVertex != null) && (endVertex != null)) {
            result = beginVertex.connect(endVertex);
            result2 = endVertex.connect(beginVertex);
        }
        if (result && result2)
            edgeCount += 2;
        return result && result2;
    }


    /**
     * remove edge between vertices
     */
    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {

        VertexInterface<T> start = vertices.get(begin);
        VertexInterface<T> finish = vertices.get(end);
        if (start != null && finish != null) {
            if (start.disconnect(finish, 0) && finish.disconnect(start)) {
                edgeCount -= 2;
                return true;
            }

        }
        return false;
    }


    /**
     * remove unweighted edge
     */
    @Override
    public boolean removeEdge(T begin, T end) {
        return this.removeEdge(begin, end, 0);
    }


    /**
     * check if the 2 vertices are connected
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;

        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> neighbors = beginVertex
                .getNeighborIterator();
            while (!found && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    found = true;
            }
        }

        return found;

    }


    /**
     * number of vertices in the graph
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }


    /**
     * get number of edges
     */
    @Override
    public int getNumberOfEdges() {
        // TODO Auto-generated method stub
        return edgeCount;
    }


    /*
     * 8
     * check if the graph is empty
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return vertices.isEmpty();
    }


    /**
     * get vertices of the graph
     */
    @Override
    public List<VertexInterface<T>> getVertices() {

        List<VertexInterface<T>> list = new ArrayList<>();

        for (T vertex : vertices.keySet()) {
            list.add(vertices.get(vertex));
        }
        return list;
    }


    /**
     * clear the graph
     */
    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;

    }


    /**
     * reset vertices and their weights for next processing
     */
    void resetVertices() {
        for (T vertexLabel : vertices.keySet()) {
            VertexInterface<T> vertex = vertices.get(vertexLabel);
            vertex.unvisit();
            vertex.setCost(0);
            vertex.setPredecessor(null);
        }
    }


    /*
     * 
     * get breadth first search of the graph
     * 
     * @param origin starting point
     */
    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {
        // reset values of visited and costs
        resetVertices();

        // create queue to store traversal order
        Queue<T> traversalOrder = new ArrayDeque<>();
        Queue<VertexInterface<T>> vertexQueue = new ArrayDeque<>();

        VertexInterface<T> originVertex = vertices.get(origin);
        if (originVertex != null) {
            originVertex.visit();
            // add to queues
            traversalOrder.offer(origin);
            vertexQueue.offer(originVertex);

            // use BFS
            while (!vertexQueue.isEmpty()) {
                VertexInterface<T> frontVertex = vertexQueue.poll();
                // get neighbors of q.front()
                Iterator<VertexInterface<T>> neighbors = frontVertex
                    .getNeighborIterator();
                // for all neighbors, add to queue, and add to traversal order
                while (neighbors.hasNext()) {
                    VertexInterface<T> nextNeighbor = neighbors.next();
                    // add only if not visited
                    if (!nextNeighbor.isVisited()) {
                        nextNeighbor.visit();
                        traversalOrder.offer(nextNeighbor.getLabel());
                        vertexQueue.offer(nextNeighbor);
                    }
                }
            }
        }

        return traversalOrder;
    }


    /**
     * get shortest path between origin and destination
     * 
     * @param origin
     *            starting point
     * @param destination
     *            ending point
     * @param path
     *            shortest path
     */
    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {
        // reset values of visited and costs
        resetVertices();
        boolean done = false;

        Queue<VertexInterface<T>> vertexQueue = new ArrayDeque<>();

        VertexInterface<T> originVertex = vertices.get(origin);
        VertexInterface<T> destinationVertex = vertices.get(destination);
        if (originVertex == null || destinationVertex == null)
            return Integer.MAX_VALUE;
        originVertex.visit();
        vertexQueue.offer(originVertex);

        // use BFS
        while (!done && !vertexQueue.isEmpty()) {
            // get neighbors of q.front()

            VertexInterface<T> frontVertex = vertexQueue.poll();
            Iterator<VertexInterface<T>> neighbors = frontVertex
                .getNeighborIterator();
            // for all neighbors, add to queue, and add to traversal order
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                // add only if not visited
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(frontVertex.getCost() + 1);
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.offer(nextNeighbor);
                }

                if (nextNeighbor.equals(destinationVertex)) {
                    done = true;
                }
            }

        }
        int pathLength = Integer.MAX_VALUE;
        if (done) {
            pathLength = (int)destinationVertex.getCost();
            path.push(destination);

            VertexInterface<T> vertex = destinationVertex;
            while (vertex.getPredecessor() != null) {
                vertex = vertex.getPredecessor();
                path.push(vertex.getLabel());
            }

        }
        return pathLength;
    }


    /**
     * suggest friends to origin based on friends of friends
     * 
     * @param origin
     * @return
     */
    public List<T> suggestFriends(T origin) {
        if (!vertices.containsKey(origin))
            return null;
        List<T> result = new ArrayList<>();
        VertexInterface<T> originVertex = vertices.get(origin);
        List<VertexInterface<T>> setFriends = new ArrayList<>();

        // create a set of all friends that origin has right now
        Iterator<VertexInterface<T>> neighbors = originVertex
            .getNeighborIterator();

        // while iterator has next element

        while (neighbors.hasNext()) {
            VertexInterface<T> nextNeighbor = neighbors.next();
            setFriends.add(nextNeighbor);
        }

        // iterate over all the friends of origin
        Iterator<VertexInterface<T>> friends = setFriends.iterator();
        while (friends.hasNext()) {
            // friend of origin
            VertexInterface<T> nextNeighbor = friends.next();
            Iterator<VertexInterface<T>> friendsOfFriend = nextNeighbor
                .getNeighborIterator();
            while (friendsOfFriend.hasNext()) {
                VertexInterface<T> nextFriend = friendsOfFriend.next();
                if (!setFriends.contains(nextFriend)
                    && nextFriend != originVertex && !result.contains(nextFriend
                        .getLabel())) {
                    result.add(nextFriend.getLabel());
                }
            }

        }

        return result;
    }


    /**
     * check if graph has this vertex
     * 
     * @param vertexLabel
     * @return
     */
    public boolean hasVertex(T vertexLabel) {
        return vertices.containsKey(vertexLabel);
    }

}
