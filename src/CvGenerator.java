import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CvGenerator extends InputHelper   {

 // Method to get integer input from user with error handling


 public void generateResume(Scanner scanner) {


  // Get user input for personal details
  System.out.println("WELCOME TO RESUME BUILDER");
  System.out.println("Enter Full Name:");
  String name = scanner.nextLine();

  System.out.println("Enter Email:");
  String email = scanner.nextLine();


  int phone = getInput("Enter Phone:", scanner);


  System.out.println("Enter Address:");
  String address = scanner.nextLine();

  System.out.println("Enter Professional Summary:");
  String summary = scanner.nextLine();



  // Get user input for work experiences
  int numWorkExperiences = getInput("Enter the number of work experiences:", scanner);

  String[][] workDetails = new String[numWorkExperiences][5]; // [numWorkExperiences][5] to store company, post, start date, end date, and description

  for (int i = 0; i < numWorkExperiences; i++) {
   System.out.println("Enter details for Work Experience " + (i + 1) + ":");
   System.out.println("Company:");
   workDetails[i][0] = scanner.nextLine();
   System.out.println("Post:");
   workDetails[i][1] = scanner.nextLine();
   System.out.println("Start Date:");
   workDetails[i][2] = scanner.nextLine();
   System.out.println("End Date:");
   workDetails[i][3] = scanner.nextLine();
   System.out.println("Description:");
   workDetails[i][4] = scanner.nextLine();
  }




  // Get user input for education details
  int numEducations = getInput("Enter the number of education details:", scanner);

// Create a 2D array to store education details: [numEducations][4] to store school, degree, start date, and end date
  String[][] educationDetails = new String[numEducations][4];

// Loop to collect education details from the user
  for (int i = 0; i < numEducations; i++) {
   System.out.println("Enter details for Education " + (i + 1) + ":");
   System.out.println("School:");
   educationDetails[i][0] = scanner.nextLine();
   System.out.println("Degree:");
   educationDetails[i][1] = scanner.nextLine();
   System.out.println("Start Date:");
   educationDetails[i][2] = scanner.nextLine();
   System.out.println("End Date:");
   educationDetails[i][3] = scanner.nextLine();
  }


// Get user input for skills
  int numSkills = getInput("Enter the number of skills:", scanner);

  String[] skills = new String[numSkills];

  for (int i = 0; i < numSkills; i++) {
   System.out.println("Enter Skill " + (i + 1) + ":");
   skills[i] = scanner.nextLine();
  }


  scanner.close();

  String fileName = "myResume.pdf";

  try {
   Document document = new Document();
   PdfWriter.getInstance(document, new FileOutputStream(fileName));
   document.open();

   // Define fonts and colors
   BaseFont bf = BaseFont.createFont("fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
   BaseColor lighterBlack = new BaseColor(75, 75, 75); // Adjust RGB values to make it lighter
   BaseColor SeparatorColor = new BaseColor(200, 200, 200);
   BaseColor darkerBlue = new BaseColor(0, 0, 128);
   Font sectionFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD, darkerBlue);

   // Add name
   Font nameFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 25, Font.BOLD, darkerBlue);
   Paragraph nameParagraph = new Paragraph(name, nameFont);
   nameParagraph.setAlignment(Paragraph.ALIGN_CENTER);
   document.add(nameParagraph);

// Add contact details
   Font contactFont = FontFactory.getFont(FontFactory.TIMES, 12, java.awt.Font.PLAIN, BaseColor.BLACK);

   Paragraph addressParagraph = new Paragraph(address, contactFont);
   addressParagraph.setAlignment(Paragraph.ALIGN_CENTER);
   document.add(addressParagraph);

   Paragraph contactInfoParagraph = new Paragraph();
   contactInfoParagraph.setAlignment(Paragraph.ALIGN_CENTER);
   contactInfoParagraph.setFont(contactFont);
   contactInfoParagraph.add(new Chunk("M: ", FontFactory.getFont(FontFactory.TIMES, 12, java.awt.Font.BOLD, BaseColor.BLACK)));
   contactInfoParagraph.add(new Chunk(String.valueOf(phone), FontFactory.getFont(FontFactory.TIMES, 12, java.awt.Font.PLAIN, BaseColor.BLACK)));
   contactInfoParagraph.add(new Chunk("   "));
   contactInfoParagraph.add(new Chunk("E: ", FontFactory.getFont(FontFactory.TIMES, 12, java.awt.Font.BOLD, BaseColor.BLACK)));
   contactInfoParagraph.add(new Chunk(email, FontFactory.getFont(FontFactory.TIMES, 12, java.awt.Font.PLAIN, BaseColor.BLACK)));
   document.add(contactInfoParagraph);

   document.add(Chunk.NEWLINE);

   // Add separator
   Font separatorFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.NORMAL, SeparatorColor);
   Paragraph Separator = new Paragraph("----------------------------------------------------------------------------------------------------------------",
           separatorFont);
   Separator.setAlignment(Paragraph.ALIGN_CENTER);
   document.add(Separator);
   document.add(Chunk.NEWLINE);


   // Add professional summary
   Paragraph professionalSummary = new Paragraph("PROFESSIONAL SUMMARY", sectionFont);
   document.add(professionalSummary);

   Font summaryFont = FontFactory.getFont(String.valueOf(bf), 12, lighterBlack);
   Paragraph summaryParagraph = new Paragraph(summary, summaryFont);
   summaryParagraph.setIndentationLeft(20); // Indentation for professional summary
   document.add(summaryParagraph);
   document.add(Chunk.NEWLINE);



   // Add work history
   Paragraph workHistory = new Paragraph("WORK HISTORY", sectionFont);
   document.add(workHistory);
// Add work experiences dynamically
   Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
   Font normalFont = FontFactory.getFont(String.valueOf(bf), 12, java.awt.Font.PLAIN, lighterBlack);

   for (int i = 0; i < numWorkExperiences; i++) {

    // Create a paragraph for work experience
    Paragraph workExperience = new Paragraph();

    // Make the company name bold
    Chunk boldCompanyName = new Chunk(workDetails[i][0], boldFont);
    workExperience.add(boldCompanyName);

    // Add the date range
    workExperience.add(" | " + workDetails[i][2] + " - " + workDetails[i][3]);

    // Add a new line
    workExperience.add(new Chunk(Chunk.NEWLINE));

    // Make the position bold
    Chunk boldPosition = new Chunk(workDetails[i][1], boldFont);
    workExperience.add(boldPosition);

    // Add a new line
    workExperience.add(new Chunk(Chunk.NEWLINE));

    // Add the description
    workExperience.add(new Chunk("\u2022 " + workDetails[i][4], normalFont));

    // Set the left indentation
    workExperience.setIndentationLeft(20);

    // Add the work experience paragraph to the document
    document.add(workExperience);

    // Add a new line after each work experience
    document.add(Chunk.NEWLINE);
   }


   // Add skills section with two columns
// Create the "Skills" section paragraph
   Paragraph skillsSection = new Paragraph("SKILLS", sectionFont);
   document.add(skillsSection);

// Create a PdfPTable with two columns for the skills
   PdfPTable skillsTable = new PdfPTable(2);
   skillsTable.setWidthPercentage(100);

// Add skills to the table
   for (int i = 0; i < numSkills; i++) {
    PdfPCell cell1 = new PdfPCell(new Phrase("\u2022 " + skills[i], FontFactory.getFont(String.valueOf(bf), 12, java.awt.Font.PLAIN, lighterBlack)));
    cell1.setBorder(Rectangle.NO_BORDER);
    // Set left indentation for the skills
    cell1.setPaddingLeft(20);
    skillsTable.addCell(cell1);

    // If there's only one skill and it's in the last row, add an empty cell to the second column
    if (i == numSkills - 1 && numSkills % 2 == 1) {
     PdfPCell emptyCell = new PdfPCell();
     emptyCell.setBorder(Rectangle.NO_BORDER);
     skillsTable.addCell(emptyCell);
    }
   }

// Add the table to the document
   document.add(skillsTable);
   document.add(Chunk.NEWLINE);


   // Add education section
   Paragraph educationSection = new Paragraph("EDUCATION", sectionFont);
   document.add(educationSection);

// Add education details dynamically
   Font educationFont = FontFactory.getFont(String.valueOf(bf), 12, java.awt.Font.PLAIN, lighterBlack);
   for (int i = 0; i < numEducations; i++) {
    Paragraph educationDetail = new Paragraph("\u2022 " + educationDetails[i][0] + " | " + educationDetails[i][1] + ", " + educationDetails[i][2] + " - " + educationDetails[i][3], educationFont);
    educationDetail.setIndentationLeft(20); // Indentation for education section
    document.add(educationDetail);
    document.add(Chunk.NEWLINE);
   }

   document.close();
   System.out.println("Resume generated successfully!");
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
}
