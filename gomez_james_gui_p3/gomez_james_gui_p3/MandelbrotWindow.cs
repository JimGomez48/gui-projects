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
        private readonly int height = 256;
        private readonly int stride = 64;

        public MandelbrotWindow()
            : base() {
            canvas = new Canvas();
            this.Content = canvas;
            image = new Image();
            this.Width = width;
            this.Height = height;
            //int width = 128;
            //int height = width;
            //int stride = width / 8;
            //byte[] pixels = new byte[height * stride];

            //bmpSource = BitmapSource.Create(
            //    width,
            //    height,
            //    96,
            //    96,
            //    PixelFormats.Indexed1,
            //    BitmapPalettes.Gray16,
            //    pixels,
            //    stride
            //);

            grid = new MandelbrotGrid(0, 0, 720, 720, width, height, 1000, 1000);

            byte[] data = new byte[height * stride];
            Random random = new Random();
            for (int i = 0; i < data.Length; i++) {
                data[i] = (byte)random.Next(0, 65535);
            }

            //data = grid.generateCounts();

            bmpSource = BitmapSource.Create(
                width,
                height,
                96,
                96,
                PixelFormats.Indexed1,
                BitmapPalettes.Gray16,
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
