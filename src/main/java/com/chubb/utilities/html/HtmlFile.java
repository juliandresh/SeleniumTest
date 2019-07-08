package com.chubb.utilities.html;

import com.chubb.config.ConfigurationFile;

import java.io.FileWriter;
import java.io.IOException;

import static j2html.TagCreator.*;

public class HtmlFile {
    public void createHtml(ConfigurationFile configFile, String timeStamp)
    {
        String htmlLocation = configFile.getPdfLocation();

        String htmlString = getHtmlCode();
        FileWriter fw = null;
        try {
            fw = new FileWriter(htmlLocation+"\\testHtml.html");
            fw.write(htmlString);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlCode(){
        return html(
                head(
                        title("Titulo")
                ),
                body(
                        h1("Hola mundo")
                )
        ).render();
    }
}
