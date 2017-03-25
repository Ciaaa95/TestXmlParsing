package com.xml_loadingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        result = (TextView) findViewById(R.id.result);
        ArrayList<String> notes = new ArrayList<>();
        try {
            File root = new File(getExternalFilesDir(null), "XML");

            if (!root.exists()) {
                root.mkdir();
            }
            File filepath = new File(root, "test.xml");
            FileWriter writer = new FileWriter(filepath);
            tv1.setText("2");
            writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?> \n<notes> \n<note>1</note> \n<note> 2 </note \n</notes>");
            writer.flush();
            writer.close();
            tv1.setText("Successo!!!");
            FileInputStream inputStream = new FileInputStream(filepath);
            try{
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(inputStream, null);
                parser.nextTag();
                result.setText(" "+parser.getName());
                parser.nextTag();
                result.append(" " + parser.getName());
                if(parser.next() == XmlPullParser.TEXT){
                    result.append(" "+parser.getText());
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                tv1.setText(e.toString());
            }
         } catch (IOException e) {
            e.printStackTrace();
            tv2.setText(e.toString());

        }

    }

}

