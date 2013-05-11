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
        private double xStart;
        private double yStart;
        private double width;
        private double height;
        private int rows;
        private int cols;
        private int maxIterations;
        private double maxModulus;
        int xIntervals;
        int yIntervals;
        double dx, dy;


        /// <summary>
        /// Generates a 2-D array containing divergance iteration counts for each pixel. 
        /// This array is persistent within this class until this method is called again, 
        /// whereupon it will be recalculated. A second 1-D array containing corresponding
        /// pixel data is also generated.
        /// </summary>
        /// <returns>A 1-D array containing pixel data representative of the divergance 
        /// iteration counts for each point in the comlex plain.
        /// </returns>
        public byte[] generateCounts() {
            byte[] pixelData = new byte[rows * cols];

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

                    //store pixel data and scale up by 6 for a better looking image                    
                    pixelData[i * cols + j] = (byte)(data[i, j] * 6);
                }
            }

            //printData();
            return pixelData;
        }

        public void setParams(double xStart, double yStart, double width, double height,
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
        }

        private Complex toComplex(int row, int col) {
            double x = col - cols / 2;
            double y = row - rows / 2;
            x *= 0.006;
            y *= 0.006;
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

    }
}
