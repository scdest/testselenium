package comment_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentTable {
    private WebElement tableElement;
    private WebDriver driver;

    public CommentTable(WebElement tableElement, WebDriver driver){
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public void setTableElement(WebElement tableElement){
        this.tableElement = tableElement;
    }

    public List<WebElement> getRows(){
        List<WebElement> rows = tableElement.findElements(By.xpath("//tbody/tr"));
        return rows;
    }

    public  List<WebElement> getHeadings(){
        WebElement headinRows = tableElement.findElement(By.xpath("//thead/tr"));
        List<WebElement> headingColumns = headinRows.findElements(By.xpath(".//th"));
        return headingColumns;
    }

    public List<List<WebElement>> getRowsWithColumns(){
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for(WebElement row : rows){
            List<WebElement> rowWithColumns = row.findElements(By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }
        return rowsWithColumns;
    }

    public String getValueFromCell(int rowNumber, int columnNumber){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        WebElement cell = rowsWithColumns.get(rowNumber - 1).get(columnNumber-1);
        return cell.getText();
    }

    public List<Map<String,WebElement>> getRowsWithColumnsByHeadings(){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeadings = new ArrayList<Map<String, WebElement>>();
        Map<String, WebElement> rowByHeadings;
        List<WebElement> headingColumns = getHeadings();

        for(List<WebElement> row: rowsWithColumns){
            rowByHeadings = new HashMap<String, WebElement>();
            for(int i=0;i< headingColumns.size();i++){
                String heading = headingColumns.get(i).getText();
                WebElement cell = row.get(i);
                rowByHeadings.put(heading,cell);
            }
            rowsWithColumnsByHeadings.add(rowByHeadings);
        }
        return rowsWithColumnsByHeadings;
    }

    public String getValueFromCell(int rowNumber, String columnName){
        List<Map<String,WebElement>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings();
        System.out.println(rowsWithColumnsByHeadings.get(rowNumber-1).get(columnName).getText());
        return rowsWithColumnsByHeadings.get(rowNumber-1).get(columnName).getText();
    }

    public List<String> getRowsValuesByColumn(String columnName){
        List<String> rowValues = new ArrayList<String>();
        for(int i =1; i < getRows().size(); i++){
            rowValues.add(getValueFromCell(i,columnName));
        }
        return rowValues;
    }

    public List<String> getRowsValuesByColumnForAllPages(String columnName){
        List <List<String>> pageRows = new ArrayList<List<String>>();
        pageRows.add(getRowsValuesByColumn(columnName));
        try {
            while (driver.findElement(By.xpath("//a[text()='>']")).isDisplayed()) {
                driver.findElement(By.xpath("//a[text()='>']")).click();
                setTableElement(driver.findElement(By.xpath("//table[@class='webgrid']")));
                pageRows.add(getRowsValuesByColumn(columnName));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        List<String> rowsCombined = pageRows.get(0);
        for(int i = 1; i < pageRows.size();i++){
            rowsCombined.addAll(pageRows.get(i));
        }
        driver.findElement(By.xpath("//a[@href='/?page=1'][contains(text(),'1')]")).click();
        return rowsCombined;
    }
}
