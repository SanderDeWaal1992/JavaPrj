package map.controllers;

import Tiles.models.MovableTile;
import Tiles.models.Tile;
import Tiles.models.TilePart;
import util.GridCoords;
import Tiles.factories.FactoryProducer;
import util.TaskHandler;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public final class Map {
    private map.views.Map mapView;
    private map.models.Map mapModel;
    private final TaskHandler taskHandler;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /*map initialisation and setup*/
    public Map(map.models.Map mapModel) {
        util.GridCoords bCoord = new GridCoords(0, 0);
        this.mapView = new map.views.Map(this, mapModel);
        this.mapModel = mapModel;
        mapModel.setRowCnt(30);
        mapModel.setColumnCnt(30);
        mapModel.setViewPortRowCnt(20);
        mapModel.setViewPortColumnCnt(20);

        taskHandler = new TaskHandler();

        bCoord.setX(6);
        bCoord.setY(6);
        mapModel.setPlayerTile(FactoryProducer.getFactory("MOVABLETILE").getMovableTile("Human_1", bCoord, this, mapModel));

        //mapModel.setPlayerControllerTile(new Tiles.controllers.MovableTile(mapModel.getPlayerModelTile(), this, mapModel.getRowCnt(), mapModel.getColumnCnt()));

        //fill map with tiles
        //:TODO put this in model and add array storage read/write
        for (int x = 0; x < mapModel.getColumnCnt(); x++) {
            for (int y = 0; y < mapModel.getRowCnt(); y++) {
                bCoord.setX(x);
                bCoord.setY(y);
                mapModel.addInTileList(FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Pavement_1", bCoord, this, mapModel));
                if(((x==0 || x==(mapModel.getColumnCnt() - 1)) && y!=(mapModel.getRowCnt()-1)) || y==0|| y==(mapModel.getRowCnt()-2))
                    mapModel.addInTileList(FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Wall_1", bCoord, this, mapModel));
                //mapModel.addInTileList(new Models.Tiles.Pavement(bCoord));
            }
        }
        mapModel.addInTileList(mapModel.getPlayerTile());

        bCoord.setX(2);
        bCoord.setY(2);
        mapModel.addInTileList(FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Tree_1", bCoord, this, mapModel));

        bCoord.setX(27);
        bCoord.setY(27);
        mapModel.addInTileList(FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Tree_1", bCoord, this, mapModel));

        bCoord.setX(15);
        bCoord.setY(15);
        mapModel.addInTileList(FactoryProducer.getFactory("FIXEDTILE").getFixedTile("Hause_1", bCoord, this, mapModel));


        bCoord.setX(5);
        bCoord.setY(5);
        final Tiles.mcWrapper.Tile b = FactoryProducer.getFactory("MOVABLETILE").getMovableTile("Human_1", bCoord, this, mapModel);
        taskHandler.addTask(R -> b.getController().execute("argumentString"));
        b.getController().addPad(5, 10, Tile.Directions.DOWN);
        b.getController().addPad(5, 10, Tile.Directions.RIGHT);
        b.getController().addPad(5, 10, Tile.Directions.UP);
        b.getController().addPad(5, 10, Tile.Directions.LEFT);
        mapModel.addInTileList(b);

        bCoord.setX(1);
        bCoord.setY(1);
        final Tiles.mcWrapper.Tile c = FactoryProducer.getFactory("MOVABLETILE").getMovableTile("Human_1", bCoord, this, mapModel);
        taskHandler.addTask(R -> c.getController().execute("argumentString"));
        c.getController().addPad(2, 10, Tile.Directions.DOWN);
        c.getController().addPad(2, 10, Tile.Directions.RIGHT);
        c.getController().addPad(2, 10, Tile.Directions.UP);
        c.getController().addPad(2, 10, Tile.Directions.LEFT);
        mapModel.addInTileList(c);

        setTaskHandlerInterval(100);
        setViewUpdateHandler(5);
        updateView();
    }

    /*player tile movement*/
    Boolean moveMentInterval = false;
    private void move(Tile.Directions direction) {
        int addX = 0;
        int addY = 0;
        if(moveMentInterval == true ) return;

        switch (direction) {
            case LEFT:
                addX = -1;
                break;
            case RIGHT:
                addX = 1;
                break;
            case UP:
                addY = -1;
                break;
            case DOWN:
            default:
                addY = 1;
                break;
        }
        if (mapModel.getPlayerTile().getController().moveTile(direction, addX, addY) == true) {
            updateView();
            moveMentInterval = true;
        }
    }

    public void moveRight() {
        move(MovableTile.Directions.RIGHT);
    }

    public void moveLeft() {
        move(MovableTile.Directions.LEFT);
    }

    public void moveUp() {
        move(MovableTile.Directions.UP);
    }

    public void moveDown() {
        move(MovableTile.Directions.DOWN);
    }


    /*move tile in map*/
    public Boolean moveTileInTileList(util.GridCoords nextCoord, Tiles.mcWrapper.Tile tile) {
        Boolean success = true;

        //remove old tile
        success &= mapModel.deleteFromTileList(tile.getModel().getCoord(), tile);

        if (success == true) {
            //set and add new tile
            tile.getModel().setCoord(nextCoord);
            mapModel.addInTileList(tile);
        }

        return success;
    }


    /*View update */
    private Boolean viewUpdateReq = false;

    private void updateView() {
        viewUpdateReq = true;
    }

    private Boolean setViewUpdateHandlerOnlyOnce = true;

    private void setViewUpdateHandler(final long msInterval) {
        if (setViewUpdateHandlerOnlyOnce == true) scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //if (taskHandler.executeTasks("stringArgument") == true) {
                if (viewUpdateReq == true) {
                    viewUpdateReq=false;
                    mapView.revalidate();
                    mapView.repaint();
                    moveMentInterval = false;
                }
                //}
            }
        }, msInterval, msInterval, MILLISECONDS);
        setViewUpdateHandlerOnlyOnce = false;
    }

    /*tile task execution*/
    private Boolean setTaskHandlerIntervalOnlyOnce = true;

    private void setTaskHandlerInterval(final long msInterval) {
        if (setTaskHandlerIntervalOnlyOnce == true) scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (taskHandler.executeTasks("stringArgument") == true) {
                    updateView();
                }
            }
        }, msInterval, msInterval, MILLISECONDS);
        setTaskHandlerIntervalOnlyOnce = false;
    }


    /*remaining methods*/
    public map.views.Map getView() {
        return mapView;
    }

    public List<TilePart> getColidableTiles(GridCoords gridCoords) {
        return mapModel.getColidableTiles(gridCoords);
    }

}









































