package homework;


import java.util.Stack;

public class CustomerReverseOrder {

    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    private Stack<Customer> customersStack = new Stack<>();

    public void add(Customer customer) {
        this.customersStack.push(customer);
    }

    public Customer take() {
        return customersStack.pop();
    }
}
