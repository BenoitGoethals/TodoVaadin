package be.dragoncave.web;

import be.dragoncave.domain.Task;
import be.dragoncave.domain.TaskStatus;
import be.dragoncave.domain.TaskType;
import be.dragoncave.domain.User;
import be.dragoncave.service.TaskService;
import be.dragoncave.service.UserService;
import be.dragoncave.util.AppConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

/**
 * Created by benoit on 05/11/2016.
 */

public class TaskEditForm extends Window {


    private Grid grid;

    public TaskEditForm(Grid grid) {
        super("Edit Task"); // Set window caption
        Task selected = (Task) ((Grid.SingleSelectionModel) grid.getSelectionModel()).getSelectedRow();
        center();
        FormLayout form = new FormLayout();
        form.setWidth("400px");
        form.setSpacing(true);
        form.setMargin(true);
        TextField tf1 = new TextField("Description");
        tf1.setIcon(FontAwesome.USER);
        tf1.setWidth(15, Unit.PICAS);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Must be given", false));
        tf1.setValue(selected.getDescription());
        form.addComponent(tf1);
        this.grid = grid;

        ComboBox comboboxStatus = new ComboBox("Task Status");
        comboboxStatus.setNullSelectionAllowed(false);
        TaskStatus.getTaskStatuses().forEach(s -> comboboxStatus.addItem(s));
        comboboxStatus.setIcon(FontAwesome.USER);
        comboboxStatus.setRequired(true);
        comboboxStatus.addValidator(new NullValidator("Must be given", false));
        comboboxStatus.setValue(selected.getTaskStatus());


        ComboBox comboboxTypes = new ComboBox("Task Type");
        comboboxTypes.setNullSelectionAllowed(false);
        TaskType.getTaskTypes().forEach(s -> comboboxTypes.addItem(s));

        comboboxTypes.setRequired(true);
        comboboxTypes.addValidator(new NullValidator("Must be given", false));
        comboboxTypes.setValue(selected.getTaskType());

        DateField dateFieldSt = new DateField("Start date");
        dateFieldSt.setResolution(Resolution.MINUTE);
        dateFieldSt.setImmediate(true);

        dateFieldSt.setRequired(true);
        dateFieldSt.addValidator(new NullValidator("Must be given", false));
        dateFieldSt.setValue(DateUtils.asDate(selected.getStartDate()));

        DateField dateFieldEnd = new DateField("End date");
        dateFieldEnd.setResolution(Resolution.MINUTE);
        dateFieldEnd.setImmediate(true);

        dateFieldEnd.setRequired(true);
        dateFieldEnd.addValidator(new NullValidator("Must be given", false));
        dateFieldEnd.setValue(DateUtils.asDate(selected.getEndDate()));

        ComboBox comboboxUsers = new ComboBox("USER");
        comboboxUsers.setWidth(10, Unit.PICAS);
        comboboxUsers.setContainerDataSource(new BeanItemContainer(User.class, AppConfiguration.getApplicationContext().getBean(UserService.class).users()));
        comboboxUsers.setItemCaptionPropertyId("forName");
        comboboxUsers.setNullSelectionAllowed(false);

        comboboxUsers.setRequired(true);
        comboboxUsers.addValidator(new NullValidator("Must be given", false));
        comboboxUsers.setValue(selected.getUser());


        form.addComponent(comboboxStatus);
        form.addComponent(comboboxTypes);
        form.addComponent(dateFieldSt);
        form.addComponent(dateFieldEnd);
        form.addComponent(comboboxUsers);


        setContent(form);

        // Disable the close button
        setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("OK");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                Task task = new Task();
                task.setStartDate(DateUtils.asLocalDateTime(dateFieldSt.getValue()));
                task.setUser((User) comboboxUsers.getValue());
                task.setDescription(tf1.getValue());
                task.setEndDate(DateUtils.asLocalDateTime(dateFieldEnd.getValue()));
                task.setTaskStatus((TaskStatus) comboboxStatus.getValue());
                task.setTaskType((TaskType) comboboxTypes.getValue());
                task.setNbrTask();
                AppConfiguration.getApplicationContext().getBean(TaskService.class).save(task);
                ((BeanItemContainer<Task>) grid.getContainerDataSource()).addBean(task);
                grid.markAsDirty();

                close();
            }
        });
        form.addComponent(ok);
    }


}
