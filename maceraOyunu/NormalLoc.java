package maceraOyunu;

public class NormalLoc extends Location {

    public NormalLoc(Player player, String name){
        super(player,name);
    }
    @Override
    ///ölme ihtimali normal locationda yok
    public boolean onLocation(){
        return true;
    }
}
