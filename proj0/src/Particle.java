import edu.princeton.cs.algs4.StdRandom;

import java.awt.*;
import java.util.Map;

public class Particle {
    public ParticleFlavor flavor;
    public int lifespan;

    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    public Particle(ParticleFlavor flavor) {
        this.flavor = flavor;
        if (flavor == ParticleFlavor.FIRE || flavor == ParticleFlavor.PLANT
            || flavor == ParticleFlavor.FLOWER) {
            lifespan = LIFESPANS.get(flavor);
        }
        else
            lifespan = -1;
    }

    public void decrementLifespan () {
        if (lifespan > 0) {
            lifespan -= 1;
        }
        if (lifespan == 0) {
            flavor = ParticleFlavor.EMPTY;
            lifespan = -1;
        }
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY) {
            return Color.BLACK;
        }
        else if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        }
        else if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        }
        else if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        }
        else if (flavor == ParticleFlavor.WATERSAND) {
            return new Color(139, 69, 13);
        }
        if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }
        return Color.GRAY;
    }

    public void moveInto(Particle other) {
        other.flavor = flavor;
        other.lifespan = lifespan;

        flavor = ParticleFlavor.EMPTY;
        lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        Particle other = neighbors.get(Direction.DOWN);
        if (other.flavor == ParticleFlavor.EMPTY) {
            moveInto(other);
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int selectRandom = StdRandom.uniformInt(3);
        Particle other = null;
        if (selectRandom == 1) {
            other = neighbors.get(Direction.LEFT);
        }
        else if (selectRandom == 2) {
            other = neighbors.get(Direction.RIGHT);
        }
        if (other != null && other.flavor == ParticleFlavor.EMPTY) {
            moveInto(other);
        }
    }

    public void grow(Map<Direction, Particle> neighbors) {
        int selectRandom = StdRandom.uniformInt(10);
        Particle other = null;
        Direction[] directions = {Direction.UP, Direction.LEFT, Direction.RIGHT};
        if (selectRandom < 3) {
            other = neighbors.get(directions[selectRandom]);
        }

        if (other != null && other.flavor == ParticleFlavor.EMPTY) {
            other.flavor = flavor;
            other.lifespan = LIFESPANS.get(flavor);
        }
    }

    private void burn(Particle Neighbor, int select) {
        if ((Neighbor.flavor == ParticleFlavor.FLOWER || Neighbor.flavor == ParticleFlavor.PLANT) && select <= 1) {
            Neighbor.flavor = ParticleFlavor.FIRE;
            Neighbor.lifespan = FIRE_LIFESPAN;
        }
    }

    public void burn(Map<Direction, Particle> neighbors) {
        Direction[] directions = Direction.values();
        for (Direction direction : directions) {
            burn(neighbors.get(direction), StdRandom.uniformInt(5));
        }
    }

    public void waterAbsorption(Map<Direction, Particle> neighbors) {
        Direction[] directions = {Direction.UP, Direction.LEFT, Direction.RIGHT};
        for (Direction direction : directions) {
            Particle other = neighbors.get(direction);
            int selectRandom = StdRandom.uniformInt(100);
            if (other.flavor == ParticleFlavor.WATER && selectRandom < 2) {
                other.flavor = ParticleFlavor.EMPTY;
                flavor = ParticleFlavor.WATERSAND;
            }
            else if (other.flavor == ParticleFlavor.WATERSAND && selectRandom < 1) {
                flavor = ParticleFlavor.WATERSAND;
            }
        }
    }

    public void action(Map<Direction, Particle> neighbors) {
        if (flavor == ParticleFlavor.EMPTY)
            return;
        if (flavor != ParticleFlavor.BARRIER)
            fall(neighbors);
        if (flavor == ParticleFlavor.WATER)
            flow(neighbors);
        if (flavor == ParticleFlavor.PLANT || flavor == ParticleFlavor.FLOWER)
            grow(neighbors);
        if (flavor == ParticleFlavor.FIRE)
            burn(neighbors);
        if (flavor == ParticleFlavor.SAND)
            waterAbsorption(neighbors);
    }
}