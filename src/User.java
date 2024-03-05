public class User {
    protected int userId;
    protected String userName;
    protected String password;
    protected int role;
    protected String email;
    protected Long contactNumber;

    public User() {}

    public User(int userId) {
        this.userId = userId;
    }

    public User(String userName, String password, int role, String email, Long contactNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public User(int userId, String userName, String password, int role, String email, Long contactNumber) {
        this(userName, password, role, email, contactNumber);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
