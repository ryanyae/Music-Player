package model;

public class LogPrinter {

    public void printLog(EventLog el) {
        for (Event next: el) {
            System.out.println(next.toString());
        }
    }
}
