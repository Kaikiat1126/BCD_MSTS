package msts.obj;

public class Patient extends User{

    public Patient() {
        super();
    }

    public Patient(int userId) {
        super(userId);
    }

    public Patient(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Patient(int userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
