package model;

/**
 * Created by jakub on 29/03/2017.
 */
public class Property {
    private int pid;
    private String name;
    private String value;

    public Property(int pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    public Property(int pid, String name, String value) {
        this.pid = pid;
        this.name = name;
        this.value = value;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
