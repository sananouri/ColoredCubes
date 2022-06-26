import java.util.*;

public class Cube {
    private Double weight;
    private ArrayList<Integer> colors;
    private static Map<Double, Cube> cubes = new HashMap<>();
    private static ArrayList<Double> weights = new ArrayList<>();
    private static int[][] graphMatrix;
    private static ArrayList<Tower>[][] towers;

    public Cube(double weight, int c1, int c2, int c3, int c4, int c5, int c6) {
        this.weight = weight;
        colors = new ArrayList<>();
        colors.add(c1);
        colors.add(c2);
        colors.add(c3);
        colors.add(c4);
        colors.add(c5);
        colors.add(c6);
        weights.add(weight);
        cubes.put(weight, this);
    }

    public static ArrayList<Double> getWeights() {
        return weights;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public static Cube getCube(int index) {
        return cubes.get(weights.get(index));
    }

    public Double getWeight() {
        return weight;
    }

    public static void makeGraph() {
        int n = weights.size();
        graphMatrix = new int[n][n];
        towers = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graphMatrix[i][j] = 0;
                towers[i][j] = new ArrayList<>();
                if (canMakeTower(i, j)) {
                    graphMatrix[i][j] = 1;
                }
            }
        }
    }

    private static boolean canMakeTower(int cube1Index, int cube2Index) {
        if (cube1Index >= cube2Index) {
            return false;
        }
        Cube cube1 = getCube(cube1Index);
        Cube cube2 = getCube(cube2Index);
        ArrayList<Integer> colors = cube2.getColors();
        Tower tower;
        boolean result = false;
        for (Integer color : cube1.getColors()) {
            if (colors.contains(color)) {
                tower = new Tower(
                        2,
                        5 - cube1.getColors().indexOf(color),
                        5 - colors.indexOf(color));
                tower.addToTopColors(cube1.getColors().get(tower.getTopColorIndex()));
                tower.addToTopColors(color);
                towers[cube1Index][cube2Index].add(tower);
                result = true;
            }
        }
        return result;
    }

    public static void setHighestTower() {
        int n = cubes.size(), maxHeight = 0, maxHeightRow = 0, maxHeightColumn = 0,maxCombinedHeight;
        for (int k = 1; k < n; k++) {
            for (int i = 0, j = i + k; i < n && j < n; i++) {
                if (graphMatrix[i][j] == 1 && maxHeight < 2) {
                    maxHeight = 2;
                    maxHeightRow = i;
                    maxHeightColumn = j;
                }
                for (int p = i + 1; p < j; p++) {
                    if (Tower.getMaxHeight(towers[i][p]) == 0 || Tower.getMaxHeight(towers[p][j]) == 0)
                        continue;
                    maxCombinedHeight = getMaxCombinedHeight(i, j, p);
                    graphMatrix[j][i] = p;
                    if (maxHeight < maxCombinedHeight) {
                        maxHeight = maxCombinedHeight;
                        maxHeightRow = i;
                        maxHeightColumn = j;
                    }
                }
            }
        }
        Tower.setHighestTowerTopColors(towers[maxHeightRow][maxHeightColumn]);
        Tower.setHighestTower(maxHeightRow, maxHeightColumn);
    }

    private static int getMaxCombinedHeight(int row, int column, int cubeIndex) {
        Tower tower;
        int maxHeight = 0;
        for (Tower tower1 : towers[row][cubeIndex]) {
            for (Tower tower2 : towers[cubeIndex][column]) {
                if (tower1.getBottomColorIndex() == 5 - tower2.getTopColorIndex()) {
                    tower = new Tower(tower1.getHeight() + tower2.getHeight() - 1,
                            tower1.getTopColorIndex(), tower2.getBottomColorIndex());
                    tower.addToTopColors(tower1.getTopColors(), tower2.getTopColors());
                    towers[row][column].add(tower);
                    if (maxHeight < tower.getHeight()) {
                        maxHeight = tower.getHeight();
                    }
                }
            }
        }
        return maxHeight;
    }

    public static void setTower(int from, int to) {
        if (graphMatrix[from][to] != 0) {
            setTower(from, graphMatrix[from][to]);
            Tower.addCubeToTower(getCube(graphMatrix[from][to]));
            setTower(graphMatrix[from][to], to);
        }
    }
}
