package be.dragoncave.web;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by benoit on 03/11/2016.
 */
@SpringUI
public class MainPanel extends UI {

    @Autowired
    private final TaskService taskService;
    @Autowired

    private CountryConverter countryConverter;
    @Autowired

    private UserService userService;
    @Autowired

    private CountryRepository countryRepository;

    public MainPanel(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        load();
        List<Task> tasks=taskService.tasks();
        System.out.println(tasks.size());
        VerticalLayout verticalLayout=new VerticalLayout();
        Grid grid=new Grid();
        grid.addColumn("task");
        grid.setCaption("My Grid");

        // Disable selecting items
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        tasks.forEach(f->grid.addRow(f.getDescription()));

        verticalLayout.addComponent(grid);
        setContent(verticalLayout);
    }




    private void load(){

        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        countryRepository.save(countries);
        System.out.println(countryRepository.count());
        for(int i=1;i<=200;i++){
            User persUser = new User("xwcwx"+i, "sdd"+i, "dqd"+i, "dsqd"+i, "9899", "dfsdf", countryRepository.findOne(i), LocalDateTime.now().minusYears(50));
            userService.save(persUser);
            Task taskPers = new Task("ffsdf"+i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());

        }
    }
}
