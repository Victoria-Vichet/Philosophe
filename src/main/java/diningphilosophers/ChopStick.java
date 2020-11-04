package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    synchronized void take() throws InterruptedException{
        while(!iAmFree){
            wait();
        }
        assert(iAmFree);
        this.iAmFree = false;
        System.out.printf("La baguette"+myNumber+"est prise");
        notifyAll();
    }
    
    synchronized void  release() throws InterruptedException{
        while(iAmFree){
            wait();
        }
        assert(!iAmFree);
        this.iAmFree = true;
        System.out.printf("La baguette"+myNumber+"est rendue");
        notifyAll();
    }
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
