package by.it.shpakovskiy.jd02_02;

class Manager implements Runnable {
    private BulbaStore store;
    private boolean alive;
    private boolean endWork;

    //FIX. for compatibility with Java 8
    private static String repeat(String s, int n){
        return new String(new char[n]).replace("\0", s);
    }

    Manager(BulbaStore store) {
        this.store = store;
        Thread thread = new Thread(this, "Manager");
        thread.start();
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
                alive = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (!endWork) {
            Helper.sleep(1);
            store.checkForCashiers(this);
            if (store.isClosedStore()) {
                store.freePersonal(this);
                break;
            }
        }
        if (ShopRunner.IN_A_TABLE){
            String s = "│ Total buyers count: " + String.format("%4d", store.getTotalBuyersCount()) +
                    ". Total revenue: $" + String.format("%6.2f", store.getRevenue()) + "." +
                    repeat(" ",116) + "│\n" +
                    "└" + repeat("─",167) + "┘";
            System.out.println(s);
        }
        System.out.println("########################## BulbaStore closed! Buy! ##########################");
    }

    void endOfWorkingDay() {
        endWork = true;
    }
}
