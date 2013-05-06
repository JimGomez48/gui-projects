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
            createParamsPanel();
            DockPanel.SetDock(gridParams, Dock.Left);
            dockPanel.Children.Add(gridParams);
            canvas = new Canvas();
            DockPanel.SetDock(canvas, Dock.Bottom);
            dockPanel.Children.Add(canvas);

            image = new Image();
            this.Width = width + gridParams.Width;
            this.Height = height;
            menu.Width += gridParams.Width;
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

        private void createParamsPanel() {
            gridParams = new Grid();
            gridParams.Width = 200;
            gridParams.HorizontalAlignment = HorizontalAlignment.Left;
            gridParams.VerticalAlignment = VerticalAlignment.Top;
            gridParams.ShowGridLines = false;

            //Add rows to grid
            ColumnDefinition col0 = new ColumnDefinition();
            gridParams.ColumnDefinitions.Add(col0);
            ColumnDefinition col1 = new ColumnDefinition();
            gridParams.ColumnDefinitions.Add(col1);

            //Add columns to grid
            RowDefinition row0 = new RowDefinition();
            gridParams.RowDefinitions.Add(row0);
            RowDefinition row1 = new RowDefinition();
            gridParams.RowDefinitions.Add(row1);
            RowDefinition row2 = new RowDefinition();
            gridParams.RowDefinitions.Add(row2);
            RowDefinition row3 = new RowDefinition();
            gridParams.RowDefinitions.Add(row3);
            RowDefinition row4 = new RowDefinition();
            gridParams.RowDefinitions.Add(row4);
            RowDefinition row5 = new RowDefinition();
            gridParams.RowDefinitions.Add(row5);
            RowDefinition row6 = new RowDefinition();
            gridParams.RowDefinitions.Add(row6);
            RowDefinition row7 = new RowDefinition();
            gridParams.RowDefinitions.Add(row7);
            
            //Create labels
            Label xStartLabel = new Label();  xStartLabel.Content = "X Start";
            Label yStartLabel = new Label(); yStartLabel.Content = "Y Start";
            Label rowsLabel = new Label(); rowsLabel.Content = "Rows";
            Label columnsLabel = new Label(); columnsLabel.Content = "Columns";
            Label widthLabel = new Label(); widthLabel.Content = "Content";
            Label heightLabel = new Label(); heightLabel.Content = "Height";
            Label maxIterationsLabel = new Label(); maxIterationsLabel.Content = "Max Iterations";
            Label maxModulusLabel = new Label(); maxModulusLabel.Content = "Max Modulus";

            //Create Textboxes
            TextBox xStartText = new TextBox();
            TextBox yStartText = new TextBox();
            TextBox rowsText = new TextBox();
            TextBox columnsText = new TextBox();
            TextBox widthText = new TextBox();
            TextBox heightText = new TextBox();
            TextBox maxIterationsText = new TextBox();
            TextBox maxModulusText = new TextBox();

            Grid.SetRow(xStartLabel, 0); Grid.SetColumn(xStartLabel, 0);
            Grid.SetRow(xStartText, 0); Grid.SetColumn(xStartText, 1);
            Grid.SetRow(yStartLabel, 1); Grid.SetColumn(yStartLabel, 0);
            Grid.SetRow(yStartText, 1); Grid.SetColumn(yStartText, 1);
            Grid.SetRow(rowsLabel, 2); Grid.SetColumn(rowsLabel, 0);
            Grid.SetRow(rowsText, 2); Grid.SetColumn(rowsText, 1);
            Grid.SetRow(columnsLabel, 3); Grid.SetColumn(columnsLabel, 0);
            Grid.SetRow(columnsText, 3); Grid.SetColumn(columnsText, 1);
            Grid.SetRow(widthLabel, 4); Grid.SetColumn(widthLabel, 0);
            Grid.SetRow(widthText, 4); Grid.SetColumn(widthText, 1);
            Grid.SetRow(heightLabel, 5); Grid.SetColumn(heightLabel, 0);
            Grid.SetRow(heightText, 5); Grid.SetColumn(heightText, 1);
            Grid.SetRow(maxIterationsLabel, 6); Grid.SetColumn(maxIterationsLabel, 0);
            Grid.SetRow(maxIterationsText, 6); Grid.SetColumn(maxIterationsText, 1);
            Grid.SetRow(maxModulusLabel, 7); Grid.SetColumn(maxModulusLabel, 0);
            Grid.SetRow(maxModulusText, 7); Grid.SetColumn(maxModulusText, 1);
            
            gridParams.Children.Add(xStartLabel);
            gridParams.Children.Add(xStartText);
            gridParams.Children.Add(yStartLabel);
            gridParams.Children.Add(yStartText);
            gridParams.Children.Add(rowsLabel);
            gridParams.Children.Add(rowsText);
            gridParams.Children.Add(columnsLabel);
            gridParams.Children.Add(columnsText);
            gridParams.Children.Add(widthLabel);
            gridParams.Children.Add(widthText);
            gridParams.Children.Add(heightLabel);
            gridParams.Children.Add(heightText);
            gridParams.Children.Add(maxIterationsLabel);
            gridParams.Children.Add(maxIterationsText);
            gridParams.Children.Add(maxModulusLabel);
            gridParams.Children.Add(maxModulusText);
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
