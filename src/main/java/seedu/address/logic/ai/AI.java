package seedu.address.logic.ai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;


public class AI {

    /**
     * Returns the Cosine Similarity between two vectors
     *
     * @param featureMap1 features of first vector
     * @param featureMap2 features of second vector
     * @return cosine similarity score
     */
    public static double getCosineSimilarity(HashMap<String, Double> featureMap1,
                                               HashMap<String, Double> featureMap2) {
        Set<String> featureSet = new HashSet<>(featureMap1.keySet());
        featureSet.addAll(featureMap2.keySet());
        double bigP = 0;
        double bigQ = 0;
        double pq = 0;
        for (String feature: featureSet) {
            double p = featureMap1.getOrDefault(feature, 0.0);
            double q = featureMap2.getOrDefault(feature, 0.0);
            bigP += Math.pow(p, 2);
            pq += p * q;
            bigQ += Math.pow(q, 2);
        }
        return (pq / (Math.sqrt(bigP) * Math.sqrt(bigQ)));
    }

    /**
     * Returns the Euclidean Distance between two vectors
     *
     * @param featureMap1 features of first vector
     * @param featureMap2 features of second vector
     * @return euclidean distance
     */
    public static double getEuclideanDistance(HashMap<String, Double> featureMap1,
                                           HashMap<String, Double> featureMap2) {
        Set<String> featureSet = new HashSet<>(featureMap1.keySet());
        featureSet.addAll(featureMap2.keySet());
        double dist = 0;
        for (String feature: featureSet) {
            double p = featureMap1.getOrDefault(feature, 0.0);
            double q = featureMap2.getOrDefault(feature, 0.0);
            dist += Math.pow(p - q, 2);
        }
        return Math.sqrt(dist);
    }

    /**
     * Returns the Manhattan Distance between two vectors
     *
     * @param featureMap1 features of first vector
     * @param featureMap2 features of second vector
     * @return manhattan distance
     */
    public static double getManhattanDistance(HashMap<String, Double> featureMap1,
                                              HashMap<String, Double> featureMap2) {
        Set<String> featureSet = new HashSet<>(featureMap1.keySet());
        featureSet.addAll(featureMap2.keySet());
        double dist = 0;
        for (String feature: featureSet) {
            double p = featureMap1.getOrDefault(feature, 0.0);
            double q = featureMap2.getOrDefault(feature, 0.0);
            dist += Math.abs(p - q);
        }
        return dist;
    }

    private static void normalizeRepoCount(HashMap<String, HashMap<String, Double>> features) {
        double sum = features.values().parallelStream().mapToDouble(u -> u.get("repo-count")).sum();
        features.values().parallelStream().forEach(k -> k.replace("repo-count", k.get("repo-count") / sum));
    }

    private static HashMap<String, Double> sort(HashMap<String, Double> distances) {
        LinkedList<HashMap.Entry<String, Double>> list =
                new LinkedList<>(distances.entrySet());

        list.sort(HashMap.Entry.comparingByValue());

        HashMap<String, Double> result = new LinkedHashMap<>();

        for (Iterator<HashMap.Entry<String, Double>> it = list.descendingIterator(); it.hasNext(); ) {

            HashMap.Entry<String, Double> aa = it.next();

            result.put(aa.getKey(), aa.getValue());
        }
        return result;
    }

    private static void normalize(HashMap<String, Double> scores) {
        double sum = scores.values().parallelStream().mapToDouble(v -> v).sum();
        scores.keySet().parallelStream().forEach(k -> scores.replace(k, 1 - scores.get(k) / sum));
    }

    /**
     * Returns the Average Distance Metric between two vectors
     *
     * @param featureMap1 features of first vector
     * @param featureMap2 features of second vector
     * @return average distance score
     */
    public static double getDistanceMetric(HashMap<String, Double> featureMap1, HashMap<String, Double> featureMap2) {
        return (getEuclideanDistance(featureMap1, featureMap2)
                + getManhattanDistance(featureMap1, featureMap2)
                + (1 - getCosineSimilarity(featureMap1, featureMap2))) / 3.0;
    }

    /**
     * Returns the sorted {@code HashMap} object based on decreasing similarity
     *
     * @param userName username of the user to compare with
     * @param stats stats of all the users
     * @return a sorted dictionary based on similarity
     */
    public static HashMap<String, Double> getSimilarityScore(String userName,
                                                             HashMap<String, HashMap<String, Double>> stats) {
        normalizeRepoCount(stats);
        HashMap<String, Double> mainUserStat = stats.remove(userName);
        HashMap<String, Double> distance = new HashMap<>();
        stats.keySet().parallelStream().forEach(u -> distance.put(u, getDistanceMetric(mainUserStat, stats.get(u))));
        normalize(distance);
        return sort(distance);
    }
}
