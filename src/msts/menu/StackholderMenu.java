package msts.menu;

import msts.StatusContainer;

public abstract class StackholderMenu {

    public static StackholderMenu instance = null;

    public static StackholderMenu getMenu() {
        if (instance == null) {
            instance = new StackholderMenu() {
                @Override
                public void generateMenu() {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            };
        }
        return instance;
    }

    public StackholderMenu(){}
    public abstract void generateMenu();
    public void ViewMedicineTransaction(){}

    protected void logout() {
        System.out.println("Logging out...");
        StatusContainer.currentUser = null;
        MainMenu.getMenu().generateMainMenu();
    }
}
