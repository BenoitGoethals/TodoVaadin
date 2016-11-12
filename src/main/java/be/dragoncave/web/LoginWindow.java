package be.dragoncave.web;


import be.dragoncave.domain.Role;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;


public class LoginWindow extends Window {

    private final TextField username;
    private final PasswordField passwordField;


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
        username.setValue("benoit");

        form.addComponent(username);

        passwordField = new PasswordField("Password");
        passwordField.setIcon(FontAwesome.USER);
        passwordField.setWidth(15, Unit.PICAS);
        passwordField.setRequired(true);
        passwordField.addValidator(new NullValidator("Must be given", false));
        passwordField.setValue("ranger14");


        form.addComponent(passwordField);


        setContent(form);


        // Disable the close button
        setClosable(true);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        // Trivial logic for closing the sub-window
        Button ok = new Button("Login");
        horizontalLayout.addComponent(ok);
        Label label =new Label();
        //horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.addComponent(label);
        form.addComponent(horizontalLayout);
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {


                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username.getValue(), passwordField.getValue(), Role.getRoles());

                SecurityContextImpl secureContext = new SecurityContextImpl();
                try {

                    //authRequest.setAuthenticated(true);
                    authenticationManager.authenticate(authRequest);

                    secureContext.setAuthentication(authRequest);
                    SecurityContextHolder.setContext(secureContext);
                    //  SecurityContextHolder.getContext().setAuthentication(authRequest)
                    //

                        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                    System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
                    System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                        close();

                } catch (BadCredentialsException e) {
                    label.setValue("Bad");
                }


            }
        });


    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }


}
