package bobrov.projectBook.connectionBD;

import static org.junit.Assert.*;
import org.junit.Test;


import java.util.ArrayList;

/**
 * Created by Rabbik on 02.08.2016.
 */
public class ConnectToBDTest {


    @Test
    public void readDate() throws Exception {
        ConnectToBD connect = new ConnectToBD(new ConnectionBD("jdbc:mysql://127.0.0.1:3306/mybookcollections", "root", "root"));
        assertEquals(true, connect.readDate() instanceof ArrayList);
    }

}