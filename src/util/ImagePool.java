package util;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class ImagePool {
    private java.util.Map<String, BufferedImage> ImageCacheList = new HashMap<String, BufferedImage>();
    private int bSizeX=0, bSizeY=0;
    public ImagePool(){}

    public BufferedImage getImage(String imageLoc, int sizeX, int sizeY) {
        BufferedImage image = null;
        if(bSizeX!=sizeX || bSizeY!=sizeY) ImageCacheList.clear(); // clear image cache list when sizes changed

        if (ImageCacheList.containsKey(imageLoc) == false) {
            try {
                image = util.utilities.getImage(imageLoc, sizeX, sizeY);
            } catch (FileNotFoundException e) {
                try {
                    image = util.utilities.getImage("./src/media/errorTile.png", sizeX, sizeY);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            ImageCacheList.put(imageLoc, image);

        } else image = ImageCacheList.get(imageLoc);

        bSizeX=sizeX;
        bSizeY=sizeY;
        return image;
    }
}
