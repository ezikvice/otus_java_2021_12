package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    Map<Customer, String> customersData = new NavigableMap<>(new Comparator<Customer>() {
        @Override
        public int compare(Customer o1, Customer o2) {
            return ;
        }
    });

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk

        return null; // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
//        return customersData.; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        customersData.put(customer, data);
    }
}
