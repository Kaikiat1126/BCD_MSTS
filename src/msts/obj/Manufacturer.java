package msts.obj;

public class Manufacturer extends User{

    public Manufacturer() {
        super();
    }

    public Manufacturer(String userId) {
        super(userId);
    }

    public Manufacturer(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Manufacturer(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
