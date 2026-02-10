package org.example.DesignPatterns.Behavior;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * https://algomaster.io/learn/lld/template-method
 * The Template Method Design Pattern is a behavioral
 * design pattern that defines the skeleton of an algorithm in a base class,
 * but allows subclasses to override specific steps of the algorithm
 * without changing its overall structure.
 *
 * 1. You have a well-defined sequence of steps to perform a task.
 * 2. Some parts of the process are shared across all implementations.
 * 3. You want to allow subclasses to customize specific steps without rewriting the whole algorithm.
 *
 * The Template Method Pattern solves this by capturing the common workflow
 * in a base class and pushing the customizable steps into subclasses,
 * ensuring that the overall structure
 * remains consistent while allowing flexibility where needed.
 * */
public class TemplateMethodDesignPattern {
    /**
     * 1. The Problem: Exporting Reports
     * Let’s say you’re building a tool that allows your application
     * to export reports in different formats — such as CSV, PDF, and Excel.
     * */

    static class ReportData{
        public List<String> getHeaders() {
            return Arrays.asList("ID", "Name", "Value");
        }

        public List<Map<String, Object>> getRows() {
            return Arrays.asList(
                    Map.of("ID", 1, "Name", "Item A", "Value", 100.0),
                    Map.of("ID", 2, "Name", "Item B", "Value", 150.5),
                    Map.of("ID", 3, "Name", "Item C", "Value", 75.25)
            );
        }
    }

    static class TemplateMethodDesignPatternNaive{
        static class CSVReportExporter {
            public void export(ReportData  data, String filePath){
                System.out.println("CSV Exporter: Preparing data (common)...");
                // ... data preparation logic ...

                System.out.println("CSV Exporter: Opening file '" + filePath + ".csv' (common)...");
                // ... file opening logic ...

                System.out.println("CSV Exporter: Writing CSV header (specific)...");
                // String.join(",", data.getHeaders());
                // ... write header to file ...

                System.out.println("CSV Exporter: Writing CSV data rows (specific)...");
                // for (Map<String, Object> row : data.getRows()) { ... format and write row ... }

                System.out.println("CSV Exporter: Writing CSV footer (if any) (common)...");

                System.out.println("CSV Exporter: Closing file '" + filePath + ".csv' (common)...");
                // ... file closing logic ...
                System.out.println("CSV Report exported to " + filePath + ".csv");
            }
        }

        static class PdfReportExporterNaive {
            public void export(ReportData data, String filePath) {
                System.out.println("PDF Exporter: Preparing data (common)...");
                // ... data preparation logic ...

                System.out.println("PDF Exporter: Opening file '" + filePath + ".pdf' (common)...");
                // ... PDF library specific file opening ...

                System.out.println("PDF Exporter: Writing PDF header (specific)...");
                // ... PDF library specific header writing ...

                System.out.println("PDF Exporter: Writing PDF data rows (specific)...");
                // ... PDF library specific data row writing ...

                System.out.println("PDF Exporter: Writing PDF footer (if any) (common)...");

                System.out.println("PDF Exporter: Closing file '" + filePath + ".pdf' (common)...");
                // ... PDF library specific file closing ...
                System.out.println("PDF Report exported to " + filePath + ".pdf");
            }
        }

        /**
         * What’s Wrong with This Design?
         * 1. Code Duplication -
         * 2. Maintenance Overhead
         * 3. Inconsistent Behavior
         * 4. Poor Extensibility
         * */
    }

    /**
     * What We Really Need?
     * Define the common report export workflow once, in a base class.
     * Allow subclasses to override only the format-specific steps,
     * like writing headers and data rows.
     * Ensure that all report exporters follow the same sequence,
     * and enforce consistency in the algorithm structure.
     * */

    /**
     * The Template Method pattern defines the skeleton of an algorithm in
     * a method, deferring some steps to subclasses.
     * It allows you to keep the overall structure of the process
     * consistent, while giving subclasses the
     * flexibility to customize specific parts of the algorithm.
     *
     * 1. AbstractClass (e.g., AbstractReportExporter):
     *    Contains the template method: a final method (e.g., exportReport())
     *    that defines the fixed sequence of steps for the algorithm.
     * 2. Concrete Classes (e.g., CsvReportExporter, PdfReportExporter)
     * */

    static class TemplateMethodDesignPatternImpl{
        static abstract class AbstractReportExporter{
            public final void exportReport(ReportData data, String filePath) {
                prepareData(data);
                openFile(filePath);
                writeHeader(data);
                writeDataRows(data);
                writeFooter(data);
                closeFile(filePath);
                System.out.println("Export complete: " + filePath);
            }

            protected void prepareData(ReportData data) { // Hook method
                System.out.println("Preparing report data (common step)...");
            }

            protected void openFile(String filePath) { // Hook method
                System.out.println("Opening file '" + filePath);
            };

            protected abstract void writeHeader(ReportData data);

            protected abstract void writeDataRows(ReportData data);

            protected void writeFooter(ReportData data) { // Hook method
                System.out.println("Writing footer (default: no footer).");
            }

            protected void closeFile(String filePath) { // Hook method
                System.out.println("Closing file '" + filePath);
            };
        }

        static class CsvReportExporter extends AbstractReportExporter{

            @Override
            protected void writeHeader(ReportData data) {
                System.out.println("CSV Headers::::");
            }

            @Override
            protected void writeDataRows(ReportData data) {
                System.out.println("CSV Data::::");
            }
        }

        static class PDFReportExporter extends AbstractReportExporter{

            @Override
            protected void writeHeader(ReportData data) {
                System.out.println("PDF Headers::::");
            }

            @Override
            protected void writeDataRows(ReportData data) {
                System.out.println("PDF Data::::");
            }
        }

        /**
         * Eliminated code duplication by extracting the shared export process into a base class.
         * Ensured consistency across all exporters by enforcing the same algorithm structure.
         * Made the system extensible — adding a new exporter (e.g., Excel)
         * only requires creating a new subclass.
         * Improved maintainability — common logic changes only in one place.
         * Reduced risk of errors — the order of steps is controlled and protected by the abstract base class.
         * */
    }



    public static void main(String[] args) {
        ReportData reportData = new ReportData();

        TemplateMethodDesignPatternNaive.CSVReportExporter csvReportExporter = new TemplateMethodDesignPatternNaive.CSVReportExporter();
        csvReportExporter.export(reportData, "sales_report");

        TemplateMethodDesignPatternNaive.PdfReportExporterNaive pdfReportExporterNaive = new TemplateMethodDesignPatternNaive.PdfReportExporterNaive();
        pdfReportExporterNaive.export(reportData, "financial_summary");

        TemplateMethodDesignPatternImpl.PDFReportExporter pdfReportExporter = new TemplateMethodDesignPatternImpl.PDFReportExporter();
        TemplateMethodDesignPatternImpl.CsvReportExporter csvReportExporter1  = new TemplateMethodDesignPatternImpl.CsvReportExporter();

        csvReportExporter1.exportReport(reportData, "sales_report");
        pdfReportExporter.exportReport(reportData, "financial_summary");

    }
}

