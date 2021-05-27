package ExcelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readexcelsheet {
	
	static XSSFWorkbook wb;
	static XSSFSheet sh;
	static XSSFRow row;
	public String ColName="Result";
    public int col_num;
	
	 public void WriteResult( String excelfilepath, String sheetname, String Ress, int DR) throws Exception
	    {
	        FileInputStream file_input_stream= new FileInputStream(excelfilepath);
	        wb=new XSSFWorkbook(file_input_stream);
	        sh=wb.getSheet(sheetname);
	        XSSFRow Row=sh.getRow(0);
	 
	        int sheetIndex=wb.getSheetIndex(sheetname);
	        DataFormatter formatter = new DataFormatter();
	        if(sheetIndex==-1)
	        {
	            System.out.println("No such sheet in file exists");
	        } else      {
	            col_num=-1;
	                for(int i=0;i<Row.getLastCellNum();i++)
	                {
	                    XSSFCell cols=Row.getCell(i);
	                    String colsval=formatter.formatCellValue(cols);
	                    if(colsval.trim().equalsIgnoreCase(ColName.trim()))
	                    {
	                        col_num=i;
	                        break;
	                    }
	                }
	              
	                Row= sh.getRow(DR);
	                try
	                    {
	                    //get my Row which is equal to Data  Result and that colNum
	                        XSSFCell cell=sh.getRow(DR).getCell(col_num);
	                        // if no cell found then it create cell
	                        if(cell==null) {
	                            cell=Row.createCell(col_num);                           
	                        }
	                        //Set Result is pass in that cell number
	                        cell.setCellValue(Ress);
	                                         
	                         
	                    }
	                catch (Exception e)
	                {
	                    System.out.println(e.getMessage()); 
	                } 
	    
	        }
	            FileOutputStream file_output_stream=new FileOutputStream(excelfilepath);
	            wb.write(file_output_stream);
	            file_output_stream.close();
	            if(col_num==-1) {
	                System.out.println("Column you are searching for does not exist");
	            }
	    }
	
	
	 // This method handles the excel - opens it and reads the data from the
	// respective cells using a for-loop & returns it in the form of a string array
	@SuppressWarnings("deprecation")
	public static Object[][] getExcelData(String fileName, String sheetName) {

		Object[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			wb = new XSSFWorkbook(fis);
		    sh = wb.getSheet(sheetName);
			row = sh.getRow(0);
			DataFormatter formatter = new DataFormatter();
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];//this will start from 2nd row

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {
					row = sh.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();
					
					
					switch (cell.getCellTypeEnum()) {
			        case BOOLEAN:
			            System.out.print(cell.getBooleanCellValue());
			            break;
			        case STRING:
			            System.out.print(cell.getRichStringCellValue().getString());
			            break;
			        case NUMERIC:
			        	String.valueOf((int)cell.getNumericCellValue());
			        	
			            if (DateUtil.isCellDateFormatted(cell)) 
			            {
			                System.out.print(cell.getDateCellValue());
			            } else {
			            	Double testData= Double.valueOf(cell.getNumericCellValue()); 
			            	Integer doucon = testData.intValue();
			                System.out.print(doucon);
			            }
			            break;
			        case FORMULA:
			            System.out.print(cell.getCellFormula());
			            break;
			        case BLANK:
			        	
			        	if(row==null)
			        		data[i][j]= "";
	                    else
	                    {
	                        cell= row.getCell(j);
	                        if(cell==null)
	                        	data[i][j]= ""; //if it get Null value it pass no data 
	                        else
	                        {
	                            String value=formatter.formatCellValue(cell);
	                            data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	                        }
	                    }
			        	
			            System.out.print("");
			            break;
			        default:
			            System.out.print("");
			    }
			    System.out.print("\t");
			}
			  System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return data;
	}
}
