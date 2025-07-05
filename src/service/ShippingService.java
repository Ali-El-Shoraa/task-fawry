package service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.Shippable;

class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        Map<String, Integer> itemCount = new HashMap<>();
        Map<String, Double> itemWeight = new HashMap<>();

        for (Shippable item : items) {
            itemCount.put(item.getName(), itemCount.getOrDefault(item.getName(), 0) + 1);
            itemWeight.put(item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : itemCount.keySet()) {
    double weightPerUnit = itemWeight.get(name);
    int count = itemCount.get(name);
    System.out.println(count + "x " + name + " (" + (weightPerUnit * 1000) + "g each)");
    System.out.println("Total weight: " + (weightPerUnit * count * 1000) + "g");
}


        // for (String name : itemCount.keySet()) {
        //     System.out.println(itemCount.get(name) + "x " + name);
        //     System.out.println(itemWeight.get(name) * 1000 + "g");
        // }

        System.out.println("Total package weight " + totalWeight + "kg");
        System.out.println("-------------------------");
    }

        public double getShippingCost(List<Shippable> items) {
        double totalWeight = items.stream().mapToDouble(Shippable::getWeight).sum();
        return totalWeight * 30;
    }
}

