import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
	    Scanner InputStream=null;
	    PrintWriter WriteOutput,WriteToFile;

	    try
        {
            InputStream=new Scanner(new FileInputStream("input.txt"));
        }
        catch(FileNotFoundException e)
        {
            out.println("File input.txt not found");
            out.println("or could not be opened.");
            System.exit(-1);
        }

	    //How it works
        //First determine number of emails
        String fileLines;
	    int numberOfEmails=0, trackEmails=0;
	    char temp;
        while(InputStream.hasNextLine())
        {
            fileLines=InputStream.nextLine();
            for(int i=0;i<fileLines.length();i++)
            {
                temp=fileLines.charAt(i);
                if(temp=='@')
                    numberOfEmails++;   //count emails.
            }
        }

        //Create Email array with correct total of emails
        Email [] EmailArr=new Email[numberOfEmails];
        InputStream.close();

        //First read each string until one containing an "@" symbol is found
        //Next split that string into two parts delimited by the @ symbol.
        //Split the second substring into further parts delimited by the '.' symbol.
        try {
            InputStream=new Scanner(new FileInputStream("input.txt"));
        } catch (FileNotFoundException e) {
            out.println("File 'input.txt' not found");
            System.exit(-1);
        }
        String currentString, userName, domain, ext, department;
        while(InputStream.hasNext())    //Read Next String
        {

            //Read each string
            currentString=InputStream.next();

            //Check if String is an Email
            if(currentString.contains("@")&&!(currentString.endsWith("@"))&&!currentString.startsWith("@")&&currentString.contains("."))
            {
                String [] firstSplitArr = currentString.split("@");      //Split the Email string into 2
                //out.println("FirstSplitArray length is " + firstSplitArr.length);
                if(firstSplitArr.length>1&&!firstSplitArr[1].matches(".*\\d+.*"))
                {
                    String[] secondSplitArr = firstSplitArr[1].split("\\.");   //Split the right half of the Email using a dot delimiter. Double backslash required.

                   /* out.println("FirstSplitArray length is " + firstSplitArr.length);
                    for (String tempStr : firstSplitArr) {
                        out.println(tempStr);
                    }
                    out.println("Second Array length is " + secondSplitArr.length);
                    for (String tempStr : secondSplitArr) {
                        out.println(tempStr);
                    }
                    */
                    secondSplitArr[secondSplitArr.length - 1] = secondSplitArr[secondSplitArr.length - 1].replace(",", "");    //3 new strings if subdomain otherwise 2 new string
                    if (secondSplitArr.length == 3) {
                        userName = firstSplitArr[0];
                        department = secondSplitArr[0];
                        domain = secondSplitArr[1];
                        ext = secondSplitArr[2];
                        EmailArr[trackEmails++] = new UniversityEmail(userName, domain, ext, department);
                    } else if (secondSplitArr.length == 2) {
                        userName = firstSplitArr[0];
                        domain = secondSplitArr[0];
                        ext = secondSplitArr[1];
                        EmailArr[trackEmails++] = new Email(userName, domain, ext);
                    }
                }
            }
        }
        //For Output:
        //Use a do/while loop with switch statements reading an input value from 0-8
        //Overwrite toString for Email class to use this as output.

        //out.println("There are " +numberOfEmails +" emails in the file.");
        //out.println("Email Tracker = " + trackEmails);
        Email[] RealEmails=new Email[trackEmails];
        System.arraycopy(EmailArr,0,RealEmails,0,trackEmails);

        try{
            //WriteOutput= new PrintWriter(new FileOutputStream("AllEmails.txt"));
            WriteToFile= new PrintWriter(new FileOutputStream("out.txt"));
            Scanner readInput =new Scanner(System.in);
            out.print("Enter Email Code: ");
            int emailCode=readInput.nextInt();
                    if(emailCode==0) {
                        for (Email tempEmail : RealEmails) {
                            if (tempEmail.getCode() == 0) {
                                WriteToFile.println(tempEmail);
                            }
                        }
                    }
                    else if(emailCode>0 && emailCode<8)
                    {
                        for (Email tempEmail : RealEmails) {
                            if (tempEmail.getCode()==emailCode ) {
                                WriteToFile.println(tempEmail);
                            }
                        }
                    }
                    else if (emailCode == 8)
                    {
                        for (Email tempEmail : RealEmails) {
                            if (tempEmail.getCode()!=0 ) {
                                WriteToFile.println(tempEmail);
                            }
                        }
                    }
                    /*
            for(Email tempEmail: RealEmails)
            {
                WriteOutput.println(tempEmail);
            }
            WriteOutput.close();*/
            WriteToFile.close();
        }
        catch(FileNotFoundException x)
        {
            System.exit(-1);
        }

    }
}


//Bonus Requirements
//If emailString.split results in 1 array then incomplete Email, then don't store.
//What if incomplete email is like this: "incomplete@java" ?
//If domain, subdomain or extension contains a #, then don't store.