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
 * TrainLinkedList class
 * links all train car nodes together in a doubly linked list
 */
public class TrainLinkedList {

    private TrainCarNode head;
    private TrainCarNode tail;
    private TrainCarNode cursor;

    //keeps track of total weight of all train cars and product loads
    private double totalWeight;
    //keeps track of total value of all product loads
    private double totalValue;
    //keeps track of total length of all train cars
    private double totalLength;
    //true if at least a single item in the list is dangerous
    private boolean anyDangerousItems;
    //keeps track of size of link list (amount train car nodes)
    private int size;
    //keeps track of how many dangerous product loads are in the list
    private int dangerousCounter;
    //heading for printManifest() and related methods
    private final String printHeading = "  "+String.format("%-37s%-50s", "Car:",
            "Load:") + "\n    " + String.format("%5s%13s%16s%1s%8s%16s%14s%12s",
            "Num", "Length (m)", "Weight (t)", "|", "Name", "Weight (t)",
            "Value" + " ($)", "Dangerous" ) + "\n" + "=======================" +
            "===============+==============================================" +
            "====\n";


    /**
     * default constructor
     * creates TrainLinkedList without any TrainCar objects in it.
     *
     * Postcondition: This TrainLinkedList has been initialized to an empty
     * linked list. head, tail, and cursor are all set to null.
     */
    public TrainLinkedList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * gets TrainCarNode data at cursor
     *
     * Precondition: The list is not empty (cursor is not null).
     *
     * @return
     * TrainCarNode at cursor
     *
     * @throws NullNodeException
     * exception thrown when list is empty
     */
    public TrainCarNode getCursorData() throws NullNodeException {
        if(cursor != null)
            return cursor;
        else
            throw new NullNodeException("List is empty");
    }

    /**
     * sets trainCarNode at cursor
     *
     * Precondition: The list is not empty (cursor is not null).
     *
     * Postcondition: The cursor node now contains a reference to car as
     * its data.
     *
     * @param car
     * TrainCarNode to be referenced at cursor
     *
     * @throws NullNodeException
     * exception thrown when list is empty
     */
    public void setCursorData(TrainCarNode car) throws NullNodeException {
        if(cursor != null)
            this.cursor = car;
        else
            throw new NullNodeException("List is empty");
    }

    /**
     * Progresses cursor forward by one link
     *
     * Precondition: The list is not empty (cursor is not null) and cursor
     * does not currently reference the tail of the list.
     *
     * Postcondition: The cursor has been advanced to the next TrainCarNode,
     * or has remained at the tail of the list.
     *
     * @throws NullNodeException
     * exception when there is a null car following the cursor
     */
    public void cursorForward() throws NullNodeException{
        if(cursor != null && cursor.getNext() != null ){
            cursor = cursor.getNext();
        }else{
            throw new NullNodeException("No next car, " +
                    "cannot move cursor forward.");
        }
    }

    /**
     * Brings the cursor backwards by one link
     *
     * Precondition: The list is not empty (cursor is not null) and the cursor
     * does not currently reference the head of the list.
     *
     * Postcondition: The cursor has been moved back to the previous
     * TrainCarNode, or has remained at the head of the list.
     *
     * @throws NullNodeException
     * exception when there is a null car preceding the cursor
     */
    public void cursorBackward() throws NullNodeException{
        if(cursor != null &&cursor.getPrev() != null){
            cursor = cursor.getPrev();
        }else{
            throw new NullNodeException("No previous car, cannot move cursor " +
                    "backwards.");
        }
    }

    /**
     * Updates variables that keep track of info (ex. size of link list,
     * total weight, etc.)
     * this variation adds to the existing variables
     *
     * @param node
     * the node to be referenced when updating info
     */
    public void addInfo(TrainCarNode node){
        //updates the dangerous counter and updates if there is a dangerous
        // item in the list
        if(node.getCar().getLoad().getIsDangerous()){
            dangerousCounter++;
            if(dangerousCounter > 0)
                anyDangerousItems = true;
        }

        //if there is no productLoad, only the info about the train car will
        // be referred to when updated. when there is a product load added
        // following the construction of the train car, the attributes of the
        // product load will be referred to when updating the variables.
        if(node.getCar().isEmpty()) {
            totalLength = totalLength + node.getCar().getLength();
            totalWeight = totalWeight + node.getCar().getWeight();
            size++;
        }else{
            totalValue = totalValue + node.getCar().getLoad().getValue();
            totalWeight = totalWeight + node.getCar().getLoad().getWeight();
            //updates the dangerous counter and updates if there is a dangerous
            // item in the list
            if(node.getCar().getLoad().getIsDangerous()){
                dangerousCounter++;
                if(dangerousCounter > 0)
                    anyDangerousItems = true;
            }
        }
    }

