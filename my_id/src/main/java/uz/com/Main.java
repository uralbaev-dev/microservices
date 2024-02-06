package uz.com;

import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import com.spire.barcode.QRCodeECL;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @className: Main
 * @date: 05.02.2024
 * @author: Uralbaev Diyorbek
 */

public class Main {
    public static final String client_id = "octo_sdk-6oIplJ0AmUqKFJO8TGr2rQav1QMkvJ3T3VFqczAo";
    public static final String method = "strong";
    public static final String branch_id = "00980";

    public static void main(String []args) throws IOException {
        //Instantiate a BarcodeSettings object
        BarcodeSettings settings = new BarcodeSettings();
        //Set barcode type
        settings.setType(BarCodeType.QR_Code);
        //Set barcode data
        String data = "client_id=" + client_id +"&method="+ method +"&branch_id=" + branch_id + "&operator=T41";
        settings.setData(data);
        //Set barcode module width
        settings.setX(2);
        //Set error correction level
        settings.setQRCodeECL(QRCodeECL.M);

        //Set text visibility
        settings.setShowText(false);
        settings.setShowTopText(true);
        settings.setShowBottomText(true);

        //Set border visibility
        settings.hasBorder(false);

        //Instantiate a BarCodeGenerator object based on the specific settings
        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
        //Generate QR code image
        BufferedImage bufferedImage = barCodeGenerator.generateImage();

        //save the image to a .png file
        ImageIO.write(bufferedImage,"png",new File("D:/OCTO/QR_Code.png"));
    }
}
