package Basics.Excel;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

    public static void main(String[] args) throws IOException {

        DaraDriven daraDriven = new DaraDriven();
        ArrayList<String> data = daraDriven.getData("Delete Profile");
        System.out.println(data);

    }
}
