package be.dragoncave.web;

import be.dragoncave.domain.Task;
import be.dragoncave.service.TaskService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by benoit on 03/11/2016.
 */
@SpringUI
public class MainPanel extends UI {

    @Autowired
    private final TaskService taskService;

    public MainPanel(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        List<Task> tasks=taskService.tasks();
        VerticalLayout verticalLayout=new VerticalLayout();
        Grid grid=new Grid();
        grid.addColumn("task");
        grid.setCaption("My Grid");

        // Disable selecting items
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        tasks.forEach(f->grid.addRow(f.getDescription()));

        verticalLayout.addComponent(grid);
        setContent(new Label("test"));
    }
}
