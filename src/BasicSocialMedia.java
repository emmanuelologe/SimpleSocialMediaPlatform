
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicSocialMedia {
    private Map<String, UserProfile> users;

    public BasicSocialMedia() {
        users = new HashMap<>();
    }

    // Method to create a new user
    public void createUser(String username, String bio) {
        if (!users.containsKey(username)) {
            users.put(username, new UserProfile(username, bio));
            System.out.println("User created: " + username);
        } else {
            System.out.println("Username already exists.");
        }
    }

    // Method to follow a user
    public void followUser(String followerUsername, String followeeUsername) {
        UserProfile follower = users.get(followerUsername);
        UserProfile followee = users.get(followeeUsername);

        if (follower == null || followee == null) {
            System.out.println("One of the users does not exist.");
            return;
        }

        follower.follow(followeeUsername);
        System.out.println(followerUsername + " is now following " + followeeUsername);
    }

    // Method to list the users a specific user is following, including their bios
    public void listFollowing(String username) {
        UserProfile user = users.get(username);
        if (user != null) {
            user.listFollowing(users);
        } else {
            System.out.println("User not found.");
        }
    }
    
        // Method to post a text update for a user
    public void postTextUpdate(String username, String update) {
        UserProfile user = users.get(username);
        if (user != null) {
            user.addPost(update);
            System.out.println(username + " posted: " + update);
        } else {
            System.out.println("User not found.");
        }
    }

    // Method to post an image update for a user
    public void postImageUpdate(String username, String update, String imageUrl) {
        UserProfile user = users.get(username);
        if (user != null) {
            user.addPost(update, imageUrl);
            System.out.println(username + " posted: " + update + " [Image: " + imageUrl + "]");
        } else {
            System.out.println("User not found.");
        }
    }

    // Method to display a user's feed, including posts from users they follow
    public void displayFeed(String username) {
        UserProfile user = users.get(username);
        if (user != null) {
            System.out.println("Feed for " + username + ":");
            for (String followedUser : user.getFollowing()) {
                UserProfile followedProfile = users.get(followedUser);
                if (followedProfile != null) {
                    List<UserPost> posts = followedProfile.getPosts();
                    if (!posts.isEmpty()) {
                        System.out.println("Posts by " + followedProfile.getUsername() + ":");
                        for (UserPost post : posts) {
                            System.out.println("- " + post.displayPost());
                        }
                    } else {
                        System.out.println(followedProfile.getUsername() + " has no posts.");
                    }
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Method to save data to files (omitted for simplicity)
    public void saveData() throws IOException {
        // Implement saving data to files
    }

    // Method to load data from files (omitted for simplicity)
    public void loadData() throws IOException {
        // Implement loading data from files
    }

    // Main method to run the platform
        public static void main(String[] args) {
        BasicSocialMedia platform = new BasicSocialMedia();

        // Creating users
        platform.createUser("Alice", "Loves cats");
        platform.createUser("Bob", "Enjoys hiking");

        // // Creating posts
        // platform.createPost("Alice", "Hello world!", null);
        // platform.createPost("Bob", "Check out this cool photo!", "http://example.com/photo.jpg");

        // Following users
        platform.followUser("Alice", "Bob");

        // Display Alice's following list with bios
        platform.listFollowing("Alice");
        // Posting text and image updates
        platform.postTextUpdate("Alice", "Enjoying the sunny weather today!");
        platform.postImageUpdate("Bob", "Hiking the mountain trails.", "http://example.com/mountain.jpg");
        platform.postTextUpdate("Charlie", "Captured a beautiful sunset.");
        // Display feed
        System.out.println("Alice's feed:");
        platform.displayFeed("Alice");

        // Save data to files
        try {
            platform.saveData();
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }

        // Load data from files
        try {
            platform.loadData();
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Failed to load data: " + e.getMessage());
        }
    }
}
