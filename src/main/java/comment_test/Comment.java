package comment_test;

import org.openqa.selenium.By;

import java.util.*;

public class Comment {
    String number;
    String text;
    String active;
    List<String> categories = new ArrayList<String>();

    public Comment(String number, String text, String active, List<String> categories) {
        this.number = number;
        this.text = text;
        this.active = active;
        this.categories = categories;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(number, comment.number) &&
                Objects.equals(text, comment.text) &&
                Objects.equals(active, comment.active) &&
                Objects.equals(categories, comment.categories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, text, active, categories);
    }


}
