package karate;

import com.intuit.karate.junit5.Karate;

public class KarateTestRunner {

    @Karate.Test
    Karate testProducts() {
        return Karate.run("products").relativeTo(getClass());
    }
}