    /**
     * Updates variables that keep track of info (ex. size of link list,
     * total weight, etc.)
     * this variation subtracts from the existing variables
     *
     * @param node
     * the node to be referenced when updating info
     */
    public void removeInfo(TrainCarNode node){
        totalLength = totalLength - node.getCar().getLength();
        totalValue = totalValue - node.getCar().getLoad().getValue();
        totalWeight = totalWeight - node.getCar().getWeight();
        if(node.getCar().getLoad().getIsDangerous()){
            dangerousCounter--;
            if(dangerousCounter > 0)
                anyDangerousItems = true;
            else if(dangerousCounter == 0)
                anyDangerousItems = false;
        }
        size--;
    }

    /**
     * Insert a car after the car the cursor is referring to
     *
     * Precondition: This TrainCar object has been instantiated
     *
     * Postcondition: The new TrainCar has been inserted into the train after
     * the position of the cursor. All TrainCar objects previously on the
     * train are still on the train, and their order has been preserved.
     * The cursor now points to the inserted car.
     *
     * @param newCar
     * the train car you want to add after the cursor
     *
     * @throws  IllegalArgumentException
     * exception thrown when the provided node is null
     */
    public void insertAfterCursor(TrainCar newCar) throws
            IllegalArgumentException{
        TrainCarNode x = new TrainCarNode(newCar);
        if(newCar == null)
            throw new IllegalArgumentException("The given node is null");
        else {
            if (cursor == null || head == null) {
                head = x;
                tail = head;
                cursor = head;
                head.setNext(tail);
                tail.setPrev(head);
                head.setPrev(null);
                tail.setNext(null);
            } else if (cursor.getNext() != null) {
                cursor.getNext().setPrev(x);
                cursor.getNext().getPrev().setNext(cursor.getNext());
                cursor.setNext(cursor.getNext().getPrev());
                cursor.getNext().setPrev(cursor);
                cursor = cursor.getNext();
            } else {
                cursor.setNext(x);
                cursor.getNext().setPrev(cursor);
                cursor = cursor.getNext();
                tail = cursor;
            }


            addInfo(x);
        }
    }

    /**
     * removes train car node that is referred to at the cursor
     *
     * Precondition: The cursor is not null.
     *
     * Postcondition: The TrainCarNode referenced by the cursor has been
     * removed from the train. The cursor now references the next node, or
     * the previous node if no next node exists.
     *
     * @return
     * train car that was removed
     *
     * @throws NullNodeException
     * exception for when the cursor is at a null position
     */
    public TrainCar removeCursor() throws NullNodeException{
        if(cursor != null){

            TrainCar o = cursor.getCar();
            removeInfo(cursor);
            if(cursor == head && cursor != tail) {
                cursor = head.getNext();
                head = head.getNext();
                cursor.setPrev(null);
            }else if(cursor == tail && cursor != head){
                cursor = tail.getPrev();
                tail = tail.getPrev();
                cursor.setNext(null);
            }else if(cursor == head && cursor == tail){
                head = null;
                tail = null;
                cursor = null;
            }else{
                cursor = cursor.getPrev();
                cursor.setNext(cursor.getNext().getNext());
                cursor.getNext().setPrev(cursor.getNext().getPrev().getPrev());
                cursor = cursor.getNext();
            }
            return o;
        }else{
            throw new NullNodeException("Null cursor position");
        }
    }

    /**
     * provides the amount of train cars in the link list
     *
     * @return
     * the amount of train cars in link list
     */
    public int size(){
        return size;
    }

    /**
     * provides total length of all train cars
     *
     * @return
     * total length of all train cars
     */
    public double getLength(){
        return totalLength;
    }

    /**
     * provides total weight of all train cars and product loads
     *
     * @return
     * total weight of all train cars and product loads
     */
    public double getWeight(){
        return totalWeight;
    }

    /**
     * provides total value of all product loads
     *
     * @return
     * total value of all product loads
     */
    public double getValue(){
        return totalValue;
    }

    /**
     * checks if there is at least one dangerous product in the link list
     *
     * @return
     * true if there is at least one dangerous product, false if otherwise
     */
    public boolean isDangerous(){
        return anyDangerousItems;
    }

