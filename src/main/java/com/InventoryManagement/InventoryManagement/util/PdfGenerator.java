package com.InventoryManagement.InventoryManagement.util;

import com.InventoryManagement.InventoryManagement.model.entity.BillBE;
import com.InventoryManagement.InventoryManagement.model.entity.BillItemBE;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

public class PdfGenerator {
    public static ByteArrayInputStream generateInvoice(BillBE bill) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Invoice", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Customer: " + bill.getCustomerName()));
            String userName = bill.getUser() != null ? bill.getUser().getUserName() : "N/A";
            document.add(new Paragraph("Billed By: " + userName));
            document.add(new Paragraph("Date: " + bill.getBillDate()));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4);
            Stream.of("Product", "Quantity", "Price", "Subtotal")
                    .forEach(header -> {
                        PdfPCell cell = new PdfPCell();
                        cell.setPhrase(new Phrase(header));
                        table.addCell(cell);
                    });

            List<BillItemBE> items = bill.getItems();
            for (BillItemBE item : items) {
                table.addCell(item.getProductName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(String.valueOf(item.getPrice()));
                table.addCell(String.valueOf(item.getSubtotal()));
            }

            document.add(table);
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Total: ₹" + bill.getTotalAmount()));

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
