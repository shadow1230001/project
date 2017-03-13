package validation;

import com.intransition.labs.dto.SignupForm;
import com.intransition.labs.service.UserService;
import com.intransition.labs.validation.RegistrationFormValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.Errors;


public class RegistrationFormValidatorTest  {
    private RegistrationFormValidator validator;
    private SignupForm form;
    @Mock
    private UserService userService;
    @Mock
    private Errors errors;

    @Before
    public void beforeEachTest() {
        MockitoAnnotations.initMocks(this);
        validator = new RegistrationFormValidator();
        validator.setUserService(userService);
        form = new SignupForm();
    }

    @Test
    public void validateNoErrors() {
        form.setEmail("vladimir@mail.ru");
        form.setFirstName("vladimir");
        form.setLastName("lyubin");
        form.setPassword("qwertyuiop");
        form.setPasswordConfirm("qwertyuiop");
        form.setNickname("vl");

        validator.validate(form, errors);
        Assert.assertEquals(errors.getErrorCount(), 0);
    }
}