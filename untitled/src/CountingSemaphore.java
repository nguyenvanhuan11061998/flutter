public class CountingSemaphore {
    int value;
    public CountingSemaphore(int initValue) {
        value = initValue;
    }

    public CountingSemaphore() {
    }

    public synchronized void P() {
        value--;
        if (value < 0) Util.myWait(this);
    }
    public synchronized void V() {
        value++;
        if (value <= 0) notify();
    }

    public synchronized void maxTang(int value) {
        value++;
        if (value > 10) {
            System.out.println("Watting1 ... ");
            Util.myWait(this);
        }
    }
    public synchronized void minGiam(int value) {
        value --;
        if (value < 1) {
            System.out.println("Watting2 ... ");
            Util.myWait(this);
        }
    }

    public synchronized void tang(int value) {
        if(value == 1) notify();
    }

    public synchronized void giam(int value) {
        if(value == 10) notify();
    }
}
