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
using Microsoft.Win32;
using System.IO;
using System.Xml;

namespace gomez_james_gui_p3
{
    class MandelbrotWindow : Window
    {
        private DockPanel dockPanel;
        private Menu menu;
        private ParamsPanel paramsPanel;
        private Canvas canvas;
        private Image image;
        private BitmapSource bmpSource;
        private MandelbrotGrid mandelbrotGrid;

        private readonly int width = 720;
        private readonly int height = 720;
        private readonly int stride;        

        public MandelbrotWindow()
            : base() {
                this.ResizeMode = System.Windows.ResizeMode.NoResize;
            dockPanel = new DockPanel();
            dockPanel.HorizontalAlignment = HorizontalAlignment.Left;
            dockPanel.VerticalAlignment = VerticalAlignment.Top;
            this.Content = dockPanel;

            ScrollViewer canvasScroller = new ScrollViewer();
            ScrollViewer paramsScroller = new ScrollViewer();
            canvasScroller.HorizontalScrollBarVisibility = ScrollBarVisibility.Auto;
            canvasScroller.VerticalScrollBarVisibility = ScrollBarVisibility.Auto;
            paramsScroller.HorizontalScrollBarVisibility = ScrollBarVisibility.Auto;
            paramsScroller.VerticalScrollBarVisibility = ScrollBarVisibility.Auto;                        
            
            createMenu();
            DockPanel.SetDock(menu, Dock.Top);
            dockPanel.Children.Add(menu);
            paramsPanel = new ParamsPanel();
            
            DockPanel.SetDock(paramsScroller, Dock.Left);
            dockPanel.Children.Add(paramsScroller);
            paramsScroller.Content = paramsPanel;

            canvas = new Canvas();
            DockPanel.SetDock(canvasScroller, Dock.Bottom);
            dockPanel.Children.Add(canvasScroller);
            canvasScroller.Content = canvas;

            image = new Image();
            this.Width = width + paramsPanel.Width;
            this.Height = height;
            menu.Width += paramsPanel.Width;
            stride = width;

            mandelbrotGrid = new MandelbrotGrid(0, 0, width, height, width, height, 500, 500);
            byte[] data = mandelbrotGrid.generateCounts();
            bmpSource = BitmapSource.Create(width, height, 96, 96,
                PixelFormats.Gray8, null, data, stride);
            image.Source = bmpSource;

            canvas.Children.Add(image);
            canvas.Width = width;
            canvas.Height = height;
            //Canvas.SetTop(image, 0 - height * 0.22);
            Canvas.SetTop(image, 0);
            Canvas.SetLeft(image, 0);            
        }

        public Menu MainMenu { get { return menu; } }

        public Canvas Canvas { get { return canvas; } }

        public Grid Grid { get { return paramsPanel; } }

        private void createMenu() {
            menu = new Menu();
            menu.HorizontalAlignment = System.Windows.HorizontalAlignment.Left;
            menu.VerticalAlignment = System.Windows.VerticalAlignment.Top;
            menu.Width = width;
            menu.IsMainMenu = true;

            MenuItem fileItem = new MenuItem();
            fileItem.Header = "File";
            menu.Items.Add(fileItem);

            MenuItem loadParamsItem = new MenuItem();
            loadParamsItem.Header = "Load Parameters...";
            loadParamsItem.Click += loadParamsItem_Click;
            fileItem.Items.Add(loadParamsItem);
            MenuItem saveParamsItem = new MenuItem();
            saveParamsItem.Header = "Save Parameters...";
            saveParamsItem.Click += saveParamsItem_Click;
            fileItem.Items.Add(saveParamsItem);
            fileItem.Items.Add(new Separator());
            MenuItem generateImageItem = new MenuItem();
            generateImageItem.Header = "Generate Image";
            generateImageItem.Click += generateImageItem_Click;
            fileItem.Items.Add(generateImageItem);
            MenuItem saveImageItem = new MenuItem();
            saveImageItem.Header = "Save Image...";
            saveImageItem.Click += saveImageItem_Click;
            fileItem.Items.Add(saveImageItem);
            fileItem.Items.Add(new Separator());
            MenuItem exitItem = new MenuItem();
            exitItem.Header = "Exit";
            exitItem.Click += exitItem_Click;
            fileItem.Items.Add(exitItem);
        }

        private void loadParamsItem_Click(object sender, RoutedEventArgs e) {
            paramsPanel.loadParamsXML();
        }

        public void saveParamsItem_Click(object sender, RoutedEventArgs e) {
            if (paramsPanel.writeParamsXML()) {
                MessageBox.Show(this, "Paramaters have been saved.");
            }
            else
                MessageBox.Show(this, "Failed to save parameters");
        }

        public void generateImageItem_Click(object sender, RoutedEventArgs e) {
            MessageBox.Show(this, "TODO: Generate Image");
        }

        public void saveImageItem_Click(object sender, RoutedEventArgs e) {            
            SaveFileDialog dialog = new SaveFileDialog();
            dialog.DefaultExt = ".jpg";
            dialog.Filter = "JPEG (.jpg)|*.jpg";
            Nullable<bool> result = dialog.ShowDialog();

            if (result == true) {
                using (FileStream fileStream = File.Create(dialog.FileName)) {
                    BitmapEncoder encoder = new JpegBitmapEncoder();
                    encoder.Frames.Add(BitmapFrame.Create(bmpSource));
                    encoder.Save(fileStream);
                }
            }
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
