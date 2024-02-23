import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product tv42Inches, tv50Inches, tv60Inches, noNameProduct;

    @BeforeEach
    void setUp() {
        //name 19 chars
        tv42Inches = new Product("Television 42Inches", 999, 1, true);
        //name 20 chars
        tv50Inches = new Product("Television 50 Inches", 1000, 99.99, true);
        //name 21 chars
        tv60Inches = new Product("Television 60 Inches.", 5001, 245.99, false);
        //name 0 chars
        noNameProduct = new Product(null, 5000, 0, false);
    }

    @AfterEach
    void tearDown() {
        tv42Inches = tv50Inches = tv60Inches = noNameProduct = null;
    }

    @Nested
    class ContructorAndGetterTests{

        @Test
        void boundaryTestsForProductNameValidation() {
            // Validation is: max 20 chars, truncate string to 20 if above.

            // Testing lower boundaries for product name, set at constructor level
            assertEquals("Television 42Inches", tv42Inches.getProductName()); // 19 chars - accepted as is

            // Testing on boundaries for product name, set at constructor level
            assertEquals("Television 50 Inches", tv50Inches.getProductName()); // 20 chars - accepted as is

            // Testing upper boundaries for product name, set at constructor level
            assertEquals("Television 60 Inches", tv60Inches.getProductName()); // 21 chars - value truncated to 20 chars

            // Testing when product name is null at constructor level, that the empty string "" is defaulted
            assertEquals("", noNameProduct.getProductName());
        }

        @Test
        void boundaryTestsForProductCodeValidation() {
            // Validation is: between 1000 and 5000 inclusive

            // Testing lower boundaries for product code, set at constructor level
            assertEquals(5000, tv42Inches.getProductCode());  // 999 is invalid - should default to 5000
            assertEquals(1000, tv50Inches.getProductCode());  // 1000 is valid - value accepted

            //testing upper boundaries of product code, set at constructor level
            assertEquals(5000, noNameProduct.getProductCode()); // 5000 is valid - value accepted
            assertEquals(5000, tv60Inches.getProductCode());    // 5001 is invalid - should default to 5000
        }

        @Test
        void boundaryTestsForUnitCostValidation() {
            // Validation is: greater than zero with no upper boundary

            // Testing the lower boundaries for UnitCost, set at constructor level
            assertEquals(1, tv42Inches.getUnitCost());            // 1 is valid - value accepted
            assertEquals(99.99, tv50Inches.getUnitCost());        // 99.99 is valid - value accepted
            assertEquals(245.99, tv60Inches.getUnitCost());       // 245.99 is valid - value accepted
            assertEquals(1, noNameProduct.getUnitCost()); // 0 is invalid - defaulting to max value for integer
        }

        @Test
        void testsForInCurrentProductLine() {
            // No validation was done on these fields.
            // These tests check that the field was updated in the constructor.

            //These products should be in the current product line
            assertTrue( tv42Inches.isInCurrentProductLine());
            assertTrue( tv50Inches.isInCurrentProductLine());
            //These products should NOT be in the current product line
            assertFalse( tv60Inches.isInCurrentProductLine());
            assertFalse( noNameProduct.isInCurrentProductLine());
        }
    }

    @Nested
    class SetterTests {

        @Test
        void setProductName() {
            // Validation: Product name should only be updated in the setter if the new value is 20 chars or fewer.
            //             There is no truncating taking place at mutator level.

            assertEquals("Television 42Inches", tv42Inches.getProductName());  //verify contents first

            //19 chars - update should be performed
            tv42Inches.setProductName("TV 42 Inches - Grey");
            assertEquals("TV 42 Inches - Grey", tv42Inches.getProductName());

            //20 chars - update should be performed
            tv42Inches.setProductName("TV 42 Inches - White");
            assertEquals("TV 42 Inches - White", tv42Inches.getProductName());

            //21 chars - update should be ignored.
            tv42Inches.setProductName("TV 42 Inches - Orange");
            assertEquals("TV 42 Inches - White", tv42Inches.getProductName());
        }

        @Test
        void setUnitCost() {
            // Validation is: Unit Cost is greater than zero with no upper boundary.
            //                No defaulting values at mutator level.

            assertEquals(99.99, tv50Inches.getUnitCost()); // verify contents first

            tv50Inches.setUnitCost(0);                               // value below lower boundary
            assertEquals(99.99, tv50Inches.getUnitCost());  // update should be ignored.

            tv50Inches.setUnitCost(1);                               // value on lower boundary
            assertEquals(1, tv50Inches.getUnitCost());      // update should be performed
        }

        @Test
        void setProductCode() {
            // Validation is: Product Code is between 1000 and 5000 (both inclusive).
            //                No defaulting values at mutator level.

            assertEquals(5000, tv60Inches.getProductCode());  // verify contents first

            tv60Inches.setProductCode(999);                            // value below lower boundary
            assertEquals(5000, tv60Inches.getProductCode());  // update should be ignored

            tv60Inches.setProductCode(1000);                           // value on lower boundary
            assertEquals(1000, tv60Inches.getProductCode());  // update should be performed

            tv60Inches.setProductCode(5000);                          // value on upper boundary
            assertEquals(5000, tv60Inches.getProductCode()); // update should be performed

            tv60Inches.setProductCode(5001);                          // value above upper boundary
            assertEquals(5000, tv60Inches.getProductCode()); // update should be ignored
        }

        @Test
        void setInCurrentProductLine() {
            //Note: there is no validation on this field

            // Checking that a product can be taken out of the current product line
            assertTrue(tv50Inches.isInCurrentProductLine());
            tv50Inches.setInCurrentProductLine(false);
            assertFalse(tv50Inches.isInCurrentProductLine());

            // Checking that a product can be added to the current product line
            assertFalse(tv60Inches.isInCurrentProductLine());
            tv60Inches.setInCurrentProductLine(true);
            assertTrue(tv60Inches.isInCurrentProductLine());
        }

    }

    @Nested
    class ToStringTests {
        @Test
        void testToString() {

            //Testing when the value of inCurrentProductLine is true
            String toStringContents = tv50Inches.toString();
            assertTrue(toStringContents.contains("Product description: " + tv50Inches.getProductName()));
            assertTrue(toStringContents.contains("product code: " + tv50Inches.getProductCode()));
            assertTrue(toStringContents.contains("unit cost: " + tv50Inches.getUnitCost()));
            assertTrue(toStringContents.contains("currently in product line: Y"));

            //Testing when the value of inCurrentProductLine is false
            toStringContents = tv60Inches.toString();
            assertTrue(toStringContents.contains("Product description: " + tv60Inches.getProductName()));
            assertTrue(toStringContents.contains("product code: " + tv60Inches.getProductCode()));
            assertTrue(toStringContents.contains("unit cost: " + tv60Inches.getUnitCost()));
            assertTrue(toStringContents.contains("currently in product line: N"));
        }
    }
}
