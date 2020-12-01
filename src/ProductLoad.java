/*
 * @author Jeremy Samuel
 * E-mail: jeremy.samuel@stonybrook.edu
 * Stony Brook ID: 113142817
 * CSE 214
 * Recitation Section 3
 * Recitation TA: Dylan Andres
 * HW #2
 */

/**
 * ProductLoad class
 * Contains product that is stored in the train car
 */
public class ProductLoad {

    //name starts out with "Empty" and is changed if a name is later specified
    private String name = "Empty";
    private double weight;
    private double value;
    private boolean isDangerous;

    /**
     * Default constructor for ProductLoad object
     */
    public ProductLoad() {

    }

    /**
     * ProductLoad constructor with parameters
     *
     * @param name
     * name of product
     * @param weight
     * product weight
     * @param value
     * product value
     * @param isDangerous
     * true if product is dangerous and false if not
     */
    public ProductLoad(String name, double weight, double value,
                       boolean isDangerous) {
        setName(name);
        setWeight(weight);
        setValue(value);
        setIsDangerous(isDangerous);
    }

    /**
     * getter method for product name
     *
     * @return
     * product name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for product weight
     *
     * @return
     * product weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * getter method for product value
     *
     * @return
     * product value
     */
    public double getValue() {
        return value;
    }

    /**
     * getter method for if a product is dangerous or not
     *
     * @return
     * true if product is dangerous, false if otherwise
     */
    public boolean getIsDangerous() {
        return isDangerous;
    }

    /**
     * setter method for product name
     *
     * @param name
     * product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method for product weight
     *
     * @param weight
     * product weight
     *
     * @throws IllegalArgumentException
     * exception thrown when negative numbers are given
     */
    public void setWeight(double weight) throws IllegalArgumentException {
        if(weight >= 0)
            this.weight = weight;
        else
            throw new IllegalArgumentException("Only positive numbers allowed");
    }

    /**
     * setter method for product value
     *
     * @param value
     * product value
     *
     * @throws IllegalArgumentException
     * exception thrown when negative numbers are given
     */
    public void setValue(double value) throws IllegalArgumentException {
        if(value >= 0)
            this.value = value;
        else
            throw new IllegalArgumentException("Only positive numbers allowed");
    }

    /**
     * setter method for if a product is dangerous or not
     *
     * @param dangerous
     * true if product is dangerous, false if otherwise
     */
    public void setIsDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

}
