package com.example.snehatummarguddi.cleanbanglore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snehatummarguddi on 26/06/17.
 */

public class DataProvider {
private static List<Course> data =new ArrayList<>();
    public static List<Course> getData() {return data;}
    static {
        data.add(new Course(10101,"software testing", "this is last second exam",3 )
        );
        data.add(new Course(10102,"operation research","this is last exam",4));
        data.add(new Course(10102,"operation research2","this is plast exam",5));

    }
}
