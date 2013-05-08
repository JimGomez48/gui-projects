using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;

namespace gomez_james_gui_p3
{
    class ParamsPanel : Grid
    {
        //Textboxes
        private TextBox xStart;
        private TextBox yStart;
        private TextBox rows;
        private TextBox columns;
        private TextBox width;
        private TextBox height;
        private TextBox maxIterations;
        private TextBox maxModulus;

        //labels
        private Label xStart_label;
        private Label yStart_label;
        private Label rows_label;
        private Label columns_label;
        private Label width_label;
        private Label height_label;
        private Label maxIterations_label;
        private Label maxModulus_label;

        public TextBox XStart {get { return xStart; }}
        
        public TextBox YStart {get { return yStart; }}
        
        public TextBox Rows {get { return rows; }}
        
        public TextBox Columns {get { return columns; }}        

        public TextBox Width1 {get { return width; }}
        
        public TextBox Height1 {get { return height; }}        

        public TextBox MaxIterations {get { return maxIterations; }}        

        public TextBox MaxModulus {get { return maxModulus; }}        

        public ParamsPanel() {
            Width = 120;
            HorizontalAlignment = HorizontalAlignment.Left;
            VerticalAlignment = VerticalAlignment.Top;
            ShowGridLines = false;

            //Add rows  with single column to gridParams
            ColumnDefinition col0 = new ColumnDefinition();
            ColumnDefinitions.Add(col0);
            for (int i = 0; i < 16; i++) {
                RowDefinitions.Add(new RowDefinition());
            }

            createLabels();
            createTextBoxes();
            arrangeAndAddUiItems();            
        }

        private void createLabels() {
            xStart_label = new Label();
            xStart_label.Content = "X Start";
            xStart_label.Margin = new Thickness(10, 10, 10, 0);
            yStart_label = new Label();
            yStart_label.Content = "Y Start";
            yStart_label.Margin = new Thickness(10, 10, 10, 0);
            rows_label = new Label();
            rows_label.Content = "Rows";
            rows_label.Margin = new Thickness(10, 10, 10, 0);
            columns_label = new Label();
            columns_label.Content = "Columns";
            columns_label.Margin = new Thickness(10, 10, 10, 0);
            width_label = new Label();
            width_label.Content = "Width";
            width_label.Margin = new Thickness(10, 10, 10, 0);
            height_label = new Label();
            height_label.Content = "Height";
            height_label.Margin = new Thickness(10, 10, 10, 0);
            maxIterations_label = new Label();
            maxIterations_label.Content = "Max Iterations";
            maxIterations_label.Margin = new Thickness(10, 10, 10, 0);
            maxModulus_label = new Label();
            maxModulus_label.Content = "Max Modulus";
            maxModulus_label.Margin = new Thickness(10, 10, 10, 0);
        }

        private void createTextBoxes() {            
            xStart = new TextBox();
            xStart.Margin = new Thickness(10, 0, 10, 10);
            yStart = new TextBox();
            yStart.Margin = new Thickness(10, 2, 10, 10);
            rows = new TextBox();
            rows.Margin = new Thickness(10, 2, 10, 10);
            columns = new TextBox();
            columns.Margin = new Thickness(10, 2, 10, 10);
            width = new TextBox();
            width.Margin = new Thickness(10, 2, 10, 10);
            height = new TextBox();
            height.Margin = new Thickness(10, 2, 10, 10);
            maxIterations = new TextBox();
            maxIterations.Margin = new Thickness(10, 2, 10, 10);
            maxModulus = new TextBox();
            maxModulus.Margin = new Thickness(10, 2, 10, 10);
        }

        private void arrangeAndAddUiItems() {
            Grid.SetRow(xStart_label, 0);
            Grid.SetColumn(xStart_label, 0);
            Grid.SetRow(xStart, 1);
            Grid.SetColumn(xStart, 0);
            Grid.SetRow(yStart_label, 2);
            Grid.SetColumn(yStart_label, 0);
            Grid.SetRow(yStart, 3);
            Grid.SetColumn(yStart, 0);
            Grid.SetRow(rows_label, 4);
            Grid.SetColumn(rows_label, 0);
            Grid.SetRow(rows, 5);
            Grid.SetColumn(rows, 0);
            Grid.SetRow(columns_label, 6);
            Grid.SetColumn(columns_label, 0);
            Grid.SetRow(columns, 7);
            Grid.SetColumn(columns, 0);
            Grid.SetRow(width_label, 8);
            Grid.SetColumn(width_label, 0);
            Grid.SetRow(width, 9);
            Grid.SetColumn(width, 0);
            Grid.SetRow(height_label, 10);
            Grid.SetColumn(height_label, 0);
            Grid.SetRow(height, 11);
            Grid.SetColumn(height, 0);
            Grid.SetRow(maxIterations_label, 12);
            Grid.SetColumn(maxIterations_label, 0);
            Grid.SetRow(maxIterations, 13);
            Grid.SetColumn(maxIterations, 0);
            Grid.SetRow(maxModulus_label, 14);
            Grid.SetColumn(maxModulus_label, 0);
            Grid.SetRow(maxModulus, 15);
            Grid.SetColumn(maxModulus, 0);

            Children.Add(xStart_label);
            Children.Add(xStart);
            Children.Add(yStart_label);
            Children.Add(yStart);
            Children.Add(rows_label);
            Children.Add(rows);
            Children.Add(columns_label);
            Children.Add(columns);
            Children.Add(width_label);
            Children.Add(width);
            Children.Add(height_label);
            Children.Add(height);
            Children.Add(maxIterations_label);
            Children.Add(maxIterations);
            Children.Add(maxModulus_label);
            Children.Add(maxModulus);
        }

    }
}
