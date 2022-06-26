import java.util.ArrayList;
import java.util.Collections;

public class Tower {
    private int height;
    private int topColorIndex;
    private int bottomColorIndex;
    private ArrayList<Integer> topColors;
    private static int[] highestTowerTopColors;
    private static Cube[] highestTower;
    private static int highestTowerIndex = 0;

    public Tower(int height, int topColorIndex, int bottomColorIndex) {
        this.height = height;
        this.topColorIndex = topColorIndex;
        this.bottomColorIndex = bottomColorIndex;
        topColors = new ArrayList<>();
    }

    public int getHeight() {
        return height;
    }

    public int getTopColorIndex() {
        return topColorIndex;
    }

    public int getBottomColorIndex() {
        return bottomColorIndex;
    }

    public ArrayList<Integer> getTopColors() {
        return topColors;
    }

    public static Cube[] getHighestTower() {
        return highestTower;
    }

    public static int[] getHighestTowerTopColors() {
        return highestTowerTopColors;
    }

    public static void buildHighestTower() {
        Collections.sort(Cube.getWeights());
        Cube.makeGraph();
        Cube.setHighestTower();
    }

    public void addToTopColors(ArrayList<Integer> colors1, ArrayList<Integer> colors2) {
        for (Integer color : colors1) {
            topColors.add(topColors.size(), color);
        }
        topColors.remove(topColors.size() - 1);
        for (Integer color : colors2) {
            topColors.add(topColors.size(), color);
        }
    }

    public void addToTopColors(int color) {
        if (!topColors.contains(color)) {
            topColors.add(topColors.size(), color);
        }
    }

    public static int getMaxHeight(ArrayList<Tower> towers) {
        int max = 0;
        for (Tower tower : towers) {
            if (max < tower.height) {
                max = tower.height;
            }
        }
        return max;
    }

    public static void setHighestTowerTopColors(ArrayList<Tower> towers) {
        if (towers.isEmpty()) {
            highestTower = new Cube[1];
            highestTower[0] = Cube.getCube(0);
            highestTowerTopColors = new int[1];
            highestTowerTopColors[0] = Cube.getCube(0).getColors().get(0);
            return;
        }
        int maxHeight = 0;
        Tower highestTower = towers.get(0);
        for (Tower tower : towers) {
            if (maxHeight < tower.height) {
                maxHeight = tower.height;
                highestTower = tower;
            }
        }
        highestTowerTopColors = new int[highestTower.getTopColors().size()];
        int index = 0;
        for (Integer color : highestTower.getTopColors()) {
            highestTowerTopColors[index] = color;
            index++;
        }
    }

    public static void setHighestTower(int row, int column) {
        if (highestTowerTopColors.length == 1) {
            return;
        }
        highestTower = new Cube[highestTowerTopColors.length];
        highestTower[0] = Cube.getCube(column);
        highestTowerIndex++;
        Cube.setTower(column, row);
        highestTower[highestTowerIndex] = Cube.getCube(row);
        highestTowerIndex++;
    }

    public static void addCubeToTower(Cube cube) {
        highestTower[highestTowerIndex] = cube;
        highestTowerIndex++;
    }
}
