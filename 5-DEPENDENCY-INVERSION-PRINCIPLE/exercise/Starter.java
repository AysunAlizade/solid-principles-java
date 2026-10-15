import java.util.ArrayList;
import java.util.List;

// Defines the contract for order storage
interface OrderRepository {
    void save(String order);
}

// Stores orders using MySQL
class MySQLOrderRepository implements OrderRepository {

    @Override
    public void save(String order) {
        System.out.println("Saving order to MySQL: " + order);
    }
}

// Stores orders using MongoDB
class MongoOrderRepository implements OrderRepository {

    @Override
    public void save(String order) {
        System.out.println("Saving order to MongoDB: " + order);
    }
}

// Stores orders in memory for testing
class InMemoryOrderRepository implements OrderRepository {
    private List<String> savedOrders = new ArrayList<>();

    @Override
    public void save(String order) {
        savedOrders.add(order);
        System.out.println("Saving order in memory: " + order);
    }

    public List<String> getSavedOrders() {
        return new ArrayList<>(savedOrders);
    }
}

// Processes orders without depending on a concrete database
class OrderProcessor {
    private OrderRepository repository;

    public OrderProcessor(OrderRepository repository) {
        this.repository = repository;
    }

    public void process(String order) {
        System.out.println("Processing order: " + order);
        repository.save(order);
    }
}

// Main class
public class Starter {

    public static void main(String[] args) {

        // Use in-memory storage for testing
        InMemoryOrderRepository memoryRepository = new InMemoryOrderRepository();

        OrderProcessor testProcessor = new OrderProcessor(memoryRepository);

        testProcessor.process("Order #1001");
        testProcessor.process("Order #1002");

        // Read back the orders saved in memory
        System.out.println("Saved orders: "
                + memoryRepository.getSavedOrders());

        System.out.println();

        // Use MySQL storage
        OrderRepository mysqlRepository = new MySQLOrderRepository();

        OrderProcessor productionProcessor =new OrderProcessor(mysqlRepository);
        
        productionProcessor.process("Order #1003");

        System.out.println();

        // MongoDB can also be selected without changing OrderProcessor
        OrderRepository mongoRepository = new MongoOrderRepository();

        OrderProcessor mongoProcessor = new OrderProcessor(mongoRepository);
        
        mongoProcessor.process("Order #1004");
    }
}
