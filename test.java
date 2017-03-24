package seleniumtest;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.csvreader.CsvReader;
@RunWith(Parameterized.class)
public class test {
	private String num;//学号
	private String name;//姓名
	private String text;//git
	public test(String num, String name,String text)
	{
		this.num=num;
		this.name=name;
		this.text=text;
	}
	@Parameters
	public static Collection<Object[]> getData() throws IOException{
		String inString;
		File inFile = new File("D:/inputgit.csv"); // 读取的CSV文件
		String[][] array=new String[117][3];
		int i=0;
		try {
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            CsvReader creader = new CsvReader(reader, ',');
            while(creader.readRecord()){
                inString = creader.getRawRecord();//读取一行数据
                String[] data=inString.split(",");
                array[i]=data;
                i++;
            }  
            creader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
		return Arrays.asList(array);
	}
	@Test
	public void seleniumTest() {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.get("http://121.193.130.195:8080/");
		WebElement element1 = driver.findElement(By.id("name"));
		element1.sendKeys(this.num);
		WebElement element2 = driver.findElement(By.id("pwd"));
		element2.sendKeys(this.num.substring(4,10));
		WebElement btn = driver.findElement(By.id("submit"));
		btn.click();
		WebElement element =driver.findElement(By.xpath("//tbody[@id='table-main']/tr[last()]/td[last()]"));
		text=element.getText();
		assertEquals(this.text,text);		


	}

}
