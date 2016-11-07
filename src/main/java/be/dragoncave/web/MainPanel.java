package be.dragoncave.web;

import be.dragoncave.domain.*;
import be.dragoncave.persistance.CountryRepository;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.TaskServiceImpl;
import be.dragoncave.service.UserService;
import be.dragoncave.util.CountryConverter;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by benoit on 03/11/2016.
 */
@SpringUI
public class MainPanel extends UI {


    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    private final TaskService taskService;
    @Autowired

    private CountryConverter countryConverter;
    @Autowired

    private UserService userService;
    @Autowired

    private CountryRepository countryRepository;
    private Task task = new Task();

    public MainPanel(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostConstruct
    private void init() {
        List<Country> countries = countryConverter.parse("src/main/resources/countries.xml");

        taskService.deleteAll();
        userService.deleteAll();


        countryRepository.deleteAll();
        countryRepository.save(countries);
        List<Country> countriesArray = (List<Country>) countryRepository.findAll();
        System.out.println(countryRepository.count());

        for (int i = 1; i <= 10; i++) {
            User persUser = new User("xwcwx" + i, "sdd" + i, "dqd" + i, "dsqd" + i, "9899", "dfsdf", countriesArray.get(i), LocalDateTime.now().minusYears(50));
            userService.save(persUser);
            Task taskPers = new Task("ffsdf" + i, LocalDateTime.now().plusMonths(i), LocalDateTime.now().plusMonths(i).plusDays(20), TaskType.PRIVATE, TaskStatus.RUNNING);
            taskPers.setUser(persUser);
            taskService.save(taskPers);
            System.out.print(taskPers.getId());
        }
        System.out.println("hallo");
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        loadData();
        HorizontalLayout layout = new HorizontalLayout();
        BeanItemContainer<Task> dataSource = new BeanItemContainer<Task>(Task.class, taskService.tasks());
        dataSource.addNestedContainerBean("user");
        Grid grid = new Grid("My data grid", dataSource);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        layout.addComponent(grid);
        grid.setSizeFull();
        layout.setExpandRatio(grid, 1);
        grid.setColumns("description", "endDate", "startDate",
                "taskType", "taskStatus", "user.name", "user.forName", "user.userID");
        //  grid.addColumn("startDate", LocalDate.class);
        // grid.getColumn("busy")
        //       .setConverter(new BooleanTrafficLight())
        //     .setRenderer(new HtmlRenderer());
        //grid.getColumn("endDate").setConverter(new LocalDateToStringConverter());

        grid.getColumn("startDate").setConverter(new LocalDateToStringConverter());
        grid.getColumn("endDate").setConverter(new LocalDateToStringConverter());
        //  dataSource.addNestedContainerBean("country");


        Panel panel = new Panel();
        panel.setSizeFull();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        Button addButton = new Button("Add Task");
        horizontalLayout.addComponent(addButton);

        addButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                TaskForm sub = new TaskForm(grid);

                // Add it to the root component
                UI.getCurrent().addWindow(sub);
            }
        });

        Button deleteButton = new Button("Delete Task");
        deleteButton.setEnabled(false);
        horizontalLayout.addComponent(deleteButton);
        Button editButton = new Button("Edit Button");
        editButton.setEnabled(false);
        horizontalLayout.addComponent(editButton);

        deleteButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                if (grid.getSelectedRow() != null) {
                    Task selected = (Task) ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
                    taskService.delete(selected);

                    System.out.println(selected.getClass());
                    Item item = grid.getContainerDataSource().getItem(selected);

                    dataSource.removeItem(grid.getSelectedRow());
                    boolean ok = dataSource.removeItem(grid.getSelectedRow());
                    deleteButton.setEnabled(false);
                    grid.clearSortOrder();
                    grid.markAsDirty();
                }
            }

        });

        editButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

                if (grid.getSelectedRow() != null) {
                    Task selected = (Task) ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
                    taskService.delete(selected);

                    Item item = grid.getContainerDataSource().getItem(selected);
                    TaskEditForm taskEditForm=new TaskEditForm(grid);
                    // Add it to the root component
                    UI.getCurrent().addWindow(taskEditForm);
                    deleteButton.setEnabled(false);
                    grid.clearSortOrder();
                    grid.markAsDirty();
                }
            }

        });
        grid.addSelectionListener(new SelectionEvent.SelectionListener() {

            @Override
            public void select(SelectionEvent event) {
                // Notification.show("Select row: "+grid.getSelectedRow());
                deleteButton.setEnabled(true);
                editButton.setEnabled(true);

            }
        });


        grid.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    Object itemId = event.getItemId();
                    grid.setDetailsVisible(itemId, !grid.isDetailsVisible(itemId));

                    // Task selected = (Task) ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();

                    BeanItem<Task> item = dataSource.getItem(event.getItemId());
                    Task task = item.getBean();
                    Notification.show("Nr : " + task.getNbrTask() + System.lineSeparator() + "Description : " + task.getDescription() + System.lineSeparator() + " Start :" + task.getStartDate() + System.lineSeparator() + "End : " + task.getEndDate() + System.lineSeparator() + " User : " + task.getUser().getForName() + "  " + task.getUser().getName());
                }
            }
        });


        verticalLayout.addComponent(horizontalLayout);
        List<Task> tasks = taskService.tasks();
        List<Country> countries = (List<Country>) countryRepository.findAll();
        System.out.println(tasks.size());


        layout.setSizeFull();

        layout.addComponent(verticalLayout);


        //  bornColumn.setRenderer(new DateRenderer("%1$tB %1$te, %1$tY",
        //        Locale.ENGLISH));
        // bornColumn.setConverter(new  );

        // tasks.forEach(f->grid.addRow(f.getDescription()));
        ComboBox comboBox = new ComboBox("select one");
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


        Calendar calendar = new Calendar();
        //  calendar.setWidth("800px");

        calendar.setSizeFull();

        calendar.setStartDate(new GregorianCalendar(2016, 11, 16, 13, 00, 00).getTime());
        calendar.setEndDate(new GregorianCalendar(2016, 12, 16, 13, 00, 00).getTime());


        GregorianCalendar start = new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        end.add(java.util.Calendar.HOUR, 2);
        calendar.addEvent(new BasicEvent("Calendar study",
                "Learning how to use Vaadin Calendar",
                start.getTime(), end.getTime()));


        taskService.tasks().forEach(t -> {

            calendar.addEvent(new BasicEvent("Calendar study",
                    "Learning how to use Vaadin Calendar",
                    DateUtils.asDate(t.getStartDate()), DateUtils.asDate(t.getEndDate().toLocalDate())));

        });

        verticalLayout.addComponent(calendar);

        // create generated column and specify our "generator/formatter"
        //  table.addGeneratedColumn("rules", new RuleGenerator());


        setContent(layout);
    }


    private void loadData() {

    }

}
