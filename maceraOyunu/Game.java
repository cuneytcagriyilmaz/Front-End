package maceraOyunu;

import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);

    public void start() {

        System.out.println("Macera Oyununa Hoş Geldiniz! ");
        System.out.print("Lütfen Bir İsim Giriniz: ");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Sayın " + player.getName() + " Bu karanlık ve sisli adaya hoş geldiniz");
        System.out.println("Burada yaşananların hepsi gerçek ! ");
        System.out.println("Lütfen Bir Karakter Seçin! :");
        player.selectChar();


        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("########### Bölgeler ########");
            System.out.println();
            System.out.println("1 - Güvenli Ev -- >Düşman Yok Canınız Yenilenir");
            System.out.println("2 - Eşya Dükkanı --> Silah veya Zırh Satın Alabilirsiniz.");
            System.out.println("3 - Mağara --> Ödül < Yemek >, dikkatli ol karşına zombi çıkabilir.");
            System.out.println("4 - Orman --> Ödül < Odun >, dikkatli ol karşına vampir çıkabilir");
            System.out.println("5 - Nehir --> Ödül < Su>, dikkatli ol karşına ayı çıkaiblir");
            System.out.println("0- Çıkış Yap --> Oyunu Sonlandır");
            System.out.print("Gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3 :
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5 :
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen Geçerli Bir Bölge Giriniz ! ");
            }

            if (location == null) {
                System.out.println("Karanlık ve Sisli Adadan Çabuk Vazgeçtin");
                break;
            }
            // location metodu çağırıldı Mağaza ve Safe house a geçiş yaptı program
            if (!location.onLocation()) {
                System.out.println("Game Over!");
                break;
            }


        }

    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }
}
