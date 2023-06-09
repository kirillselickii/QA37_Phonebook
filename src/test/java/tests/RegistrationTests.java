package tests;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition() {

        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }
    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        User user = new User().withEmail("pochtadl9testov"+i+"@gmail.com").withPassword("12345&Yes");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        // Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isLogged(),"check is sing out present");
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
        Assert.assertEquals(app.getHelperUser().getMessage(),"No Contacts here!");
    }
    @Test(description = "Bug  report N23467 Fixed")
    public void registrationWrongEmail(){
        User user = new User().withEmail("pochtadl9testovgmail.com").withPassword("12345&Yes");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format\n" +
                "            Email must contains one @ and minimum 2 symbols after last dot\n" +
                "            Password must contain at least one uppercase letter!\n" +
                "            Password must contain at least one lowercase letter!\n" +
                "            Password must contain at least one digit!\n" +
                "            Password must contain at least one special symbol"));
        // Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }
    @Test
    public void registrationWrongPassword(){

        User user = new User().withEmail("pochtadl9testov@gmail.com").withPassword("12345$No");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format\n" +
                "            Email must contains one @ and minimum 2 symbols after last dot\n" +
                "            Password must contain at least one uppercase letter!\n" +
                "            Password must contain at least one lowercase letter!\n" +
                "            Password must contain at least one digit!\n" +
                "            Password must contain at least one special symbol"));
        // Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }
    @Test
    public void registrationNewValidPasswordExistUser(){

        User user = new User().withEmail("pochtadl9testov@gmail.com").withPassword("12345$Ok");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
        // Assert.assertTrue(app.getHelperUser().isAlertPresent2("Wrong email or password format"));
    }
    @Test
    public void registrationExistsUser(){

        User user = new User().withEmail("pochtadl9testov@gmail.com").withPassword("12345&Yes");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }

}