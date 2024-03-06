package MenuGenerator;

public abstract class StackholderMenu {
    public MainMenu mainMenu;
    public StackholderMenu(){
        this.mainMenu = MainMenu.getMenu();
    }
    public abstract void generateMenu();
    public void ViewMedicineTransaction(){}
}
