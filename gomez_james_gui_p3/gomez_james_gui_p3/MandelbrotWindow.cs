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
        private Grid contentGrid;
        private Canvas canvas;
        private Image image;
        private BitmapSource bmpSource;
        private MandelbrotGrid grid;

        private readonly int width = 1024;
        private readonly int height = 1024;
        private readonly int stride;

        public MandelbrotWindow()
            : base() {
            contentGrid = new Grid();
            canvas = new Canvas();
            this.Content = contentGrid;

            image = new Image();
            this.Width = width * 0.8;
            //this.Height = height * 0.6;
            stride = width;

            grid = new MandelbrotGrid(0, 0, width, height, width, height, 500, 500);
            byte[] data = grid.generateCounts();
            bmpSource = BitmapSource.Create(
                width,
                height,
                96,
                96,
                PixelFormats.Gray8,
                null,
                data,
                stride);

            image.Source = bmpSource;            
            canvas.Children.Add(image);
            contentGrid.Children.Add(createMenu());
            contentGrid.Children.Add(Canvas);
            Canvas.SetTop(image, 0 - height * 0.22);
            Canvas.SetTop(image, 25);
            Canvas.SetLeft(image, 0);
        }

        public Canvas Canvas {
            get { return canvas; }
        }

        private Menu createMenu() {
            Menu menu = new Menu();
            menu.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
            menu.VerticalAlignment = System.Windows.VerticalAlignment.Top;
            menu.Width = width;            
            menu.IsMainMenu = true;

            MenuItem fileItem = new MenuItem();
            fileItem.Header = "File";
            menu.Items.Add(fileItem);

            MenuItem loadFromFileItem = new MenuItem();
            loadFromFileItem.Header = "Load Parameters From File...";
            loadFromFileItem.Click += loadFromFileItem_Click;
            fileItem.Items.Add(loadFromFileItem);
            MenuItem saveFromFileItem = new MenuItem();
            saveFromFileItem.Header = "Save Parameters From File...";
            saveFromFileItem.Click += saveFromFileItem_Click;            
            fileItem.Items.Add(saveFromFileItem);
            fileItem.Items.Add(new Separator());
            MenuItem generateImageItem = new MenuItem();
            generateImageItem.Header = "Generate Image";
            generateImageItem.Click += generateImageItem_Click;
            fileItem.Items.Add(generateImageItem);
            MenuItem saveImageItem = new MenuItem();
            saveImageItem.Header = "Save Image";
            saveImageItem.Click += saveImageItem_Click;
            fileItem.Items.Add(saveImageItem);
            fileItem.Items.Add(new Separator());
            MenuItem exitItem = new MenuItem();
            exitItem.Header = "Exit";
            exitItem.Click += exitItem_Click;            
            fileItem.Items.Add(exitItem);

            return menu;
        }        

        private void loadFromFileItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show("Load Params");
        }

        public void saveFromFileItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show("Save Params");
        }

        public void generateImageItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show("Generate Image");
        }

        public void saveImageItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show("Save Image");
        }

        private void exitItem_Click(object sender, RoutedEventArgs e) {
            Application.Current.Shutdown();
        }

        [STAThread]
        public static void Main(String[] args) {
            MandelbrotWindow window = new MandelbrotWindow();
            window.Title = "Mandelbrot";
            Application app = new Application();
            app.Run(window);
        }
    }
}
