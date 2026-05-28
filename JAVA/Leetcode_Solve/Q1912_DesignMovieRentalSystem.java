package Leetcode_Solve;

import java.util.TreeSet;
import java.util.*;

public class Q1912_DesignMovieRentalSystem {

    private Map<Integer, TreeSet<Entry>> avail;


    private TreeSet<Entry> rented;


    private Map<Long, Integer> priceMap;


    private final Comparator<Entry> availComparator = (a, b) -> {
        if (a.price != b.price) return Integer.compare(a.price, b.price);
        if (a.shop != b.shop) return Integer.compare(a.shop, b.shop);
        return Integer.compare(a.movie, b.movie);
    };


    private final Comparator<Entry> rentedComparator = (a, b) -> {
        if (a.price != b.price) return Integer.compare(a.price, b.price);
        if (a.shop != b.shop) return Integer.compare(a.shop, b.shop);
        return Integer.compare(a.movie, b.movie);
    };


    private static class Entry {
        int shop;
        int movie;
        int price;
        Entry(int s, int m, int p) { shop = s; movie = m; price = p; }
    }


    private static long key(int shop, int movie) {
        return (((long) shop) << 32) | ((long) movie & 0xffffffffL);
    }

    public Q1912_DesignMovieRentalSystem(int n, int[][] entries) {
        avail = new HashMap<>();
        priceMap = new HashMap<>();
        rented = new TreeSet<>(rentedComparator);

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            long k = key(shop, movie);
            priceMap.put(k, price);
            // Add to available set for that movie
            avail.computeIfAbsent(movie, mv -> new TreeSet<>(availComparator))
                    .add(new Entry(shop, movie, price));
        }
    }


    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        TreeSet<Entry> set = avail.get(movie);
        if (set == null) return res;
        Iterator<Entry> it = set.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            res.add(it.next().shop);
            count++;
        }
        return res;
    }


    public void rent(int shop, int movie) {
        long k = key(shop, movie);
        int price = priceMap.get(k);
        Entry e = new Entry(shop, movie, price);
        TreeSet<Entry> set = avail.get(movie);

        if (set != null) {
            set.remove(e);

        }
        rented.add(e);
    }


    public void drop(int shop, int movie) {
        long k = key(shop, movie);
        int price = priceMap.get(k);
        Entry e = new Entry(shop, movie, price);

        rented.remove(e);
        avail.computeIfAbsent(movie, mv -> new TreeSet<>(availComparator))
                .add(e);
    }


    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<Entry> it = rented.iterator();
        int count = 0;
        while (it.hasNext() && count < 5) {
            Entry e = it.next();
            res.add(Arrays.asList(e.shop, e.movie));
            count++;
        }
        return res;
    }
}
