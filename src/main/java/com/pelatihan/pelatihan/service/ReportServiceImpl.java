package com.pelatihan.pelatihan.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.pelatihan.pelatihan.model.Users;
import com.pelatihan.pelatihan.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ReportServiceImpl implements ReportService{

    private final UsersRepository usersRepository;

    public ReportServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    @Override
    public Object generateExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("DATA USER");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont headerFont = ((XSSFWorkbook) workbook).createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 12);  
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        headerStyle.setFont(headerFont);


        Row headerRow = sheet.createRow(0);

        Cell idCell = headerRow.createCell(0);
        idCell.setCellValue("ID");
        idCell.setCellStyle(headerStyle);

        Cell usernameCell = headerRow.createCell(1);
        usernameCell.setCellValue("USERNAME");
        usernameCell.setCellStyle(headerStyle);

        Cell passwordCell = headerRow.createCell(2);
        passwordCell.setCellValue("PASSWORD");
        passwordCell.setCellStyle(headerStyle);

        Cell statusCell = headerRow.createCell(3);
        statusCell.setCellValue("Status");
        statusCell.setCellStyle(headerStyle);

        List<Users> usersList = usersRepository.findAll();

        int currentIndexRow = 1; // guna untuk membuat baris pada excel
        for(Users user : usersList) {
            Row bodyRow = sheet.createRow(currentIndexRow);

            Cell cell = bodyRow.createCell(0);
            cell.setCellValue(user.getId());

            cell = bodyRow.createCell(1);
            cell.setCellValue(user.getUsername());  

            cell = bodyRow.createCell(2);
            cell.setCellValue(user.getPassword());

            cell = bodyRow.createCell(3);
            cell.setCellValue(user.getStatus());

            currentIndexRow++;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            workbook.write(outputStream);
            outputStream.toByteArray();
        }finally{
            workbook.close();
        }

        return outputStream.toByteArray();
    }
}
