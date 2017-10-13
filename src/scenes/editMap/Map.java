package scenes.editMap;

import java.awt.event.KeyEvent;

public class Map extends map.controllers.Map {

    @Override
    public Boolean moveTileInTileList(remaining.GridCoords nextCoord, tiles.mcWrapper.Tile tile) {
        Boolean success = true;

        //remove old tile
        if(!(tile.getModel().getCoord().getX()==0 && tile.getModel().getCoord().getY()==0)) //TODO: not a nice solution. The tile could actually be located at (0,0)
            success &= mapModel.deleteFromTileList(tile.getModel().getCoord(), tile);

        if (success == true) {
            //set and add new tile
            tile.getModel().setCoord(nextCoord);
            mapModel.addInTileList(tile);
            this.updateView();
        }

        return success;
    }

    @Override
    public void updateView() {
        super.updateView();
    }

    public map.models.Map getMapModel(){
        return mapModel;
    }

    public remaining.GridCoords getCoords(int x, int y){

        return new remaining.GridCoords(mapModel.getViewportCoordsStart().getX()+x/getView().getTileWidth(), mapModel.getViewportCoordsStart().getY()+y/getView().getTileHeight());
    }

    @Override
    public Boolean handleKey(KeyEvent e) {
        final remaining.GridCoords newViewportCoordsStart = new remaining.GridCoords(this.getMapModel().getViewportCoordsStart().getX(),this.getMapModel().getViewportCoordsStart().getY());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                newViewportCoordsStart.setX(newViewportCoordsStart.getX()-1);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                newViewportCoordsStart.setX(newViewportCoordsStart.getX()+1);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                newViewportCoordsStart.setY(newViewportCoordsStart.getY()-1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                newViewportCoordsStart.setY(newViewportCoordsStart.getY()+1);
                break;
            default:
                break;
        }

        if(newViewportCoordsStart.getX()<0)
            newViewportCoordsStart.setX(0);
        else if (newViewportCoordsStart.getX()>this.getMapModel().getRowCnt() - this.getMapModel().getViewPortRowCnt())
            newViewportCoordsStart.setX(this.getMapModel().getRowCnt() - this.getMapModel().getViewPortRowCnt());

        if(newViewportCoordsStart.getY()<0)
            newViewportCoordsStart.setY(0);
        else if (newViewportCoordsStart.getY() >this.getMapModel().getColumnCnt() - this.getMapModel().getViewPortColumnCnt())
            newViewportCoordsStart.setY(this.getMapModel().getColumnCnt() -this.getMapModel().getViewPortColumnCnt());

        this.getMapModel().setViewportCoordsStart(newViewportCoordsStart);

        this.updateView();
        return true;
    }

}
