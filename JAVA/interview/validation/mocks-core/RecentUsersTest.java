import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public final class RecentUsersTest {
    private record Event(long time, String user) {}
    private static int checks;
    private static void equal(int actual, int expected) {
        checks++;
        if (actual != expected) throw new AssertionError(actual + " != " + expected);
    }
    private static void rejects(Runnable action) {
        try { action.run(); }
        catch (IllegalArgumentException expected) { checks++; return; }
        throw new AssertionError("invalid call accepted");
    }
    private static int oracle(List<Event> accepted, long timestamp, long window) {
        Set<String> present = new HashSet<>();
        for (Event event : accepted) {
            if (event.time() > timestamp-window && event.time() <= timestamp) present.add(event.user());
        }
        return present.size();
    }
    public static void main(String[] args) {
        rejects(() -> new RecentUsers(0));
        rejects(() -> new RecentUsers(-1));
        rejects(() -> new RecentUsers(Long.MIN_VALUE));
        RecentUsers fixture = new RecentUsers(10);
        equal(fixture.record(0,"A"),1);
        equal(fixture.record(5,"B"),2);
        equal(fixture.record(10,"A"),2);
        equal(fixture.record(15,"C"),2);
        RecentUsers repeat = new RecentUsers(1);
        equal(repeat.record(4,"A"),1);
        equal(repeat.record(4,"A"),1);
        equal(repeat.record(5,"B"),1);
        RecentUsers atomic = new RecentUsers(10);
        equal(atomic.record(0,"A"),1);
        rejects(() -> atomic.record(20,null));
        equal(atomic.record(1,"B"),2);
        rejects(() -> atomic.record(20,""));
        equal(atomic.record(2,"C"),3);
        rejects(() -> atomic.record(1,"D"));
        rejects(() -> atomic.record(-1,"D"));
        equal(atomic.record(3,"D"),4);
        RecentUsers boundary = new RecentUsers(Long.MAX_VALUE);
        equal(boundary.record(0,"A"),1);
        equal(boundary.record(Long.MAX_VALUE-1,"B"),2);
        equal(boundary.record(Long.MAX_VALUE,"C"),2);
        equal(boundary.record(Long.MAX_VALUE,"C"),2);
        Random random = new Random(6032026L);
        long[] windows = {1,2,5,100,Long.MAX_VALUE};
        for (int trial=0; trial<80; trial++) {
            long window = windows[trial%windows.length];
            RecentUsers implementation = new RecentUsers(window);
            List<Event> accepted = new ArrayList<>();
            long time = trial%2 == 0 ? 0 : Long.MAX_VALUE-10000;
            for (int step=0; step<250; step++) {
                time += random.nextInt(5);
                String user = "U" + random.nextInt(9);
                accepted.add(new Event(time,user));
                equal(implementation.record(time,user),oracle(accepted,time,window));
                if (step%50 == 0) {
                    final long invalidTime = time;
                    rejects(() -> implementation.record(invalidTime,null));
                    // A successful equal-time call proves invalid input did not advance
                    // or otherwise invalidate the accepted prefix's future behavior.
                    accepted.add(new Event(time,user));
                    equal(implementation.record(time,user),oracle(accepted,time,window));
                }
            }
        }
        System.out.println("Java recent-user window: " + checks + " checks passed (20,000 seeded primary operations)");
    }
}
