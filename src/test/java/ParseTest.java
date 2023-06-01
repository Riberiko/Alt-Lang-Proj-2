import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseTest {

    @Test
    void test1()
    {
        String cellStr1 = "Benefon,Vega,1999,Discontinued,145 x 56 x 23 mm (5.71 x 2.20 x 0.91 in),190 g (6.70 oz),Mini-SIM,Monochrome graphic,,6 lines,V1,";
        Cell actualOne = new Cell();
        actualOne.setOem("Benefon");
        actualOne.setModel("Vega");
        actualOne.setLaunch_announced(1999);
        actualOne.setLaunch(0);
        actualOne.setLaunch_status("Discontinued");
        actualOne.setBody_dimensions("145 x 56 x 23 mm (5.71 x 2.20 x 0.91 in)");
        actualOne.setBody_weight(190);
        actualOne.setBody_sim("Mini-SIM");
        actualOne.setDisplay_type("Monochrome graphic");
        actualOne.setDisplay_size(0);
        actualOne.setDisplay_resolution("6 lines");
        actualOne.setFeatures_sensors("V1");
        actualOne.setPlatform_os("NULL");


        Assertions.assertEquals(actualOne.toString(), ParseCell.parseCell(cellStr1).toString());
    }

    @Test
    void test2()
    {
        String cellStr2 = "Garmin-Asus,nuvifone M10,\"2010, January. Released 2010, March\",Discontinued,-,-,Mini-SIM,\"TFT resistive touchscreen, 65K colors\",\"3.5 inches, 34.9 cm\",\"480 x 800 pixels, 5:3 ratio (~267 ppi density)\",Accelerometer,Microsoft Windows Mobile 6.5.3 Professional";
        Cell actualTwo = new Cell();
        actualTwo.setOem("Garmin-Asus");
        actualTwo.setModel("nuvifone M10");
        actualTwo.setLaunch_announced(2010);
        actualTwo.setLaunch(0);
        actualTwo.setLaunch_status("Discontinued");
        actualTwo.setBody_dimensions("NULL");
        actualTwo.setBody_weight(0);
        actualTwo.setBody_sim("Mini-SIM");
        actualTwo.setDisplay_type("TFT resistive touchscreen, 65K colors");
        actualTwo.setDisplay_size(3.5f);
        actualTwo.setDisplay_resolution("480 x 800 pixels, 5:3 ratio (~267 ppi density)");
        actualTwo.setFeatures_sensors("Accelerometer");
        actualTwo.setPlatform_os("Microsoft Windows Mobile 6.5.3 Professional");

        Assertions.assertEquals( actualTwo.toString(), ParseCell.parseCell(cellStr2).toString());
    }

    @Test
    void test3()
    {
        String cellStr3 = "Gigabyte,GSmart G1305 Boston,\"2010, April. Released 2010, April\",Discontinued,116 x 56.8 x 12.4 mm (4.57 x 2.24 x 0.49 in),118 g (4.16 oz),Mini-SIM,\"TFT capacitive touchscreen, 256K colors\",\"3.2 inches, 30.5 cm (~46.3% screen-to-body ratio)\",\"320 x 480 pixels, 3:2 ratio (~180 ppi density)\",Accelerometer,Android 1.6 (Donut)";
        Cell actualThree = new Cell();
        actualThree.setOem("Gigabyte");
        actualThree.setModel("GSmart G1305 Boston");
        actualThree.setLaunch_announced(2010);
        actualThree.setLaunch(0);
        actualThree.setLaunch_status("Discontinued");
        actualThree.setBody_dimensions("116 x 56.8 x 12.4 mm (4.57 x 2.24 x 0.49 in)");
        actualThree.setBody_weight(118);
        actualThree.setBody_sim("Mini-SIM");
        actualThree.setDisplay_type("TFT capacitive touchscreen, 256K colors");
        actualThree.setDisplay_size(3.2f);
        actualThree.setDisplay_resolution("320 x 480 pixels, 3:2 ratio (~180 ppi density)");
        actualThree.setFeatures_sensors("Accelerometer");
        actualThree.setPlatform_os("Android 1.6 (Donut)");

        Assertions.assertEquals(actualThree.toString(), ParseCell.parseCell(cellStr3).toString());
    }
}
