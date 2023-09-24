import java.util.Iterator;

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
public interface VertexInterface<T> {

    /**
     * 
     * @return get vertex label
     */
    T getLabel();


    /**
     * 
     * @return number of neigbor vertices
     */
    int getNumberOfNeighbors();


    /**
     * mark current as visited
     */
    void visit();


    /**
     * mark current as unvisited
     */
    void unvisit();


    /**
     * 
     * @return whether current is visited or not
     */
    boolean isVisited();


    /**
     * 
     * @param endVertex
     * @param edgeWeight
     * @return whether edge was formed between vertices or not
     */
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);


    /**
     * 
     * @param endVertex
     * @return connect to end vertex with unweighted edge
     */
    boolean connect(VertexInterface<T> endVertex);


    /**
     * disconnects edge between vertices
     * 
     * @param endVertex
     * @param edgeWeight
     * @return whether disconnected
     */
    boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);


    /**
     * 
     * @param endVertex
     * @return whether disconnected edge
     */
    boolean disconnect(VertexInterface<T> endVertex);


    /**
     * create iterator by following all edges at this vertex
     * 
     * @return
     */
    Iterator<VertexInterface<T>> getNeighborIterator();


    /**
     * 
     * @return whether the vertex has any neighbor
     */
    boolean hasNeighbor();


    /**
     * 
     * @return get unvisited neigbor
     */
    VertexInterface<T> getUnvisitedNeighbor();


    /**
     * record as previous vertex on path to this vertex
     * 
     * @param predecessor
     */
    void setPredecessor(VertexInterface<T> predecessor);


    /**
     * 
     * @return recorded predecessor
     */
    VertexInterface<T> getPredecessor();


    /**
     * 
     * @return check whether predecessor recorded for this vertex
     */
    boolean hasPredecessor();


    /**
     * 
     * @param newCost
     *            cost of path to this vertex
     */
    void setCost(double newCost);


    /**
     * 
     * @return cost of path to this vertex
     */
    double getCost();
}
