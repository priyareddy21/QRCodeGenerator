package com.example.demo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.example.demo.model.Employee;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    public static void generateQRCode(Employee employee) {
        String qrCodePath = "./src/main/resources/static/images/";

        try {
            String qrCodeName = qrCodePath + employee.getFirstName() + employee.getId() + "-QRCODE.png";
            var qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
                    "ID: " + employee.getId() + "\n" +
                            "FirstName: " + employee.getFirstName() + "\n" +
                            "LastName: " + employee.getLastName() + "\n" +
                            "Email: " + employee.getEmail() + "\n" +
                            "Mobile: " + employee.getPhnNumber(),
                    BarcodeFormat.QR_CODE, 400, 400);
            Path path = FileSystems.getDefault().getPath(qrCodeName);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("QR Code generated successfully at: " + path.toString());
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR Code: " + e.getMessage());
        }
    }
}