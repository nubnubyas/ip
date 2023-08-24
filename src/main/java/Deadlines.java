/**
 * The Deadlines class extends the Task class.
 * It contains a description, a deadline, and inherits the
 * completion status functionality from the Task class.
 */
public class Deadlines extends Task {
    protected String deadline;

    /**
     * Constructs a Deadlines object with the given description and deadline.
     *
     * @param description the description of the Deadlines task
     * @param deadline the deadline of the Deadlines task
     */
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of the Deadlines task,
     * including its task type indicator, description, and deadline.
     *
     * @return a string representation of the Deadlines task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}