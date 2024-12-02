package beans;

public class KyNang {
    private String name;
    private String level;

    // Constructor
    public KyNang(String name, String level) {
        this.name = name;
        this.level = level;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
