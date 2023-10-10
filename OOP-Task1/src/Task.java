import java.time.LocalTime;

class Task { // Можно зарекордить, но так понятнее
    private final String description;
    private final LocalTime time;

    public Task(String description, LocalTime time) {
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getTime() {
        return time;
    }
}
