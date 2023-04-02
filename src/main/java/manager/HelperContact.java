package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {super(wd);}

    public void openAddContactForm() {
        pause (500);
        click(By.xpath("//*[text()='ADD']"));
    }
    public void fillAddContactForm(Contact contact) {
        type((By.cssSelector("[placeholder='Name']")), contact.getName());
        type((By.cssSelector("[placeholder='Last Name']")), contact.getLastName());
        type((By.cssSelector("[placeholder='Phone']")), contact.getPhone());
        type((By.cssSelector("[placeholder='email']")), contact.getEmail());
        type((By.cssSelector("[placeholder='Address']")), contact.getAddress());
        type((By.cssSelector("[placeholder='description']")), contact.getDescription());
    }
    public void submitAddContactForm() {
        click(By.xpath("//*[text()='Save']"));
    }
    public void returntoHome(){
        click(By.xpath("//*[text()='HOME']"));
    }
    public boolean isContactPresent(){
        pause(500);
        return isElementPresent(By.xpath("//*[text()='Sonic']"));
    }


}
