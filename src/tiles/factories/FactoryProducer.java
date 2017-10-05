package tiles.factories;


public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("MOVABLETILE")){
            return new MovableTileFactory();
        } else if(choice.equalsIgnoreCase("FIXEDTILE")){
            return new FixedTileFactory();
        } else return null;
    }
}

