package persistence;

import model.MyCart;
import model.Ticket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderInvalidFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MyCart mc = reader.read();
            fail("IOException expected");
        }
        catch (IOException e) {
             //pass
        }
    }

    @Test
    void testReaderEmptyCart() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCart.json");
        try {
            MyCart mc = reader.read();
            assertEquals("Yuki's cart", mc.getName());
            assertEquals(0,mc.quantity());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralCart() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCart.json");
        try {
            MyCart mc = reader.read();
            assertEquals("Yuki's cart", mc.getName());
            List<Ticket> tickets = mc.getTickets();
            assertEquals(2, mc.quantity());
            checkTicket("lower", 100, 12, 12, 100.0, tickets.get(0));
            checkTicket("upper", 201,1,1,99.9,tickets.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
