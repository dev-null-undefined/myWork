/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagetoassci.art;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author marti
 */
public class ImageToASSCIArt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedImage image;
        int width;
        int height;
        // TODO code application logic here
        if (args.length == 0) {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chose Image... ");
            int o = fc.showOpenDialog(null);
            if (o == JFileChooser.APPROVE_OPTION) {

                File input = fc.getSelectedFile();
                try {

                    fc.setDialogTitle("Chose txt to write in... ");
                    o = fc.showOpenDialog(null);
                    if (o == JFileChooser.APPROVE_OPTION) {
                        File out = fc.getSelectedFile();
                        FileWriter fw = new FileWriter(out);
                        image = ImageIO.read(input);
                        width = image.getWidth();
                        height = image.getHeight();
                        for (int y = 0; y < height - 1; y++) {
                            for (int x = 0; x < width - 1; x++) {
                                int p = image.getRGB(x, y);
                                int a = (p >> 24) & 0xff;
                                int r = (p >> 16) & 0xff;
                                int g = (p >> 8) & 0xff;
                                int b = p & 0xff;
                                double dark = (int) (((r + g + b + a) / 4.0) / 255.0 * 10);
                                if (dark > 9) {
                                    fw.write(" ");
                                } else {
                                    if (dark > 8) {
                                        fw.write(".");
                                    } else {
                                        if (dark > 7) {
                                            fw.write(":");
                                        } else {
                                            if (dark > 6) {
                                                fw.write("-");
                                            } else {
                                                if (dark > 5) {
                                                    fw.write("=");
                                                } else {
                                                    if (dark > 4) {
                                                        fw.write("+");
                                                    } else {
                                                        if (dark > 3) {
                                                            fw.write("*");
                                                        } else {
                                                            if (dark > 2) {
                                                                fw.write("#");
                                                            } else {
                                                                if (dark > 1) {
                                                                    fw.write("%");
                                                                } else {
                                                                    fw.write("@");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            fw.write(System.lineSeparator());
                        }
                        fw.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ImageToASSCIArt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            for (String string : args) {

                try {
                    File input = new File(string);
                    if (input.isDirectory()) {
                        for(File f:input.listFiles(new FileFilter() {
                            @Override
                            public boolean accept(File pathname) {
                                return pathname.getName().endsWith(".png");
                            } 
                       })){
                           File out = new File(f.getAbsolutePath() + ".txt");
                        FileWriter fw = new FileWriter(out);
                        image = ImageIO.read(f);
                        width = image.getWidth();
                        height = image.getHeight();
                        for (int y = 0; y < height - 1; y++) {
                            for (int x = 0; x < width - 1; x++) {
                                int p = image.getRGB(x, y);
                                int a = (p >> 24) & 0xff;
                                int r = (p >> 16) & 0xff;
                                int g = (p >> 8) & 0xff;
                                int b = p & 0xff;
                                double dark = (int) (((r + g + b + a) / 4.0) / 255.0 * 10);
                                if (dark > 9) {
                                    fw.write(" ");
                                } else {
                                    if (dark > 8) {
                                        fw.write(".");
                                    } else {
                                        if (dark > 7) {
                                            fw.write(":");
                                        } else {
                                            if (dark > 6) {
                                                fw.write("-");
                                            } else {
                                                if (dark > 5) {
                                                    fw.write("=");
                                                } else {
                                                    if (dark > 4) {
                                                        fw.write("+");
                                                    } else {
                                                        if (dark > 3) {
                                                            fw.write("*");
                                                        } else {
                                                            if (dark > 2) {
                                                                fw.write("#");
                                                            } else {
                                                                if (dark > 1) {
                                                                    fw.write("%");
                                                                } else {
                                                                    fw.write("@");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            fw.write(System.lineSeparator());
                        }
                        fw.close(); 
                        }
                    } else {
                        File out = new File(input.getAbsolutePath() + ".txt");
                        FileWriter fw = new FileWriter(out);
                        image = ImageIO.read(input);
                        width = image.getWidth();
                        height = image.getHeight();
                        for (int y = 0; y < height - 1; y++) {
                            for (int x = 0; x < width - 1; x++) {
                                int p = image.getRGB(x, y);
                                int a = (p >> 24) & 0xff;
                                int r = (p >> 16) & 0xff;
                                int g = (p >> 8) & 0xff;
                                int b = p & 0xff;
                                double dark = (int) (((r + g + b + a) / 4.0) / 255.0 * 10);
                                if (dark > 9) {
                                    fw.write(" ");
                                } else {
                                    if (dark > 8) {
                                        fw.write(".");
                                    } else {
                                        if (dark > 7) {
                                            fw.write(":");
                                        } else {
                                            if (dark > 6) {
                                                fw.write("-");
                                            } else {
                                                if (dark > 5) {
                                                    fw.write("=");
                                                } else {
                                                    if (dark > 4) {
                                                        fw.write("+");
                                                    } else {
                                                        if (dark > 3) {
                                                            fw.write("*");
                                                        } else {
                                                            if (dark > 2) {
                                                                fw.write("#");
                                                            } else {
                                                                if (dark > 1) {
                                                                    fw.write("%");
                                                                } else {
                                                                    fw.write("@");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            fw.write(System.lineSeparator());
                        }
                        fw.close();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ImageToASSCIArt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
