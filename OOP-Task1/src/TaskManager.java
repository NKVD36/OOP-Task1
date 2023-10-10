import java.time.LocalTime;
import java.util.*;

/** @noinspection ALL*/
public class TaskManager {
    private List<Task> tasks;
    private Timer timer;

    public TaskManager() {
        tasks = new ArrayList<>();
        timer = new Timer();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void start() {
        timer.scheduleAtFixedRate(new TimerTask() { // Устанавливаем задачи с определенным интервалом
            @Override
            public void run() {
                LocalTime currentTime = LocalTime.now();
                for (Task task : tasks) {
                    if (currentTime.equals(task.getTime())) {
                        System.out.println("Task due: " + task.getDescription());
                    }
                }
            }
        }, 0, 1000); // Проверяем каждую секунду

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Введите команду (add, remove, tasks, quit):");
            input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "add":
                    addTaskFromUserInput(scanner);
                    break;
                case "remove":
                    removeTaskFromUserInput(scanner);
                    break;
                case "tasks":
                    showTaskFromUserInput(scanner);
                    break;
                case "quit":
                    break;
                default:
                    System.out.println("Неправильная команда!");
                    break;
            }
        } while (!input.equals("quit"));

        timer.cancel();
    }

    private void addTaskFromUserInput(Scanner scanner) {
        System.out.println("Введите описание задачи:");
        String description = scanner.nextLine().trim();

        System.out.println("Введите время задачи (HH:MM):");
        String timeStr = scanner.nextLine().trim();

        try {
            LocalTime time = LocalTime.parse(timeStr);
            Task task = new Task(description, time);
            addTask(task);
            System.out.println("Задача добавлена!");
        } catch (Exception e) {
            System.out.println("Ошибка парсинга времени! Проверьте формат (HH:MM).");
        }
    }

    private void removeTaskFromUserInput(Scanner scanner) {
        System.out.println("Введите индекс задачи, чтобы удалить:");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            removeTask(task);
            System.out.println("Задача удалена!");
        } else {
            System.out.println("Неправильный индекс задачи!");
        }
    }

    private void showTaskFromUserInput(Scanner scanner) {
        int i = 1;
        System.out.println("Ваш список задач:");
        if (tasks.size() == 0) {
            System.out.println("У вас еще нет задач!");
        } else {
            for (Task task : tasks) {
                    System.out.println(i + ". " + task.getDescription() + " (" + task.getTime() + ")");
                    i++;
            }
        }
        System.out.println("Время сейчас: " + (LocalTime.now()));
    }
}