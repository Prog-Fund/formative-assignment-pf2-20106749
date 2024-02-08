/**
 * A scaled down version of a Product class.
 *
 * @author Mairead Meagher, Siobhan Drohan
 */
public class Product {

    private String productName = "";    // max length = 20 characters, default value is "".
                                    // When constructor is called, if the name is >20 chars, you should
                                    // only store the first 20 characters (Hint: use substr())

    private int productCode = 5000;  // valid values 1000 - 5000 inclusive - default value is 5000

    private double unitCost = 1;  //valid values are any positive number - default to 1

    private boolean inCurrentProductLine;   // no validation required. Default
    /**
     * Constructor for objects of class Product
     * @param productName Name of the product
     * @param productCode Code of the product
     * @param unitCost Unit cost of the product - valid values are any positive number
     */
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        setProductName(productName);
        setProductCode(productCode);
        setUnitCost(unitCost);
        this.inCurrentProductLine = inCurrentProductLine;
    }

    //-------
    //getters
    //-------
    /**
     * Returns the Product Name
     */
    public String getProductName(){
        return productName;
    }

    /**
     * Returns the Unit Cost
     */
    public double getUnitCost(){
        return unitCost;
    }

    /**
     * Returns the Product Code
     */
    public int getProductCode() {
        return productCode;
    }

    /**
     * Returns a boolean indicating if the product is in the current product line
     */
    public boolean isInCurrentProductLine() {
        return inCurrentProductLine;
    }

    //-------
    //setters
    //-------
    /**
     * Updates the Product Code to the value passed as a parameter
     * @param productCode The new Product Code
     */
    public void setProductCode(int productCode) {
        if (productCode >= 1000 && productCode <= 5000) {
            this.productCode = productCode;
        }
    }
    /**
     * Updates the Product Name to the value passed as a parameter
     * @param productName The new Product Name
     */
    public void setProductName(String productName) {
        if (productName != null) {
            if (productName.length() > 20) {
                this.productName = productName.substring(0, 20);
            } else {
                this.productName = productName;
            }
        }
    }
    /**
     * Updates the Unit Cost to the value passed as a parameter
     * @param unitCost The new unit cost for the product
     */
    public void setUnitCost(double unitCost) {
        if (unitCost >= 1) {
            this.unitCost = unitCost;
        }
    }
    /**
     * Updates the boolean indicating whether the product is in the current product line or not.
     * @param inCurrentProductLine Indicator that determines if the product is in the current product line or not.
     */
    public void setInCurrentProductLine(boolean inCurrentProductLine) {
        this.inCurrentProductLine = inCurrentProductLine;
    }

    /**
     * Builds a String representing a user-friendly representation of the object state
     * @return Details of the specific product
     */
    public String toString()
    //  The toString should return a String in the form e.g:
    //  "Product description: Flatscreen TV  product code: 2000  unit cost: 1000 and currently in product line: Y"

    {
        return
                "Product description: " + productName +
                "  product code: " + productCode +
                "  unit cost: " + unitCost +
                " and currently in product line: " + (inCurrentProductLine ? "Y" : "N");
    }
}
