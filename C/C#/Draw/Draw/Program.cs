using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Draw
{   
    class Program
    {
        public static Bitmap CreateNonIndexedImage(Bitmap src)
        {

            using (Graphics gfx = Graphics.FromImage(src))
            {
                gfx.DrawImage(src, 0, 0);
            }

            return src;
        }
        static void Main(string[] args)
        {
            
            String znak;
            Console.Write("1)usecka\n2)obdelnik\n3)kruh\n4)trojuhelnik\n5)Elipsa\n6)Lichobeznik\nVase volba:");
            int volba = Convert.ToInt32(Console.ReadLine());
            switch (volba)
            {
                case 1:
                    Console.Write("Vyberte znak:");
                    znak = Console.ReadLine();
                    Console.Write("Zadejte delku usecky:");
                    int x = Convert.ToInt32(Console.ReadLine());
                    Console.Write("|");
                    for (; x >= 0; x--)
                    {
                        Console.Write(znak);
                    }
                    Console.Write("|");
                    Console.ReadLine();
                    break;
                case 2:
                    Console.Write("Vyberte znak:");
                    znak = Console.ReadLine().Substring(0, 1);
                    Console.Write("Vyplnene true/false:");
                    String input = Console.ReadLine();
                    String mezera = " ";
                    if (input.ToLower().Equals("true"))
                    {
                        mezera = znak;
                    }
                    Console.Write("Zadej a:");
                    int a = Convert.ToInt32(Console.ReadLine());
                    Console.Write("Zadej b:");
                    int b = Convert.ToInt32(Console.ReadLine());
                    String mezery = "";
                    for (int bb = b - 2; bb > 0; bb--)
                    {
                        mezery += mezera;
                    }
                    String znaky = "";
                    for (int bb = b; bb > 0; bb--)
                    {
                        znaky += znak;
                    }
                    a--;
                    for (int aa = a; aa >= 0; aa--)
                    {
                        if (aa == a)
                        {
                            Console.WriteLine(znaky);
                        }
                        else
                        {
                            if (aa == 0)
                            {
                                Console.WriteLine(znaky);
                            }
                            else
                            {
                                Console.WriteLine(znak + mezery + znak);
                            }
                        }
                    }
                    break;
                case 3:
                    Console.Write("Zadejte polomer:");
                    int polomer = Convert.ToInt32(Console.ReadLine());
                    Boolean[,] kruh = new Boolean[polomer * 2, polomer * 2];
                    for (x = 0; x < polomer - 1; x++)
                    {
                        double temp = (double)(x) / (double)polomer;
                        int y = (int)Math.Round(Math.Sqrt(1 - ((temp) * (temp))) * (double)polomer);
                        kruh[polomer - x, polomer - y] = true;

                        kruh[polomer + x, polomer - y] = true;

                        kruh[polomer - x, polomer + y - 1] = true;

                        kruh[polomer + x, polomer + y - 1] = true;
                    }
                                       Bitmap image = new Bitmap("C:\\Users\\marti\\Pictures\\download.bmp");
                    image=CreateNonIndexedImage(image);
                    Color black = Color.FromArgb(255, 255, 255);
                    for (int y = 0; y < polomer * 2; y++)
                    {
                        for (x = 0; x < polomer * 2; x++)
                        {
                            if (kruh[x, y])
                            {
                                image.SetPixel(x, y, black);
                                Console.Write("*");
                            }
                            else
                            {
                                Console.Write(" ");
                            }
                        }
                        Console.WriteLine("");
                    }
                    break;
                default:
                    Console.WriteLine("Pracuji na tom.....");
                    break;
            }
            Console.ReadLine();
        }
    }
}
