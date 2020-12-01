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
 * TrainCarNode class
 * puts train car in a node to be used in a doubly-linked list
 */
public class TrainCarNode {

    private TrainCarNode prev;
    private TrainCarNode next;
    private TrainCar car;

    /**
     * TrainCarNode constructor with the ability to specify the car you want
     * the node to refer to
     *
     * @param car
     * the specified car you want the node to refer to
     */
    public TrainCarNode(TrainCar car){
        setCar(car);
    }

    /**
     * setter method for previous train car link
     *
     * @param prev
     * the previous train car node that you want to link to
     */
    public void setPrev(TrainCarNode prev) {
        this.prev = prev;
    }

    /**
     * setter method for next train car link
     *
     * @param next
     * the following train car node that you want to link to
     */
    public void setNext(TrainCarNode next) {
        this.next = next;
    }

    /**
     * setter method for train car node's train car
     *
     * @param car
     * the specified train car that you want the trainCarNode to refer to
     */
    public void setCar(TrainCar car) {
        this.car = car;
    }

    /**
     * getter method for previous train car link
     *
     * @return
     * previous train car link
     */
    public TrainCarNode getPrev() {
        return prev;
    }

    /**
     * getter method for next train car link
     *
     * @return
     * next train car link
     */
    public TrainCarNode getNext() {
        return next;
    }

    /**
     * getter method for the car referred to in the trainCarNode
     *
     * @return
     * car referred to in the trainCarNode
     */
    public TrainCar getCar() {
        return car;
    }

}
