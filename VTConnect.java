import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
public class VTConnect {

    private Graph<Profile> graph;

    /**
     * Empty constructor
     */
    VTConnect() {
        graph = new Graph<>();
    }


    /**
     * 
     * @param p
     *            new user in the network
     */
    void addUser(Profile p) {

        if (this.exists(p) == false)
            graph.addVertex(p);
    }


    /**
     * 
     * @param p
     *            user to be removed
     * @return
     */
    Profile removeUser(Profile p) {
        VertexInterface<Profile> removed = graph.removeVertex(p);
        if (removed != null) {
            List<Profile> templist = new ArrayList<>(p.getFriendProfiles());

            for (int i = 0; i < templist.size(); i++) {
                Profile friend = templist.get(i);
                p.unFriend(friend);
                friend.unFriend(p);
            }
        }
        return removed.getLabel();

    }


    /**
     * 
     * @param a
     *            user 1
     * @param b
     *            user 2
     * @return if friendship was made or not
     */
    boolean createFriendship(Profile a, Profile b) {
        if (graph.addEdge(a, b, 0)) {
            a.addFriend(b);
            b.addFriend(a);
            return true;
        }

        return false;
    }


    /**
     * 
     * @param a
     *            profile a
     * @param b
     *            profile b
     * @return if friendship was removed or not
     */
    boolean removeFriendship(Profile a, Profile b) {
        if (graph.removeEdge(a, b)) {
            a.unFriend(b);
            b.unFriend(a);
            return true;
        }
        return false;
    }


    /**
     * 
     * @param a
     *            user 1
     * @param b
     *            user 2
     * @return if they have friendship or not
     */
    boolean hasFriendship(Profile a, Profile b) {
        return graph.hasEdge(a, b);

    }


    /**
     * 
     * @param startPoint
     *            display each profile and friends from starting point
     */
    void traverse(Profile startPoint) {
        Queue<Profile> traversal = graph.getBreadthFirstTraversal(startPoint);
        for (Profile p : traversal) {
// System.out.println("In for loop");
            p.display();
            System.out.println();
        }
    }


    /**
     * 
     * @param user
     *            input user
     * @return true if user present
     */
    boolean exists(Profile p) {
        List<VertexInterface<Profile>> vertices = graph.getVertices();
        // check whether duplicate profile exists
        for (VertexInterface<Profile> v : vertices) {
            Profile profile = v.getLabel();
            if (profile.getName().equals(p.getName()) && profile.getStatus()
                .equals(p.getStatus()) && profile.getFriendProfiles().equals(p
                    .getFriendProfiles())) {

                return true;
            }
        }

        return false;
    }


    /**
     * 
     * @param user
     * @return suggested friends
     */
    List<Profile> friendSuggestion(Profile user) {
        return graph.suggestFriends(user);
    }


    /**
     * 
     * @param a
     * @param b
     * @return distance between two friendships
     */
    int friendshipDistance(Profile a, Profile b) {
        Stack<Profile> path = new Stack<>();
        int distance = graph.getShortestPath(a, b, path);
        return distance == Integer.MAX_VALUE ? -1 : distance;

    }

}
