package manager;

import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {super(wd);}

    public void openAddContactForm() {
        //pause (500);
        click(By.cssSelector("a[href='/add']"));
    }
    public void fillAddContactForm(Contact contact) {
        type((By.cssSelector("[placeholder='Name']")), contact.getName());
        type((By.cssSelector("[placeholder='Last Name']")), contact.getLastName());
        type((By.cssSelector("[placeholder='Phone']")), contact.getPhone());
        type((By.cssSelector("[placeholder='email']")), contact.getEmail());
        type((By.cssSelector("[placeholder='Address']")), contact.getAddress());
        type((By.cssSelector("[placeholder='description']")), contact.getDescription());
    }
    public void returntoHome(){
    click(By.xpath("//*[text()='HOME']"));
    }
    public void saveContact() {
        click(By.cssSelector(".add_form__2rsm2>button"));
    }
    public boolean isContactAddedByName(String name) {
    List<WebElement> list =wd.findElements(By.cssSelector("h2"));
    for (WebElement el:list) {
        if( el.getText().equals(name)){
            return true;
        }
    }
    return false;
}
    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list =wd.findElements(By.cssSelector("h3"));
        for(WebElement el:list){
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }
    public boolean isAddPageStillDisplayed() {
        return isElementPresent(By.cssSelector("a.active[href='/add']"));
    }

    public void clearAllContacts() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        list.clear();
    }
    public void deleteContact1(){
        click(By.xpath("(//h2)[1]"));
        click(By.xpath("//*[text()='Remove']"));
    }
    public int listOfContactsBeforeDel() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        int size1 = list.size();
        return size1;
    }
    public int listOfContactsAfterDel() {
        List<WebElement> list = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        int size2 = list.size();
        return size2;
    }
    public boolean isContactDeleted() {
        if (listOfContactsAfterDel() < listOfContactsBeforeDel()){
           return true;
        }
           return false;
    }
}
