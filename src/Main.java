package src;
/*
 * This program runs a console that can be used to view and edit infomration in a school system, such as giving grades and marking attendance
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        EduMaster eduMaster = new EduMaster();
        eduMaster.startProgram();
    }


}