    /**
     * Searches link list for a specified productLoad and sums its weight and
     * value. It is then printed in a single record.
     *
     * @param name
     * name of product to specify
     */
    public void findProduct(String name){
        TrainCarNode lastCursor;
        if(cursor != head)
            lastCursor = cursor.getPrev();
        else
            lastCursor = head;

        cursor = head;
        String s = String.format("%8s%16s%14s%12s", "Name", "Weight (t)",
                "Value" + " ($)", "Dangerous" ) + "\n" +
                "==================================================\n";
        int dangerousCount = 0;
        double duplicateWeight = 0;
        double duplicateValue = 0;
        int duplicateCount = 0;

        while(cursor != null){
            if(cursor.getCar().getLoad().getName().equalsIgnoreCase(name)) {
                if (cursor.getCar().getLoad().getIsDangerous()) {
                    dangerousCount++;
                }
                duplicateWeight = duplicateWeight + cursor.getCar().getLoad().
                        getWeight();
                duplicateValue = duplicateValue + cursor.getCar().getLoad().
                        getValue();
                duplicateCount++;
            }
            if(cursor.getNext() != null)
                cursor = cursor.getNext();
            else
                break;

        }

        if(dangerousCount != 0){
            s = s + String.format("%8s%16s%14s%12s",
                    name, String.format("%.1f" ,duplicateWeight),
                    doubleDollarConversion(duplicateValue), "YES") + "\n";
        }else if(dangerousCount == 0){
            s = s + String.format("%8s%16s%14s%12s",
                    name, String.format("%.1f" ,duplicateWeight),
                    doubleDollarConversion(duplicateValue), "NO") + "\n";
        }



        if(duplicateCount != 0){
            System.out.println("The following products were found on " +
                    duplicateCount + " cars:");
            System.out.println(s);
        }else{
            System.out.println("No record of " + name + " on board train.");
        }

        cursor = lastCursor.getNext();
    }

    /**
     * Prints all the train cars with their information and the
     * information of each train car's product loads
     */
    public void printManifest(){
        TrainCarNode lastCursor = cursor;
        cursor = head;
        String x = printHeading;
        int count = 0;
        String productName;
        double productWeight;
        double productValue;
        boolean productDangerous;
        String dangerousString;
        String carPointer;

        while(count!=size()) {
            count++;
            if(cursor.getCar().isEmpty()){
                productName = "Empty";
                productWeight = 0;
                productValue = 0;
                productDangerous = false;
            }else{
                productName = cursor.getCar().getLoad().getName();
                productWeight = cursor.getCar().getLoad().getWeight();
                productValue = cursor.getCar().getLoad().getValue();
                productDangerous = cursor.getCar().getLoad().getIsDangerous();
            }

            if(productDangerous){
                dangerousString = "YES";
            }else {
                dangerousString = "NO";
            }

            if(cursor == lastCursor){
                carPointer = "->";
            }else{
                carPointer = "";
            }

            x = x + String.format("%2s%5s%13s%18s%1s%8s%16s%14s%12s",
                    carPointer, count, cursor.getCar().getLength(),
                    cursor.getCar().getWeight(),
                    "|", productName, productWeight,
                    doubleDollarConversion(productValue),
                    dangerousString) + "\n";
            cursor = cursor.getNext();
        }

        System.out.println(x);
        cursor = lastCursor;
    }

    /**
     * Removes all dangerous cars from the trains and maintains the order of
     * the cars on the train.
     *
     * Postcondition: All dangerous cars have been removed from this train.
     * The order of all non-dangerous cars must be maintained upon the
     * completion of this method.
     *
     * @throws NullPointerException
     * exception thrown in the case where there is no cars that have a
     * defined product load
     */
    public void removeDangerousCars() throws NullPointerException{
        TrainCarNode lastCursor;
        if(cursor != head)
            lastCursor = cursor.getPrev();
        else
            lastCursor = head;
        cursor = head;
        while(cursor != null){
            if(cursor.getCar().getLoad().getIsDangerous())
                removeCursor();

            if(cursor.getNext() != null)
                cursor = cursor.getNext();
            else
                break;
        }
        if (lastCursor.getNext() != null) {
            cursor = lastCursor.getNext();
        }else
            cursor = lastCursor;

    }

    /**
     * Converts a double for a product loads value into a dollar format (ex.
     * 132.40 instead of 132.4)
     *
     * @param x
     * the specified double you want to convert to the dollar format
     *
     * @return
     * returns the converted double
     */
    public static String doubleDollarConversion(double x){
        return String.format("%.2f", x);
    }

    /**
     * Provides information about the train
     *
     * @return
     * A string containing the train's size, total length, total weight,
     * total value and if there is a dangerous item in the train or not.
     */
    public String toString(){
        String trainInfo = "";
        if(isDangerous())
            trainInfo = "Train: " + size() + " cars, " + getLength() + " " +
                    "meters, " + String.format("%.1f" ,getWeight()) + " tons," +
                    " $" + doubleDollarConversion(getValue()) + " value, " +
                    "DANGEROUS";
        else if(!isDangerous())
            trainInfo = "Train: " + size() + " cars, " + getLength() + " " +
                    "meters, " + String.format("%.1f" ,getWeight()) + " tons, $"
                    + doubleDollarConversion(getValue()) +
                    " value, not dangerous";

        return trainInfo;

    }

}
