package msts.obj;

public class Distributor extends User{

    public Distributor() {
        super();
    }

    public Distributor(int userId) {
        super(userId);
    }

    public Distributor(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public Distributor(int userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
