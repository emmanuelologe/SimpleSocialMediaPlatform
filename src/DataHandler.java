import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

public class DataHandler {
    private static final String USER_FILE = "data/users.txt";
    private static final String POST_FILE = "data/posts.txt";

    // Save user data to file
    public static void saveUsers(HashMap<String, UserProfile> users) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE))) {
            for (UserProfile user : users.values()) {
                writer.write(user.getUsername() + "," + user.getBio());
                writer.newLine();
            }
        }
    }

    // Load user data from file
    public static HashMap<String, UserProfile> loadUsers() throws IOException {
        HashMap<String, UserProfile> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                UserProfile user = new UserProfile(parts[0], parts[1]);
                users.put(parts[0], user);
            }
        }
        return users;
    }

    // Save post data to file
    public static void savePosts(ArrayList<UserPost> posts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(POST_FILE))) {
            for (UserPost post : posts) {
                // Save the author, content, and optional image URL
                writer.write(post.getAuthor().getUsername() + "," + post.getContent());
                if (post.isImagePost()) {
                    writer.write("," + post.getImageUrl());
                }
                writer.newLine();
            }
        }
    }

    // Load post data from file
    public static ArrayList<UserPost> loadPosts(HashMap<String, UserProfile> users) throws IOException {
        ArrayList<UserPost> posts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(POST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                UserProfile user = users.get(parts[0]);
                if (parts.length == 2) {
                    posts.add(new UserPost(user, parts[1])); // Text post
                } else if (parts.length == 3) {
                    posts.add(new UserPost(user, parts[1], parts[2])); // Image post
                }
            }
        }
        return posts;
    }
}
