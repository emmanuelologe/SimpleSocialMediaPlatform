
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserProfile {
    private String username;
    private String bio;
    private Set<String> following;
    private List<UserPost> posts;

    public UserProfile(String username, String bio) {
        this.username = username;
        this.bio = bio;
        this.following = new HashSet<>();
        this.posts = new ArrayList<>();
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for bio
    public String getBio() {
        return bio;
    }

    // Method to follow another user
    public void follow(String userToFollow) {
        following.add(userToFollow);
    }
    // Method to add a text post
    public void addPost(String content) {
        posts.add(new UserPost(this, content));
    }

    // Method to add an image post
    public void addPost(String content, String imageUrl) {
        posts.add(new UserPost(this, content, imageUrl));
    }

    // Method to get the list of posts
    public List<UserPost> getPosts() {
        return posts;
    }

    // Method to get the list of users this user is following
    public Set<String> getFollowing() {
        return following;
    }

    // Method to list all users this user is following, with their bios
    public void listFollowing(Map<String, UserProfile> allUsers) {
        if (following.isEmpty()) {
            System.out.println(username + " is not following anyone.");
        } else {
            System.out.println(username + " is following:");
            for (String user : following) {
                UserProfile followedUser = allUsers.get(user);
                if (followedUser != null) {
                    System.out.println("- " + followedUser.getUsername() + ": " + followedUser.getBio());
                }
            }
        }
    }

    // Method to display user information
    public void displayUserInfo() {
        System.out.println("Username: " + username);
        System.out.println("Bio: " + bio);
        System.out.println("Following: " + following.size() + " users");
    }
}
