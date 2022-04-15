package Basics.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DaraDriven {

    public ArrayList<String> getData(String testCaseName) throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();

        //FileInputStream argument
        FileInputStream fileInputStream = new FileInputStream("E:\\IdeaProjects\\RestAssured\\DemoData.xlsx");

        //Создаем объект workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //Количество страниц
        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("testdata")) {

                //Получаем страницу
                XSSFSheet sheet = workbook.getSheetAt(i);

                //Identify the column with testcases

                Iterator<Row> rows = sheet.iterator();

                //Первая строка. Row - это коллекция cells(ячеек)
                Row firstrow = rows.next();

                //Получили коллекцию ячеек первой строки
                Iterator<Cell> cell = firstrow.cellIterator();

                int k = 0;

                int column = 0;

                while (cell.hasNext()) {

                    Cell value = cell.next();

                    if (value.getStringCellValue().equalsIgnoreCase("Test")) {

                        column = k;

                    }
                    k++;
                }

                System.out.println(column);

                while (rows.hasNext()) {

                    Row r = rows.next();

                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                        Iterator<Cell> cv = r.cellIterator();

                        while (cv.hasNext()) {

                            Cell cell1 = cv.next();

                            if (cell1.getCellType() == CellType.STRING) {

                                arrayList.add(cell1.getStringCellValue());

                            } else {

                                String numberConvertedToText = NumberToTextConverter.toText(cell1.getNumericCellValue());

                                arrayList.add(numberConvertedToText);

                            }
                        }
                    }
                }
            }
        }

        return arrayList;
    }
}

