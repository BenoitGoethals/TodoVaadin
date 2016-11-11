package be.dragoncave.web;


import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by benoit on 11/11/2016.
 */

public class LoginView  extends LoginForm {
    @Override
    protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        layout.addComponent(userNameField);
        layout.addComponent(passwordField);
        layout.addComponent(loginButton);
        layout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        return layout;
    }

    // You can also override this method to handle the login directly, instead of using the event mechanism
    @Override
    protected void login(String userName, String password) {
        System.err.println(
                "Logged in with user name " + userName +
                        " and password of length " + password.length()
        );
    }
}
