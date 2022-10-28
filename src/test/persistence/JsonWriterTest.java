package persistence;

import model.MyCart;
import model.Ticket;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MyCart mc = new MyCart("Yuki's cart");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCart() {
        try {
            MyCart mc = new MyCart("Yuki's cart");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCart.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCart.json");
            mc = reader.read();
            assertEquals("Yuki's cart", mc.getName());
            assertEquals(0, mc.quantity());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCart() {
        try {
            MyCart mc = new MyCart("Yuki's cart");
            mc.addTicket(new Ticket("lower", 100, 12, 12, 100.0));
            mc.addTicket(new Ticket("upper", 201,1,1,99.9));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCart.json");
            writer.open();
            writer.write(mc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCart.json");
            mc = reader.read();
            List<Ticket> tickets = mc.getTickets();
            assertEquals("Yuki's cart", mc.getName());
            assertEquals(2, mc.quantity());
            checkTicket("lower", 100, 12, 12, 100.0, tickets.get(0));
            checkTicket("upper", 201,1,1,99.9,tickets.get(1));
        }
        catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
