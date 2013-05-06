using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
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

        private readonly int width = 1024;
        private readonly int height = 1024;
        private readonly int stride;

        public MandelbrotWindow()
            : base() {
            canvas = new Canvas();
            this.Content = canvas;
            image = new Image();
            this.Width = width * 0.8;
            this.Height = height * 0.7;
            stride = width;
                        
            grid = new MandelbrotGrid(0, 0, width, height, width, height, 500, 500);
            byte[] data = grid.generateCounts();
            bmpSource = BitmapSource.Create(
                width,
                height,
                96,
                96,
                PixelFormats.Gray8,
                BitmapPalettes.Gray256,
                data,
                stride);
            image.Source = bmpSource;
            canvas.Children.Add(image);
            Canvas.SetTop(image, 0 - height / 6);
            Canvas.SetLeft(image, 0);
        }

        public Canvas Canvas {
            get { return canvas; }
        }

        [STAThread]
        public static void Main(String[] args) {
            MandelbrotWindow window = new MandelbrotWindow();
            window.Name = "MandelbrotWindow";
            window.Title = "Mandelbrot";            
            Application app = new Application();
            app.Run(window);
        }
    }
}
