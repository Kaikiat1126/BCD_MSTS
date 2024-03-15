package msts.obj;

public class Patient extends User{

    public Patient() {
        super();
    }

    public Patient(String userId) {
        super(userId);
    }

    public Patient(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Patient(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
