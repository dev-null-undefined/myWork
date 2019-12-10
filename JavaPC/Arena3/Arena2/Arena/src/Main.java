
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import java.util.InputMismatchException;

public class Main {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // TODO Auto-generated method
        JFileChooser fc;
        Postava hrac = Main.vytvorPostavu();
        System.out.println(hrac.toString());
        int vyber1 = 0;
        while (hrac.getHp() > 0) {
            System.out.println(
                    "Menu \n 1 - Zapas \n 2 - Zobrazit charakter \n 3 - rodelit skillpointy \n 4 - Ulozit charakter \n 5 - nahrat charakter \n 6 - konec hry");
            vyber1 = sc.nextInt();
            switch (vyber1) {
                case 1:
                    Postava nepritel = PraskHoKokota.Enemy(hrac.getLevel());
                    System.out.println("Nepritel:"+nepritel.toString()+"\nJa:"+hrac.toString());
                    hrac.setHpFullHealth();
                    System.out.println(nepritel);
                    System.out.println(hrac);
                    PraskHoKokota.Boj(hrac, nepritel);
                    System.out.println("vysledky");
                    System.out.println(hrac);
                    System.out.println(nepritel);
                    break;
                case 2:
                    System.out.println(hrac);
                    break;
                case 3:
                    int PlusSkill = 0;
                    if (hrac.getSkillPoint() != 0) {
                        for (int i = hrac.getSkillPoint(); i >= 0; i--) {
                            System.out.print("Do jak� schopnosti chcete d�t skillpoint zb�v� " + (i + 1)
                                    + "\n 1 = sila\n 2 = inteligence \n 3 = obratnost \n 4 = odolnost \n Vase cislo je ?");
                            PlusSkill = sc.nextInt();
                            switch (PlusSkill) {
                                case 1:
                                    System.out.println("1 Sila");
                                    hrac.silaPlus(1);
                                    break;
                                case 2:
                                    System.out.println("2 Inteligence");
                                    hrac.inteligencePlus(1);
                                    break;
                                case 3:
                                    System.out.println("3 Obratnost");
                                    hrac.obratnostPlus(1);
                                    break;
                                case 4:
                                    System.out.println("4 odolnost");
                                    hrac.odolnostPlus(1);
                                    break;
                                default:
                                    System.out.println("Zadal si moc velky/maly cislo debilku");
                                    i++;
                            }
                        }

                    } else {
                        System.out.println("Nemas zadny volny Skillpointy");
                    }
                    hrac.resetSkillPoint();
                    break;
                case 4:
                    // ulozit
                    fc = new JFileChooser();
                    int x = fc.showSaveDialog(null);
                    if (x == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        try (FileWriter fw = new FileWriter(f);) {
                            fw.write(hrac.toSaveString());
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    break;
                case 5:
                    fc = new JFileChooser();
                    int y = fc.showOpenDialog(null);
                    if (y == JFileChooser.APPROVE_OPTION) {
                        File f = fc.getSelectedFile();
                        try (BufferedReader br = new BufferedReader(new FileReader(f));) {
                            String[] lines = new String[9];
                            for (int i = 0; i < 9; i++) {
                                lines[i] = br.readLine();
                            }
                            hrac = Main.nacitaniPostavy(lines);
                            if(hrac==null){
                                System.out.println("Nacteni failnulo.");
                            }
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    // nahrat
                    break;
                case 6:
                    hrac.setHp(0);
                    break;
                default:
                    System.out.println("Spatny cislo zkus to znovu");
                    break;
            }

        }
    }

    private static Postava nacitaniPostavy(String[] lines) {
        // TODO Auto-generated method stub
        Postava hrac = null;
        String jmeno = lines[0].split(":")[1];
        String clas = lines[0].split("\\(")[1].split("\\)")[0];
        int level = Integer.valueOf(lines[2].split("=")[1]);
        int sila = Integer.valueOf(lines[3].split("=")[1]);
        int inteligence = Integer.valueOf(lines[4].split("=")[1]);
        int obratnost = Integer.valueOf(lines[5].split("=")[1]);
        int odolnost = Integer.valueOf(lines[6].split("=")[1]);
        int zkusenosti = Integer.valueOf(lines[7].split("=")[1]);
        String x = lines[8].split("=")[1];
        Rasa rasa = null;
        switch (x) {
            case "Clovek":
                rasa = new Clovek();
                break;
            case "Elf":
                rasa = new Elf();
                break;
            case "Trpaslik":
                rasa = new Trpaslik();
                break;
            case "TemnyElf":
                rasa = new TemnyElf();
                break;
            case "Troll":
                rasa = new Troll();
                break;
            case "Nemrtvy":
                rasa = new Nemrtvy();
                break;
        }
        if (level != 0 && rasa != null) {
            switch (clas) {
                case "Valecnik":
                    hrac = new Valecnik(jmeno, sila, inteligence, obratnost, odolnost, rasa);
                    hrac.pridejZk(zkusenosti);
                    break;
                case "Mag":
                    hrac = new Mag(jmeno, sila, inteligence, obratnost, odolnost, rasa);
                    hrac.pridejZk(zkusenosti);
                    break;
                case "Pruzkumnik":
                    hrac = new Pruzkumnik(jmeno, sila, inteligence, obratnost, odolnost, rasa);
                    hrac.pridejZk(zkusenosti);
                    break;
            }
        }
        return hrac;
    }

    public static Postava vytvorPostavu() {

        Postava hrac = null;

        System.out.print("Jak se tvoje postava bude jmenovat ?");
        String jmeno = sc.nextLine();
        int trida = 0;
        boolean succes = false;
        while (!succes) {
            System.out.println("Vyber si tridu:\n 1 = valecnik \n 2 = mag \n 3 = Pruzkumnik");
            trida = sc.nextInt();
            switch (trida) {
                case 1:
                    System.out.println("Valecnik");
                    succes = true;
                    break;
                case 2:
                    System.out.println("Mag");
                    succes = true;
                    break;
                case 3:
                    System.out.println("Pruzkumnik");
                    succes = true;
                    break;
            }
        }

        int input = 0;
        int sila = 0;
        int inteligence = 0;
        int obratnost = 0;
        int odolnost = 0;
        for (int i = 2; i >= 0; i--) {
            System.out.print("Do jak� schopnosti chcete d�t skillpoint zb�v� " + (i + 1)
                    + "\n 1 = sila\n 2 = inteligence \n 3 = obratnost \n 4 = odolnost \n Vase cislo je ?");
            input = sc.nextInt();
            switch (input) {
                case 1:
                    System.out.println("1 Sila");
                    sila++;
                    break;
                case 2:
                    System.out.println("2 Inteligence");
                    inteligence++;
                    break;
                case 3:
                    System.out.println("3 Obratnost");
                    obratnost++;
                    break;
                case 4:
                    System.out.println("4 odolnost");
                    odolnost++;
                    break;
                default:
                    System.out.println("Zadal si moc velky/maly cislo debilku");
                    i++;

            }
        }

        Rasa rasa = null;
        succes = false;
        while (!succes) {
            System.out
                    .print("Vyber si rasu : \n 1.Clovek \n 2.Elf \n 3.Trpaslik \n 4.Temny Elf \n 5.Troll \n 6.Nemrtvy");
            int vyber = sc.nextInt();
            switch (vyber) {
                case 1:
                    System.out.println("Clovek");
                    succes = true;
                    rasa = new Clovek();
                    break;
                case 2:
                    System.out.println("Elf");
                    succes = true;
                    rasa = new Elf();
                    break;
                case 3:
                    System.out.println("Trpaslik");
                    succes = true;
                    rasa = new Trpaslik();
                    break;
                case 4:
                    System.out.println("Temny elf");
                    succes = true;
                    rasa = new TemnyElf();
                    break;
                case 5:
                    System.out.println("Troll");
                    succes = true;
                    rasa = new Troll();
                    break;
                case 6:
                    System.out.println("Nemrtvy");
                    succes = true;
                    rasa = new Nemrtvy();
                    break;
            }
        }
        switch (trida) {
            case 1:
                hrac = new Valecnik(jmeno, sila, inteligence, obratnost, odolnost, rasa);
                succes = true;
                break;
            case 2:
                hrac = new Mag(jmeno, sila, inteligence, obratnost, odolnost, rasa);

                succes = true;
                break;
            case 3:
                hrac = new Pruzkumnik(jmeno, sila, inteligence, obratnost, odolnost, rasa);
                succes = true;
                break;
        }
        return hrac;
    }

}
