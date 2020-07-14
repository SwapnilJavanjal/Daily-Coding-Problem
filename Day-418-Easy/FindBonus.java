/*
  Good morning! Here's your coding interview problem for today.

This problem was asked by Atlassian.

MegaCorp wants to give bonuses to its employees based on how many lines of codes they have written. They would like to give the smallest positive amount to each worker consistent with the constraint that
if a developer has written more lines of code than their neighbor, they should receive more money.

Given an array representing a line of seats of employees at MegaCorp, determine how much each one should get paid.

For example, given [10, 40, 200, 1000, 60, 30], you should return [1, 2, 3, 4, 2, 1].


*/
import java.util.*;


public class FindBonus {
    class Node {
        int val;
        int idx;
        public Node(int i, int id) {
            val = i;
            idx = id;
        }
    }
    public List<Integer> getBonuses(List<Integer> lineOfCodes) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(lineOfCodes.size(), 1));

        if(lineOfCodes.size() == 0)
            return result;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });
        int idx = 0;
        for(int i: lineOfCodes) {
            pq.add(new Node(i, idx++));
        }

        Collections.fill(result, 1);
        
        while(!pq.isEmpty()) {
            Node temp = pq.poll();
            int idxTemp = temp.idx;
            int val = temp.val;

            if(idxTemp > 0 && val > lineOfCodes.get(idxTemp - 1)) {
                result.set(idxTemp, Math.max(result.get(idxTemp), result.get(idxTemp - 1)+1));
            }

            if(idxTemp < lineOfCodes.size() - 1 && val > lineOfCodes.get(idxTemp + 1)) {
                result.set(idxTemp, Math.max(result.get(idxTemp), result.get(idxTemp + 1) + 1));
            }
        }



        return result;
    }

    public List<Integer> getBonusesIterative(List<Integer> lineOfCodes) {
        List<Integer> result = new ArrayList<>(Collections.nCopies(lineOfCodes.size(), 1));
        if(lineOfCodes.size() == 0)
            return result;

        for(int i = 1; i < lineOfCodes.size(); i++) {
            if(lineOfCodes.get(i - 1) < lineOfCodes.get(i)) {
                result.set(i, Math.max(result.get(i), result.get(i - 1)+1 ));
            }
        }

        for(int i = result.size() - 2; i >= 0; i--) {
            if(lineOfCodes.get(i + 1) < lineOfCodes.get(i)) {
                result.set(i, Math.max(result.get(i), result.get(i + 1)+1 ));
            }
        }
        return result;
    }

        public static void main(String[] args) {

        FindBonus fb = new FindBonus();
        List<Integer> loc = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        loc.add(10);
        loc.add(40);
        loc.add(200);
        loc.add(1000);
        loc.add(60);
        loc.add(30);
        res = fb.getBonuses(loc);
        System.out.print("Using Priority Queue - Answer is ");
        for(int o: res)
            System.out.print(o+", ");

        res = fb.getBonuses(loc);
        System.out.print("Using Array approach - Answer is ");
        for(int o: res)
            System.out.print(o+", ");
    }

}
