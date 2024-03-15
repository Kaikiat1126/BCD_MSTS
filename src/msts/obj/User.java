package msts.obj;

public class User {
    protected String userId;
    protected String username;
    protected String password;
    protected int role;
    protected String email;
    protected Long contactNumber;

    public User() {}

    public User(String userId) {
        this.userId = userId;
    }

    public User(String username, String password, int role, String email, Long contactNumber) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public User(String userId, String username, String password, int role, String email, Long contactNumber) {
        this(username, password, role, email, contactNumber);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }
}
