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
import javafx.scene.layout.Pane;
import org.apache.shiro.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpSession;


public class LoginWindow extends Window{

    private final TextField username;
    private final PasswordField passwordField;



    public TextField getUsername() {
        return username;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public LoginWindow(AuthenticationManager authenticationManager) {
        super("Login");
        center();
        FormLayout form = new FormLayout();
        form.setWidth("400px");
        form.setSpacing(true);
        form.setMargin(true);
         username = new TextField("username");
        username.setIcon(FontAwesome.USER);
        username.setWidth(15, Unit.PICAS);
        username.setRequired(true);
        username.addValidator(new NullValidator("Must be given", false));

        form.addComponent(username);

         passwordField=new PasswordField("Password");
        passwordField.setIcon(FontAwesome.USER);
        passwordField.setWidth(15, Unit.PICAS);
        passwordField.setRequired(true);
        passwordField.addValidator(new NullValidator("Must be given", false));


        form.addComponent(passwordField);




        setContent(form);


        // Disable the close button
        setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("Login");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {




                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username.getValue(), passwordField.getValue());

                SecurityContextImpl secureContext = new SecurityContextImpl();
                authenticationManager.authenticate(authRequest);
                secureContext.setAuthentication(authRequest);
                SecurityContextHolder.setContext(secureContext);
              //  SecurityContextHolder.getContext().setAuthentication(authRequest);

                if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
                    System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                    close();
                }


            }
        });
        form.addComponent(ok);
    }



}
