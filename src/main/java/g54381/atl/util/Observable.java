package g54381.atl.util; // package gxxxx.atl.util;

public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();


}

