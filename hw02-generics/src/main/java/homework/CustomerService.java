package homework;


import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entryForCopy = customers.firstEntry();
        return getCopy(entryForCopy);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entryForCopy = customers.higherEntry(customer);
        return getCopy(entryForCopy);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    //TODO: скорее всего есть более правильный способ вернуть глубокую копию Entry
    // либо нужно использовать не TreeMap, а более специализированный класс
    private Map.Entry<Customer, String> getCopy(Map.Entry<Customer, String> entryForCopy) {
        if(entryForCopy == null) {
            return null;
        }
        Customer c = entryForCopy.getKey();
        String data = entryForCopy.getValue();
        Customer copiedCustomer = new Customer(c.getId(), c.getName(), c.getScores());
        return new AbstractMap.SimpleEntry<>(copiedCustomer, data);
    }
}
