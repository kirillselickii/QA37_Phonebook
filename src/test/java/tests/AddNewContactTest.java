package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.security.util.ByteArrayTagOrder;

import java.util.Random;

public class AddNewContactTest extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            //app.getHelperUser().logout();
            app.getHelperUser().login(new User().withEmail("pochtadl9testov@gmail.com").withPassword("12345&Yes"));
        }
    }
    @Test
    public void addNewContactSuccess(){

        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Sonic")
                .lastName("Super")
                .phone("0539876" + i)
                .email("supersonic@gmail.com")
                .address("NY")
                .description("Brother")
                .build();
        app.getHelperContact().openAddContactForm();
        app.getHelperContact().fillAddContactForm(contact);
        app.getHelperContact().submitAddContactForm();
        Assert.assertEquals(app.getHelperContact().isContactPresent(), "Sonic");

    }
   @AfterMethod
    public void posCondition(){
       app.getHelperContact().returntoHome();
    }


}