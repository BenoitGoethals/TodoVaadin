package be.dragoncave.domain;

import java.util.EnumSet;

/**
 * Created by benoit on 01/11/2016.
 */
public enum TaskStatus {
    STARTED(1),
    RUNNING(2),
    STOPPED(3),
    ENDED(4);

    private int status;

    TaskStatus(int status) {
        status = status;
    }

    public static EnumSet<TaskStatus> getTaskStatuses() {
        return EnumSet.allOf(TaskStatus.class);
    }
}
