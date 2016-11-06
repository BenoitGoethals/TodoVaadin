package be.dragoncave.web;

import be.dragoncave.domain.Task;
import be.dragoncave.domain.TaskStatus;
import be.dragoncave.domain.TaskType;
import be.dragoncave.domain.User;
import be.dragoncave.service.UserService;
import be.dragoncave.util.AppConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.*;

import javax.swing.*;

/**
 * Created by benoit on 05/11/2016.
 */

public class TaskForm  extends Window {




    public TaskForm(Task task) {
        super("Subs on Sale"); // Set window caption
        center();
        FormLayout form = new FormLayout();
        form.setWidth("400px");
        form.setSpacing(true);
        form.setMargin(true);
        TextField tf1 = new TextField("Description");
        tf1.setIcon(FontAwesome.USER);
        tf1.setWidth(15,Unit.PICAS);
        tf1.setRequired(true);
        tf1.addValidator(new NullValidator("Must be given", false));
        form.addComponent(tf1);


        ComboBox comboboxStatus = new ComboBox("Task Status");
        comboboxStatus.setNullSelectionAllowed(false);
        TaskStatus.getTaskStatuses().forEach(s->comboboxStatus.addItem(s));
        comboboxStatus.setIcon(FontAwesome.USER);
        comboboxStatus.setRequired(true);
        comboboxStatus.addValidator(new NullValidator("Must be given", false));



        ComboBox comboboxTypes = new ComboBox("Task Type");
        comboboxTypes.setNullSelectionAllowed(false);
        TaskType.getTaskTypes().forEach(s->comboboxTypes.addItem(s));

        comboboxTypes.setRequired(true);
        comboboxTypes.addValidator(new NullValidator("Must be given", false));

        DateField dateFieldSt = new DateField("Start date");
        dateFieldSt.setResolution(Resolution.MINUTE);
        dateFieldSt.setImmediate(true);

        dateFieldSt.setRequired(true);
        dateFieldSt.addValidator(new NullValidator("Must be given", false));


        DateField dateFieldEnd = new DateField("End date");
        dateFieldEnd.setResolution(Resolution.MINUTE);
        dateFieldEnd.setImmediate(true);

        dateFieldEnd.setRequired(true);
        dateFieldEnd.addValidator(new NullValidator("Must be given", false));

        ComboBox comboboxUsers = new ComboBox("Task Type");
        comboboxUsers.setWidth(10,Unit.PICAS);
        comboboxUsers.setContainerDataSource(new BeanItemContainer(User.class, AppConfiguration.getApplicationContext().getBean(UserService.class).users()));
        comboboxUsers.setItemCaptionPropertyId("forName");
        comboboxUsers.setNullSelectionAllowed(false);

        comboboxUsers.setRequired(true);
        comboboxUsers.addValidator(new NullValidator("Must be given", false));




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
                close(); // Close the sub-window
            }
        });
        form.addComponent(ok);
    }
}
