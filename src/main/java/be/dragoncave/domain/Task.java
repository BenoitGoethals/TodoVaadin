package be.dragoncave.domain;


import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by benoit on 01/11/2016.
 */
@Entity
public class Task {

    public Task(String description, LocalDateTime startDate, LocalDateTime endDate, TaskType taskType, TaskStatus taskStatus) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.nbrTask = String.valueOf(startDate.hashCode() + endDate.hashCode());
    }

    public Task() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TASK_ID", nullable = false)
    private long id;
    @Column(nullable = false, unique = true)
    private String nbrTask;
    @Column(unique = false)
    private String description;
    @Column(name = "DATE_CREATED", unique = true)
    // @Temporal(TemporalType.DATE)
    private LocalDateTime startDate;
    @Column(name = "DATE_ENDED", unique = true)
    //@Temporal(TemporalType.DATE)
    private LocalDateTime endDate;
    @Enumerated(EnumType.ORDINAL)
    @Column(unique = false)
    private TaskType taskType;
    @Enumerated(EnumType.ORDINAL)
    @Column(unique = false)
    private TaskStatus taskStatus;


    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = true, unique = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNbrTask() {
        return nbrTask;
    }

    public void setNbrTask(String nbrTask) {
        this.nbrTask = nbrTask;
    }

    public void setNbrTask() {
        this.nbrTask = String.valueOf(startDate.hashCode() + endDate.hashCode());
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return getId() == task.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", nbrTask='" + nbrTask + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", taskType=" + taskType +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
