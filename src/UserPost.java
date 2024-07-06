public class UserPost {
    private UserProfile author;
    private String content;
    private String imageUrl;  // Nullable; if null, the post is a text post

    // Constructor for text posts
    public UserPost(UserProfile author, String content) {
        this(author, content, null);
    }

    // Constructor for image posts
    public UserPost(UserProfile author, String content, String imageUrl) {
        this.author = author;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    // Get the author of the post
    public UserProfile getAuthor() {
        return author;
    }

    // Get the content of the post
    public String getContent() {
        return content;
    }

    // Get the image URL of the post
    public String getImageUrl() {
        return imageUrl;
    }

    // Check if the post is a text post
    public boolean isTextPost() {
        return imageUrl == null;
    }

    // Check if the post is an image post
    public boolean isImagePost() {
        return imageUrl != null;
    }

    // Display the post content
    public String displayPost() {
        if (isImagePost()) {
            return getAuthor().getUsername() + ": " + getContent() + " [Image: " + getImageUrl() + "]";
        } else {
            return getAuthor().getUsername() + ": " + getContent();
        }
    }
}
