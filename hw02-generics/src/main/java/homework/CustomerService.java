package homework;


import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> customers = new TreeMap<>((o1, o2) -> Long.compare(o1.getScores(), o2.getScores()));

    public Map.Entry<Customer, String> getSmallest() {
        return customers.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return customers.ceilingEntry(customer);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
