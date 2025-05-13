import static org.junit.Assert.*;
import org.junit.Test;


public class FlikTest {

    @Test
    public void flikTest(){
        assertTrue(Flik.isSameNumber(127,127));
        assertTrue(Flik.isSameNumber(128,128));
    }

    @Test
    public void HsTest(){
        assertTrue(Flik.isSameNumber(1,1));
    }
}
