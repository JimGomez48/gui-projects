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
        private BitmapSource bmpSource;
        private MandelbrotGrid grid;

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
            grid = new MandelbrotGrid(0, 0, 720, 720, 100, 100, 1000, 1000);
        }

        public Canvas Canvas {
            get { return canvas; }
        }

        [STAThread]
        public static void Main(String[] args) {
            MandelbrotWindow window = new MandelbrotWindow();
            Application app = new Application();
            //app.Run(window);
        }
    }
}
