import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Random;

public final class LruContractTest {
    private static int checks;
    private static void check(boolean value) {
        checks++;
        if (!value) throw new AssertionError("check " + checks);
    }
    public static void main(String[] args) {
        try {
            new ContractLru(-1);
            throw new AssertionError("negative capacity accepted");
        } catch (IllegalArgumentException expected) { checks++; }
        ContractLru example = new ContractLru(2);
        example.put(1, -1); example.put(2, 20);
        check(example.get(1).equals(OptionalInt.of(-1)));
        example.put(3, 30);
        check(example.get(2).isEmpty());
        check(example.keysLeastFirst().equals(List.of(1, 3)));
        List<Integer> detached = example.keysLeastFirst();
        detached.clear();
        check(example.keysLeastFirst().size() == 2);
        Random random = new Random(5022026L);
        for (int capacity = 0; capacity <= 12; capacity++) {
            ContractLru actual = new ContractLru(capacity);
            Map<Integer, Integer> values = new HashMap<>();
            List<Integer> order = new ArrayList<>();
            for (int operation = 0; operation < 2000; operation++) {
                int key = random.nextInt(21) - 10;
                if (random.nextBoolean()) {
                    int value = switch (random.nextInt(5)) {
                        case 0 -> -1;
                        case 1 -> Integer.MIN_VALUE;
                        case 2 -> Integer.MAX_VALUE;
                        default -> random.nextInt();
                    };
                    actual.put(key, value);
                    values.put(key, value);
                    order.remove(Integer.valueOf(key));
                    order.add(key);
                    if (order.size() > capacity) values.remove(order.remove(0));
                } else {
                    Integer value = values.get(key);
                    OptionalInt expected = value == null ? OptionalInt.empty() : OptionalInt.of(value);
                    check(actual.get(key).equals(expected));
                    if (value != null) {
                        order.remove(Integer.valueOf(key));
                        order.add(key);
                    }
                }
                check(actual.keysLeastFirst().equals(order));
                check(order.size() <= capacity);
            }
        }
        System.out.println("Java LRU contract: " + checks + " checks passed");
    }
}
