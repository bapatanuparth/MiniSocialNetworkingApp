import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 */
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
 * @param <T>
 * @version 11/17/2022
 *
 */
public interface GraphInterface<T> {

    /**
     * 
     * @param vertexLabel
     *            add to graph
     * @return whether new vertex added or not in graph
     */
    boolean addVertex(T vertexLabel);


    /**
     * 
     * @return removed vertex
     */
    VertexInterface<T> removeVertex(T vertexLabel);


    /**
     * 
     * @param begin
     *            1st vertex
     * @param end
     *            second vertex
     * @param edgeWeight
     *            weight of edge
     * @return whether edge is added between them or not
     */
    boolean addEdge(T begin, T end, double edgeWeight);


    /**
     * 
     * @param begin
     *            1st vertex
     * @param end
     *            2nd vertex
     * @param edgeWeight
     *            weight of the edge
     * @return whether edge removed or not
     */
    boolean removeEdge(T begin, T end, double edgeWeight);


    /**
     * 
     * @param begin
     *            1st vertex
     * @param end
     *            2nd vertex
     * @return remove unweighted edge
     */
    boolean removeEdge(T begin, T end);


    /**
     * 
     * @param begin
     *            1st vertex
     * @param end
     *            2nd vertex
     * @return whether edge exists between 2 vertices
     */
    boolean hasEdge(T begin, T end);


    /**
     * return number of vertices in graph
     * 
     * @return
     */
    int getNumberOfVertices();


    /**
     * Number of undirected edges in graph
     * 
     * @return
     */
    int getNumberOfEdges();


    /**
     * 
     * @return whether graph is empty
     */
    boolean isEmpty();


    /**
     * 
     * @return list of all vertices of the graph
     */
    List<VertexInterface<T>> getVertices();


    /**
     * clear graph
     */
    void clear();


    /**
     * 
     * @param origin
     *            starting point for BFS
     * @return
     */
    Queue<T> getBreadthFirstTraversal(T origin);


    /**
     * 
     * @param origin
     * @param destination
     * @param path
     * @return shortest distance between origin and destination
     */
    int getShortestPath(T origin, T destination, Stack<T> path);

}
