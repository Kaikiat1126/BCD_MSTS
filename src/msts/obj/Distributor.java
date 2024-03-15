package msts.obj;

public class Distributor extends User{

    public Distributor() {
        super();
    }

    public Distributor(String userId) {
        super(userId);
    }

    public Distributor(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Distributor(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
