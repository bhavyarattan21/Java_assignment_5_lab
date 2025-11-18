package service;

public class Loader implements Runnable {
    private String message;

    public Loader(String message) {
        this.message = message;
    }

    public void run() {
        System.out.print(message);
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
