using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Numerics;

namespace gomez_james_gui_p3
{
    class MandelbrotGrid
    {
        int[,] data;
        private double xStart, yStart, width, height;

        private readonly int rows;
        private readonly int cols;
        private readonly int maxIterations;
        private readonly double maxModulus;

        int xIntervals;
        int yIntervals;
        double dx, dy;

        /// <summary>
        /// Creates a new two-dimensional Mandelbgrot grid representing a complex plain
        /// </summary>
        /// <param name="xStart">The x starting position</param>
        /// <param name="yStart">The y starting position</param>
        /// <param name="width">The width of the Mandelbrot grid</param>
        /// <param name="height">the height of the Mandelbrot grid</param>
        /// <param name="numRows">the number of rows to be contained within the Mandelbrot grid</param>
        /// <param name="numCols">the number of columns to be contained within the Mandelbrot grid</param>
        /// <param name="maxIterations">the maximum amount of iterations before we consider any complex point to diverge</param>
        /// <param name="maxModulus">the maximum complex modulus value before we consider any complex point to diverge</param>
        public MandelbrotGrid(double xStart, double yStart, double width, double height,
            int numRows, int numCols, int maxIterations, double maxModulus) {

            this.xStart = xStart;
            this.yStart = yStart;
            this.width = width;
            this.height = height;
            this.rows = numRows;
            this.cols = numCols;
            this.maxIterations = maxIterations;
            this.maxModulus = maxModulus;
            data = new int[rows, cols];

            // calculated properties 
            xIntervals = cols - 1;
            yIntervals = numRows - 1;
            dx = width / xIntervals;
            dy = height / yIntervals;

            generateCounts();
            printData();
            //testPrint();
        }

        private void generateCounts() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Complex c = toComplex(i, j);
                    Complex z = new Complex(0, 0);

                    int iterations = 0;
                    while (true) {
                        z = z * z + c;
                        iterations++;
                        if (z.Magnitude * z.Magnitude >= maxModulus || iterations >= maxIterations)
                            break;
                    }

                    if (iterations >= maxIterations)
                        data[i, j] = 0;
                    else
                        data[i, j] = iterations;
                }
            }
        }

        private Complex toComplex(int row, int col) {
            double x = col - cols / 2;
            double y = row - rows / 2;
            x *= 0.01;
            y *= 0.01;
            return new Complex(x, y);
        }

        private void printData() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Console.Write(data[i, j] + " ");
                }
                Console.WriteLine();
            }
        }

        //for testing only
        private void testPrint() {
            Complex z;

            z = new Complex(0.1, 0.1); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);
            z = new Complex(0.1, 0.12); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);
            z = new Complex(0.0956, 0.124); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);
            z = new Complex(0.09376, 0.1237088); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);
            z = new Complex(0.09348, 0.123198705499136); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);
            z = new Complex(0.09362, 0.12303); Console.WriteLine(z + " " + z.Magnitude * z.Magnitude);            
        }
    }
}
