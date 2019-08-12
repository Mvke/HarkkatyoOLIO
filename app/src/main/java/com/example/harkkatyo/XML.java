package com.example.harkkatyo;

import android.content.Context;
import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class XML {

        public static String CreateXMLString(ArrayList<TransactionInfo> arrayList) {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            try {
                xmlSerializer.setOutput(writer);

                //Start Document
                xmlSerializer.startDocument("UTF-8", true);
                xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                //Open Tag <file>
                xmlSerializer.startTag("", "file");


                for(TransactionInfo transactionInfo : arrayList) {
                    xmlSerializer.startTag("", "From");
                    xmlSerializer.text(transactionInfo.getAccountnumberfrom());
                    xmlSerializer.endTag("", "From");
                    xmlSerializer.startTag("", "To");
                    xmlSerializer.text(transactionInfo.getAccountnumberto());
                    xmlSerializer.endTag("", "To");
                    xmlSerializer.startTag("", "Ammount");
                    xmlSerializer.text(transactionInfo.getMoneytxt());
                    xmlSerializer.endTag("", "Ammount");
                }

                //end tag <file>
                xmlSerializer.endTag("", "file");
                xmlSerializer.endDocument();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return writer.toString();
        }


    }
