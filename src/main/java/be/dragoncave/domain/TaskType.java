package be.dragoncave.domain;

import java.util.EnumSet;

/**
 * Created by benoit on 01/11/2016.
 */
public enum TaskType {
    PRIVATE(1),
    PUBLIC(2);


    private int taskType;

    TaskType(int status){
        status=status;
    }

    public static EnumSet<TaskType> getTaskTypes(){
        return EnumSet.allOf(TaskType.class);
    }
}
