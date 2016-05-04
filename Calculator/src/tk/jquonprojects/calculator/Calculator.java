package tk.jquonprojects.calculator;

/**
 *
 * @author Jaiquon Smith <jaydeshaun@live.ca>
 */
public class Calculator {

    //Store value 1, 2, and the total
    private double[] values = new double[3];

    /**
     * Creates the default values
     */
    public Calculator() {
        for (int i = 1; i < 3; i++) {
            setValue(i, 0);
        }
    }

    /**
     * Sets the defined values
     *
     * @param val1 The first value before the operand
     * @param val2 The second value after the operand
     */
    public Calculator(double val1, double val2) {
        setValue(1, val1);
        setValue(2, val2);
    }

    /**
     * Sets the defined values from an array
     *
     * @param values An array of both values, before and after the operand
     * @throws IllegalArgumentException If the array doesn't have upto 2 values
     */
    public Calculator(double[] values) {
        int len = values.length;
        if (len < 1 || len > 2) {
            throw new IllegalArgumentException("Values can only contain upto 2 values");
        }
        for (int i = 0; i < 2; i++) {
            setValue(i+1, values[i]);
        }
    }

    /**
     * Changes a value of either side of the operand
     * 
     * @param which Selects which value to change
     * @param value The new value
     */
    public void setValue(int which, double value) {
        if (which != 1 && which != 2) {
            throw new IllegalArgumentException("Which must be either 1 or 2 of a value");
        }
        values[which - 1] = value;
    }

    /**
     * Changes the first value before the operand
     * 
     * @param value The new value
     * @throws IllegalArgumentException If new value is higher or lower than acceptable value
     */
    public void setValue1(double value) {
        if (value > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Value is to low/high");
        }
        values[0] = value;
    }

    /**
     * Changes the second value after the operand
     * 
     * @param value The new value
     * @throws IllegalArgumentException If new value is higher or lower than acceptable value
     */
    public void setValue2(double value) {
        if (value > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Value is to low/high");
        }
        values[1] = value;
    }
    
    /**
     * Changes the total
     * 
     * @param total The new total
     * @throws IllegalArgumentException If the new total is higher or lower than acceptable value
     */
    public void setTotal(double total) {
        if (total > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Value is to low/high");
        }
        values[2] = total;
    }

    /**
     * Returns a value of either side of the operand
     * 
     * @param which Selects which value to return
     * @return The value of either side of the operand
     */
    public double getValue(int which) {
        if (which != 1 || which != 2) {
            throw new IllegalArgumentException("Which must be either 1 or 2 of a value");
        }
        return values[which-1];
    }

    /**
     * Returns the first value, before the operand
     * 
     * @return The first value
     */
    public double getValue1() {
        return values[0];
    }

    /**
     * Returns the second value, after the operand
     * 
     * @return The second value
     */
    public double getValue2() {
        return values[1];
    }
    
    /**
     * Returns the total
     * 
     * @return The total
     */
    public double getTotal() {
        return values[2];
    }

    /**
     * Adds the values from both sides of the operand and changes the total value
     * 
     * @return The new total value
     */
    public double add() {
        setTotal(values[0] + values[1]);
        return getTotal();
    }
    
    /**
     * Subtracts the values from both sides of the operand and changes the total value
     * 
     * @return The new total value
     */
    public double subtract() {
        setTotal(values[0] - values[1]);
        return getTotal();
    }
    
    /**
     * Multiplies the values from both sides of the operand and changes the total value
     * 
     * @return The new total value
     */
    public double multiply() {
        setTotal(values[0] * values[1]);
        return getTotal();
    }
    
    /**
     * Divides the values from both sides of the operand and changes the total value
     * 
     * @return The new total value
     */
    public double divide() {
        setTotal(values[0] / values[1]);
        return getTotal();
    }
    
    /**
     * Resets all the values to 0
     */
    public void reset() {
        setValue(1,0);
        setValue(2,0);
        setTotal(0);
    }

}
