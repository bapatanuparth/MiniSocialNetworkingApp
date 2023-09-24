import java.util.ArrayList;

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
public class Profile {

    /**
     * name of the person
     */
    private String name;

    /**
     * status of the person
     */
    private String status;

    /**
     * Friends of the person
     */
    private ArrayList<Profile> friendProfiles;

    /**
     * Empty Constructor
     */
    public Profile() {
        this.friendProfiles = new ArrayList<>();
    }


    /**
     * 
     * @param name
     *            name of the user
     * @param status
     *            status of the user
     * @param friendProfiles
     *            friends of the user
     */
    public Profile(
        String name,
        String status,
        ArrayList<Profile> friendProfiles) {
        this.name = name;
        this.status = status;
        this.friendProfiles = friendProfiles;

    }


    /**
     * 
     * @param name
     *            of the user
     * @param status
     *            of the user
     */
    public Profile(String name, String status) {
        this.name = name;
        this.status = status;
        this.friendProfiles = new ArrayList<>();
    }


    /**
     * 
     * @param firstName
     * @param lastName
     */
    public void setName(String firstName, String lastName) {
        this.name = firstName + " " + lastName;
    }


    /**
     * 
     * @return name of the user
     */
    public String getName() {
        return name;
    }


    /**
     * 
     * @return status of the user
     */
    public String getStatus() {
        return status;
    }


    /**
     * 
     * @param status
     *            set the status of user
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 
     * @return list of friends of user
     */
    public ArrayList<Profile> getFriendProfiles() {
        return friendProfiles;
    }


    /**
     * 
     * @param friendProfiles
     *            list of friends
     */
    public void setFriendProfiles(ArrayList<Profile> friendProfiles) {
        this.friendProfiles = friendProfiles;
    }


    /**
     * displays profile and friends profile
     */
    void display() {
        System.out.println("Name: " + this.getName());
        System.out.println("\tStatus: " + this.getStatus());
        System.out.println("\tNumber of friend profiles: " + this.friendProfiles
            .size());
        System.out.println("Friends:");
        for (Profile p : this.friendProfiles) {
            System.out.println("\t" + p.getName());
        }
        // System.out.println();
    }


    /**
     * 
     * @param user
     *            add new user to the friend list
     */
    void addFriend(Profile user) {
        this.friendProfiles.add(user);
    }


    /**
     * 
     * @param user
     * @return true if a connection was removed successfully
     */
    boolean unFriend(Profile user) {
        return friendProfiles.remove(user);

    }


    @Override
    public String toString() {
        return "Name: " + this.name + "\n\tStatus: " + this.status
            + "\n\tNumber of friend profiles: " + this.friendProfiles.size()
            + "\n";
    }

}
