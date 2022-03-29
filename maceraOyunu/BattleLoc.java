package maceraOyunu;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(int maxObstacle, Player player, String name, Obstacle obstacle, String award) {
        super(player, name); //  locationdan gelen player ve name'ler
        this.obstacle = obstacle;
        this.award = award; //dışarıdan aşınan award'ı buradaki award'a eşitledik
        this.maxObstacle = maxObstacle;
    }

    public BattleLoc(Player player, String name) {
        super(player, name);
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan Buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor ! ");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase(); // Girilen harfi büyülttük
        if (selectCase.equals("S") && combat(obsNumber)) {
            if (combat(obsNumber)) {
                System.out.println(this.getName() + " Tüm Düşmanları Yendiniz !");
                return true;
            }
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz.");
            return false;
        }
        return true;
    }

    //kaç canavarla savaşılacaksa onu parametre olarak veirriz.
    // kaç canavarla karşılaşacaksak o kadar döngü koyuyoruz
    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            //Canavar her öldüğünde diğer canavarlar orjinal can ile gelecekler
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i );
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz Vurdunuz !");
                    //canavarın canından oyuncunun toplam hasarını çıkarıyoruz
                    this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Canavar Size Vurdu ! ");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    else {
                        return false;
                    }
                }
                if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                    System.out.println("Düşmanı yendiniz!");
                    System.out.println(this.getObstacle().getAward()+" Para Kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney()+ this.getObstacle().getAward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                }
            }
        }
        return true;
    }
    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-------------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());

    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.obstacle.getHealth());
        System.out.println("----------------------------");
    }

    public void obstacleStats(int i) {
        //düşman değerleri
        System.out.println(i+". "+this.getObstacle().getName() + " Değerleri");
        System.out.println("-----------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1; // 0 la 3 arasında rasgele sayı üretir. Max değer içeriye girdiğimizin bir eksiği.

    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
