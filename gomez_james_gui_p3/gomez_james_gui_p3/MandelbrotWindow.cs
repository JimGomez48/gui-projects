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
        private DockPanel dockPanel;
        private Menu menu;
        private Grid gridParams;
        private Canvas canvas;
        private Image image;
        //private BitmapSource bmpSource;
        private MandelbrotGrid grid;

        private readonly int width = 720;
        private readonly int height = 720;
        private readonly int stride;

        public MandelbrotWindow()
            : base() {
            dockPanel = new DockPanel();
            this.Content = dockPanel;
            this.ResizeMode = System.Windows.ResizeMode.NoResize;
            createMenu();
            DockPanel.SetDock(menu, Dock.Top);
            dockPanel.Children.Add(menu);
            gridParams = new Grid();
            DockPanel.SetDock(gridParams, Dock.Left);
            dockPanel.Children.Add(gridParams);
            canvas = new Canvas();
            DockPanel.SetDock(canvas, Dock.Bottom);
            dockPanel.Children.Add(canvas);

            image = new Image();
            this.Width = width;
            this.Height = height;
            stride = width;

            grid = new MandelbrotGrid(0, 0, width, height, width, height, 500, 500);
            byte[] data = grid.generateCounts();
            image.Source = BitmapSource.Create(width, height, 96, 96,
                PixelFormats.Gray8, null, data, stride);

            //image.Source = bmpSource;
            canvas.Children.Add(image);
            //Canvas.SetTop(image, 0 - height * 0.22);
            //Canvas.SetTop(image, 0);
            //Canvas.SetLeft(image, 0);
        }

        public Menu MainMenu { get { return menu; } }

        public Canvas Canvas { get { return canvas; } }

        public Grid Grid { get { return gridParams; } }

        private void createMenu() {
            menu = new Menu();
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
        }

        private void loadFromFileItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show(this, "Load Params");
        }

        public void saveFromFileItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show(this, "Save Params");
        }

        public void generateImageItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show(this, "Generate Image");
        }

        public void saveImageItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show(this, "Save Image");
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
