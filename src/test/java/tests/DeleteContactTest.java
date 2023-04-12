package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends TestBase {
    @BeforeClass
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("pochtadl9testov@gmail.com").withPassword("12345&Yes"));
            app.helperContact().clearAllContacts();
        }
    }
    @Test
    public void deleteContact() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Sonic")
                .lastName("Super")
                .phone("0539876" + i)
                .email("supersonic" + i + "@gmail.com")
                .address("NY")
                .description("Best Friend")
                .build();
        app.helperContact().openAddContactForm();
        app.helperContact().fillAddContactForm(contact);
        app.helperContact().saveContact();
        app.helperContact().listOfContactsBeforeDel();
        System.out.print(app.helperContact().listOfContactsBeforeDel());
        app.helperContact().deleteContact1();
        app.helperContact().listOfContactsAfterDel();
        System.out.print(app.helperContact().listOfContactsAfterDel());
        Assert.assertTrue(app.helperContact().isContactDeleted());

    }
}
