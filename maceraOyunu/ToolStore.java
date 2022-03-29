package maceraOyunu;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("---- Mağazaya Hoşgeldiniz ! ----");

        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 -Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış Yap");
            System.out.print("Seçimizin : ");

            int selectCase = Location.input.nextInt();

            while (selectCase < 1 || selectCase > 3) {
                System.out.println("Geçersiz bir değer girdiniz tekrar deneyiniz. ");
                selectCase = input.nextInt(); // Ata sınıfından geldiği için bşaına Location.input.nextInt() Yazılmasına gerek yok!
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Tekrar Bekleriz.");
                    showMenu = false;
                    break;
            }
        }
        return true;

    }

    public void printWeapon() {
        System.out.println("----- Silahlar ------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "- " + w.getName()
                    + " <Para : " + w.getPrice() + " ,Hasar : " + w.getDamage());
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyWeapon() {
        System.out.print("Bir Silah Seçiniz: ");
        int selectWeaponID = input.nextInt();

        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Geçersiz değer, tekrar giriniz");
            selectWeaponID = input.nextInt();
            if (selectWeaponID !=0){
                Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
                if (selectedWeapon != null) {
                    if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                        System.out.println("Yeterli Paranız Bulunmamaktadır");
                    } else {
                        // Satın Alma
                        System.out.println(selectedWeapon.getName() + " Silahını Satın Aldınız");
                        int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                        this.getPlayer().setMoney(balance);
                        System.out.println("Kalan Paranız: " + this.getPlayer().getMoney());
                        System.out.println("Önceki Silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                        this.getPlayer().getInventory().setWeapon(selectedWeapon);
                        System.out.println("Yeni Silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    }
                }

            }
        }

    }


    public void printArmor() {
        System.out.println("----- Zırhlar -----");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    " <Para : " + a.getPrice() + " ,Zırh : " + a.getBlock());
        }
    }

    public void buyArmor() {
        System.out.print("Bir Zırh Seçiniz: ");

        int selectedArmorID = input.nextInt();

        while (selectedArmorID < 1 || selectedArmorID > Armor.armors().length) {
            System.out.println("Geçersiz değer, Tekrar Giriniz");
            selectedArmorID = input.nextInt();
        }

        if (selectedArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectedArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli Paranız Bulunmamaktadır");
                } else {
                    System.out.println(selectedArmor.getName() + " Zırhını Satın Aldınız: ");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kalan Bakiyeniz: " + this.getPlayer().getMoney());
                }

            }
        }

    }
}
