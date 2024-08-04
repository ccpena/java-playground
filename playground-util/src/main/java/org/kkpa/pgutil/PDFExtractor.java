package org.kkpa.pgutil;

import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.state.Concatenate;
import org.apache.pdfbox.contentstream.operator.state.Restore;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.pdfbox.contentstream.operator.state.SetMatrix;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PDFExtractor extends PDFStreamEngine {

  public PDFExtractor() throws IOException {
    addOperator(new Concatenate());
    addOperator(new DrawObject());
    addOperator(new Save());
    addOperator(new Restore());
    addOperator(new SetMatrix());
  }

  public static void extractPDFContent(String filePath) {
    try (PDDocument document = PDDocument.load(new File(filePath), "91186523")) {
      // Extract text
      document.setAllSecurityToBeRemoved(true);
      PDFTextStripper stripper = new PDFTextStripper();
      String text = stripper.getText(document);
      System.out.println("Extracted Text:");
      System.out.println(text);

      // Extract table data (you'll need to parse the text to get structured data)
      // This is a simplified example; you might need more sophisticated parsing
      if (text.contains("RESUMEN")) {
        String[] lines = text.split("\n");
        for (String line : lines) {
          if (line.contains("SALDO ANTERIOR") || line.contains("TOTAL ABONOS") ||
                  line.contains("TOTAL CARGOS") || line.contains("SALDO ACTUAL")) {
            System.out.println(line.trim());
          }
        }
      }

      // Extract transaction details
      if (text.contains("FECHA")) {
        String[] lines = text.split("\n");
        boolean inTransactionTable = false;
        for (String line : lines) {
          if (line.contains("FECHA") && line.contains("DESCRIPCIÓN")) {
            inTransactionTable = true;
            System.out.println("\nTransaction Details:");
            continue;
          }
          if (inTransactionTable && !line.trim().isEmpty()) {
            System.out.println(line.trim());
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // Get the classloader
    ClassLoader classLoader = PDFExtractor.class.getClassLoader();

    // Get the resource as a stream
    try (InputStream inputStream = classLoader.getResourceAsStream("bancolombia.pdf")) {
      if (inputStream == null) {
        System.out.println("File not found in resources folder!");
        return;
      }

      // Create a temporary file
      File tempFile = File.createTempFile("temp", ".pdf");
      tempFile.deleteOnExit();

      // Copy the input stream to the temporary file
      Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

      // Now use the temporary file path
      String pdfFilePath = tempFile.getAbsolutePath();
      extractPDFContent(pdfFilePath);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
