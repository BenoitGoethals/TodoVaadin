package be.dragoncave.web;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Collection;
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
        List<Country> countries= (List<Country>) countryRepository.findAll();
        System.out.println(tasks.size());
        HorizontalLayout layout=new HorizontalLayout();

        layout.setSizeFull();
        VerticalLayout verticalLayout=new VerticalLayout();
        layout.addComponent(verticalLayout);


        BeanItemContainer<Task> dataSource = new BeanItemContainer<Task>(Task.class,taskService.tasks());
      //  dataSource.addNestedContainerBean("user");
      //  dataSource.addNestedContainerBean("country");
        Grid grid = new Grid("My data grid",dataSource);
        layout.addComponent(grid);
        grid.setSizeFull();
        layout.setExpandRatio(grid, 1);
        grid.setColumnOrder("description", "startDate", "endDate",
                "taskType", "taskStatus", "user");

       // grid.getColumn("busy")
         //       .setConverter(new BooleanTrafficLight())
           //     .setRenderer(new HtmlRenderer());




       // tasks.forEach(f->grid.addRow(f.getDescription()));
        ComboBox comboBox=new ComboBox("select one");
        final BeanItemContainer<Country> container =
                         new BeanItemContainer<Country>(Country.class);
        container.addAll((Collection<? extends Country>) countryRepository.findAll());

        comboBox.setContainerDataSource(container);
        comboBox.setItemCaptionPropertyId("countryName");
        comboBox.setFilteringMode(FilteringMode.CONTAINS);
        comboBox.setImmediate(true);
        // Set the filtering mode
        comboBox.setFilteringMode(FilteringMode.CONTAINS);

        comboBox.setPageLength(5);

       // layout.addComponent(comboBox);
        verticalLayout.addComponent(grid);


        // create generated column and specify our "generator/formatter"
      //  table.addGeneratedColumn("rules", new RuleGenerator());






        setContent(layout);
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
