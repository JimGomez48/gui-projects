using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media.Imaging;
using System.Numerics;

namespace gomez_james_gui_p3
{
    class MandelbrotWindow : Window
    {
        private Canvas canvas;
        private Image image;
        private BitmapSource bmpsource;

        public MandelbrotWindow()
            : base() {
            canvas = new Canvas();
            this.Content = canvas;

            image = new Image();
            //bmpsource = BitmapSource.Create(
            //    int, 
            //    int, 
            //    double, 
            //    double, 
            //    pixelFormat, 
            //    BitmapPalette, 
            //    Array, 
            //    int
            //);

            Complex x = new Complex(0.1, 0.1);
            Complex y = new Complex(0.1, 0.12);
            Console.WriteLine(x); 
            Console.WriteLine(y);

            //x.Mod() == x.Mag * x.Mag
            Console.WriteLine(x.Magnitude * x.Magnitude);
            Console.WriteLine(y.Magnitude * y.Magnitude);
        }

        public Canvas Canvas {
            get { return canvas; }
        }

        [STAThread]
        public static void Main(String[] args) {
            MandelbrotWindow window = new MandelbrotWindow();
            Application app = new Application();
            app.Run(window);
        }
    }
}
