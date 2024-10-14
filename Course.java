import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String courseCode, String title, String description, int capacity) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getAvailableSlots() {
        return capacity - enrolled;
    }

    public boolean enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Title: %s, Description: %s, Capacity: %d, Available Slots: %d",
                courseCode, title, description, capacity, getAvailableSlots());
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Failed to register for " + course.getTitle() + ". No available slots.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course) && course.dropStudent()) {
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("Failed to drop " + course.getTitle() + ". You are not registered.");
        }
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }
}

class CourseRegistrationSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void registerStudentForCourse(Student student, Course course) {
        student.registerCourse(course);
    }

    public void dropStudentCourse(Student student, Course course) {
        student.dropCourse(course);
    }
}

public class Main {
    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Adding courses to the system
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of programming.", 30));
        system.addCourse(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25));
        system.addCourse(new Course("PHY101", "Physics I", "Introduction to classical mechanics.", 20));

        // Adding students to the system
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter your Student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = system.findStudent(studentId);
                    if (student != null) {
                        System.out.print("Enter Course Code to register: ");
                        String courseCode = scanner.nextLine();
                        Course course = system.findCourse(courseCode);
                        if (course != null) {
                            system.registerStudentForCourse(student, course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your Student ID: ");
                    studentId = scanner.nextLine();
                    student = system.findStudent(studentId);
                    if (student != null) {
                        System.out.print("Enter Course Code to drop: ");
                        String courseCode = scanner.nextLine();
                        Course course = system.findCourse(courseCode);
                        if (course != null) {
                            system.dropStudentCourse(student, course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
