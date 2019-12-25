class BoundedBuffer {
    int x = 10;
    final int size = 10;
    double[] buffer = new double[size];
    int inBuf = 0, outBuf = 0;
    BinarySemaphore mutex = new BinarySemaphore(true);
    CountingSemaphore isEmpty = new CountingSemaphore(0);
    CountingSemaphore isFull = new CountingSemaphore(size);

    CountingSemaphore tangGiam = new CountingSemaphore();

    public void deposit(double value) {
        isFull.P();// wait if buffer is full
        mutex.P(); // ensures mutual exclusion
        buffer[inBuf] = value; // update the buffer
        inBuf = (inBuf + 1) % size;
        mutex.V();
        isEmpty.V();  // notify any waiting consumer
    }
    public double fetch() {
        double value;
        isEmpty.P(); // wait if buffer is empty
        mutex.P();  // ensures mutual exclusion
        value = buffer[outBuf]; //read from buffer
        outBuf = (outBuf + 1) % size;
        mutex.V();
        isFull.V(); // notify any waiting producer
        return value;
    }

    public void hamTang() {
        tangGiam.giam(x);
        tangGiam.maxTang(x);
        mutex.P();
        System.out.println("T1: " + x);
        x = x+1;
        mutex.V();

    }

    public void hamGiam() {
        tangGiam.tang(x);
        tangGiam.minGiam(x);
        mutex.P();
        System.out.println("T2: " + x);
        x = x-1;
        mutex.V();

    }
}

