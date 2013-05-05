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

        private readonly int width = 512;
        private readonly int height = 512;
        private readonly int stride;

        public MandelbrotWindow()
            : base() {
            canvas = new Canvas();
            this.Content = canvas;
            image = new Image();
            this.Width = width * 1.2;
            this.Height = height * 1.2;
            stride = width;

            byte[] data = new byte[height * stride];
            //Random random = new Random();
            //random.NextBytes(data);
            grid = new MandelbrotGrid(0, 0, width, height, width, height, 200, 200);
            data = grid.generateCounts();

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
            Canvas.SetTop(image, 0);
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
