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
 * TrainCar class
 * Contains individual trains, which hold the product loads
 */
public class TrainCar {

    private final double length;
    private final double weight;
    private ProductLoad load = new ProductLoad();

    /**
     * Constructor for TrainCar
     *
     * @param length
     * length of the train car
     * @param weight
     * weight of the train car
     *
     * @throws IllegalArgumentException
     * exception is thrown in an inputted value is negative.
     */
    public TrainCar(double length, double weight)
            throws IllegalArgumentException{
        if(length < 0 || weight < 0){
            throw new IllegalArgumentException("Invalid input: inputs " +
                    "cannot be negative");
        }else {
            this.length = length;
            this.weight = weight;
        }
    }

    /**
     * getter method for train car's length
     *
     * @return
     * length of train car
     */
    public double getLength() {
        return length;
    }

    /**
     * getter method for train car's weight
     *
     * @return
     * weight of train car
     */
    public double getWeight() {
        return weight;
    }

    /**
     * getter method for train car's product load
     *
     * @return
     * product load of train car
     */
    public ProductLoad getLoad() {
        return load;
    }

    /**
     * setter method for train car's product load
     *
     * @param load
     * product load that you want to assign to train car
     */
    public void setLoad(ProductLoad load) {
        this.load = load;
    }

    /**
     * checks if train car is empty. does so by checking if the product
     * load's weight and value remain zero and the name remains "Empty"
     *
     * @return
     * true if train is empty, false if otherwise
     */
    public boolean isEmpty(){
        return (load.getValue() == 0) && (load.getWeight() == 0) &&
                (load.getName() == "Empty");
    }
}
