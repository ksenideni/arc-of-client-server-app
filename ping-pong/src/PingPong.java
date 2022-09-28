public class PingPong {
    public static void main(String[] args) {
        Object monitor = new Object();

        Thread ping = new Thread(() -> {
            synchronized (monitor) {
                while (true) {
                    System.out.println("ping");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread pong = new Thread(() -> {
            while (true) {
                synchronized (monitor) {
                    System.out.println("pong");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        ping.start();
        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        pong.start();
        try {
            pong.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}